package ru.fadedfog.bludbourne;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.utils.Json;

public abstract class InputComponent implements Component {
	protected Entity.Direction currentDirection = null;
	protected Entity.State currentState = null;
	protected Json json;
	protected static Map<Keys, Boolean> keys = new HashMap<Keys, Boolean>();
	protected static Map<Mouse, Boolean> mouseButtons = new HashMap<Mouse, Boolean>();
	
	protected enum Keys {
		LEFT, RIGHT, UP, DOWN, QUIT;
	}
	
	protected enum Mouse {
		SELECT, DOACTION;
	}
	
	static {
		mouseButtons.put(Mouse.SELECT, false);
		mouseButtons.put(Mouse.DOACTION, false);
	}
	
	public InputComponent() {
		json = new Json();
	}
	
	public abstract void update(Entity entity, float delta);
 	 
}
