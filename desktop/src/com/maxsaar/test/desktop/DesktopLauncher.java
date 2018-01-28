package com.maxsaar.test.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.maxsaar.test.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "lmao";
		config.height = 540;
		config.width = 960;
		new LwjglApplication(new MyGdxGame(), config);
	}
}
