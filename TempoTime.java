/*Programmer: Ryan Dubeau
 * Date: 11/6/2020
 * Purpose: To code a game for my final project
 */
import javax.swing.*;



import java.awt.*;
import java.awt.event.*;
import java.util.EventListener;

import java.util.Random;

public class TempoTime {
	/**
	 * MAIN METHOD
	 * This main method starts the GUI and runs the createMainWindow() method.
	 * This method should not be changed.
	 */

	public static void main (String [] args) {
		javax.swing.SwingUtilities.invokeLater (new Runnable () {
			public void run () {
				createTempoTimeWindow ();

			}
		});
	}

	/** Create lots of variables */
	static JLayeredPane contentPane;
	static Random randomGenerator = new Random();

	static JButton newGameButton;
	static JButton quitGameButton;
	static JLabel score;
	static int scoreLabel;
	static JLayeredPane gamePanel;
	static JLabel arrowUp;
	static JLabel arrowRight;
	static JLabel arrowLeft;
	static JLabel arrowDown;
	static final int MOVE_SPEED = 2200;
	static int speed = MOVE_SPEED;
	static JLabel lightUp ;
	static Timer actualMovement;
	static int arrowX = randomGenerator.nextInt(1750);
	static int arrowY = randomGenerator.nextInt(1010);
	private static void createTempoTimeWindow() {



		//Create and set up the frame
		JFrame frame = new JFrame("Tempo Time");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable (true);


		//Create the score and scoreLabel
		score = new JLabel ("Score: ");
		score.setLocation(1800,950);
		score.setSize(1000, 35);
		score.setFont(new Font("Bree Serif", 1, 23 ));
		scoreLabel = 0;
		score.setText(String.valueOf(scoreLabel));


		//Create the contentPane and title
		contentPane = new JLayeredPane();
		contentPane.setPreferredSize(new Dimension(300,310));
		contentPane.setBackground(Color.GRAY);
		contentPane.setOpaque(true);


		JLabel title = new JLabel("Tempo Time");
		title.setSize(1000,40);
		title.setLocation(900,0);
		title.setFont(new Font("Bree Serif", 1, 23));
		title.setForeground(Color.RED);


		//Create the instructions
		JLabel instructions = new JLabel(new ImageIcon("Instructions:"));
		instructions.setFont(new Font ("Comic Sans MS", 4, 15));
		instructions.setSize(1000,100);
		instructions.setLocation(1500,65);


		arrowUp = CreateScaledImage("arrow-1.png", 40, 40);
		arrowUp.setSize(40,40);
		arrowUp.setVisible(false);
		contentPane.setLayer(arrowUp, 1);
		contentPane.add(arrowUp);
		changeArrowUpLocation();

		arrowRight = CreateScaledImage("arrow-2.png" , 40, 40);
		arrowRight.setSize(40,40);
		arrowRight.setVisible(false);
		contentPane.setLayer(arrowRight, 1);
		contentPane.add(arrowRight);
		changeArrowRightLocation();

		arrowLeft = CreateScaledImage("arrow-left.png", 40, 40);
		arrowLeft.setSize(40,40);
		arrowLeft.setVisible(false);
		contentPane.setLayer(arrowLeft, 1);
		contentPane.add(arrowLeft);
		changeArrowLeftLocation();

		arrowDown = CreateScaledImage("arrow-down.png", 40, 40);
		arrowDown.setSize(40, 40);
		arrowDown.setVisible(false);
		contentPane.setLayer(arrowDown, 1);
		contentPane.add(arrowDown);
		changeArrowDownLocation();

		//Create the buttons
		newGameButton = new JButton("New Game");
		newGameButton.setSize(100,30);
		newGameButton.setLocation(1800,0);

		quitGameButton = new JButton("Quit");
		quitGameButton.setSize(100,30);
		quitGameButton.setLocation(1800, 35);
		quitGameButton.addActionListener(new QuitGameButtonListener());

		//Add everything to the contentPane and frame
		arrowUp.setVisible(false);
		arrowDown.setVisible(false);
		arrowLeft.setVisible(false);
		arrowRight.setVisible(false);
		contentPane.add(title);
		contentPane.add(instructions);
		contentPane.add(newGameButton);
		contentPane.add(quitGameButton);
		contentPane.add(score);
		scoreLabel = 0;
		ArrowListener listener = new ArrowListener();
		newGameButton.addActionListener(new NewGameButtonListener());
		frame.setContentPane(contentPane);
		contentPane.addKeyListener(listener);
		contentPane.requestFocus();	
		contentPane.setPreferredSize(new Dimension(2000,1050));
		
		actualMovement = new Timer(MOVE_SPEED, new AnimationTimerHandler());
		actualMovement.setRepeats(true);
		actualMovement.start();

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	

		/**  */

		
		/**Create the new game button listener method*/

	}

	
	public static class AnimationTimerHandler implements ActionListener {

