package com.universe.exploration;
import javafx.scene.Parent;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.universe.exploration.camera.SpaceshipMonitor;
import com.universe.exploration.common.tools.exceptions.PlanetCountOutOfRangeException;
import com.universe.exploration.listener.UEEvent;
import com.universe.exploration.listener.UEListener;
import com.universe.exploration.player.PlayerStatus;
import com.universe.exploration.player.StatusConsumption;
import com.universe.exploration.starsystem.StarSystem;
import com.universe.exploration.starsystem.StarSystemFactory;
import com.universe.exploration.ui.UIController;
import com.universe.exploration.view.GameObjectCanvas;
import com.universe.exploration.view.PlanetGfxContainer;

public class UniverseExploration extends ApplicationAdapter implements InputProcessor {
	/**
	 * Game objects are handled here
	 */
	private GameObjectCanvas canvas;
	
	/**
	 * Controls the camera
	 */
	private SpaceshipMonitor playerMonitor;

	/**
	 * Star system
	 */
	private StarSystem ua;
	
	/**
	 * User interface
	 */
	private UIController uiController;
	
	/**
	 * Contains player spaceship status.
	 */
	private PlayerStatus playerStatus;
	
	private boolean gameStatusPaused = false;

	@SuppressWarnings("unused")
	private Stage uiStage;
	
	@Override
	public void create () {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		
		uiStage = new Stage(new ScreenViewport());
		playerStatus = new PlayerStatus();
		playerMonitor = new SpaceshipMonitor();
		
		stageSetup();
		
		pauseGame(false);
	}
	
	private void stageSetup() {
		setupUiController();
		createStarSystem();
		setupInputProcessors();
	}
	
	private void setupUiController() {
		uiController = new UIController();
		uiController.setHyperspaceJumpListener(new UEListener() {
			@Override
			public void handleEventClassEvent() {
				if(!gameStatusPaused) {
					createStarSystem();
					playerStatus.decreasePowerBy(StatusConsumption.POWER_DECREMENT_HYPERSPACE_JUMP);
				}
			};
		});
	}
	
	private void pauseGame(boolean pause) {
		setGameStatusPaused(pause);
		uiController.setGameStatusPaused(pause);
	}
	
	@Override
	public void dispose() {
		canvas.destroy();
	}

	@Override
	public void render () {	
		canvas.updateCameraOnCanvas(playerMonitor.getOrthographicCamera());
		canvas.drawGameContent();
		
		playerStatus.updateStatus();
		uiController.updateUI(playerStatus);
		
		if(playerStatus.getCrewmen() == 0 && !gameStatusPaused) {
			pauseGame(true);
			uiController.showGameOverWindow(new ClickListener() {
		    	@Override
		    	public void clicked(InputEvent event, float x, float y) {
		    		stageSetup();
		    		playerStatus = new PlayerStatus();
		    		pauseGame(false);
		    	}
		    });
		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		}
	}
	
	/**
	 * Both UniverseExploration and UIController use InputProcessors so we must use InputMultiPlexer
	 * to catch all those events.
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
	 * @return
	 */
	private boolean createStarSystem() {
		StarSystemFactory uf = new StarSystemFactory();
		
		try {
			ua = uf.makeStarSystem();
			canvas = new GameObjectCanvas(this.ua);
			
			// Start game canvas. All graphics processing starts from this class.
			
			canvas.updateCameraOnCanvas(this.playerMonitor.getOrthographicCamera());
			canvas.setPlanetClickListener(new UEListener() {
				@Override
				public void handleEventClassEvent(UEEvent e) {
					uiController.showPlanetarySurveyWindow((PlanetGfxContainer)e.getPayLoad());
				};
			});
		} catch(PlanetCountOutOfRangeException e) {
			return false;
		} 
		
		return true;
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.InputProcessor#keyDown(int)
	 */
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.InputProcessor#keyUp(int)
	 */
	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.InputProcessor#keyTyped(char)
	 */
	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.InputProcessor#touchDown(int, int, int, int)
	 */
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		canvas.checkIfHitCoordinatesMatchPlanets();
		return true;
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.InputProcessor#touchUp(int, int, int, int)
	 */
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.InputProcessor#touchDragged(int, int, int)
	 */
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.InputProcessor#mouseMoved(int, int)
	 */
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.InputProcessor#scrolled(int)
	 */
	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @return the gameStatusPaused
	 */
	public boolean isGameStatusPaused() {
		return gameStatusPaused;
	}

	/**
	 * @param gameStatusPaused the gameStatusPaused to set
	 */
	public void setGameStatusPaused(boolean gameStatusPaused) {
		this.gameStatusPaused = gameStatusPaused;
	}
}
