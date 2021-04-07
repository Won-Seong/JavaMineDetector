package mineDetector;

import javax.swing.JButton;

public class mineButton extends JButton{

	boolean isMine;
	boolean isDetected;
	boolean forMouseRight;
	public static int detectedButtonNumber = 0;
	
	int mineDetectNum;
	
	mineButton(String str){
		super(str);
		forMouseRight = false;
		isDetected = false;
		isMine = false;
		mineDetectNum = 0;
	
	}
	
	
}
