package game.states;

import game.Handler;
import game.gfx.Background;
import game.sounds.SoundLoader;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class MenuState extends State {

	private Background bg;

	private int currentChoice = 0;
	private String[] options = { "Start", "Help", "Quit" };

	private Color titleColor;
	private Font titleFont;

	private Font font;

	public MenuState(Handler handler) {
		super(handler);

		bg = new Background("/textures/menu/34.jpg", handler);

		titleColor = new Color(128, 0, 0);
		titleFont = new Font("Century Gothic", Font.BOLD, 60);

		font = new Font("Arial", Font.PLAIN, 60);
		SoundLoader.MENU.loop();

	}

	public void draw(Graphics g) {

		// draw bg
		bg.draw(g);

		// draw title
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("AL Montakm", handler.getWidth() / 3, 100);

		// draw menu options
		g.setFont(font);
		for (int i = 0; i < options.length; i++) {
			if (i == currentChoice) {
				g.setColor(Color.RED);
			} else {
				g.setColor(Color.BLACK);
			}
			g.drawString(options[i], handler.getWidth() / 2 - 60, 260 + i * 60);
		}
		if (first != true)
			options[0] = "Continue";

	}

	private void select() {
		if (currentChoice == 0) {
			if (first)
				first = false;
			handler.getGame().setMenu(false);
			SoundLoader.MENU.stop();
		}
		if (currentChoice == 1) {
			// help
		}
		if (currentChoice == 2) {
			System.exit(0);
		}
	}

	public void getInput() {

		if (delayCounter == delay) {
			if (handler.getKeyManager().enter) {
				select();
			} else if (handler.getKeyManager().up) {
				currentChoice--;
				if (currentChoice == -1) {
					currentChoice = options.length - 1;
				}
			} else if (handler.getKeyManager().down) {
				currentChoice++;
				if (currentChoice == options.length) {
					currentChoice = 0;
				}
			} else if (handler.getKeyManager().esc && first != true)
				handler.getGame().setMenu(false);
			delayCounter = 0;
		} else
			delayCounter++;
	}

	@Override
	public void tick() {
		getInput();
	}

	@Override
	public void render(Graphics g) {
		draw(g);
	}

}
