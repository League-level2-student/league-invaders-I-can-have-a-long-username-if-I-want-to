import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class LeagueInvaders extends JPanel implements ActionListener, KeyListener {

	public static final int table_width = 500; // width of the screen "table"
	public static final int table_height = 800;// height of the screen "table"
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	JFrame f;
	int currentState;
	Color tempColor;
	final Font TITLE = new Font("Comic Sans MS", Font.PLAIN, 48);
	final Font INSTRUCTIONS = new Font("Comic Sans MS", Font.PLAIN, 24);
	Ship s = new Ship(250, 700, 50, 50);
	ArrayList<Alien> a = new ArrayList<Alien>();
	ArrayList<Projectile> p = new ArrayList<Projectile>();
	int score = 0;
	// variables for computer paddle

	/*
	 * Paint all the graphics here.
	 */
	void drawMenuState(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(0, 0, table_width, table_height);
		g.setFont(TITLE);
		g.setColor(RColor());// yellow
		g.drawString("Oh no another rip off", 0, 100);// LEAGUE INVADERS
		g.setFont(INSTRUCTIONS);
		g.setColor(RColor());
		g.drawString("Enter to go in", 150, 300);
		g.setColor(RColor());
		g.drawString("Space for help", 150, 500);
		g.setColor(RColor());
		g.drawString("----------------", 150, 500);
		g.setColor(RColor());
		g.drawString("Lol no help 4 u", 150, 548);
		
	}

	void drawGameState(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, table_width, table_height);
		try {
			g.drawImage((BufferedImage) ImageIO.read(this.getClass().getResourceAsStream("space.png")),0,0,table_width,table_height,null);
		} catch (IOException e) {
		}
		g.setColor(RColor());
		g.drawString("SCORE: "+score, 0, 0);
		s.paint(g);
		for (int i = 0; i < a.size(); i++)
			a.get(i).paint(g);
		for (int i = 0; i < p.size(); i++)
			p.get(i).paint(g);

	}

	void drawEndState(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(0, 0, table_width, table_height);
		g.setFont(TITLE);
		g.setColor(RColor());// yellow
		g.drawString("RIPPPPPPPPPPPP YUO", 0, 100);// LEAGUE INVADERS
		g.setFont(INSTRUCTIONS);
		g.setColor(RColor());
		g.drawString("You OOFed " + score + " bad guys", 130, 300);
		g.setColor(RColor());
		g.drawString("Enter to try again", 150, 500);
	}

	public void paint(Graphics g) {
		super.paintComponent(g);
		switch (currentState) {
		case MENU:
			drawMenuState(g);
			break;
		case GAME:
			drawGameState(g);
			break;
		case END:
			drawEndState(g);
			break;
		default:
			break;
		}

	}// end of paint method - put code above for anything dealing with drawing -

	void updateMenuState() {
	}

	void updateGameState() {
		try {
			s.update();
			for (int i = 0; i < a.size(); i++)
				if (a.get(i).isActive) {
					a.get(i).update();
					if (new Rectangle(a.get(i).x, a.get(i).y, a.get(i).width, a.get(i).height).intersects(s.x, s.y,
							s.width, s.height)) {
						currentState++;
						a.clear();
						p.clear();
						s = new Ship(250, 700, 50, 50);
					}
				} else
					a.remove(i);
			for (int i = 0; i < p.size(); i++)
				if (p.get(i).isActive) {
					p.get(i).update();
					for (int j = 0; j < a.size(); j++) {
						if (new Rectangle(p.get(i).x, p.get(i).y, p.get(i).width, p.get(i).height)
								.intersects(new Rectangle(a.get(j).x, a.get(j).y, a.get(j).width, a.get(j).height))) {
							a.remove(j);
							p.remove(i);
							score++;
						}
					}
				} else
					p.remove(i);
			if (RNum(0, 60) == 1)
				a.add(new Alien(RNum(0, table_width - 50), 0, 50, 50));
		} catch (Exception e) {
		}
	}

	void updateEndState() {
	}

	public void update() {
		switch (currentState) {
		case MENU:
			updateMenuState();
			break;
		case GAME:
			updateGameState();
			break;
		case END:
			updateEndState();
			break;
		default:
			break;
		}

	}// end of update method - put code above for any updates on variable

	// ==================code above ===========================

	@Override
	public void actionPerformed(ActionEvent arg0) {
		update();
		repaint();
	}

	public static void main(String[] arg) {
		LeagueInvaders d = new LeagueInvaders();
	}

	public LeagueInvaders() {
		currentState = MENU;
		f = new JFrame();
		f.setTitle("SpaceInvaders Rip Off");
		f.setSize(table_width, table_height);
		f.setBackground(Color.BLACK);
		f.setResizable(false);
		f.addKeyListener(this);
		f.add(this);
		t = new Timer(17, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		tempColor = RColor();
	}

	Timer t;

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (currentState != GAME) {
				currentState++;
			}
			if (currentState > END) {
				currentState = MENU;
				score = 0;
			}
		}
		if (currentState == GAME) {
			if (e.getKeyCode() == 65) { // key a was pressed
				if (s.l == 1)
					s.l++;
				s.l = s.l * 1.1;
			}

			if (e.getKeyCode() == 68) {// key d was released
				if (s.r == 1)
					s.r++;
				s.r = s.r * 1.1;
			}

			if (e.getKeyCode() == 87) { // key w was pressed
				if (s.u == 1)
					s.u++;
				s.u = s.u * 1.1;
			}

			if (e.getKeyCode() == 83) {// key s was pressed
				if (s.d == 1)
					s.d++;
				s.d = s.d * 1.1;
			}
			if (e.getKeyCode() == 32) {
				p.add(new Projectile(s.x + s.width / 2, s.y, 10, 10));
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (currentState == GAME) {
			if (e.getKeyCode() == 65) { // key a was pressed
				s.l = 1;
			}

			if (e.getKeyCode() == 68) {// key d was releas
				s.r = 1;
			}

			if (e.getKeyCode() == 87) { // key w was pressed
				s.u = 1;
			}

			if (e.getKeyCode() == 83) {// key s was pressed
				s.d = 1;
			}
		}
		if (e.getKeyCode() == 79) {// o key pressed
		}
		if (e.getKeyCode() == 80) {// o key pressed
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	static Color RColor() {// gets a random color
		return new Color(RNum(0, 255), RNum(0, 255), RNum(0, 255));
	}

	static int RNum(int a, int b) {// generates a random number between a and b
									// using Math.random()
		// return 4; //chosen my fair die guaranteed to be random
		return (int) (Math.random() * (b - a) + a);
	}

}