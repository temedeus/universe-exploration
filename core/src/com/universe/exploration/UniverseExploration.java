package com.universe.exploration;
import com.universe.exploration.listener.UEListener;
import com.universe.exploration.player.PlayerStatus;
import com.universe.exploration.player.StatusConsumption;
import com.universe.exploration.starsystem.StarSystem;
import com.universe.exploration.starsystem.StarSystemFactory;
import com.universe.exploration.ui.UIController;
import com.universe.exploration.view.GameObjectCanvas;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.universe.exploration.camera.SpaceshipMonitor;
import com.universe.exploration.common.tools.exceptions.PlanetCountOutOfRangeException;
import com.badlogic.gdx.Input;

public class UniverseExploration extends ApplicationAdapter {
	private GameObjectCanvas canvas;
	SpaceshipMonitor shm;
	double angle = 0;
	StarSystem ua;

	private UIController uiController;
	
	/**
	 * Contains player spaceship status.
	 */
	private PlayerStatus playerStatus;

	@Override
	public void create () {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		playerStatus = new PlayerStatus();
		
		uiController = new UIController();
		uiController.setHyperspaceJumpListener(new UEListener() {
			@Override
			public void handleEventClassEvent() {
				createStarSystem();
			};
		});
		
		createStarSystem();
		
		// Start game canvas. All graphics processing starts from this class.
		
		shm = new SpaceshipMonitor();
		canvas.updateCameraOnCanvas(this.shm.getOrthographicCamera());
	}
	
	private void createStarSystem() {
		StarSystemFactory uf = new StarSystemFactory();
		
		try {
			ua = uf.makeStarSystem();
			canvas = new GameObjectCanvas(this.ua);
		} catch(PlanetCountOutOfRangeException e) {
			// TODO: error handling on planet count error (overall error handling?)
		}
	}
	
	@Override
	public void dispose() {
		canvas.destroy();
	}

	@Override
	public void render () {	
		this.canvas.updateCameraOnCanvas(this.shm.getOrthographicCamera());
		this.canvas.drawGameContent();
		
		playerStatus.decreaseAirBy(StatusConsumption.AIR_DECREMENT);
		uiController.updateUI(playerStatus);
		
		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		}
	}
}
