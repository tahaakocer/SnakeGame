package snakeGame;

import java.awt.Color;
import javax.swing.JLabel;

public class lblBorders extends JLabel{
	
	public	lblBorders(int x,int y,int width , int height) {	
		
		setBounds(x, y, width, height);	
		setBackground(new Color(92, 100, 153));	
		setOpaque(true);
		setText(null);
		
		
	}
}
