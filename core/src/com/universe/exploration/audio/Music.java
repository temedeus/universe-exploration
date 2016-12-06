/**
 * 
 */
package com.universe.exploration.audio;

import java.io.File;

/**
 * Enumeration containing all the music files in the game.
 * 
 * @author 5.11.2015 Teemu Puurunen
 *
 */
public enum Music implements IAudioFile {
    BASIC_AMBIENT("SPACE", "space.ogg"), ALIENS_ARRIVE("ALIENS_ARRIVE", "aliensarrive.ogg");

    private final static String DIR = "music" + File.separatorChar;

    private final String id;
    private final String audioFile;

    private Music(String id, String audioFile) {
	this.id = id;
	this.audioFile = audioFile;
    }

    public String getId() {
	return id;
    }

    public String getAudioFilePath() {
	return DIR + audioFile;
    }
}
