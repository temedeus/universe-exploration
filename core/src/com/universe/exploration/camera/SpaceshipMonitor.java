package com.universe.exploration.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class SpaceshipMonitor {
	private OrthographicCamera camera;
	
	private static final float ZOOM_MAX = 0;
	
	private static final float ZOOM_MIN = -5;
	
	public SpaceshipMonitor() {
		camera = new OrthographicCamera(1920, 1080);
		camera.zoom = ZOOM_MIN;
		camera.update();
	}
	
	public OrthographicCamera getOrthographicCamera() {
		return camera;
	}
	
	public void zoomIn()
	{
		if(camera.zoom <= ZOOM_MAX) {
			camera.zoom += .01;
			camera.update();
		}
	}
	
	public void zoomOut()
	{
		if(camera.zoom >= ZOOM_MIN) {
		    camera.zoom -= .01;
		    camera.update();
		}
	}
}
