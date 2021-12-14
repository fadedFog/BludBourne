package ru.fadedfog.bludbourne;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.utils.Json;

import ru.fadedfog.bludbourne.Entity.Direction;
import ru.fadedfog.bludbourne.Entity.State;

public class PlayerPhysicsComponent extends PhysicsComponent {
	private Json json;
	private Vector2 currentEntityPosition;
	private Vector2 nextEntityPosition;
	private Entity.State state;
	private Entity.Direction currentDirection;
	private Vector3 mouseSelectCoordinates;
	private boolean isMouseSelectEnabled = false;
	private Ray selectionRay;
	private float selectRayMaximumDistance = 32.0f;
	
	public PlayerPhysicsComponent() {
		json = new Json();
		currentEntityPosition = new Vector2();
		nextEntityPosition = new Vector2();
		state = State.IDLE;
		currentDirection = Direction.RIGHT;
		mouseSelectCoordinates = new Vector3(0, 0, 0);
		selectionRay = new Ray(new Vector3(), new Vector3());
	}
	
	@Override
	public void dispose() {
		
	}
	
	@Override
	public void update(Entity entity, MapManager mapMg, float delta) {

	}
	
	@Override
	public void receiveMessage(String fullMessage) {
		String[] string = fullMessage.split(Component.MESSAGE_TOKEN);
		
		if (string.length == 0) {
			return;
		}
		
		if (string.length == 2) {
			if (string[0].equalsIgnoreCase
					(MESSAGE.INIT_START_POSITION.toString())) {
				currentEntityPosition = json.fromJson(Vector2.class, string[1]);
				nextEntityPosition.set(currentEntityPosition.x, currentEntityPosition.y);
			} else if (string[0].equalsIgnoreCase
					(MESSAGE.CURRENT_STATE.toString())) {
				state = json.fromJson(Entity.State.class, string[1]);
			} else if (string[0].equalsIgnoreCase
					(MESSAGE.CURRENT_DIRECTION.toString())) {
				currentDirection = json.fromJson(Entity.Direction.class, string[1]);
			} else if (string[0].equalsIgnoreCase
					(MESSAGE.INIT_SELECT_ENTITY.toString())) {
				mouseSelectCoordinates = json.fromJson(Vector3.class, string[1]);
				isMouseSelectEnabled = true;
			}
		}
	}

	
	
}
