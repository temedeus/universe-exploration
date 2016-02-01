/**
 * 
 */
package com.universe.exploration.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

/**
 * @author 5.11.2015 Teemu Puurunen
 *
 */
public class AudioHandler {
    public Long id = null;
    public Sound audio;

    /**
	 * 
	 */
    public AudioHandler(IAudioFile audioFile) {
	audio = Gdx.audio.newSound(Gdx.files.internal(audioFile.getAudioFilePath()));
    }
}
