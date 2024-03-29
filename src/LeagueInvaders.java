
//This is the main class
import java.awt.Dimension;

import javax.swing.JFrame;

public class LeagueInvaders {
	JFrame window = new JFrame();

	final static int WIDTH = 500;
	final static int HEIGHT = 800;
	GamePanel gamePanel = new GamePanel();

	public static void main(String[] args) {
		LeagueInvaders game = new LeagueInvaders();
		game.setup();
	}

	void setup() {
		window.add(gamePanel);
		window.setVisible(true);
		window.getContentPane().setPreferredSize(new Dimension(WIDTH, HEIGHT));
		window.addKeyListener(gamePanel);
		window.pack();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gamePanel.startGame();
	}

}
