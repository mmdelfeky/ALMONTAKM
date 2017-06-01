package game.gfx;

import java.awt.image.BufferedImage;

public class Assets {

	private static final int width = 32, height = 32;
	private static final int PlayerWidth = 150, PlayerHeight = 117;
	private static final int snailWidth = 40, snailHeight = 40;
	public static BufferedImage grass, stone, flower, stoneGrass, bridge,home,home2,tree,
			bridge2,water;
	public static BufferedImage[] player_down, player_up, player_left,
			player_right, snail_down, snail_up, snail_left, snail_right;

	public static BufferedImage[] waterFall;

	public static void init() {
		SpriteSheet sheet = new SpriteSheet(
				ImageLoader.loadImage("/textures/player/sheet2.png"));

		player_down = new BufferedImage[7];
		player_up = new BufferedImage[7];
		player_left = new BufferedImage[7];
		player_right = new BufferedImage[7];

		for (int i = 0; i < 7; i++) {
			player_down[i] = sheet.crop(i * PlayerWidth, 0, PlayerWidth,
					PlayerHeight);
		}
		for (int i = 0; i < 7; i++) {
			player_up[i] = sheet.crop(i * PlayerWidth, PlayerHeight,
					PlayerWidth, PlayerHeight);
		}
		for (int i = 0; i < 7; i++) {
			player_left[i] = sheet.crop(i * PlayerWidth, PlayerHeight * 2,
					PlayerWidth, PlayerHeight);
		}

		for (int i = 0; i < 7; i++) {
			player_right[i] = sheet.crop(i * PlayerWidth, PlayerHeight * 3,
					PlayerWidth, PlayerHeight);
		}

		 sheet = new SpriteSheet(
				ImageLoader.loadImage("/textures/snail/snail.png"));
		snail_down = new BufferedImage[5];
		snail_up = new BufferedImage[5];
		snail_left = new BufferedImage[5];
		snail_right = new BufferedImage[5];

		for (int i = 0; i < 5; i++) {
			snail_down[i] = sheet.crop(i * snailWidth, 0, snailWidth,
					snailHeight);
		}
		for (int i = 0; i < 5; i++) {
			snail_right[i] = sheet.crop(i * snailWidth, snailHeight,
					snailWidth, snailHeight);
		}
		for (int i = 0; i < 5; i++) {
			snail_up[i] = sheet.crop(i * snailWidth, snailHeight * 2,
					snailWidth, snailHeight);
		}

		for (int i = 0; i < 5; i++) {
			snail_left[i] = sheet.crop(i * snailWidth, snailHeight * 3,
					snailWidth, snailHeight);
		}

		
		sheet = new SpriteSheet(
				ImageLoader.loadImage("/textures/world/sheet5.png"));
		waterFall = new BufferedImage[4];
		for (int i = 0; i < 4; i++) {
			waterFall[i] = sheet.crop((i + 7) * width, 0, width, height);
		}
		/*water = new BufferedImage[4];
		for (int i = 0; i < 4; i++) {
			water[i] = sheet.crop(i * width, height, width, height);
		}*/

		grass = sheet.crop(0, 0, width, height);
		stoneGrass = sheet.crop(width, 0, width, height);
		bridge2 = sheet.crop(width * 5, 0, width, height);
		bridge = sheet.crop(width * 13, 0, width, height);
		flower = sheet.crop(width * 4, 0, width, height);
		stone = sheet.crop(width * 6, 0, width, height);
		water =sheet.crop(width*2, 0, width, height);
		sheet = new SpriteSheet(
				ImageLoader.loadImage("/textures/world/home.gif"));
		home=sheet.crop(0, 0, 75, 90);
		
		sheet = new SpriteSheet(
				ImageLoader.loadImage("/textures/world/home2.gif"));
		home2=sheet.crop(0, 0, 145,175);
		
		sheet = new SpriteSheet(
				ImageLoader.loadImage("/textures/world/tree.gif"));
		tree=sheet.crop(0, 0, 62, 82);
	}
	

}