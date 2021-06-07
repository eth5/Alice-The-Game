package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.new_game.Main;


public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1400;
		config.height = 600;
		config.pauseWhenBackground = true;
		config.pauseWhenMinimized = true;
		config.backgroundFPS = 60;
		config.foregroundFPS = 60;

		// TODO : create abstraction over LwjglAplication, so you wont have to set config by yourself and call super.create() in ApplicationLoop
		new LwjglApplication(new Main(), config);

	}
}