package mineDetector;

import java.awt.Button;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayer;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class GameBoard extends JPanel {

	int x_board, y_board;
	int button_num;
	int mineNum;
	Vector<mineButton> button;
	boolean isOver;

	MouseAdapter adapterForButton;

	GameBoard() {

		reset();

	}

	public void reset() {
		isOver = true;
		x_board = 0;
		y_board = 0;
		button_num = 0;
		mineNum = 0;
		try {
			button.removeAllElements();
		} catch (NullPointerException e) {
			System.out.println("bad!");
		}
		removeAll();

		switch (Main.level) {

		case ELEMENTARY:
			x_board = 10;
			y_board = 10;
			mineNum = 10;
			break;
		case INTERMEDIATE:
			x_board = 20;
			y_board = 10;
			mineNum = 30;
			break;
		case ADVANCED:
			x_board = 30;
			y_board = 20;
			mineNum = 50;
			break;
		default:
			break;
		}

		button_num = x_board * y_board;
		setLayout(new GridLayout(x_board, y_board));

		button = new Vector<>(button_num);
		for (int i = 0; i < button.capacity(); i++)
			button.add(new mineButton(""));
		for (mineButton but : button) {
			but.addMouseListener(adapterForButton = new MouseAdapter() {

				public void mousePressed(MouseEvent e) {
					if (e.getButton() == 1) {
						if (isOver == false) {
							if (but.isMine == true) {

								but.setBackground(new Color(255, 0, 0));// Red
								new GameOver();

								for (mineButton but2 : button) {
									but2.setText("!");
									but2.isMine = false;
									but2.mineDetectNum = 0;
								}
								isOver = true;

							} else {
								but.setText(String.valueOf(but.mineDetectNum));
								if (but.isDetected == false) {
									but.isDetected = true;
									mineButton.detectedButtonNumber++;
								}
								if (mineButton.detectedButtonNumber == (button_num - mineNum) + 1) {
									new Win();
									isOver = true;
								}

							}
						} else {
							new ReStart();
						}
					} else if (e.getButton() == 3) {
						if (but.isDetected == false) {
							if (but.forMouseRight == false) {
								but.setBackground(new Color(0, 0, 255));
								but.forMouseRight = true;
								mineNum--;
							} else if (but.forMouseRight == true) {
								but.setBackground(new Button().getBackground());
								but.forMouseRight = false;
								mineNum++;
							}
						}

					}

				}

			});
			add(but);
		} // Button construct

	}

	public void mineDetectNum() {

		Vector<Integer> forCount = new Vector<>(8);
		Vector<Integer> forCountForZero = new Vector<>(5);
		Vector<Integer> forCountForNine = new Vector<>(5);

		forCount.add(-y_board - 1);
		forCount.add(-y_board);
		forCount.add(-y_board + 1);
		forCount.add(-1);
		forCount.add(1);
		forCount.add(y_board - 1);
		forCount.add(y_board);
		forCount.add(y_board + 1);

		forCountForZero.add(-y_board);
		forCountForZero.add(-y_board + 1);
		forCountForZero.add(1);
		forCountForZero.add(y_board);
		forCountForZero.add(y_board + 1);

		forCountForNine.add(-y_board - 1);
		forCountForNine.add(-y_board);
		forCountForNine.add(-1);
		forCountForNine.add(y_board - 1);
		forCountForNine.add(y_board);

		for (int i = 0; i < button.size(); i++) {

//			if (button.elementAt(i).isMine == true)
//				button.elementAt(i).setBackground(new Color(0, 0, 0));// For debug!!! ,Green color

			if ((i % y_board) == 0) {
				for (int itr : forCountForZero) {
					try {
						if (button.elementAt(i + itr).isMine == true)
							button.elementAt(i).mineDetectNum++;
					} catch (Exception e) {

					}
				}
			} else if ((i % y_board) == y_board - 1) {
				for (int itr : forCountForNine) {
					try {
						if (button.elementAt(i + itr).isMine == true)
							button.elementAt(i).mineDetectNum++;
					} catch (Exception e) {

					}
				}
			} else {
				for (int itr : forCount) {
					try {
						if (button.elementAt(i + itr).isMine == true)
							button.elementAt(i).mineDetectNum++;
					} catch (Exception e) {

					}
				}
			}

		}

	}

}
