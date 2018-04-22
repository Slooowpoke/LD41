package com.coxon.rhythm.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.coxon.rhythm.RunGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "It's your turn to boogie - LD41";
		config.resizable = false;
		config.width = 1280;
		config.height = 720;
		new LwjglApplication(new RunGame(), config);
	}
}
