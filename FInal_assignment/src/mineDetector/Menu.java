package mineDetector;

import javax.swing.JMenuBar;
import java.util.Vector;
import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Menu extends JMenuBar {

	JMenu gameMenu;
	JMenu fileMenu;
	JMenu helpMenu;

	JMenuItem gameItem[];
	JMenuItem fileItem[];

	ActionListener gameMenuListener[];

	Menu(GameBoard gameboard) {

		gameMenu = new JMenu("Game");
		gameItem = new JMenuItem[3];
		gameMenuListener = new ActionListener[3];

		gameItem[0] = new JMenuItem("start");
		gameItem[1] = new JMenuItem("Level");
		gameItem[2] = new JMenuItem("Exit");

		gameItem[0].addActionListener(gameMenuListener[0]);
		gameItem[1].addActionListener(gameMenuListener[1]);

		gameMenu.add(gameItem[0]);
		gameMenu.add(gameItem[1]);
		gameMenu.add(gameItem[2]);

		fileMenu = new JMenu("File");
		fileItem = new JMenuItem[2];

		fileItem[0] = new JMenuItem("Save");
		fileItem[1] = new JMenuItem("Load");

		ActionListener saveListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if (gameboard.isOver == true) {
					new ReStart();
					return;
				}

				try {
					FileOutputStream fileOutStream = new FileOutputStream("D://number_data.dat");

					fileOutStream.write(gameboard.button_num);

					for (mineButton itr : gameboard.button) {
						fileOutStream.write(itr.mineDetectNum);

					}
					for (mineButton itr : gameboard.button) {
						if (itr.isMine == true)
							fileOutStream.write(1);
						else
							fileOutStream.write(0);
					}
					for (mineButton itr : gameboard.button) {
						if (itr.forMouseRight == true)
							fileOutStream.write(1);
						else
							fileOutStream.write(0);
					}
					for (mineButton itr : gameboard.button) {
						if (itr.isDetected == true)
							fileOutStream.write(1);
						else
							fileOutStream.write(0);
					}
					System.out.println("Save!");
				} catch (IOException error) {
					System.out.println("can't save!");

				}
			};
		};

		ActionListener loadListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Vector<Integer> vecForDetectNum = new Vector<>(gameboard.button_num);
				Vector<Integer> vecForIsMine = new Vector<>(gameboard.button_num);
				Vector<Integer> vecForMouseRight = new Vector<>(gameboard.button_num);
				Vector<Integer> vecIsDetected = new Vector<>(gameboard.button_num);

				try {
					FileInputStream fileInStream = new FileInputStream("D://number_data.dat");

					int butNum = fileInStream.read();
					if (butNum == 88)
						butNum = 600;
					if (gameboard.button_num != butNum) {
						System.out.println("Can't load! please check level!");

						fileInStream.close();
						return;
					}

					for (int i = 0; i < gameboard.button_num; i++)
						vecForDetectNum.add(fileInStream.read());
					for (int i = 0; i < gameboard.button_num; i++)
						vecForIsMine.add(fileInStream.read());
					for (int i = 0; i < gameboard.button_num; i++) {
						vecForMouseRight.add(fileInStream.read());
					}
					for (int i = 0; i < gameboard.button_num; i++) {
						vecIsDetected.add(fileInStream.read());
					}

					for (int i = 0; i < gameboard.button_num; i++) {
						gameboard.button.elementAt(i).mineDetectNum = vecForDetectNum.remove(0);
						

						if (vecForIsMine.elementAt(0) == 1)
							gameboard.button.elementAt(i).isMine = true;
						else
							gameboard.button.elementAt(i).isMine = false;
						vecForIsMine.remove(0);

						if (vecForMouseRight.elementAt(0) == 1) {
							gameboard.button.elementAt(i).forMouseRight = true;
							gameboard.button.elementAt(i).setBackground(new Color(0,0,255));
						} else {
							gameboard.button.elementAt(i).forMouseRight = false;
							gameboard.button.elementAt(i).setBackground(new Button().getBackground());
						}
						vecForMouseRight.remove(0);
						
						if (vecIsDetected.elementAt(0) == 1) {
							gameboard.button.elementAt(i).isDetected = true;
							gameboard.button.elementAt(i)
							.setText(String.valueOf(gameboard.button.elementAt(i).mineDetectNum));
						}
						else {
							gameboard.button.elementAt(i).isDetected = false;
							gameboard.button.elementAt(i)
							.setText(String.valueOf(""));
						}
						vecIsDetected.remove(0);
						
						

					}

					System.out.println("Load!");
				} catch (IOException error) {
					System.out.println("can't load!");
				} catch (Exception error) {
					System.out.println("can't load!");
				}

			}
		};

		fileItem[0].addActionListener(saveListener);
		fileItem[1].addActionListener(loadListener);

		fileMenu.add(fileItem[0]);
		fileMenu.add(fileItem[1]);

		helpMenu = new JMenu("Help");

		JMenuItem helpItem = new JMenuItem("Help");
		helpMenu.add(helpItem);

		add(gameMenu);
		add(fileMenu);
		add(helpMenu);

	}

};
