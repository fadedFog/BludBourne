package ru.fadedfog.bludbourne;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;

public class PlayerInputComponents extends InputComponent implements InputProcessor {
	private static final String TAG = PlayerInputComponents.class.getSimpleName();
	private Vector3 lastMouseCoordinates;
	
	public PlayerInputComponents() {
		this.lastMouseCoordinates = new Vector3();
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void update(Entity entity, float delta) {
		if (keys.get(Keys.LEFT)) {
			entity.sendMessage(MESSAGE.CURRENT_STATE, json.toJson(Entity.State.WALKING));
			entity.sendMessage(MESSAGE.CURRENT_DIRECTION, json.toJson(Entity.Direction.LEFT));
		} else if (keys.get(Keys.RIGHT)) {
			entity.sendMessage(MESSAGE.CURRENT_STATE, json.toJson(Entity.State.WALKING));
			entity.sendMessage(MESSAGE.CURRENT_DIRECTION, json.toJson(Entity.Direction.RIGHT));
		} else if (keys.get(Keys.UP)) {
			entity.sendMessage(MESSAGE.CURRENT_STATE, json.toJson(Entity.State.WALKING));
			entity.sendMessage(MESSAGE.CURRENT_DIRECTION, json.toJson(Entity.Direction.UP));
		} else if (keys.get(Keys.DOWN)) {
			entity.sendMessage(MESSAGE.CURRENT_STATE, json.toJson(Entity.State.WALKING));
			entity.sendMessage(MESSAGE.CURRENT_DIRECTION, json.toJson(Entity.Direction.DOWN));
		} else if (keys.get(Keys.QUIT)) {
			Gdx.app.exit();
		} else {
			entity.sendMessage(MESSAGE.CURRENT_STATE, json.toJson(Entity.State.IDLE));
			if (currentDirection == null) {
				entity.sendMessage(MESSAGE.CURRENT_DIRECTION, json.toJson(Entity.Direction.DOWN));
			}
		}
		
		if (mouseButtons.get(Mouse.SELECT)) {
			entity.sendMessage(MESSAGE.INIT_SELECT_ENTITY, json.toJson(lastMouseCoordinates));
			mouseButtons.put(Mouse.SELECT, false);
		}
	}

	@Override
	public void receiveMessage(String fullMessage) {
		String[] string = fullMessage.split(MESSAGE_TOKEN);
		
		if (string.length == 0) {
			return;
		}
		
		if (string.length == 2) {
			if (string[0].equalsIgnoreCase
					(MESSAGE.CURRENT_DIRECTION.toString())) {
				currentDirection = json.fromJson(Entity.Direction.class, string[1]);
			}
		}
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		return false;
	}

	
	@Override
	public void dispose() {
		Gdx.input.setInputProcessor(null);
	}

}
