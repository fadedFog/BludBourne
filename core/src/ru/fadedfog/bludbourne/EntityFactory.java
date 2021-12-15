package ru.fadedfog.bludbourne;

import com.badlogic.gdx.utils.Json;

public class EntityFactory {
	private static Json json = new Json();
	public static String PLAYER_CONFIG = "scripts/player.json";
	
	public static enum EntityType {
		PLAYER, 
		DEMO_PLAYER,
		NPC;
	}
	
	public static Entity getEntity(EntityType entityType) {
		Entity entity = null;
		
		switch(entityType) {
			case PLAYER:
				entity = new Entity(
						new PlayerInputComponents(),
						new PlayerPhysicsComponent(),
						new PlayerGraphicsComponent());
				
				entity.setEntityConfig(
						Entity.getEntityConfig(
								EntityFactory.PLAYER_CONFIG));
				
				return entity;
			case DEMO_PLAYER:
				entity = new Entity(
						new NPCInputComponent(),
						new PlayerPhysicsComponent(),
						new PlayerGraphicsComponent());
				
				return entity;
				
				break;
				
			case NPC:
				entity = new Entity(
						new NPCInputComponent(),
						new NPCPhysicsComponent(),
						new NPCGraphicsComponent());
				
				return entity;
				
				break;
		}
		
		return entity;
	}
	
}
