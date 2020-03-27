import java.awt.Color;
import java.awt.Graphics;

public class Ship extends GenericThing {
	int tx, ty;
	public double u, d, l, r;

	public Ship(int x, int y, int width, int height) {
		super(x, y, width, height);
		tx = x;
		ty = y;
		u = d = l = r = 1;
		if (needImage) {
			loadImage("rocket.png");
		}
	}

	public void paint(Graphics g) {
		if (gotImage) {
			g.drawImage(image, x, y, width, height, null);
		} else {
			g.setColor(Color.BLUE);
			g.fillRect(x, y, width, height);
		}
	}

	@Override
	public void update() {
		y = (int) d + y - (int) u;
		x = (int) r + x - (int) l;
		// x = (x + tx * 3) / 4;// pain to work with
		// y = (y + ty * 3) / 4;
	}
}
