package com.universe.exploration.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class SpaceshipMonitor {
	private OrthographicCamera camera;
	
	public SpaceshipMonitor() {
		this.camera = new OrthographicCamera(1920, 1080);
		this.camera.zoom = -5;
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
}
