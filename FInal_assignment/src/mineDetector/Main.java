package mineDetector;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;

public class Main extends JFrame {

	public static int count;
	Timer timer;
	TimerTask timerTask;
	GameBoard gameboard;
	Menu menu;
	UpperUI ui;
	static Level level = Level.ELEMENTARY;
	BorderLayout layout;

	Main() {
		//12141508 Kim Seong Won
		gameboard = new GameBoard();
		menu = new Menu(gameboard);
		ui = new UpperUI();
		layout = new BorderLayout();
		setLayout(layout);

		menu.gameMenuListener[0] = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				gameboard.reset();
				mineButton.detectedButtonNumber = 0;
				try {
					timer.cancel();
				}catch(Exception error) {
					
				}
				gameboard.isOver = false;
				for (int i = 0; i < gameboard.button_num; i++) {

					gameboard.button.elementAt(i).setText(String.valueOf(""));

				}

				for (int itr : randomNumber(gameboard.button_num, gameboard.mineNum))
					gameboard.button.elementAt(itr).isMine = true;

				gameboard.mineDetectNum();

//				for (mineButton itr : gameboard.button)
//					itr.setText(String.valueOf(itr.mineDetectNum));//For debug!!!

				ui.removeAll();
				ui.add(new JLabel("Total mine number : " + String.valueOf(gameboard.mineNum)));
				count = 0;
				JLabel labelForTimer = new JLabel("\tTimer : " + count);
				ui.add(labelForTimer);
				
				timer = new Timer();
				timerTask = new TimerTask() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						labelForTimer.setText("\tTimer : " + count);
						count++;

					}
				};

				timer.schedule(timerTask, 0, 1200);

				switch (level) {
				case ELEMENTARY:
					setSize(600, 480);
					break;
				case INTERMEDIATE:
					setSize(840, 540);
					break;
				case ADVANCED:
					setSize(1080, 600);
					break;
				default:
					break;
				}

			}
		};
		menu.gameMenuListener[1] = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new LevelSetting();
			}
		};
		menu.gameMenuListener[2] = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		};

		menu.gameItem[0].addActionListener(menu.gameMenuListener[0]);
		menu.gameItem[1].addActionListener(menu.gameMenuListener[1]);
		menu.gameItem[2].addActionListener(menu.gameMenuListener[2]);

		menu.gameMenu.add(menu.gameItem[0]);
		menu.gameMenu.add(menu.gameItem[1]);
		menu.gameMenu.add(menu.gameItem[2]);

		add(BorderLayout.NORTH, ui);
		add(BorderLayout.CENTER, gameboard);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(480, 480);
		setVisible(true);
		setTitle("Mine Detector");
		setJMenuBar(menu);
	}

	public static Vector<Integer> randomNumber(int boardSize, int mineNum) {

		Vector<Integer> tempVec = new Vector<>(boardSize);
		Vector<Integer> vecForReturn = new Vector<>(mineNum);
		for (int i = 0; i < tempVec.capacity(); i++)
			tempVec.add(i);

		for (int i = 0; i < mineNum - 1; i++) {
			vecForReturn.add(tempVec.remove((int) (Math.random() * tempVec.size())));
		}

		return vecForReturn;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new Main();

	}

}
