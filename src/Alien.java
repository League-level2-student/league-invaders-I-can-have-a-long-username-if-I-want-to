import java.awt.Color;
import java.awt.Graphics;

public class Alien extends GenericThing {

	public Alien(int x, int y, int width, int height) {
		this(x, y, width, height, true);
	}

	public Alien(int x, int y, int width, int height, boolean isActive) {
		this(x, y, width, height, isActive, 0, 0);
	}

	public Alien(int x, int y, int width, int height, boolean isActive, int vx, int vy) {
		super(x, y, width, height, isActive, vx, vy);
		speed = 1;

		if (needImage) {
			loadImage("alien.png");
		}
	}

	@Override
	public void update() {
		y += speed;
		if (y > LeagueInvaders.table_height) {
			isActive = false;
		}
	}

	public void paint(Graphics g) {
		if (gotImage) {
			g.drawImage(image, x, y, width, height, null);
		} else {
			g.setColor(Color.yellow);
			g.fillRect(x, y, width, height);
		}
	}

}