		public void actionPerformed (ActionEvent event) {
			
			if (actualMovement.isRunning()) {
			
				arrowUp.setForeground(Color.YELLOW);
				arrowRight.setForeground(Color.YELLOW);
				arrowLeft.setForeground(Color.YELLOW);
				arrowDown.setForeground(Color.YELLOW);
				changeArrowUpLocation();
				changeArrowRightLocation();
				changeArrowLeftLocation();
				changeArrowDownLocation();
				


			}
		}

	}
	public static class NewGameButtonListener implements ActionListener {

		public void actionPerformed (ActionEvent event) {
			changeArrowUpLocation();
			changeArrowRightLocation();
			changeArrowLeftLocation();
			changeArrowDownLocation();
			scoreLabel = 0;
			score.setText(String.valueOf(scoreLabel));
			contentPane.requestFocus();


		}
	}



	public static class QuitGameButtonListener implements ActionListener {

		public void actionPerformed (ActionEvent event) {
			arrowUp.setVisible(false);
			arrowDown.setVisible(false);
			arrowLeft.setVisible(false);
			arrowRight.setVisible(false);
			scoreLabel = 0;
			score.setText(String.valueOf(scoreLabel));
			System.exit(0);
			
		}
	
	}

	public static JLabel CreateScaledImage (String filename, int width, int height) {
		Image originalImage = new ImageIcon(filename).getImage();
		Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new JLabel(new ImageIcon(scaledImage));

	}

	/**Create the arrow listener  */

	public static class ArrowListener implements KeyListener {

		public void keyTyped (KeyEvent event) {

		}

		public void keyPressed (KeyEvent event) {

			if (event.getKeyCode() == KeyEvent.VK_UP) {
				arrowUp.setVisible(false);
				scoreLabel++;
				score.setText(String.valueOf(scoreLabel));
			}
			else if(event.getKeyCode() == KeyEvent.VK_DOWN) {
				arrowDown.setVisible(false);
				scoreLabel++;
				score.setText(String.valueOf(scoreLabel));
			}
			else if(event.getKeyCode() == KeyEvent.VK_LEFT) {
				arrowLeft.setVisible(false);
				scoreLabel++;
				score.setText(String.valueOf(scoreLabel));
			}
			else if(event.getKeyCode() == KeyEvent.VK_RIGHT) {
				arrowRight.setVisible(false);
				scoreLabel++;
				score.setText(String.valueOf(scoreLabel));
			}

		}

		public void keyReleased (KeyEvent event) {

		}
	}

	public static void changeArrowUpLocation () {
		int arrowX = randomGenerator.nextInt(1750);
		int arrowY = randomGenerator.nextInt(1000);
		arrowUp.setLocation(arrowX, arrowY);
		arrowUp.setVisible(true);
	}

	public static void changeArrowRightLocation () {
		int arrowX = randomGenerator.nextInt(1750);
		int arrowY = randomGenerator.nextInt(1000);
		arrowRight.setLocation(arrowX, arrowY);
		arrowRight.setVisible(true);
	}

	public static void changeArrowLeftLocation () {
		int arrowX = randomGenerator.nextInt(1750);
		int arrowY = randomGenerator.nextInt(1000);
		arrowLeft.setLocation(arrowX, arrowY);
		arrowLeft.setVisible(true);

	}

	public static void changeArrowDownLocation () {
		int arrowX = randomGenerator.nextInt(1750);
		int arrowY = randomGenerator.nextInt(1000);
		arrowDown.setLocation(arrowX, arrowY);
		arrowDown.setVisible(true);
		
	}
}