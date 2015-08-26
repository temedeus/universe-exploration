package com.universe.exploration;
import com.universe.exploration.listener.UEEvent;
import com.universe.exploration.listener.UEListener;
import com.universe.exploration.starsystem.StarSystem;
import com.universe.exploration.starsystem.StarSystemFactory;
import com.universe.exploration.ui.UIController;
import com.universe.exploration.view.GameObjectCanvas;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.universe.exploration.camera.PointerGuidanceSystem;
import com.universe.exploration.camera.SpaceshipMonitor;
import com.universe.exploration.common.tools.exceptions.PlanetCountOutOfRangeException;
import com.badlogic.gdx.Input;

public class UniverseExploration extends ApplicationAdapter {
	PointerGuidanceSystem gs;
	GameObjectCanvas canvas;
	SpaceshipMonitor shm;
	double angle = 0;
	StarSystem ua;

	private UIController uiController;

	@Override
	public void create () {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		StarSystemFactory uf = new StarSystemFactory();
		
		uiController = new UIController();
		uiController.setHyperspaceJumpListener(new UEListener() {
			@Override
			public void handleEventClassEvent() {
				regenerateStarSystem();
			};
		});

		
		try {
			this.ua = uf.makeStarSystem();
		} catch(PlanetCountOutOfRangeException e) {
			// TODO: error handling on planet count error (overall error handling?)
		}
		
		// Start game canvas. All graphics processing starts from this class.
		canvas = new GameObjectCanvas(this.ua);
		gs = new PointerGuidanceSystem();
		shm = new SpaceshipMonitor();
		canvas.updateCameraOnCanvas(this.shm.getOrthographicCamera());
	}
	
	private void regenerateStarSystem() {
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
	      
		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
			Gdx.app.exit();
		}
		
		this.canvas.updateCameraOnCanvas(this.shm.getOrthographicCamera());
		this.canvas.drawGameContent();
		
		uiController.updateUI();
	}
}
