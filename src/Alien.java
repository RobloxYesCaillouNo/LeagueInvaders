import java.awt.Color;
import java.awt.Graphics;

public class Alien extends GameObject {
	boolean direction = true;
	int number = 6;

	public Alien(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	public void update() {
		super.update();
		if (x > 500) {
			direction = false;
		} else if (x < 0) {
			direction = true;
		}
		if (direction == true) {
			y = y + number;
			x = x + number;
		} else if (direction == false) {
			x = x - number;
			y = y + number;
		}

	}

	public void draw(Graphics g) {
		g.drawImage(GamePanel.alienImg, x, y, width, height, null);
	}
}
