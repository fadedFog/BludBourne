package ru.fadedfog.bludbourne;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Utility {
	private static final String TAG = Utility.class.getSimpleName();
	private final static String STATUSUI_TEXTURE_ATLAS_PATH = "skins/statusui.atlas";
	private final static String STATUSUI_SKIN_PATH = "skins/statusui.json";
	private static InternalFileHandleResolver filePathResolver = new InternalFileHandleResolver();
	public static final AssetManager assetManager = new AssetManager();
	public static TextureAtlas STATUSUI_TEXTUREATLAS = new TextureAtlas(STATUSUI_TEXTURE_ATLAS_PATH);
	public static Skin STATUS_SKIN = new Skin(Gdx.files.internal(STATUSUI_SKIN_PATH), STATUSUI_TEXTUREATLAS);
	
	public static void unloadAsset(String assetFileNamePath) {
		if (assetManager.isLoaded(assetFileNamePath)) {
			assetManager.unload(assetFileNamePath);
		} else {
			Gdx.app.debug(TAG, "Asset is not loaded; Nothing to unload:"
					+ assetFileNamePath);
		}
	}
	
	public static float loadCompleted() { 
		return assetManager.getProgress();
	}
	
	public static int numberAssetsQueued() {
		return assetManager.getQueuedAssets();
	}
	
	public static boolean updateAssetLoading() {
		return assetManager.update();
	}
	
	public static boolean isAssetLoaded(String fileName) {
		return assetManager.isLoaded(fileName);
	}
	
	public static void loadMapAsset(String mapFileNamePath) {
		if (mapFileNamePath == null || mapFileNamePath.isEmpty()) {
			return;
		}
		
		if (filePathResolver.resolve(mapFileNamePath).exists()) {
			assetManager.setLoader(TiledMap.class, new TmxMapLoader(filePathResolver));
			assetManager.load(mapFileNamePath, TiledMap.class);
			assetManager.finishLoadingAsset(mapFileNamePath);
			Gdx.app.debug(TAG, "Map loaded!: " + mapFileNamePath);
		} else {
			Gdx.app.debug(TAG, "Map don't exist!: " + mapFileNamePath);
		}
	}
	
	public static TiledMap getMapAsset(String mapFileNamePath) {
		TiledMap map = null;
		
		if (assetManager.isLoaded(mapFileNamePath)) {
			map = assetManager.get(mapFileNamePath, TiledMap.class);
		} else {
			Gdx.app.debug(TAG, "Map is not loaded: " + mapFileNamePath);
		}
		
		return map;
	}
	
	public static void loadTextureAsset(String textureFileNamePath) {
		if (textureFileNamePath == null || textureFileNamePath.isEmpty()) {
			return;
		}
		
		if (filePathResolver.resolve(textureFileNamePath).exists()) {
			assetManager.setLoader(Texture.class, new TextureLoader(filePathResolver));
			assetManager.load(textureFileNamePath, Texture.class);
			assetManager.finishLoadingAsset(textureFileNamePath);
		} else {
			Gdx.app.debug(TAG, "Texture doesn't exist!: " + textureFileNamePath);
		}
	}
	
	public static Texture getTextureAsset(String textureFileNamePath) {
		Texture texture = null;
		
		if (assetManager.isLoaded(textureFileNamePath)) {
			texture = assetManager.get(textureFileNamePath);
		} else {
			Gdx.app.debug(TAG, "Texture is not loaded!: " + textureFileNamePath);
		}
		
		return texture;
	}
	
}
