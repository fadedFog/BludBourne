package ru.fadedfog.bludbourne;

import com.badlogic.gdx.utils.Array;

public class EntityConfig {
	private Entity.State state;
	private Entity.Direction direction;
	
	public String getEntityId() {
		return "";
	}

	public Array<AnimationConfig> getAnimationConfig() {
		return null;
	}
	
	public Entity.State getState() {
		return state;
	}
	
	public Entity.Direction getDirection() {
		return direction;
	}
	
}
