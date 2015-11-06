/**
 * 
 */
package com.universe.exploration.audio;

/**
 * @author 5.11.2015 Teemu Puurunen 
 *
 */
public class AudioManager {
	private AudioContainer audioContainer;
	
	public AudioManager() {
		audioContainer = new AudioContainer();
		audioContainer.addAudioHandler(MusicFiles.BASIC_AMBIENT);
	}
	
	public void updateBackGroundMusic() {
		
	}
	
	public void setMusicVolume(float volume) {
		
	}
}
