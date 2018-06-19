/**
 *
 */
package com.universe.exploration.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import java.util.HashMap;

/**
 * Use this class to manage all sound effects and music within the game.
 *
 * @author 5.11.2015 Teemu Puurunen
 */
public class AudioManager {
    /**
     * MusicConfiguration files
     */
    private HashMap<IAudioFile, Sound> audioFileCache = new HashMap<IAudioFile, Sound>();


    private HashMap<IAudioFile, com.badlogic.gdx.audio.Music> musicCache = new HashMap<IAudioFile, com.badlogic.gdx.audio.Music>();
    /**
     * We will need the ID of the currently playing music in order to smoothly
     * transition to another songs.
     */
    private com.badlogic.gdx.audio.Music currentlyPlayingBackgroundMusic;

    /**
     * Master volume for music.
     */
    private float musicVolume;

    /**
     * Master volume for sound effects.
     */
    private float soundEffectsVolume;

    /**
     * TODO: Right now we're just loading all the sounds ready because there
     * isnt many of them. Once you get to coding the main menu there is probably
     * a need to load this stuff only once necessary.
     * <p>
     * TODO: LibGdx own resource manager?
     */
    public AudioManager() {
        for (SoundEffect effect : SoundEffect.values()) {
            audioFileCache.put(effect, Gdx.audio.newSound(Gdx.files.internal(effect.getAudioFilePath())));
        }

        for (MusicConfiguration effect : MusicConfiguration.values()) {
            musicCache.put(effect, Gdx.audio.newMusic(Gdx.files.internal(effect.getAudioFilePath())));
        }
    }

    public void playSoundEffect(IAudioFile audio) {
        audioFileCache.get(audio).play();
    }

    public void playMusic(MusicConfiguration music, boolean loop) {
        currentlyPlayingBackgroundMusic = musicCache.get(music);
        musicCache.get(music).setLooping(loop);
        musicCache.get(music).play();
    }

    /**
     * @return the currentlyPlayingBackgroundMusic
     */
    public com.badlogic.gdx.audio.Music getCurrentlyPlayingBackgroundMusic() {
        return currentlyPlayingBackgroundMusic;
    }

    /**
     * @return the audioFileCache
     */
    public HashMap<IAudioFile, Sound> getAudioFileCache() {
        return audioFileCache;
    }
}
