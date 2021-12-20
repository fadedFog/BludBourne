package ru.fadedfog.bludbourne;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import ru.fadedfog.bludbourne.MapFactory.MapType;

public class TownMap extends Map {
	private static MapType mapType;
	private static String fullMapPath;
	
	public TownMap() {
		super(mapType, fullMapPath);
		
		for (Vector2 positin: npcStartPositions) {
			mapEntities.add(initEntity(Entity.getEntityConfig(townGuardWalking), positin));
		}
		
		mapEntities.add(initSpecialEntity(Entity.getEntityConfig(townBlacksmith)));
		mapEntities.add(initSpecialEntity(Entity.getEntityConfig(townMage)));
		mapEntities.add(initSpecilaEntity(Entity.getEntityConfig(townInnKeeper)));
		
		Array<EntityConfig> configs = Entity.getEntityConfigs(townFolk);
		for (EntityConfig config: configs) {
			mapEntities.add(initSpecialEntity(config));
		}
	}

}
