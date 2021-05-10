package com.universe.exploration.utils.gameassetmanager.gameassetprovider;

import com.badlogic.gdx.assets.loaders.I18NBundleLoader;
import com.badlogic.gdx.utils.I18NBundle;
import com.universe.exploration.utils.gameassetmanager.GameAssetManager;

import java.util.Locale;

public class LocaleAssetProvider extends AbstractGameAssetProvider {
    public LocaleAssetProvider(GameAssetManager gameAssetManager) {
        super(gameAssetManager);
    }

    @Override
    public void setupAssets() {
        gameAssetManager.getAssetManager().load(LocaleAsset.DefaultBundleEn.getPath(), I18NBundle.class,  new I18NBundleLoader.I18NBundleParameter(Locale.UK));
    }

    public enum LocaleAsset implements GameAsset {
        DefaultBundleEn("locale/DefaultBundle");

        private String path;

        LocaleAsset(String path) {
            this.path = path;
        }

        @Override
        public String getPath() {
            return path;
        }
    }
}
