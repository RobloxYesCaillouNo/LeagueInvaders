import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

//stuff for the game
public class GamePanel extends JPanel implements ActionListener, KeyListener {
	Timer timer = new Timer(1000 / 60, this);
	Rocketship rocketship = new Rocketship(250, 700, 50, 50);
	ObjectManager objectmanager = new ObjectManager(rocketship);
	public static BufferedImage alienImg;
	public static BufferedImage rocketImg;
	public static BufferedImage bulletImg;
	public static BufferedImage spaceImg;
	final int MENU_STATE = 0;
	final int GAME_STATE = 1;
	final int END_STATE = 2;
	int score;
	int currentState = MENU_STATE;
	Font titleFont = new Font("Roboto", Font.BOLD, 48);
	Font enterFont = new Font("Arial", Font.ITALIC, 28);
	Font inFont = new Font("Arial", Font.ITALIC, 28);
	Font gameoverFont = new Font("Roboto", Font.BOLD, 48);
	Font enemyFont = new Font("Arial", Font.ITALIC, 28);
	Font retryFont = new Font("Arial", Font.ITALIC, 28);

	public GamePanel() {
		try {

			alienImg = ImageIO.read(this.getClass().getResourceAsStream("alien.png"));
			rocketImg = ImageIO.read(this.getClass().getResourceAsStream("rocket.png"));
			bulletImg = ImageIO.read(this.getClass().getResourceAsStream("bullet.png"));
			spaceImg = ImageIO.read(this.getClass().getResourceAsStream("space.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

	public void startGame() {
		timer.start();
	}

	public void updateMenuState() {

	}

	public void updateGameState() {
		objectmanager.update();
		objectmanager.manageEnemies();
		objectmanager.checkCollision();
		objectmanager.purgeObjects();
		if (rocketship.isAlive == false) {
			currentState = END_STATE;
		}
	}

	public void updateEndState() {

	}

	public void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, 500, 800);
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("LEAGUE INVADERS", 24, 200);
		g.setFont(enterFont);
		g.drawString("Press ENTER to start", 111, 350);
		g.setFont(inFont);
		g.drawString("Press SPACE for instructions", 70, 500);
	}

	public void drawGameState(Graphics g) {
		g.drawImage(spaceImg, 0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT, null);
		objectmanager.draw(g);
	}

	public void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, 500, 800);
		g.setFont(gameoverFont);
		g.setColor(Color.BLACK);
		g.drawString("Game Over", 113, 200);
		g.setFont(enemyFont);
		g.drawString("You killed " + objectmanager.getScore() + " enemies", 120, 350);

		g.setFont(retryFont);
		g.drawString("Press ENTER to restart", 103, 505);

	}

	@Override
	public void paintComponent(Graphics g) {
		if (currentState == MENU_STATE) {
			drawMenuState(g);
		} else if (currentState == GAME_STATE) {
			drawGameState(g);
		} else if (currentState == END_STATE) {
			drawEndState(g);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (currentState == MENU_STATE) {
			updateMenuState();
		} else if (currentState == GAME_STATE) {
			updateGameState();
		} else if (currentState == END_STATE) {
			updateEndState();
		}
		repaint();

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		if (e.getKeyCode() == 10) {

			currentState++;
			if (currentState >= END_STATE) {
				currentState = MENU_STATE;
				rocketship = new Rocketship(250, 700, 50, 50);
				objectmanager = new ObjectManager(rocketship);
			}

			else if (currentState == MENU_STATE) {
				if (e.getKeyCode() == 32) {
					JOptionPane.showMessageDialog(null, "Use arrow keys to move. Press SPACE to fire. Try not to die");
				}

			}

		}

		else if (e.getKeyCode() == 37) {
			rocketship.x -= rocketship.speed;

		} else if (e.getKeyCode() == 39) {
			rocketship.x += rocketship.speed;
		} else if (e.getKeyCode() == 32) {
			objectmanager.addProjectile(new Projectile(rocketship.x, rocketship.y, 10, 10));
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

}
