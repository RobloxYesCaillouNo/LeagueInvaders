import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	Rocketship rocketship;
	ArrayList<Projectile> projlist = new ArrayList<Projectile>();
	ArrayList<Alien> alienlist = new ArrayList<Alien>();
	long enemyTimer = 0;
	int enemySpawnTime = 1000;
	int score = 0;
	public ObjectManager(Rocketship rocketship) {
		this.rocketship = rocketship;

	}
	public int getScore() {
		return score;
	}
	public void update() {
		rocketship.update();
		
		for (int i = 0; i < projlist.size(); i++) {
			projlist.get(i).update();

		}
		for (int i = 0; i < alienlist.size(); i++) {
			alienlist.get(i).update();
			getScore();
		}
	}

	public void draw(Graphics g) {
		rocketship.draw(g);
		for (int i = 0; i < projlist.size(); i++) {
			projlist.get(i).draw(g);
		}
		for (int i = 0; i < alienlist.size(); i++) {
			alienlist.get(i).draw(g);
		}
	}

	public void addProjectile(Projectile proj) {
		projlist.add(proj);
	}

	public void addAlien(Alien alien) {
		alienlist.add(alien);
	}

	public void manageEnemies() {
		if (System.currentTimeMillis() - enemyTimer >= enemySpawnTime) {
			addAlien(new Alien(new Random().nextInt(500), 0, 50, 50));

			enemyTimer = System.currentTimeMillis();
		}
	}

	public void checkCollision() {
		for (Alien a : alienlist) {

			if (rocketship.collisionBox.intersects(a.collisionBox)) {

				rocketship.isAlive = false;

			}
		}

		for (int i = 0; i < alienlist.size(); i++) {
			for (int j = 0; j < projlist.size(); j++) {
				if (alienlist.get(i).collisionBox.intersects(projlist.get(j).collisionBox)) {
					alienlist.get(i).isAlive = false;
					projlist.get(j).isAlive = false;
					score++;
				}
			}
		}

	}

	
	public void purgeObjects() {
		for (int i = 0; i < alienlist.size(); i++) {
			if (alienlist.get(i).isAlive == false) {
				alienlist.remove(i);

			}

		}
		for (int i = 0; i < projlist.size(); i++) {
			if (projlist.get(i).isAlive == false) {
				projlist.remove(i);

			}
		}
	}

}
