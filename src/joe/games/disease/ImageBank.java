package joe.games.disease;

import java.awt.Image;
import java.util.HashMap;

import javax.swing.ImageIcon;

public class ImageBank {
	public HashMap<String, ImageIcon> images;
	
	public ImageBank(){
		images = new HashMap<String, ImageIcon>();
		images.put("smile", loadImage("smile.png"));
		images.put("skull", loadImage("skull.png"));
	}
	
	public ImageIcon loadImage(String name){
			ImageIcon img= new ImageIcon(name);
			
		return img;
	}
	
	public Image getImage(String key){
		return images.get(key).getImage();
	}
}
