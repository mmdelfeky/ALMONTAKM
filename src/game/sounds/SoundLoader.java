package game.sounds;

import java.applet.Applet;
import java.applet.AudioClip;

public class SoundLoader {

	public static final AudioClip MENU = Applet.newAudioClip(SoundLoader.class
			.getResource("/sounds/CCG_MTitleMetalRemix_BB_clip.wav"));
	public static final AudioClip SHOT = Applet.newAudioClip(SoundLoader.class
			.getResource("/sounds/POP.WAV"));
	public static final AudioClip GAME = Applet.newAudioClip(SoundLoader.class
			.getResource("/sounds/Birds1.WAV"));
}
