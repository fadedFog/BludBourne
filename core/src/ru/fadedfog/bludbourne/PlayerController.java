package ru.fadedfog.bludbourne;

import java.util.Map;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;

public class PlayerController implements InputProcessor {
	private static final String TAG = PlayerController.class.getSimpleName();
	private Map<K, V> keys;
	private Map<K, V> mouseButtons;
	private Vector3 lastMouseCoordinates;
	private Entity player;
	
	public PlayerController(Entity player) {
		this.player = player;
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
	
	public void dispose() {
		
	}
	
	public void leftPressed() {
		
	}
	
	public void rightPressed() {
		
	}

	public void upPressed() {
	
	}

	public void downPressed() {
	
	}
	
	public void quitPressed() {
		
	}
	
	public void setClickedMouseCoordinates(int x, int y) {
		
	}
	
	public void selectMouseButtonPressed(int x, int y) {
		
	}
	
	public void doActionMouseButtonPressed(int x, int y) {
		
	}
	
	public void leftReleased() {
		
	}
	
	public void rightReleased() {
		
	}
	
	public void upReleased() {
		
	}
	
	public void downReleased() {
		
	}
	
	public void quitReleased() {
		
	}
	
	public void selectMouseButtonReleased(int x, int y) {
		
	}
	
	public void doActionMouseButtnReleased(int x, int y) {
		
	}
	
	public void update(float delta) {
		
	}
	
	public void hide() {
		
	}
	
	private void processInput(float delta) {
		
	}
}
