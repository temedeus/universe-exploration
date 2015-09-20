package com.universe.exploration;
import java.util.EventObject;

import com.universe.exploration.listener.UEEvent;
import com.universe.exploration.listener.UEListener;
import com.universe.exploration.player.PlayerStatus;
import com.universe.exploration.player.StatusConsumption;
import com.universe.exploration.starsystem.StarSystem;
import com.universe.exploration.starsystem.StarSystemFactory;
import com.universe.exploration.ui.UIController;
import com.universe.exploration.view.GameObjectCanvas;
import com.universe.exploration.view.PlanetGfxContainer;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.universe.exploration.camera.SpaceshipMonitor;
import com.universe.exploration.common.tools.exceptions.PlanetCountOutOfRangeException;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class UniverseExploration extends ApplicationAdapter implements InputProcessor {
	/**
	 * Game objects are handled here
	 */
	private GameObjectCanvas canvas;
	
	/**
	 * Controls the camera
	 */
	SpaceshipMonitor playerMonitor;

	/**
	 * Star system
	 */
	StarSystem ua;
	
	/**
	 * User interface
	 */
	private UIController uiController;
	
	/**
	 * Contains player spaceship status.
	 */
	private PlayerStatus playerStatus;

	
	private Stage uiStage;
	
	@Override
	public void create () {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		
		uiStage = new Stage(new ScreenViewport());

		playerStatus = new PlayerStatus();
		
		uiController = new UIController();
		uiController.setHyperspaceJumpListener(new UEListener() {
			@Override
			public void handleEventClassEvent() {
				createStarSystem();
				playerStatus.decreasePowerBy(StatusConsumption.POWER_DECREMENT_HYPERSPACE_JUMP);
			};
		});
		
		createStarSystem();
		
		// Start game canvas. All graphics processing starts from this class.
		playerMonitor = new SpaceshipMonitor();
		canvas.updateCameraOnCanvas(this.playerMonitor.getOrthographicCamera());
		canvas.setPlanetClickListener(new UEListener() {
			@Override
			public void handleEventClassEvent(UEEvent e) {
				
				uiController.createPlanetarySurveyWindow((PlanetGfxContainer)e.getPayLoad());
			};
		});
		
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
		} catch(PlanetCountOutOfRangeException e) {
			return false;
		} 
		
		return true;
	}
	
	@Override
	public void dispose() {
		canvas.destroy();
	}

	@Override
	public void render () {	
		this.canvas.updateCameraOnCanvas(this.playerMonitor.getOrthographicCamera());
		this.canvas.drawGameContent();
		
		playerStatus.updateStatus();
		
		uiController.updateUI(playerStatus);
		
		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		}
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
		return false;
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
}
