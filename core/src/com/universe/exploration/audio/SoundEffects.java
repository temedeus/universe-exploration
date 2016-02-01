/**
 * 
 */
package com.universe.exploration.audio;

/**
 * @author 5.11.2015 Teemu Puurunen
 *
 */
public enum SoundEffects implements IAudioFile {
    BASIC_AMBIENT("ANNOUNCEMENT", "announcement.ogg");

    private final static String DIR = "soundeffects/";
    private final String id;
    private final String audioFile;

    private SoundEffects(String id, String audioFile) {
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
