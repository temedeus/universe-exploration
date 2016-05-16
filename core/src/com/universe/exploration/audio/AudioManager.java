/**
 * 
 */
package com.universe.exploration.audio;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

/**
 * Use this class to manage all sound effects and music within the game.
 * 
 * @author 5.11.2015 Teemu Puurunen
 *
 */
public class AudioManager {
    /**
     * Music files
     */
    private HashMap<IAudioFile, Sound> audioFileCache = new HashMap<IAudioFile, Sound>();

    /**
     * We will need the ID of the currently playing music in order to smoothly
     * transition to another songs.
     */
    private long currentlyPlayingBackgroundMusic;

    /**
     * Master volume for music.
     */
    private float musicVolume;

    /**
     * Master volume for sound effects.
     */
    private float soundEffectsVolume;

    /**
     * TODO: Right now we´re just loading all the sounds ready because there
     * isnt many of them. Once you get to coding the main menu there is probably
     * a need to load this stuff only once necessary.
     * 
     * TODO: LibGdx own resource manager?
     */
    public AudioManager() {
	for (SoundEffect effect : SoundEffect.values()) {
	    audioFileCache.put(effect, Gdx.audio.newSound(Gdx.files.internal(effect.getAudioFilePath())));
	}

	for (Music effect : Music.values()) {
	    audioFileCache.put(effect, Gdx.audio.newSound(Gdx.files.internal(effect.getAudioFilePath())));
	}
    }

    public void playSoundEffect(IAudioFile audio) {
	audioFileCache.get(audio).play();
    }

    public void playMusic(Music music, boolean loop) {
	currentlyPlayingBackgroundMusic = (loop) ? audioFileCache.get(music).loop() : audioFileCache.get(music).play();
    }

    /**
     * TODO: transition to another song
     * 
     * @param music
     * @param loop
     */
    public void smoothTransitionTo(Music music, boolean loop) {
	audioFileCache.get(music).stop();
    }

    /**
     * @return the currentlyPlayingBackgroundMusic
     */
    public long getCurrentlyPlayingBackgroundMusic() {
	return currentlyPlayingBackgroundMusic;
    }

    /**
     * @return the audioFileCache
     */
    public HashMap<IAudioFile, Sound> getAudioFileCache() {
	return audioFileCache;
    }

    /**
     * @return the musicVolume
     */
    public float getMusicVolume() {
	return musicVolume;
    }

    /**
     * @return the soundEffectsVolume
     */
    public float getSoundEffectsVolume() {
	return soundEffectsVolume;
    }

    /**
     * @param musicVolume
     *            the musicVolume to set
     */
    public void setMusicVolume(float musicVolume) {
	this.musicVolume = musicVolume;
    }

    /**
     * @param soundEffectsVolume
     *            the soundEffectsVolume to set
     */
    public void setSoundEffectsVolume(float soundEffectsVolume) {
	this.soundEffectsVolume = soundEffectsVolume;
    }
}
