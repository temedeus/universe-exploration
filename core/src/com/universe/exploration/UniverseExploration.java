package com.universe.exploration;

import java.util.ArrayList;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.universe.exploration.camera.CameraMonitor;
import com.universe.exploration.common.tools.TextManipulationTools;
import com.universe.exploration.common.tools.exceptions.PlanetCountOutOfRangeException;
import com.universe.exploration.gamegraphics.GameObjectCanvas;
import com.universe.exploration.gamegraphics.PlanetGfxContainer;
import com.universe.exploration.listener.UEEvent;
import com.universe.exploration.listener.UEListener;
import com.universe.exploration.localization.LocalKey;
import com.universe.exploration.localization.Localizer;
import com.universe.exploration.logger.MinimalLogger;
import com.universe.exploration.mortality.Mortality;
import com.universe.exploration.player.PlayerStatus;
import com.universe.exploration.player.StatusConsumption;
import com.universe.exploration.starsystem.StarSystem;
import com.universe.exploration.starsystem.StarSystemFactory;
import com.universe.exploration.starsystem.components.PlanetCelestialComponent;
import com.universe.exploration.survey.ResourcesFound;
import com.universe.exploration.survey.SurveyStatus;
import com.universe.exploration.survey.SurveyStatusContainer;
import com.universe.exploration.survey.SurveyStatusFactory;
import com.universe.exploration.ueui.UIController;
import com.universe.exploration.ueui.WindowContainer;
import com.universe.exploration.ueui.WindowContainerEvent;
import com.universe.exploration.ueui.WindowType;
import com.universe.exploration.ueui.components.BasicWindow;
import com.universe.exploration.ueui.forms.PlanetSurveyForm;

public class UniverseExploration extends ApplicationAdapter implements InputProcessor {
    /**
     * Game objects are handled here
     */
    private GameObjectCanvas gameObjectCanvas;

    /**
     * Controls the camera
     */
    private CameraMonitor playerMonitor;

    /**
     * Star system
     */
    private StarSystem starSystem;

    /**
     * User interface.
     */
    private UIController uiController;
    public static GameStatus gameStatus;

    /**
     * Contains player spaceship status.
     */
    private PlayerStatus playerStatus;

    public static WindowContainer windowContainer;

    private MinimalLogger logger;
    private SurveyStatusContainer surveyStatusContainer;

    private Sound backgroundMusic;
    private long bgId;

    @SuppressWarnings("unused")
    private Stage uiStage;

    @Override
    public void create() {
	Gdx.app.setLogLevel(Application.LOG_DEBUG);

	basicSetup();
	stageSetup();
	pauseGame(false);

	backgroundMusic = Gdx.audio.newSound(Gdx.files.internal("space.mp3"));
	bgId = backgroundMusic.loop();
    }

    @Override
    public void dispose() {
	gameObjectCanvas.destroy();
    }

