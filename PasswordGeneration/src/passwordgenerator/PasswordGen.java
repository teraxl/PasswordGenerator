package passwordgenerator;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

public class PasswordGen {

	public static JTextArea txtAria;
	public static JScrollPane scrollpane;
	public static JPopupMenu jPopupMenu;
	public static JMenuItem itemMenu;
	public static JMenuItem itemMenuSelectAll;
	public static JLabel label01;
	public static JLabel label02;
	public static JLabel label03;
	public static JButton btnGenerate;
	public static JCheckBox chkBox;
	public static JCheckBox checkBoxPunctuation;
	public static BoxToPane boxToPane = new BoxToPane();
	public static Box boxLayout0;
	public static Box boxLayout1;
	public static Box boxLayout2;
	public static Box boxLayout3;
	public static Box boxLayout4;
	public static Box boxLayout5;
	public static JComboBox<String> combobox;
	public static JSpinner spinner = new JSpinner();
	
	
	public static void addComponentOnPane(Container pane) {
//		pane.setLayout(null);
		label01 = new JLabel("Набор символов");
		label02 = new JLabel("Длинна пароля:");
		label03 = new JLabel("символов");
		btnGenerate = new JButton("Generate");
		chkBox = new JCheckBox("Use register ");
		checkBoxPunctuation = new JCheckBox("Использовать символы");
		txtAria = new JTextArea(4, 21);
		Border border = BorderFactory.createLineBorder(Color.green);
		txtAria.setBorder(border);
		txtAria.setLineWrap(true);
		txtAria.setEditable(false);
		txtAria.setFocusable(true);
		scrollpane = new JScrollPane(txtAria);
		scrollpane.setSize(20, 20);

		if (chkBox.isSelected()) {
			txtAria.setEditable(true);
		}

		combobox = new JComboBox<>();
		combobox.addItem("[0-9]");
		combobox.addItem("[a-z]");
		combobox.addItem("[0-9] + [a-z]");
		SpinnerModel spinnerModelNumber = new SpinnerNumberModel(1, 1, null, 1);
		spinner = new JSpinner(spinnerModelNumber);
		spinner.setValue(8);
		JComponent editor = spinner.getEditor();
		JSpinner.DefaultEditor spinnerEditor = (JSpinner.DefaultEditor) editor;
		spinnerEditor.getTextField().setHorizontalAlignment(JTextField.LEFT);

		boxToPane.addBoxToPane(pane);
		
		chkBox.setSelected(true);
		checkBoxPunctuation.setSelected(true);
		combobox.setSelectedIndex(2);
		txtAria.setText(new PasswordGenerator.PasswordGeneratorBuilder().useDigits(true).useLower(true).useUpper(true)
				.usePunctuation(true).build().generate(8));

		if (combobox.getSelectedIndex() == 0) {
			chkBox.setEnabled(false);
		}

		combobox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (combobox.getSelectedIndex() == 0) {
					chkBox.setEnabled(false);
					chkBox.setSelected(false);
				} else {
					chkBox.setEnabled(true);
				}
			}
		});

		btnGenerate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int numSpinner = (int) spinner.getValue();
				PasswordGenerator passwordGenerator = new PasswordGenerator.PasswordGeneratorBuilder()
						.useDigits(combobox.getSelectedIndex() == 0 ? true
								: false || combobox.getSelectedIndex() == 2 ? true : false)
						.useLower(combobox.getSelectedIndex() == 1 ? true
								: false || combobox.getSelectedIndex() == 2 ? true : false)
						.useUpper(chkBox.isSelected() ? true : false)
						.usePunctuation(checkBoxPunctuation.isSelected() ? true : false).build();
				txtAria.setText(passwordGenerator.generate(numSpinner));
			}
		});
	}

	public static void createAndShowGUI() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame("Password Generate GUI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		addComponentOnPane(frame.getContentPane());
		frame.setSize(250, 265);
		frame.setVisible(true);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dimension.width / 2 - 136, dimension.height / 2 - 125);
		txtAria.setFocusable(true);
		scrollpane.setFocusable(true);

		// JPopapMenu
		jPopupMenu = new JPopupMenu();
		itemMenu = new JMenuItem("Copy");
		itemMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent paramActionEvent) {
				StringSelection stringToCopy = new StringSelection(txtAria.getSelectedText());
				Clipboard copyredText = Toolkit.getDefaultToolkit().getSystemClipboard();
				copyredText.setContents(stringToCopy, null);
			}
		});
		jPopupMenu.add(itemMenu);

		itemMenuSelectAll = new JMenuItem("Select ALL");
		itemMenuSelectAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent paramActionEvent) {
				// txtAria.setFocusable(true);
				txtAria.selectAll();
			}
		});
		jPopupMenu.add(itemMenuSelectAll);
		
		MouseListener mouseListener = new MouseListener() {
			
			@Override
			public void mousePressed(MouseEvent e) {
//				if (e.isPopupTrigger()) {
//					jPopupMenu.show(txtAria, e.getX(), e.getY());
//				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					jPopupMenu.show(txtAria, e.getX(), e.getY());
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {}
		};

		txtAria.addMouseListener(mouseListener);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				createAndShowGUI();
			}
		});
	}
}
