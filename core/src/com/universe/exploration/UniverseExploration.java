package com.universe.exploration;
import com.universe.exploration.spaceship.PointerGuidanceSystem;
import com.universe.exploration.spaceship.SpaceshipMonitor;
import com.universe.exploration.universe.Universe;
import com.universe.exploration.universe.UniverseFactory;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.universe.exploration.graphics.Canvas;
import com.badlogic.gdx.Input;

public class UniverseExploration extends ApplicationAdapter {
	PointerGuidanceSystem gs;
	Canvas canvas;
	SpaceshipMonitor shm;
	double angle = 0;
	Universe ua;

	@Override
	public void create () {
		UniverseFactory uf = new UniverseFactory();
		this.ua = uf.getUniverse();
		
		// Start game canvas. All graphics processing starts from this class.
		this.canvas = new Canvas(this.ua);
		this.gs = new PointerGuidanceSystem();
		this.shm = new SpaceshipMonitor();
		ua = new Universe();
		
	}
	
	@Override
	public void dispose() {
		this.canvas.destroy();
	}

	@Override
	public void render () {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);	
		
		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
			Gdx.app.exit();
		}
					
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
			angle = gs.getAngle(
					(int)Gdx.input.getX(), (int)Gdx.input.getY(), 
	 				(int)this.canvas.playerGfxPositionX(), (int)this.canvas.playerGfxPositionY());
			
			this.shm.thrusterOn();
			this.shm.move((float) angle, (float)this.shm.getVelocity());
		} else {
			this.shm.thrusterOff();
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
				this.shm.zoomIn();
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
			this.shm.zoomOut();
		}
		
		this.canvas.updateCameraOnCanvas(this.shm.getOrthographicCamera());
		this.canvas.renderGame();
	}
}
