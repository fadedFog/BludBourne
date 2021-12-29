package ru.fadedfog.bludbourne;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import ru.fadedfog.bludbourne.profile.ProfileObserver;

public class PlayerHUD  implements Screen, ProfileObserver, ConversationGraphObserver {
	private Stage stage;
	private Viewport viewport;
	private Camera camera;
	private StatusUI statusUI;
	private InventoryUI inventoryUI;
	
	public PlayerHUD(OrthographicCamera hudCamera, Entity player) {
		viewport = new ScreenViewport(camera);
		stage = new Stage(viewport);
		
		statusUI = new StatusUI();
		inventoryUI = new InventoryUI();
		stage.addActor(statusUI);
		stage.addActor(inventoryUI);
	}

	public Stage getStage() {
		return stage;
	}
	
	public void onNotify(ProfileManager profileMg, ProfileEvent event) {
		
	}

	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void dispose() {
		stage.dispose();
	}
	
	
}
