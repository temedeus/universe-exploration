package com.universe.exploration.utils;

import com.badlogic.gdx.utils.I18NBundle;
import com.universe.exploration.utils.gameassetmanager.GameAssetManager;
import com.universe.exploration.utils.gameassetmanager.gameassetprovider.LocaleAssetProvider;

import java.util.MissingResourceException;

public class Localiser {
    private static Localiser localiser;
    private GameAssetManager assetManager;
    private I18NBundle localisationBundle;

    public static Localiser getInstance(GameAssetManager assetManager) {
        if (localiser == null) {
            localiser = new Localiser(assetManager);
        }

        return localiser;
    }

    private Localiser(GameAssetManager assetManager) {
        this.assetManager = assetManager;
        localisationBundle = assetManager.getAsset(LocaleAssetProvider.LocaleAsset.DefaultBundleEn);
    }

    public String get(String locale) {
        try {
            return localisationBundle.get(locale);
        } catch (MissingResourceException e) {
            return locale;
        }
    }
}
