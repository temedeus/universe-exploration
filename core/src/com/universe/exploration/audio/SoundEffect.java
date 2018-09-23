/**
 *
 */
package com.universe.exploration.audio;

import java.io.File;

/**
 * @author 5.11.2015 Teemu Puurunen
 */
public enum SoundEffect implements IAudioFile {
    ANNOUNCEMENT("ANNOUNCEMENT", "announcement.ogg"),
    HYPERSPACEJUMP("ANNOUNCEMENT", "hyperspacejump.ogg");

    private final static String DIR = "soundeffects" + File.separator;
    private final String id;
    private final String audioFile;

    SoundEffect(String id, String audioFile) {
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
