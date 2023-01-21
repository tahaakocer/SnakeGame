package snakeGame;

import java.awt.Color;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Food extends JLabel{

	private int width = 30;
	public int rx,ry;
	public Food() {
		
		setBounds(50,50,width,width);
	//	setBackground(Color.RED);
		setOpaque(false);
		setText(null);
		
		Image img = new ImageIcon(this.getClass().getResource("elmason.png")).getImage();
		img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		setIcon(new ImageIcon(img));
	}
	
	public void randomLocation() {
		
		Random r = new Random();
		rx = (r.nextInt(14)+1)*30+50;
		ry = (r.nextInt(14)+1)*30+50;
		super.setBounds(rx, ry, width, width);
	}
}
