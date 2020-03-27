import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class GenericThing {
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	public boolean isActive = true;
	protected int vx = 0;
	protected int vy = 0;
	protected int speed = 0;
	public BufferedImage image;
	public boolean needImage = true;
	public boolean gotImage = false;

	public GenericThing(int x, int y, int width, int height) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public GenericThing(int x, int y, int width, int height, boolean isActive) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.isActive = isActive;
	}

	public GenericThing(int x, int y, int width, int height, boolean isActive, int vx, int vy) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.isActive = isActive;
		this.vx = vx;
		this.vy = vy;
	}

	public void update() {
	}

	public int getVx() {
		return vx;
	}

	public void setVx(int vx) {
		this.vx = vx;
	}

	public int getVy() {
		return vy;
	}

	public void setVy(int vy) {
		this.vy = vy;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	void loadImage(String imageFile) {
		try {
			image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
			gotImage = true;
		} catch (Exception e) {

		}
		needImage = false;

	}
}
