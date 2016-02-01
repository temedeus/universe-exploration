/**
 * 
 */
package com.universe.exploration.audio;

/**
 * @author 5.11.2015 Teemu Puurunen
 *
 */
public enum MusicFiles implements IAudioFile {
    BASIC_AMBIENT("SPACE", "space.mp3"),
    ALIENS_ARRIVE("ALIENS_ARRIVE", "aliens_arrive.ogg");

    private final static String DIR = "music/";
    
    private final String id;
    private final String audioFile;

    private MusicFiles(String id, String audioFile) {
	this.id = id;
	this.audioFile = audioFile;
    }

    /**
     * @return the id
     */
    public String getId() {
	return id;
    }

    /**
     * @return the audioFile
     */
    public String getAudioFilePath() {
	return DIR + audioFile;
    }
}
