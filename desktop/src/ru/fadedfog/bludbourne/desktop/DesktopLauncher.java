package ru.fadedfog.bludbourne.desktop;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.fadedfog.bludbourne.BludBourneGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "BludBourne";
		config.useGL30 = false;
		config.width = 800;
		config.height = 600;
		
		Application app = new LwjglApplication(new BludBourneGame(), config);
		Gdx.app = app;
		
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
	}
}
