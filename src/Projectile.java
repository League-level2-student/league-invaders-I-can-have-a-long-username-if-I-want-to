import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Projectile extends GenericThing {

	public Projectile(int x, int y, int width, int height) {
		this(x, y, width, height, true);
	}

	public Projectile(int x, int y, int width, int height, boolean isActive) {
		this(x, y, width, height, isActive, 0, 0);
	}

	public Projectile(int x, int y, int width, int height, boolean isActive, int vx, int vy) {
		super(x, y, width, height, isActive, vx, vy);
		speed = 10;
		if (needImage) {
			loadImage("bullet.png");
		}
	}

	@Override
	public void update() {
		y -= speed;
		if(y < -100) {
			isActive = false;
		}
	}

	public void paint(Graphics g) {
		if (gotImage) {
			g.drawImage(image, x, y, width, height, null);
		} else {
			g.setColor(Color.red);
			g.fillRect(x, y, width, height);
		}
	}

}
