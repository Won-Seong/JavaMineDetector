package mineDetector;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameOver extends JFrame{
	
	GameOver(){
		
		JLabel gameOver = new JLabel("Mine! GameOver!");
		add(gameOver);
		
		setSize(160, 120);
		setVisible(true);
		setTitle("GameOver!");
	}
	
}
