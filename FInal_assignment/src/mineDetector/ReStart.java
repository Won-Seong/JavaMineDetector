package mineDetector;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ReStart extends JFrame {

	ReStart(){
		add(new JLabel("Please start new Game!"));
		
		setSize(160, 120);
		setVisible(true);
		setTitle("Start");
	}
}
