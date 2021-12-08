package ru.fadedfog.bludbourne;

import java.util.Hashtable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;

public class MapManager {
	private static final String TAG = MapManager.class.getSimpleName();
	private Hashtable<String, String> mapTable;
	private Hashtable<String, Vector2> playerStartLocationTable;
	private static final String TOP_WORLD = "TOP_WORLD";
	private static final String TOWN = "TOWN";
	private static final String CASTLE_OF_DOOM = "CASTLE_OF_DOOM";
	private static final String MAP_COLLISION_LAYER = "MAP_COLLISION_LAYER";
	private static final String MAP_SPAWNS_LAYER = "MAP_SPAWNS_LAYER";
	private static final String MAP_PORTAL_LAYER = "MAP_PORTAL_LAYER";
	private static final String PLAYER_START = "PLAYER_START";
	
	private Vector2 playerStartPositionRect;
	private Vector2 closestPlayerStartPosition;
	private Vector2 convertedUtils;
	private Vector2 playerStart;
	private TiledMap currentMap = null;
	private String currentMapName;
	private MapLayer collisionLayer = null;
	private MapLayer portalLayer = null;
	private MapLayer spawnsLayer = null;
	public static final float UNIT_SCALE = 1f/16f; 

	public MapManager() {
		playerStart = new Vector2(0, 0);
		
		mapTable = new Hashtable<>();
		mapTable.put(TOP_WORLD, "maps/topworld.tmx");
		mapTable.put(TOWN, "maps/town.tmx");
		mapTable.put(CASTLE_OF_DOOM, "maps/castle_of_doom.tmx");
		
		playerStartLocationTable = new Hashtable<>();
		playerStartLocationTable.put(TOP_WORLD, playerStart.cpy());
		playerStartLocationTable.put(TOWN, playerStart.cpy());
		playerStartLocationTable.put(CASTLE_OF_DOOM, playerStart.cpy());
		
		playerStartPositionRect = new Vector2(0, 0);
		closestPlayerStartPosition = new Vector2(0, 0);
		convertedUtils = new Vector2(0, 0);
	}
	
	public void loadMap(String mapName) {
		playerStart.set(0, 0);
	
		String mapFullPath = mapTable.get(mapName);
	
		if (mapFullPath == null || mapFullPath.isEmpty()) {
			Gdx.app.debug(TAG, "Map is invalid");
			return;
		}
		
		if (currentMap != null) {
			currentMap.dispose();
		}
		
		Utility.loadMapAsset(mapFullPath);
		if (Utility.isAssetLoaded(mapFullPath)) {
			currentMap = Utility.getMapAsset(mapFullPath);
			currentMapName = mapName;
		} else {
			Gdx.app.debug(TAG, "Map not loaded!");
			return;
		}
		
		collisionLayer = currentMap.getLayers().get(MAP_COLLISION_LAYER);
		if (collisionLayer == null) {
			Gdx.app.debug(TAG, "No collision layer!");
		}
		
		portalLayer = currentMap.getLayers().get(MAP_PORTAL_LAYER);
		if (portalLayer == null) {
			Gdx.app.debug(TAG, "No portal layer!");
		}
		
		spawnsLayer = currentMap.getLayers().get(MAP_SPAWNS_LAYER);
		if (spawnsLayer == null) {
			Gdx.app.debug(TAG, "No spawn layer!");
		} else {
			Vector2 start = playerStartLocationTable.get(currentMapName);
			if (start.isZero()) {
				setClosestStartPosition(playerStart);
				start = playerStartLocationTable.get(currentMapName);
			}
			playerStart.set(start.x, start.y);
		}
		
		Gdx.app.debug(TAG, "Player Start: (" 
				+ playerStart.x + "," + playerStart.y + ")");
	}
	
	public TiledMap getCurrentMap() {
		if (currentMap == null) {
			currentMapName = TOWN;
			loadMap(currentMapName);
		}
		return currentMap;
	}
	
	public MapLayer getCollisionLayer() {
		return collisionLayer;
	}
	
	public MapLayer getPortalLayer() {
		return portalLayer;
	}
	
	public Vector2 getPlayerStartUnitScaled() {
		Vector2 playerStart = this.playerStart.cpy();
		playerStart.set(this.playerStart.x * UNIT_SCALE, 
				 this.playerStart.y * UNIT_SCALE);
		return playerStart;
	}
	
	private void setClosestStartPosition(Vector2 position) {
		playerStartPositionRect.set(0, 0);
		closestPlayerStartPosition.set(0, 0);
		float shortestDistance = 0f;
		
		for (MapObject object: spawnsLayer.getObjects()) {
			if (object.getName().equalsIgnoreCase(PLAYER_START)) {
				((RectangleMapObject) object).getRectangle().getPosition(playerStartPositionRect);
				float distance = position.dst2(playerStartPositionRect);
				
				if (distance < shortestDistance || shortestDistance == 0) {
					closestPlayerStartPosition.set(playerStartPositionRect);
					shortestDistance = distance;
				}
			}
		}
		playerStartLocationTable.put(currentMapName, closestPlayerStartPosition.cpy());
	}
	
	public void setClosestStartPositionFromScaledUnits(Vector2 position) {
		if (UNIT_SCALE <= 0) {
			return;
		}
		
		convertedUtils.set(position.x / UNIT_SCALE, position.y / UNIT_SCALE);
		setClosestStartPosition(convertedUtils);
	}
}
