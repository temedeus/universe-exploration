package com.universe.exploration.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

public class SpaceshipMonitor {
	private OrthographicCamera camera;
	
	private static final float ZOOM_MAX = -0.2f;
	
	private static final float ZOOM_MIN = -5;
	
	private Vector3 originalPosition;
	
	public SpaceshipMonitor() {
		camera = new OrthographicCamera(1920, 1080);
		camera.zoom = ZOOM_MIN;
		originalPosition = camera.position;
		camera.update();
	}
	
	public OrthographicCamera getOrthographicCamera() {
		return camera;
	}
	
	public void zoomInOnCoordinates()
	{
		if(camera.zoom <= ZOOM_MAX) {
			camera.zoom += .06;
			camera.update();
		}
	}
	
	public void zoomOutOnOriginal()
	{
		if(camera.zoom >= ZOOM_MIN) {
		    camera.zoom -= .06;
		    camera.update();
		}
	}
}
