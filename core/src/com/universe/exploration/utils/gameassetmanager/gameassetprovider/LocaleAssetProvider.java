package com.universe.exploration.utils.gameassetmanager.gameassetprovider;

import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.loaders.I18NBundleLoader;
import com.badlogic.gdx.utils.I18NBundle;
import com.universe.exploration.utils.gameassetmanager.GameAssetManager;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class LocaleAssetProvider extends AbstractGameAssetProvider {
    public LocaleAssetProvider(GameAssetManager gameAssetManager) {
        super(gameAssetManager);
    }

    @Override
    public void setupAssets() {
       getGameAssetList().forEach(asset -> {
            if (asset.getAssetLoaderParameter().isPresent()) {
                AssetLoaderParameters param = asset.getAssetLoaderParameter().get();
                gameAssetManager.getAssetManager().load(asset.getPath(), asset.getClazz(), param);
            } else {
                gameAssetManager.getAssetManager().load(asset.getPath(), asset.getClazz());
            }
        });
    }

    @Override
    List<GameAsset> getGameAssetList() {
        return Arrays.asList(LocaleAsset.values());
    }

    public enum LocaleAsset implements GameAsset {
        DefaultBundleEn("locale/DefaultBundle", I18NBundle.class) {
            @Override
            public Optional<AssetLoaderParameters<?>> getAssetLoaderParameter() {
                return Optional.of(new I18NBundleLoader.I18NBundleParameter(Locale.UK));
            }
        };

        private String path;

        public Class<?> getClazz() {
            return clazz;
        }

        private Class<?> clazz;

        LocaleAsset(String path, Class<?> clazz) {
            this.path = path;
            this.clazz = clazz;
        }

        @Override
        public Optional<AssetLoaderParameters<?>> getAssetLoaderParameter() {
            return Optional.empty();
        }

        @Override
        public String getPath() {
            return path;
        }
    }
}
