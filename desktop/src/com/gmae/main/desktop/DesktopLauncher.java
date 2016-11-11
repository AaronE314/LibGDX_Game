package com.gmae.main.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.gmae.main.Physics_Test;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = Physics_Test.HEIGHT;
		config.width = Physics_Test.WIDTH;
		new LwjglApplication(new Physics_Test(), config);
	}
}
