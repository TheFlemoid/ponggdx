package com.fdahl.apps.ponggdx.desktop;

import com.fdahl.apps.ponggdx.PongGdx;
import com.fdahl.apps.ponggdx.helper.GameType;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

		config.setIdleFPS(60);
		config.useVsync(true);
		config.setTitle("Pong gdx");

		config.setWindowedMode(960, 640);

		new Lwjgl3Application(new PongGdx(GameType.DESKTOP), config);
	}
}
