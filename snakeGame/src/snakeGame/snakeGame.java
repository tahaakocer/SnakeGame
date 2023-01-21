package snakeGame;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Time;
import javax.swing.JButton;
import java.awt.Window.Type;
import java.awt.Font;
import java.awt.Point;

import javax.swing.SwingConstants;

public class snakeGame extends JFrame {

	Snake snake = new Snake();
	Food food = new Food();
	JLabel snakeHead = new JLabel();
	ArrayList<JLabel> snakeList = new ArrayList<>();
	private JPanel contentPane;
	private ArrayList<lblBorders> border = new ArrayList<lblBorders>();
	Point p = new Point();
	Timer timerFood = new Timer();
	Timer timerSnake = new Timer();
	TimerTask timerTask;
	TimerTask timerTask2;

	int x, y; // TİMER RUN İÇİNDE
	static Integer score = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					snakeGame frame = new snakeGame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/*
	 * Array List Temizle 
	 * Skoru Temizle 
	 * timer durdur 
	 * yeni kafa oluştur 80. listeye
	 * panele ekle timer başlat
	 *
	 */
	/**
	 * Create the frame.
	 */
	public snakeGame() {

		setTitle("Snake Game");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 566, 589);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(160, 217, 180));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		snakeHead = snake.Create();

		for (int i = 0; i < 4; i++) {
			if (i == 0)
				border.add(new lblBorders(0, 50, 50, 500));
			if (i == 1)
				border.add(new lblBorders(500, 0, 50, 500));
			if (i == 2)
				border.add(new lblBorders(50, 500, 500, 50));
			if (i == 3)
				border.add(new lblBorders(0, 0, 500, 50));
			contentPane.add(border.get(i));
		}
		
		snakeList.add(snakeHead);
		snakeList.add(snake.Create());
		snakeHead.setBounds(200, 200, 30, 30);
		contentPane.add(snakeHead);	
		contentPane.add(snakeList.get(1));


		// -------------------YILAN İÇİN TİMER------------------
		food.randomLocation();
		contentPane.add(food);

		timerTask = new TimerTask() {

			@Override
			public void run() {

				x = snakeHead.getX();
				y = snakeHead.getY();
				if (snake.right) {
					x += snake.Speed;
					snakeHead.setBounds(x, y, snake.Width, snake.Width);
				} else if (snake.left) {
					x -= snake.Speed;
					snakeHead.setBounds(x, y, snake.Width, snake.Width);
				} else if (snake.up) {
					y -= snake.Speed;
					snakeHead.setBounds(x, y, snake.Width, snake.Width);
				} else if (snake.down) {
					y += snake.Speed;
					snakeHead.setBounds(x, y, snake.Width, snake.Width);
				}
				snake.Impact(contentPane, snakeList,snakeHead, timerSnake, timerFood, timerTask, timerTask2);
				snake.Grow(snakeList);

			}
		};
		timerSnake.schedule(timerTask, 0, 125);

		JLabel lblScore = new JLabel("0", SwingConstants.CENTER);
		lblScore.setFont(new Font("Tahoma", Font.PLAIN, 250));
		lblScore.setBounds(69, 146, 407, 242);
		contentPane.add(lblScore);
		// ----------------- YEM İÇİN TİMER -----------------

		// Random rand = new Random();

		timerTask2 = new TimerTask() {
			@Override
			public void run() {
				if (snakeHead.getBounds().equals(food.getBounds())) {
					for(JLabel item : snakeList) {
						while(food.getBounds().equals(item.getBounds())) {
							food.randomLocation();
						}
					}					
					score++;
					lblScore.setText(score.toString());
					snakeList.add(snake.Create());
					snakeList.add(snake.Create());
					snakeList.get(score).setBounds(-30, -30, 30, 30);
					snakeList.get(score).setBounds(-30, -30, 30, 30);

					contentPane.add(snakeList.get(score));

				}
			}

		};

		timerFood.schedule(timerTask2, 0, 100);

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int y = snakeList.get(0).getY() - snake.Speed;
				if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
					snake.GoUp();
					try {
						Thread.sleep(100);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
					snake.GoDown();
					try {
						Thread.sleep(100);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
					snake.GoLeft();
					try {
						Thread.sleep(100);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
					snake.GoRight();
					try {
						Thread.sleep(100);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
	//	java.awt.Container.getComponentZOrder();

		setContentPane(contentPane);
		contentPane.setLayout(null);

	}
}
