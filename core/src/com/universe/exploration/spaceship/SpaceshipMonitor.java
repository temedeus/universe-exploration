package com.universe.exploration.spaceship;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.universe.exploration.CoreConfiguration;

public class SpaceshipMonitor {
	private float spaceshipAngle;
	private float desiredSpaceshipAngle; 
	private float turnspeed; 
	private float velocity;
	private OrthographicCamera camera;
	
	/** 
	 * Turning direction. 0 = we're at target, -1 = turn counter-clockwise, 1 = turn clockwise.
	 */
	private int direction = 0;
	
	public SpaceshipMonitor() {
		this.desiredSpaceshipAngle = 0;
		this.turnspeed = 0;
		this.velocity = CoreConfiguration.minVelocity;
		this.spaceshipAngle = 0;
		this.camera = new OrthographicCamera(1920, 1080);
	}
	
	/**
	 * Move around canvas
	 * 
	 * @param float x
	 * @param float s
	 */
	public void move(float x, float s) {
		float xInc = (float)Math.cos(x * Math.PI/180) * -1 * s * Gdx.graphics.getDeltaTime() * 50;
		float yInc =  (float)Math.sin(x * Math.PI/180) * -1 * s * Gdx.graphics.getDeltaTime() * 50;
		this.camera.translate(xInc, yInc);
		this.camera.update();
	}
	
	public OrthographicCamera getOrthographicCamera() {
		return this.camera;
	}
	
	public void zoomIn()
	{
		this.camera.zoom += .01;
		this.camera.update();
	}
	
	public void zoomOut()
	{
	    this.camera.zoom -= .01;
	    this.camera.update();
	}
	/**
	 * Get spaceship turnspeed
	 */
	public float getVelocity() {
		return this.velocity;
	}
	
	/** 
	 * Turn spaceship to necessary angle.
	 * @return float turnspeed for graphically turning the ship
	 */
	public void thrusterOn() {
		if(this.velocity < CoreConfiguration.maxVelocity) {
			this.velocity += 0.1;
		}
	}

	/** 
	 * Turn spaceship to necessary angle.
	 * @return float turnspeed for graphically turning the ship
	 */
	public void thrusterOff() {
		if(this.velocity > 2) {
			this.velocity -= 0.1;
		}
	}
}