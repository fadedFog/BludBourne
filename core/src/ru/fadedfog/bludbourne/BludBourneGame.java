package ru.fadedfog.bludbourne;

import com.badlogic.gdx.Game;

import ru.fadedfog.bludbourne.screens.MainGameScreen;

public class BludBourneGame extends Game {
	public static final MainGameScreen mainGameScreen = new MainGameScreen();
	
	@Override
	public void create () {
		setScreen(mainGameScreen);
	}

	@Override
	public void render () {
	}
	
	@Override
	public void dispose () {
		mainGameScreen.dispose();
	}
}
