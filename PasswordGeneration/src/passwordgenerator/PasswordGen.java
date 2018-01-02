package passwordgenerator;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
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
	
	public static void addComponentOnPane(Container pane) {
		pane.setLayout(null);
		JLabel label01 = new JLabel("Набор символов");
		JLabel label02 = new JLabel("Длинна пароля:");
		JLabel label03 = new JLabel("символов");
		JButton btnGenerate = new JButton("Generate");
		JCheckBox chkBox = new JCheckBox("Use register ");
		JCheckBox checkBoxPunctuation = new JCheckBox("Использовать символы");
		txtAria = new JTextArea(4, 21);
		Border border = BorderFactory.createLineBorder(Color.green);
		txtAria.setBorder(border);
		txtAria.setLineWrap(true);
		txtAria.setEditable(false);
		scrollpane = new JScrollPane(txtAria);
		scrollpane.setSize(20, 20);

		if (chkBox.isSelected()) {
			txtAria.setEditable(true);
		}
		
		JComboBox<String> combobox = new JComboBox<>();
		combobox.addItem("[0-9]");
		combobox.addItem("[a-z]");
		combobox.addItem("[0-9] + [a-z]");
		SpinnerModel spinnerModelNumber = new SpinnerNumberModel(1, 1, null, 1);
		JSpinner spinner = new JSpinner(spinnerModelNumber);
		spinner.setValue(8);
		JComponent editor = spinner.getEditor();
		JSpinner.DefaultEditor spinnerEditor = (JSpinner.DefaultEditor) editor;
		spinnerEditor.getTextField().setHorizontalAlignment(JTextField.LEFT);
		
		Insets insest = pane.getInsets();
		Dimension size = label01.getPreferredSize();
		Dimension sizeComboBox = combobox.getPreferredSize();
		Dimension sizeLabel02 = label02.getPreferredSize();
		Dimension sizeSpinner = spinner.getPreferredSize();
		Dimension sizeLabel03 = label03.getPreferredSize();
		Dimension chkBoxSize = chkBox.getPreferredSize();
		Dimension txtAriaSize = txtAria.getPreferredSize();
		Dimension btnGenSize = btnGenerate.getPreferredSize();
		Dimension chkBoxPunctuation = checkBoxPunctuation.getPreferredSize();
		Dimension scrollPaneSize = scrollpane.getPreferredSize();
		label01.setBounds(5, 5, size.width, size.height);
		combobox.setBounds(5, 25, sizeComboBox.width + 145, sizeComboBox.height);
		label02.setBounds(5, (int)(insest.top + sizeLabel02.getHeight() + 43), sizeLabel02.width, sizeLabel02.height);
		spinner.setBounds((int)(sizeLabel02.getWidth() + 10), 54, sizeSpinner.width + 30, sizeSpinner.height);
		label03.setBounds((int)(sizeLabel02.getWidth() + sizeLabel03.width + 10), sizeLabel03.height + 44, sizeLabel03.width, sizeLabel03.height);
		chkBox.setBounds(35, 80, chkBoxSize.width, chkBoxSize.height);
		checkBoxPunctuation.setBounds(35, 100, chkBoxPunctuation.width, chkBoxPunctuation.height);
		scrollpane.setBounds(5, 125, scrollPaneSize.width - 5, scrollPaneSize.height);
		txtAria.setBounds(5, 125, txtAriaSize.width - 2, txtAriaSize.height);
		btnGenerate.setBounds(120, 190, btnGenSize.width + 20 - 3, btnGenSize.height);
		
		pane.add(label01);
		pane.add(combobox);
		pane.add(label02);
		pane.add(spinner);
		pane.add(label03);
		pane.add(chkBox);
		pane.add(checkBoxPunctuation);
		pane.add(scrollpane);
		pane.add(btnGenerate);
		
		chkBox.setSelected(true);
		checkBoxPunctuation.setSelected(true);
		combobox.setSelectedIndex(2);
		txtAria.setText(new PasswordGenerator.PasswordGeneratorBuilder()
				.useDigits(true)
				.useLower(true)
				.useUpper(true)
				.usePunctuation(true)
				.build()
				.generate(8));
		
		if(combobox.getSelectedIndex() == 0) {
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
				int numSpinner = (int)spinner.getValue();
				PasswordGenerator passwordGenerator = new PasswordGenerator.PasswordGeneratorBuilder()
						.useDigits(combobox.getSelectedIndex() == 0 ? true : false || combobox.getSelectedIndex() == 2 ? true : false)
						.useLower(combobox.getSelectedIndex() == 1 ? true : false || combobox.getSelectedIndex() == 2 ? true : false)
						.useUpper(chkBox.isSelected() ? true : false)
						.usePunctuation(checkBoxPunctuation.isSelected() ? true : false)
						.build();
				txtAria.setText(passwordGenerator.generate(numSpinner));
			}
		});
	}

	public static void createAndShowGUI() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame("Генератор паролей");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		addComponentOnPane(frame.getContentPane());
		frame.setSize(272, 250);
		frame.setVisible(true);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dimension.width / 2 - 136, dimension.height / 2 - 125);
		
		// JPopapMenu
		jPopupMenu = new JPopupMenu();
		itemMenu = new JMenuItem("Копировать");
		itemMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent paramActionEvent) {
				StringSelection stringToCopy = new StringSelection(txtAria.getSelectedText());
				Clipboard copyredText = Toolkit.getDefaultToolkit().getSystemClipboard();
				copyredText.setContents(stringToCopy, null);
			}
		});
		jPopupMenu.add(itemMenu);
		
		itemMenuSelectAll = new JMenuItem("Выделить всё");
		itemMenuSelectAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent paramActionEvent) {
				txtAria.setFocusable(true);
				txtAria.selectAll();
			}
		});
		jPopupMenu.add(itemMenuSelectAll);
		txtAria.addMouseListener(new MouseListener() {
			@Override
			public void mousePressed(MouseEvent paramMouseEvent) {
				if (paramMouseEvent.isPopupTrigger()) {
					jPopupMenu.show(txtAria, paramMouseEvent.getX(), paramMouseEvent.getY());
					
				}
			}
			
			@Override
			public void mouseReleased(MouseEvent paramMouseEvent) {}
			@Override
			public void mouseExited(MouseEvent paramMouseEvent) {}	
			@Override
			public void mouseEntered(MouseEvent paramMouseEvent) {}
			@Override
			public void mouseClicked(MouseEvent paramMouseEvent) {}
		});
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