    @Override
    public void render() {
	playerMonitor.zoom(gameStatus.isZoomIn());

	gameObjectCanvas.updateCameraOnCanvas(playerMonitor.getOrthographicCamera());
	gameObjectCanvas.drawGameContent();

	playerStatus.updateStatus();
	uiController.updateUI(playerStatus);

	if (playerStatus.getCrewmen() == 0 && !gameStatus.isPaused()) {
	    pauseGame(true);
	    BasicWindow gameOverWindow = uiController.createGameOverWindow(WindowType.GAME_OVER, createGameOverClicklistener());

	    windowContainer.add(WindowType.GAME_OVER, gameOverWindow);
	    uiController.show(gameOverWindow);
	}

	closeFinishedSurveys();

	if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE) && !Gdx.app.getType().equals(ApplicationType.WebGL)) {
	    uiController.createQuitDialog();
	}
    }

    /**
     * Basic setup to be run only at initial game start.
     */
    private void basicSetup() {
	gameStatus = new GameStatus();
	logger = new MinimalLogger();
	uiStage = new Stage(new ScreenViewport());
	playerStatus = new PlayerStatus();
	playerMonitor = new CameraMonitor();
	windowContainerSetup();
	surveyStatusContainer = new SurveyStatusContainer();
    }

    private void windowContainerSetup() {
	windowContainer = new WindowContainer();
	windowContainer.setWindowsThatMustAlert(WindowType.PLANET_DETAILS);
	windowContainer.setSpecificedWindowChangeListener(createWindowChangeListener());
    }

    private UEListener createWindowChangeListener() {
	return new UEListener() {
	    /*
	     * (non-Javadoc)
	     * 
	     * @see
	     * com.universe.exploration.listener.UEListener#handleEventClassEvent
	     * (com.universe.exploration.listener.UEEvent)
	     */
	    @Override
	    public void handleEventClassEvent(UEEvent e) {
		WindowContainerEvent event = (WindowContainerEvent) e.getPayLoad();
		gameStatus.enableSurveyMode(event.equals(WindowContainerEvent.ADD));
	    }
	};
    }

    /**
     * First setup star system and then UiController because UI uses star system
     * data.
     */
    private void stageSetup() {
	createStarSystem();
	setupUiController();
	setupInputProcessors();
    }

    private void setupUiController() {
	uiController = new UIController(gameObjectCanvas.getGameViewObjectContainer(), starSystem.getPlanets());

	uiController.setPlanetClickListener(createPlanetClickListener());
	uiController.setHyperspaceJumpListener(new UEListener() {
	    @Override
	    public void handleEventClassEvent() {
		if (!gameStatus.isPaused()) {
		    windowContainerSetup();
		    stageSetup();
		    gameStatus.enableSurveyMode(false);
		    playerStatus.decreasePowerBy(StatusConsumption.POWER_DECREMENT_HYPERSPACE_JUMP);
		    updateIngameLog("Hyperspace jump commenced!");
		}
	    };
	});

	uiController.setPlanetSurveyListener(new UEListener() {
	    @Override
	    public void handleEventClassEvent(UEEvent e) {
		if (e.getPayLoad() instanceof PlanetSurveyForm) {
		    startSurvey((PlanetSurveyForm) e.getPayLoad());
		    windowContainer.closeWindow(WindowType.SURVEY_WINDOW);
		}
	    };
	});

	uiController.setSelectedPlanetChangedListener(new UEListener() {
	    /*
	     * (non-Javadoc)
	     * 
	     * @see
	     * com.universe.exploration.listener.UEListener#handleEventClassEvent
	     * (com.universe.exploration.listener.UEEvent)
	     */
	    @Override
	    public void handleEventClassEvent(UEEvent e) {
		PlanetCelestialComponent planet = (PlanetCelestialComponent) e.getPayLoad();
		gameObjectCanvas.setSelectedPlanet(planet);
	    }
	});

	uiController.setVolumeListener(new UEListener() {
	    /*
	     * (non-Javadoc)
	     * 
	     * @see
	     * com.universe.exploration.listener.UEListener#handleEventClassEvent
	     * ()
	     */
	    @Override
	    public void handleEventClassEvent(UEEvent e) {
		Float volume = (Float) e.getPayLoad();
		backgroundMusic.setVolume(bgId, volume);
	    }
	});
    }

    private void startSurvey(PlanetSurveyForm form) {
	int surveyTeamSize = (int) form.getCrewmenCount().getValue();
	if (surveyTeamSize > 0) {
	    if (surveyStatusContainer.isSurveyTeamSizeAcceptable(surveyTeamSize, calculateAvailableMen())) {

		SurveyStatusFactory ssf = new SurveyStatusFactory();
		SurveyStatus ss = ssf.createSurveyStatus((int) playerStatus.getTime(), surveyTeamSize,
			(PlanetCelestialComponent) form.getPlanet());

		surveyStatusContainer.add(ss);

		updateIngameLog("Survey team of " + surveyTeamSize + " men and women dispatched.");
	    } else {
		updateIngameLog("Cannot dispatch survey team (" + surveyTeamSize + "). Not enough crewmen available!"
			+ playerStatus.getCrewmen());
	    }
	}
    }

    private int calculateAvailableMen() {
	return playerStatus.getCrewmen() - surveyStatusContainer.crewmenOnSurvey();
    }

    private void pauseGame(boolean pause) {
	setGameStatusPaused(pause);
	uiController.setGameStatusPaused(pause);
    }

    private ClickListener createGameOverClicklistener() {
	return new ClickListener() {
	    @Override
	    public void clicked(InputEvent event, float x, float y) {
		stageSetup();
		playerStatus = new PlayerStatus();
		pauseGame(false);
		windowContainer.closeWindow(WindowType.GAME_OVER);
	    }
	};
    }

    /**
     * Both UniverseExploration and UIController use InputProcessors so we must
     * use InputMultiPlexer to catch all those events.
     */
    private void setupInputProcessors() {
	// We monitor events from both this ApplicationAdapter and our UI stage
	// TODO: check if we could work with just one InputProcessor?
	InputMultiplexer inputMultiplexer = new InputMultiplexer();
	inputMultiplexer.addProcessor(uiController.getUiStage());
	inputMultiplexer.addProcessor(this);
	Gdx.input.setInputProcessor(inputMultiplexer);
    }

    /**
     * Attempts to create a new star system. Returns boolean result.
     * 
     * @return
     */
    private boolean createStarSystem() {
	StarSystemFactory uf = new StarSystemFactory();

	try {
	    starSystem = uf.makeStarSystem();
	    gameObjectCanvas = new GameObjectCanvas(starSystem);
	    gameObjectCanvas.updateCameraOnCanvas(playerMonitor.getOrthographicCamera());
	    gameObjectCanvas.setPlanetClickListener(createPlanetClickListener());
	} catch (PlanetCountOutOfRangeException e) {
	    return false;
	}

	return true;
    }

    public UEListener createPlanetClickListener() {
	return new UEListener() {
	    @Override
	    public void handleEventClassEvent(final UEEvent e) {
		final BasicWindow surveyWindow = uiController.createPlanetarySurveyWindow((PlanetGfxContainer) e.getPayLoad(),
			new ClickListener() {
			    @Override
			    public void clicked(InputEvent event, float x, float y) {
				final BasicWindow surveyedWindow = uiController.createPlanetSurveyedWindow(
					(PlanetGfxContainer) e.getPayLoad(), calculateAvailableMen());
				windowContainer.add(WindowType.SURVEY_WINDOW, surveyedWindow);
				uiController.show(surveyedWindow);
			    }
			});

		windowContainer.add(WindowType.PLANET_DETAILS, surveyWindow);
		surveyWindow.setPosition(Gdx.graphics.getWidth() / 2 + 100, Gdx.graphics.getHeight() / 2 - 300);
		uiController.show(surveyWindow);
	    };
	};
    }

    private void closeFinishedSurveys() {
	SurveyStatus ss = surveyStatusContainer.isSurveyOver((int) playerStatus.getTime());
	showSurveyCompleteNotification(ss);
    }

    private void showSurveyCompleteNotification(SurveyStatus ss) {

	if (ss != null) {
	    String caption = "";
	    Sound announcement = Gdx.audio.newSound(Gdx.files.internal("announcement.ogg"));
	    announcement.play();
	    ArrayList<Mortality> mc = ss.getMortalities();

	    if (mc.size() > 0) {
		caption = "You have lost " + mc.size() + " crewmen on survey.";
		updateIngameLog(caption);
		printMortalityLog(mc);
		playerStatus.decreaseCrewmen(mc.size());

	    } else {
		caption = Localizer.get(LocalKey.TITLE_SURVEY_TEAM_SURVIVED);
		updateIngameLog(caption);
	    }

	    ResourcesFound rf = ss.getResourcesFound();
	    String resourcesCaption = updateResources(rf);

	    ArrayList<String> surveyData = generateSurveyDataRows(caption, resourcesCaption, mc, rf);
	    final BasicWindow surveyClosedWindow = uiController.createSurveyClosedWindow(surveyData);

	    windowContainer.add(WindowType.SURVEY_CLOSED, surveyClosedWindow);

	    uiController.show(surveyClosedWindow);
	}
    }

    private ArrayList<String> generateSurveyDataRows(String caption, String resourcesCaption, ArrayList<Mortality> mc, ResourcesFound rf) {
	ArrayList<String> surveydata = new ArrayList<String>();

	surveydata.add(caption);
	if (mc.size() > 0) {
	    for (Mortality mortality : mc) {
		surveydata.add(Localizer.get(mortality.getCauseOfDeath().getLocalizationKey()));
	    }
	}

	surveydata.add("");
	surveydata.add(resourcesCaption);

	return surveydata;
    }

    private String updateResources(ResourcesFound rf) {
	ArrayList<String> resources = new ArrayList<String>();
	String caption = "";

	if (rf.getAir() > 0) {
	    resources.add(Localizer.get(LocalKey.GENERIC_AIR));
	    playerStatus.increaseAir(rf.getAir());
	}

	if (rf.getFood() > 0) {
	    resources.add(Localizer.get(LocalKey.GENERIC_FOOD));
	    playerStatus.increaseFood(rf.getFood());
	}

	if (rf.getWater() > 0) {
	    resources.add(Localizer.get(LocalKey.GENERIC_WATER));
	    playerStatus.increaseWater(rf.getWater());
	}

	if (resources.size() == 0) {
	    caption = "You found nothing during your survey!";
	    updateIngameLog(caption);
	} else {
	    caption = "During your survey you found: " + TextManipulationTools.joinArrayListString(resources, ", ");
	    updateIngameLog(caption);
	}

	return caption;
    }

    private void printMortalityLog(ArrayList<Mortality> mc) {
	for (Mortality mortality : mc) {
	    String localizationKey = mortality.getCauseOfDeath().getLocalizationKey();
	    updateIngameLog(" - " + Localizer.get(localizationKey));
	}
    }

    private void updateIngameLog(String message) {
	logger.add(message);
	uiController.updateLog(logger.getLog());
    }

    /**
     * @param gameStatusPaused
     *            the gameStatusPaused to set
     */
    public void setGameStatusPaused(boolean gameStatusPaused) {
	gameStatus.setPaused(gameStatusPaused);
    }

    // *************** PAST THIS GENERATED METHODS FOR INPUT PROCESSOR
    // ***************//

    /*
     * (non-Javadoc)
     * 
     * @see com.badlogic.gdx.InputProcessor#keyDown(int)
     */
    @Override
    public boolean keyDown(int keycode) {
	// TODO Auto-generated method stub
	return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.badlogic.gdx.InputProcessor#keyUp(int)
     */
    @Override
    public boolean keyUp(int keycode) {
	// TODO Auto-generated method stub
	return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.badlogic.gdx.InputProcessor#keyTyped(char)
     */
    @Override
    public boolean keyTyped(char character) {
	// TODO Auto-generated method stub
	return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.badlogic.gdx.InputProcessor#touchDown(int, int, int, int)
     */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
	gameObjectCanvas.checkIfHitCoordinatesMatchPlanets();
	return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.badlogic.gdx.InputProcessor#touchUp(int, int, int, int)
     */
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
	// TODO Auto-generated method stub
	return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.badlogic.gdx.InputProcessor#touchDragged(int, int, int)
     */
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
	// TODO Auto-generated method stub
	return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.badlogic.gdx.InputProcessor#mouseMoved(int, int)
     */
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
	// TODO Auto-generated method stub
	return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.badlogic.gdx.InputProcessor#scrolled(int)
     */
    @Override
    public boolean scrolled(int amount) {
	// TODO Auto-generated method stub
	return false;
    }
}
