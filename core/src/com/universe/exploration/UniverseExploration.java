package com.universe.exploration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.universe.exploration.audio.AudioManager;
import com.universe.exploration.audio.Music;
import com.universe.exploration.audio.SoundEffect;
import com.universe.exploration.camera.CameraMonitor;
import com.universe.exploration.casualty.Casualty;
import com.universe.exploration.common.tools.TextManipulationTools;
import com.universe.exploration.common.tools.exceptions.PlanetCountOutOfRangeException;
import com.universe.exploration.crew.CrewMemberStatus;
import com.universe.exploration.crewmember.CrewFactory;
import com.universe.exploration.crewmember.CrewMember;
import com.universe.exploration.listener.UEEvent;
import com.universe.exploration.listener.UEListener;
import com.universe.exploration.localization.Localizer;
import com.universe.exploration.logger.MinimalLogger;
import com.universe.exploration.player.CrewStatusManager;
import com.universe.exploration.player.StatusConsumption;
import com.universe.exploration.resource.Resource;
import com.universe.exploration.resource.ResourcesFoundBean;
import com.universe.exploration.spritecontainer.GameObjectCanvas;
import com.universe.exploration.starsystem.StarSystemFactory;
import com.universe.exploration.starsystem.components.PlanetCelestialComponent;
import com.universe.exploration.survey.Survey;
import com.universe.exploration.survey.SurveyContainer;
import com.universe.exploration.survey.SurveyFactory;
import com.universe.exploration.userinterface.UIController;
import com.universe.exploration.userinterface.components.window.WindowType;
import com.universe.exploration.userinterface.forms.PlanetSurveyForm;

/**
 * Main game class.
 * 
 * TODO: {@link UniverseExploration} and {@link UIController} both perform
 * actions that do not belong in these classes. Refactor.
 */
public class UniverseExploration extends ApplicationAdapter implements InputProcessor {

	/**
	 * Controls the camera
	 */
	private CameraMonitor playerMonitor;

	/**
	 * Game objects are handled here
	 */
	private GameObjectCanvas gameObjectCanvas;

	/**
	 * User interface.
	 */
	private UIController uiController;

	/**
	 * Audio related stuff.
	 */
	public static AudioManager audioManager;

	/**
	 * Game status.
	 */
	public static GameStatus gameStatus;

	private MinimalLogger logger;

	@SuppressWarnings("unused")
	private Stage uiStage;

