package passwordgenerator;

import java.awt.Component;
import java.awt.Container;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;



public class ExampleBox {
	
	public static JButton button1;
	public static JButton button2;
	public static JButton button3;
	public static JButton button4;
	public static JButton button5;
	public static Box vBox;
	public static Box hBox;
	
	public static void addComponentToPane(Container pane) {
		button1 = new JButton("Button 1");
		button2 = new JButton("Button 2");
		button3 = new JButton("Button 3");
		button4 = new JButton("Button 4");
		button5 = new JButton("Button 5");
		vBox = Box.createVerticalBox();
		hBox = Box.createHorizontalBox();
		
		hBox.add(button1);
		hBox.add(Box.createHorizontalStrut(4));
		hBox.add(button2);
		pane.add(hBox);
		pane.getPreferredSize();
	}
	
	public static void createAndShowGUI() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame("Example");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setResizable(false);
		frame.setSize(200, 200);
		addComponentToPane(frame.getContentPane());
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				createAndShowGUI();
			}
		});
	}
}
