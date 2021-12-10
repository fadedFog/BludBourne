package ru.fadedfog.bludbourne.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;

import ru.fadedfog.bludbourne.Entity;
import ru.fadedfog.bludbourne.MapManager;
import ru.fadedfog.bludbourne.PlayerController;

public class MainGameScreen implements Screen {
	private static final String TAG = MainGameScreen.class.getSimpleName();
	private PlayerController controller;
	private TextureRegion currentPlayerFrame;
	private Sprite currentPlayerSprite;
	private OrthogonalTiledMapRenderer mapRenderer = null;
	private OrthographicCamera camera = null;
	private static MapManager mapMg;
	private static Entity player;

	private static class VIEWPORT {
		static float viewportWidth;
		static float viewportHeight;
		static float virtualWidth;
		static float virtualHeight;
		static float physicalWidth;
		static float physicalHeight;
		static float aspectRatio;
	}
	
	public MainGameScreen() {
		mapMg = new MapManager();
	}
	
	@Override
	public void show() {
		setupViewport(10, 10);
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, VIEWPORT.viewportWidth, 
								VIEWPORT.viewportHeight);
		mapRenderer = new OrthogonalTiledMapRenderer(mapMg.getCurrentMap(),
													MapManager.UNIT_SCALE);
		mapRenderer.setView(camera);
		
		Gdx.app.debug(TAG, "UnitScale value is: " + mapRenderer.getUnitScale());
		
		player = new Entity();
		player.init(mapMg.getPlayerStartUnitScaled().x, mapMg.getPlayerStartUnitScaled().y);
		
		currentPlayerSprite = player.getFrameSprite();
		
		controller = new PlayerController(player);
		Gdx.input.setInputProcessor(controller);
	}

	@Override
	public void hide() {
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.position.set(currentPlayerSprite.getX(),
				currentPlayerSprite.getY(), 0f);
		camera.update();
		
		player.update(delta);
		currentPlayerFrame = player.getFrame();
		
		updatePortalLayerActivation(player.boundingBox);
		
		if (!isCollisionWithMapLayer(player.boundingBox)) {
			player.setNextPositionToCurrent();
		}
		
		controller.update(delta);
		
		mapRenderer.setView(camera);
		mapRenderer.render();
		
		mapRenderer.getBatch().begin();
		mapRenderer.getBatch().draw(currentPlayerFrame, currentPlayerSprite.getX(), 
				currentPlayerSprite.getY(), 1f, 1f);
		mapRenderer.getBatch().end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		player.dispose();
		controller.dispose();
		Gdx.input.setInputProcessor(null);
		mapRenderer.dispose();
	}
	
	private void setupViewport(int width, int height) {
		VIEWPORT.virtualWidth = width;
		VIEWPORT.virtualHeight = height;
		VIEWPORT.viewportWidth = VIEWPORT.virtualWidth;
		VIEWPORT.viewportHeight = VIEWPORT.virtualHeight;
		VIEWPORT.physicalWidth = Gdx.graphics.getWidth();
		VIEWPORT.physicalHeight = Gdx.graphics.getHeight();
		
		VIEWPORT.aspectRatio = (VIEWPORT.virtualWidth / VIEWPORT.virtualHeight);
		
		if (VIEWPORT.physicalWidth / VIEWPORT.physicalHeight 
				>= VIEWPORT.aspectRatio) {
			VIEWPORT.viewportWidth = VIEWPORT.viewportHeight * 
					(VIEWPORT.physicalWidth / VIEWPORT.physicalHeight);
			VIEWPORT.viewportHeight = VIEWPORT.virtualHeight;
		} else {
			VIEWPORT.viewportWidth = VIEWPORT.virtualWidth;
			VIEWPORT.viewportHeight = VIEWPORT.viewportWidth * 
					(VIEWPORT.physicalHeight / VIEWPORT.physicalWidth);
		}
		
		Gdx.app.debug(TAG, "WorldRenderer: virtual: (" + 
				VIEWPORT.virtualWidth + "," + VIEWPORT.virtualHeight + ")");
		Gdx.app.debug(TAG, "WorldRenderer: viewport: (" + 
				VIEWPORT.viewportWidth + "," + VIEWPORT.viewportHeight + ")");
		Gdx.app.debug(TAG, "WorldRenderer: physical: (" + 
				VIEWPORT.physicalWidth + "," + VIEWPORT.physicalHeight + ")");
		
	}

	private boolean isCollisionWithMapLayer(Rectangle boundingBox) {
		MapLayer mapCollisionLayer = mapMg.getCollisionLayer();
		
		if (mapCollisionLayer == null) {
			return false;
		}
		
		Rectangle rectangle = null;
		
		for (MapObject object: mapCollisionLayer.getObjects()) {
			if (object instanceof RectangleMapObject) {
				rectangle = ((RectangleMapObject) object).getRectangle();
				if (boundingBox.overlaps(rectangle)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	private boolean updatePortalLayerActivation(Rectangle boundingBox) {
		MapLayer mapPortalLayer = mapMg.getPortalLayer();
		
		if (mapPortalLayer == null) {
			return false;
		}
		
		Rectangle rectangle = null;
		
		for (MapObject object: mapPortalLayer.getObjects()) {
			if (object instanceof RectangleMapObject) {
				rectangle = ((RectangleMapObject)object).getRectangle();
				if(boundingBox.overlaps(rectangle)) {
					String mapName = object.getName();
					if (mapName == null) {
						return false;
					}
					
					mapMg.setClosestStartPositionFromScaledUnits(player.getCurrentPosition());
					mapMg.loadMap(mapName);
					player.init(mapMg.getPlayerStartUnitScaled().x, 
							mapMg.getPlayerStartUnitScaled().y);
					mapRenderer.setMap(mapMg.getCurrentMap());
					
					Gdx.app.debug(TAG, "Portal Activated");
					return true;
				}
			}
		}
		
		return false;
	}
}