	@Override
	public void create() {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		basicSetup();
		createCrew();
		stageSetup();

		setGameStatusPaused(false);

		audioManager.playMusic(Music.BASIC_AMBIENT, true);
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

		gameStatus.getCrewStatus().updateStatus();
		uiController.updateUI(gameStatus.getCrewStatus());

		pollForGameOver();
		closeFinishedSurveys();

		// Do not allow pressing escape in HTML version of the game.
		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE) && !Gdx.app.getType().equals(ApplicationType.WebGL)) {
			uiController.createQuitDialog();
		}
	}

	private void pollForGameOver() {
		if (UniverseExploration.gameStatus.getCrew().getAliveCrewmen().size() == 0 && !gameStatus.isPaused()) {
			uiController.createGameOverWindow(createGameOverClicklistener());
			uiController.getWindowContainer().closeAllWindows();
			setGameStatusPaused(true);
		}
	}

	/**
	 * Basic setup to be run only at initial game start.
	 */
	private void basicSetup() {
		gameStatus = new GameStatus();
		logger = new MinimalLogger();
		uiStage = new Stage(new ScreenViewport());
		gameStatus.setCrewStatus(new CrewStatusManager());
		gameStatus.getCrewStatus().setCrewMemberStatusChangeListener(createLogMessageListener());
		playerMonitor = new CameraMonitor();
		gameStatus.setSurveyStatusContainer(new SurveyContainer());
		audioManager = new AudioManager();
	}

	private UEListener createLogMessageListener() {
		return new UEListener() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see com.universe.exploration.listener.UEListener#
			 * handleEventClassEvent (java.util.EventObject)
			 */
			@Override
			public void handleEventClassEvent(UEEvent e) {
				updateIngameLog((String) e.getPayLoad());
			}
		};
	}

	/**
	 * First setup star system and then UiController because UI uses star system
	 * data.
	 * 
	 * @throws IOException
	 */
	private void stageSetup() {
		createStarSystem();
		setupUiController();
		setupInputProcessors();
	}

	/**
	 * Crew should be created only when new game is started.
	 */
	private void createCrew() {
		UniverseExploration.gameStatus.setCrew(null);
		CrewMembersInitializer initializer;
		try {
			initializer = new CrewMembersInitializer();
			CrewFactory cf = new CrewFactory(initializer.getMaleProfiles(), initializer.getFemaleProfiles());
			UniverseExploration.gameStatus.setCrew(cf.createRandomizedCrew());
		} catch (IOException e) {
			Gdx.app.log("ERROR", "Reading crewmember profile source files failed!", e);
		}
	}

	private void setupUiController() {
		uiController = new UIController(gameObjectCanvas.getGameViewObjectContainer(),
				UniverseExploration.gameStatus.getStarSystem().getPlanets());
		setupUIControllerListeners();
	}

	private void setupUIControllerListeners() {
		uiController.setLogMessageListener(createLogMessageListener());
		uiController.setPlanetClickListener(createPlanetClickListener());
		uiController.setHyperspaceJumpListener(createHyperSpaceJumpListener());
		uiController.setStartPlanetSurveyListener(createStartPlanetSurveyListener());
		uiController.setSelectedPlanetChangedListener(selectedPlanetChangedListener());
		uiController.setVolumeListener(createVolumeListener());
	}

	private UEListener createHyperSpaceJumpListener() {
		return new UEListener() {
			@Override
			public void handleEventClassEvent() {
				if (!gameStatus.isPaused()) {
					stageSetup();
					gameStatus.activateSurveyMode(false);
					gameStatus.getCrewStatus().decreasePowerBy(StatusConsumption.POWER_DECREMENT_HYPERSPACE_JUMP);
					updateIngameLog(Localizer.getInstance().get("DESC_HYPERSPACE_JUMP_COMMENCED"));
				}
			};
		};
	}

	private UEListener createStartPlanetSurveyListener() {
		return new UEListener() {
			@Override
			public void handleEventClassEvent(UEEvent e) {
				if (e.getPayLoad() instanceof PlanetSurveyForm) {
					startSurvey((PlanetSurveyForm) e.getPayLoad());
					uiController.getWindowContainer().closeWindow(WindowType.SURVEY_WINDOW);
					uiController.getWindowContainer().closeWindow(WindowType.PLANET_DETAILS);
				}
			};
		};
	}

	private UEListener selectedPlanetChangedListener() {
		return new UEListener() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see com.universe.exploration.listener.UEListener#
			 * handleEventClassEvent (com.universe.exploration.listener.UEEvent)
			 */
			@Override
			public void handleEventClassEvent(UEEvent e) {
				PlanetCelestialComponent planet = (PlanetCelestialComponent) e.getPayLoad();
				gameObjectCanvas.setSelectedPlanet(planet);
			}
		};
	}

	private UEListener createVolumeListener() {
		return new UEListener() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see com.universe.exploration.listener.UEListener#
			 * handleEventClassEvent ()
			 */
			@Override
			public void handleEventClassEvent(UEEvent e) {
				Float volume = (Float) e.getPayLoad();
				audioManager.getAudioFileCache().get(Music.BASIC_AMBIENT)
						.setVolume(audioManager.getCurrentlyPlayingBackgroundMusic(), volume);
			}
		};
	}

	private void startSurvey(PlanetSurveyForm form) {
		int surveyLength = (int) form.getSurveyLength().getValue();
		List<CrewMember> crew = form.getSelectedCrewMembers();

		if (surveyLength > 0 && crew.size() > 0) {
			if (gameStatus.getSurveyStatusContainer().isSurveyTeamAcceptable(crew)) {

				SurveyFactory surveyFactory = new SurveyFactory();
				Survey survey = surveyFactory.createSurvey((int) gameStatus.getCrewStatus().getTime(), surveyLength,
						form.getSelectedCrewMembers(), (PlanetCelestialComponent) form.getPlanet(),
						form.getSurveyName().getText());
				survey.setSurveyTeam(crew);
				gameStatus.getSurveyStatusContainer().add(survey);

				updateIngameLog("Survey (" + form.getSurveyName().getText() + ") dispatched composed of " + crew.size()
						+ " brave men and women.");
			} else {
				updateIngameLog("Cannot dispatch survey team (" + crew.size() + "). Not enough crewmen available!"
						+ gameStatus.getCrew().getCrewMenAboardSpaceShip().size());
			}
		}
	}

	private ClickListener createGameOverClicklistener() {
		return new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				createCrew();
				stageSetup();
				gameStatus.setCrewStatus(new CrewStatusManager());
				setGameStatusPaused(false);
				uiController.getWindowContainer().closeWindow(WindowType.GAME_OVER);
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
		try {
			UniverseExploration.gameStatus.setStarSystem(new StarSystemFactory().makeStarSystem());
			gameObjectCanvas = new GameObjectCanvas();
			gameObjectCanvas.updateCameraOnCanvas(playerMonitor.getOrthographicCamera());
			gameObjectCanvas.setPlanetClickListener(createPlanetClickListener());
		} catch (PlanetCountOutOfRangeException e) {
			Gdx.app.log("ERROR", "Generated planet count is not within range!", e);
		}

		return true;
	}

	public UEListener createPlanetClickListener() {
		return new UEListener() {
			@Override
			public void handleEventClassEvent(final UEEvent e) {
				uiController.createPlanetClickWindow(e);
			};
		};
	}

	private void closeFinishedSurveys() {
		showSurveyCompleteNotification(gameStatus.getSurveyStatusContainer()
				.findAndRemoveOpenSurvey((int) gameStatus.getCrewStatus().getTime()));
	}

	private void showSurveyCompleteNotification(Survey survey) {

		if (survey != null) {
			String caption = "";

			audioManager.playSoundEffect(SoundEffect.ANNOUNCEMENT);

			List<Casualty> casualtyList = survey.getMortalities();

			if (casualtyList.size() > 0) {
				updateIngameLog("You have lost " + casualtyList.size() + " crewmen on survey.");
				handleCrewmenCasualtiesFromSurvey(casualtyList);
			} else {
				caption = Localizer.getInstance().get("TITLE_SURVEY_TEAM_SURVIVED");
				updateIngameLog(caption);
			}

			for (CrewMember member : survey.getSurveyTeam()) {
				if (member.getStatus() == CrewMemberStatus.ONSURVEY) {
					member.setStatus(CrewMemberStatus.ALIVE);
				}
			}

			ResourcesFoundBean rf = survey.getResourcesFound();
			String resourcesCaption = updateResources(rf);

			List<String> surveyData = generateSurveyDataRows(caption, resourcesCaption, casualtyList, rf);
			uiController.createSurveyClosedWindow(surveyData);
		}
	}

	private List<String> generateSurveyDataRows(String caption, String resourcesCaption, List<Casualty> casualtyList,
			ResourcesFoundBean rf) {
		List<String> surveydata = new ArrayList<String>();

		surveydata.add(caption);
		if (casualtyList.size() > 0) {
			for (Casualty casualty : casualtyList) {
				surveydata.add(casualty.getMember().getName() + ": "
						+ Localizer.getInstance().get(casualty.getSurveyIncident().getLocalizationKey()));
			}
		}

		surveydata.add("");
		surveydata.add(resourcesCaption);

		return surveydata;
	}

	private String updateResources(ResourcesFoundBean foundResources) {
		List<String> resources = new ArrayList<String>();

		for (Resource resource : foundResources.getResources()) {
			resources.add(Localizer.getInstance().get(resource.getResourceLocal()));
			gameStatus.getCrewStatus().adjustStatusValue(resource);
		}

		String caption = (resources.size() == 0) ? "You found nothing during your survey!"
				: "During your survey you found: " + TextManipulationTools.implodeListAsString(resources, ", ");

		updateIngameLog(caption);

		return caption;
	}

	private void handleCrewmenCasualtiesFromSurvey(List<Casualty> casualtyList) {
		for (Casualty casualty : casualtyList) {
			String localizationKey = casualty.getSurveyIncident().getLocalizationKey();

			CrewMember member = casualty.getMember();
			member.setStatus(casualty.getSurveyIncident().causesStatus());
			member.addToCondition(casualty.getSurveyIncident().causesCondition());

			updateIngameLog(member.getName() + " (" + Localizer.getInstance().get(member.getStatus()) + ") - "
					+ Localizer.getInstance().get(localizationKey));
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
	public static void setGameStatusPaused(boolean gameStatusPaused) {
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
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.InputProcessor#keyUp(int)
	 */
	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.InputProcessor#keyTyped(char)
	 */
	@Override
	public boolean keyTyped(char character) {
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
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.InputProcessor#touchDragged(int, int, int)
	 */
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.InputProcessor#mouseMoved(int, int)
	 */
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.InputProcessor#scrolled(int)
	 */
	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
