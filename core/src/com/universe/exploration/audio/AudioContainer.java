/**
 * 
 */
package com.universe.exploration.audio;

import java.util.HashMap;

/**
 * @author 3.11.2015 Teemu Puurunen
 *
 */
public class AudioContainer {
    private HashMap<String, AudioHandler> audioHash;

    public AudioContainer() {
	audioHash = new HashMap<String, AudioHandler>();
    }

    public void addAudioHandler(IAudioFile audioFile) {
	audioHash.put(audioFile.getId(), new AudioHandler(audioFile));
    }

    public boolean changeMusicFading(String id) {

	return false;
    }

    public void setVolume(String id, float volume) {
	Long audioFilePlayID = audioHash.get(id).id;
	if (audioFilePlayID != null) {
	    audioHash.get(id).audio.setVolume((long) audioFilePlayID.floatValue(), volume);
	}

    }

}
