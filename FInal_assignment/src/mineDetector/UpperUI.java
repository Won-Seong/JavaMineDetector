package mineDetector;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UpperUI extends JPanel {

	UpperUI() {
		setLayout(new FlowLayout(3));
		JLabel please = new JLabel("Please press game start menu button");
		add(please);
		
		
		

	}

}
