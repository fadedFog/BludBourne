package ru.fadedfog.bludbourne;

import java.util.Hashtable;

import javax.swing.LayoutStyle.ComponentPlacement;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;

import ru.fadedfog.bludbourne.MapFactory.MapType;

public abstract class Map {
	private static final String TAG = Map.class.getSimpleName();
	protected static String mapPath = "maps/town.tmx";
	protected static String townGuardWalking = "scripts/town_guard_walking.json";
	protected static String townBlacksmith = "scripts/town_blacksmith.json";
	protected static String townMage = "scripts/town_mage.json";
	protected static String townInnKeeper = "scripts/town_innkeeper.json";
	protected static String townFolk = "scripts/town_folk.json";
	public static final float UNIT_SCALE = 1/16f;
	protected static final String COLLISION_LAYER = "MAP_COLLISION_LAYER";
	protected static final String SPAWNS_LAYER = "MAP_SPAWNS_LAYER";
	protected static final String PORTAL_LAYER = "MAP_PORTAL_LAYER";
	protected static final String PLAYER_START = "PLAYER_START";
	protected static final String NPC_START = "NPC_START";
	protected Json json;
	protected Vector2 playerStartPositionRect;
	protected Vector2 closestPlayerStartPosition;
	protected Vector2 convertedUtils;
	protected TiledMap currentMapTiled = null;
	protected Vector2 playerStart;
	protected Array<Vector2> npcStartPositions;
	protected Hashtable<String, Vector2> specialNPCStartPositions;
	protected MapLayer collisionLayer;
	protected MapLayer portalLayer;
	protected MapLayer spawnsLayer;
	protected MapType currentMapType;
	protected Array<Entity> mapEntities;
	
	public Map(MapType mapType, String fullMapPath) {
		
	}

	public void updateapEntities(MapManager mapMg, Batch batch, float delta) {
		for (int i = 0; i < mapEntities.size; i++) {
			mapEntities.get(i).update(mapMg, batch, delta);
		}
	}
	
	protected Entity initEntity(EntityConfig entityConfig, Vector2 position) {
		Entity entity = EntityFactory.getEntity(EntityFactory.EntityType.NPC);
		entity.setEntityConfig(entityConfig);
		
		entity.sendMessage(Component.MESSAGE.LOAD_ANIMATIONS, 
				json.toJson(entity.getEntityConfig()));
		entity.sendMessage(Component.MESSAGE.INIT_START_POSITION,
				json.toJson(position));
		entity.sendMessage(Component.MESSAGE.INIT_STATE,
				json.toJson(entity.getEntityConfig().getState()));
		entity.sendMessage(Component.MESSAGE.INIT_DIRECTION, 
				json.toJson(entity.getEntityConfig().getDirection()));
		
		return entity;
	}
	
	protected Entity initSpecialEntity(EntityConfig entityConfig) {
		Vector2 position = new Vector2(0, 0);
		
		if (specialNPCStartPositions.containsKey(entityConfig.getEntityId())) {
			position = specialNPCStartPositions.get(entityConfig.getEntityId());
		}
		
		return initEntity(entityConfig, position);
	}
	
	public Array<Entity> getMapEntity() {
		return mapEntities;
	}
	
	public Vector2 getPlayerStart() {
		return playerStart;
	}
	
	public MapLayer getCollisionLayer() {
		return collisionLayer;
	}
	
	public MapLayer getPortalLayer() {
		return portalLayer;
	}
	
	public TiledMap getCurrentTiledMap() {
		return currentMapTiled;
	}
	
	public Vector2 getPlayerStartUnitScaled() {
		return playerStartPositionRect;
	}
	
	private Array<Vector2> getNPCStartPositions() {
		return getNPCStartPositions();
	}
	
	private Hashtable<String, Vector2> getSpecialNPCStartPositions() {
		return specialNPCStartPositions;
	}
	
	private void setClosestStartPosition(Vector2 position) {
		this.closestPlayerStartPosition = position;
	}
	
	public void setClosestStartPositionFromScaledUntis(Vector2 position) {

	}
	
}
