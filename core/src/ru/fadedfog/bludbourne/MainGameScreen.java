package ru.fadedfog.bludbourne;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class MainGameScreen implements Screen {
	private OrthographicCamera hudCamera = null;
	private InputMultiplexer multiplexer;
	private static PlayerHUD playerHUD;
	private Entity player;
	
	public static class VIEWPORT {
		public static float physicalWidth;
		public static float physicalHeight;
	}
	
	public MainGameScreen(BludBourneGame game) {
		player = EntityFactory.getInstance().getEntity(EntityFactory.EntityType.PLAYER);
		hudCamera = new OrthographicCamera();
		hudCamera.setToOrtho(false, VIEWPORT.physicalWidth, VIEWPORT.physicalHeight);
		playerHUD = new PlayerHUD(hudCamera, player);
		
		multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(playerHUD.getStage());
		multiplexer.addProcessor(player.getInputProcessor());
		Gdx.input.setInputProcessor(multiplexer);
	}
	
	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		
	}

	@Override
	public void resize(int width, int height) {
		
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
		
	}

}
