package mineDetector;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.sun.xml.internal.org.jvnet.fastinfoset.stax.LowLevelFastInfosetStreamWriter;

public class LevelSetting extends JFrame {

	Vector<JButton> levelButtonVector;

	public LevelSetting() {
		// TODO Auto-generated constructor stub

		levelButtonVector = new Vector<>(3);

		levelButtonVector.add(new JButton("Elementary"));
		levelButtonVector.add(new JButton("Intermediate"));
		levelButtonVector.add(new JButton("Advanced"));

		levelButtonVector.elementAt(0).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
			
				Main.level = Level.ELEMENTARY;
				System.out.println("changed level to elementary");
			}
		});
		levelButtonVector.elementAt(1).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				Main.level = Level.INTERMEDIATE;
				System.out.println("changed level to intermediate");
			}
		});
		levelButtonVector.elementAt(2).addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				Main.level = Level.ADVANCED;
				System.out.println("changed level to advanced");
			}
		});
		
		for(JButton itr : levelButtonVector)
			add(itr);
		
		JLabel explain = new JLabel("레벨 설정은 게임을 재시작해야 적용됩니다.");
		add(explain);
		

		setSize(330, 100);
		setVisible(true);
		setTitle("Level Setting");
		setLayout(new FlowLayout(3));

	}

};
