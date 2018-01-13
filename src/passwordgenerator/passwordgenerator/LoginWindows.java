package passwordgenerator;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class LoginWindows extends JFrame {

	private static final long serialVersionUID = -4120956944805659874L;
	private static JTextField field;
	private static JPasswordField passwordField;
	
	public LoginWindows() {
		super("Вход в систему");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Box box1 = Box.createHorizontalBox();
		JLabel loginLabel = new JLabel("Login: ");
		field = new JTextField(15);
		box1.add(loginLabel);
		box1.add(Box.createHorizontalStrut(6));
		box1.add(field);
		
		Box box2 = Box.createHorizontalBox();
		JLabel passwordLabel = new JLabel("Password");
		passwordField = new JPasswordField(15);
		box2.add(passwordLabel);
		box2.add(Box.createHorizontalStrut(6));
		box2.add(passwordField);
		
		Box box3 = Box.createHorizontalBox();
		JButton okButton = new JButton("OK");
		JButton cancelButton = new JButton("CANCEL");
		box3.add(Box.createHorizontalGlue());
		box3.add(okButton);
		box3.add(Box.createHorizontalStrut(1));
		box3.add(cancelButton);
		
		loginLabel.setPreferredSize(passwordLabel.getPreferredSize());
		
		Box mainBox = Box.createVerticalBox();
		mainBox.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainBox.add(box1);
		mainBox.add(Box.createHorizontalStrut(100));
		mainBox.add(box2);
		mainBox.add(Box.createHorizontalStrut(1));
		mainBox.add(box3);
		setContentPane(mainBox);
		pack();
//		setResizable(false);
		setVisible(true);
		Dimension sizeWindows = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(
				((int)sizeWindows.getWidth() / 2) - (getWidth() / 2), 
				((int)sizeWindows.getHeight() / 2) - (getHeight() / 2), 
				(int)getWidth(), 
				(int)getHeight()
				);
	}
	
	public static void main(String[] args) {
		new LoginWindows();
	}

}
