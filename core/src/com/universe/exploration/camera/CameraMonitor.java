package com.universe.exploration.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.universe.exploration.GdxHelper;

public class CameraMonitor {
	private OrthographicCamera camera;

	private static final float ZOOM_MAX = -0.2f;

	private static final float ZOOM_MIN = -5;
	
	private static final float SPEED_MAX = 5;
	
	private Vector2 originalPosition;
	
	private Vector2 velocityVector;

	public CameraMonitor() {
		camera = new OrthographicCamera(1920, 1080);
		camera.zoom = ZOOM_MIN;
		originalPosition = new Vector2(camera.position.x, camera.position.y);
	}
	
	public void setupVelocityVectorToOriginal() {
		setupVelocityVector(originalPosition);
	}

	public void setupVelocityVector(Vector2 vector) {
		Vector2 cameraPosition = new Vector2(camera.position.x, camera.position.y);
		velocityVector = new Vector2(vector.x - cameraPosition.x, vector.y - cameraPosition.y).nor().scl(Math.min(cameraPosition.dst(vector.x, vector.y), SPEED_MAX));
	}

	public void zoom(boolean zoomIn) {
		if(zoomIn) {
			if (camera.zoom <= ZOOM_MAX) {
				performZoom(.05f);
			}
		} else {
			if (camera.zoom > ZOOM_MIN) {
				performZoom(-.05f);
			}
		}
	}
	
	private void performZoom(float zoomVal) {
		camera.zoom += zoomVal;
	}
	
	public void update() {
		float deltaTime = GdxHelper.getDeltaTime();
		
		if(velocityVector != null) {
			Vector2 tempCameraVector = new Vector2(camera.position.x, camera.position.y);
			tempCameraVector.add(velocityVector.x * deltaTime, velocityVector.y * deltaTime);
			camera.translate(tempCameraVector);
	        velocityVector.scl(1 - (0.98f * deltaTime)); 
		}

        camera.update();
    }

	public OrthographicCamera getOrthographicCamera() {
		return camera;
	}
}
