import java.awt.Color;
import java.awt.Graphics;

public class Alien extends GameObject {
	boolean direction = true;

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
			y = y + 7;
			x = x + 7;
		} else if (direction == false) {
			x = x - 7;
			y = y + 7;
		}

	}

	public void draw(Graphics g) {
		g.setColor(Color.YELLOW);
		g.drawRect(x, y, width, height);
	}
}
