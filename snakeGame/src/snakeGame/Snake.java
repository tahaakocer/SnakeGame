package snakeGame;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.util.Date;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Snake {

	Point p = new Point();
	public int Width = 30;
	public int Location = 200;
	public int Speed = 30;
	public boolean right;
	public boolean left;
	public boolean up;
	public boolean down;
	private Date dt;

	public JLabel Create() {

		JLabel snk = new JLabel();
		snk.setText(null);
		//snk.setBackground(new Color(126, 20, 156));
		snk.setOpaque(false);
		Image img = new ImageIcon(this.getClass().getResource("Snake.png")).getImage();
		img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		snk.setIcon(new ImageIcon(img));
		return snk;
	}

	private void GameOver(JPanel panel,ArrayList<JLabel> List,JLabel snakeHead,Timer timer,Timer timer2,TimerTask timerTask,TimerTask timerTask2) {
		String name = JOptionPane.showInputDialog(panel, "Skorunuz: " + snakeGame.score + "\nAdınızı giriniz: ", "GAMEOVER",JOptionPane.OK_OPTION);
		dt = new Date();
		SqlConnection.skorYaz(name,snakeGame.score, dt);
		timer.cancel();
		timer2.cancel();
		List.clear();
		snakeGame.score = 0;
		for(JLabel item : List) {
			panel.remove(item);
		}
		snakeHead = Create();
	//	panel.remove(food);
		List.add(snakeHead);
		snakeHead.setBounds(Location,Location,Width,Width);
		panel.add(snakeHead);
	//	timer.schedule(timerTask, 0, 250);
	//	timer2.schedule(timerTask2, 0, 100);
		System.exit(1);
	}

	public void Grow(ArrayList<JLabel> List) {

		for (int i = List.size() - 1; i > 0; i--) { // kuyruk takibi
			p.setLocation(List.get(i - 1).getLocation());
			List.get(i).setLocation(p);
		}
	}

	public void Impact(JPanel panel,ArrayList<JLabel> List,JLabel snakeHead,Timer timer,Timer timer2,TimerTask timerTask,TimerTask timerTask2) {
		if (snakeHead.getX() == 20 || snakeHead.getX() == 500 ||
				snakeHead.getY() == 20 || snakeHead.getY() == 500) {
			GameOver(panel,List,snakeHead,timer,timer2,timerTask,timerTask2);

		}
		for (int j = 2; j < List.size(); j++) {
			if (snakeHead.getBounds().equals(List.get(j).getBounds())) {
				GameOver(panel,List,snakeHead,timer,timer2,timerTask,timerTask2);
			}
		}
	}

	public void GoRight() {
		if (!left) {
			right = true;
			left = false;
			up = false;
			down = false;
		}
	}

	public void GoLeft() {
		if (!right) {
			right = false;
			left = true;
			up = false;
			down = false;
		}
	}

	public void GoUp() {
		if (!down) {
			right = false;
			left = false;
			up = true;
			down = false;
		}
	}

	public void GoDown() {
		if (!up) {
			right = false;
			left = false;
			up = false;
			down = true;
		}
	}
}
