package passwordgenerator;


import javax.swing.Box;
import java.awt.Container;

public class BoxToPane extends Container{
	private static final long serialVersionUID = 2022556927522905604L;
	
	public BoxToPane() {
		
	}
	
	public void addBoxToPane(Container pane) {
		Box boxLayout0 = Box.createVerticalBox();
		
		Box boxLayout1 = Box.createHorizontalBox();
		boxLayout1.add(Box.createHorizontalStrut(3));
		boxLayout1.add(PasswordGen.label01);
		boxLayout1.add(Box.createHorizontalGlue());
		
		Box boxLayoutComboBox = Box.createHorizontalBox();
		boxLayoutComboBox.add(PasswordGen.combobox);
		
		Box boxLayout2 = Box.createHorizontalBox();
		boxLayout2.add(PasswordGen.label02);
		boxLayout2.add(Box.createHorizontalStrut(3));
		boxLayout2.add(PasswordGen.spinner);
		boxLayout2.add(Box.createHorizontalStrut(3));
		boxLayout2.add(PasswordGen.label03);
		
		Box boxLayoutChkBox = Box.createHorizontalBox();
		boxLayoutChkBox.add(PasswordGen.chkBox);
		boxLayoutChkBox.add(Box.createHorizontalGlue());
		
		Box boxLayoutChkBoxPunkt = Box.createHorizontalBox();
		boxLayoutChkBoxPunkt.add(PasswordGen.checkBoxPunctuation);
		boxLayoutChkBoxPunkt.add(Box.createHorizontalGlue());
		
		Box boxLayout4 = Box.createHorizontalBox();
		boxLayout4.add(PasswordGen.scrollpane);
		
		Box boxLayout5 = Box.createHorizontalBox();
//		boxLayout5.add(Box.createHorizontalGlue());
		boxLayout5.add(PasswordGen.btnGenerate);
//		boxLayout5.setCursor(Cursor.HAND_CURSOR);
		
		boxLayout0.add(boxLayout1);
		boxLayout0.add(boxLayoutComboBox);
		boxLayout0.add(boxLayout2);
		boxLayout0.add(boxLayoutChkBox);
		boxLayout0.add(boxLayoutChkBoxPunkt);
		boxLayout0.add(boxLayout4);
		boxLayout0.add(boxLayout5);
		
		pane.add(boxLayout0);
	}
}