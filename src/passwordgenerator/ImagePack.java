package passwordgenerator;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

public class ImagePack {
	
	ClassLoader classLoader = this.getClass().getClassLoader();
	URL urlImage = classLoader.getResource("images/image_icon.png");
	ImageIcon iconImage = new ImageIcon(urlImage);
	
	public Image getImagePack() {
		return iconImage.getImage();
	}
	
}
