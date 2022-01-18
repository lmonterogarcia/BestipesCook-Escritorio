package ctrl;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import view.NoticiaDetalle;

public class Ctrl_NoticiaDetalle {
	public static void inicio() {
		new NoticiaDetalle();
	}
	
	public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight){
		
	    BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
	    Graphics2D graphics2D = resizedImage.createGraphics();
	    graphics2D.drawImage(originalImage, 150, 150, targetWidth, targetHeight, null);
	    graphics2D.dispose();
	    return resizedImage;
	};
}
