package com.universe.exploration.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class CameraMonitor {
	private OrthographicCamera camera;

	private static final float ZOOM_MAX = -0.2f;

	private static final float ZOOM_MIN = -5;

	public CameraMonitor() {
		camera = new OrthographicCamera(1920, 1080);
		camera.zoom = ZOOM_MIN;
	}

	public void zoom(boolean zoomIn) {
		if (zoomIn) {
			if (camera.zoom <= ZOOM_MAX) {
				performZoom(.05f);
			}
		} else {
			if (camera.zoom > ZOOM_MIN) {
				performZoom(-.05f);
			}
		}
		
		camera.update();
	}

	private void performZoom(float zoomVal) {
		camera.zoom += zoomVal;
	}

	public OrthographicCamera getOrthographicCamera() {
		return camera;
	}
}
