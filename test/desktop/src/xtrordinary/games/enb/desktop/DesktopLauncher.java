package xtrordinary.games.enb.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import xtrordinary.games.enb.ENB;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.resizable = false;
        config.height = 800;
        config.width = 480;
		new LwjglApplication(new ENB(), config);
	}
}
