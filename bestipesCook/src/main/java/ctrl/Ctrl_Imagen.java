package ctrl;

import java.awt.Image;
import java.net.URL;
import java.awt.Dimension;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import model.Reto;
import view.NoticiaDetalle;
import view.RecetaDetalle;
import view.RetoDetalle;

public class Ctrl_Imagen {
	public static String rutaImagenCargada = "";
	private final static Dimension boundary = new Dimension(100, 100);
	
	public static void cargarImgNoticia(String direccion) {
		try {
			URL url = new URL(direccion);
			Image image = ImageIO.read(url);
			ImageIcon imgIcon = new ImageIcon(image);
			imgIcon = optimizarImagen(imgIcon);
			NoticiaDetalle.lblImg.setIcon(imgIcon);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void cargarImgReceta(String direccion) {
		try {
			URL url = new URL(direccion);
			Image image = ImageIO.read(url);
			ImageIcon imgIcon = new ImageIcon(image);
			imgIcon = optimizarImagen(imgIcon);
			RecetaDetalle.lblImg.setIcon(imgIcon);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void cargarImgReto(String direccion) {
		try {
			URL url = new URL(direccion);
			Image image = ImageIO.read(url);
			ImageIcon imgIcon = new ImageIcon(image);
			imgIcon = optimizarImagen(imgIcon);
			RetoDetalle.lblImg.setIcon(imgIcon);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void previsualizarImgNoticia(String direccion) {
		try {
			ImageIcon imgIcon = new ImageIcon(direccion);
			imgIcon = optimizarImagen(imgIcon);
			NoticiaDetalle.lblImg.setIcon(imgIcon);
			rutaImagenCargada = direccion;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void previsualizarImgReto(String direccion) {
		try {
			ImageIcon imgIcon = new ImageIcon(direccion);
			imgIcon = optimizarImagen(imgIcon);
			RetoDetalle.lblImg.setIcon(imgIcon);
			rutaImagenCargada = direccion;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static ImageIcon optimizarImagen(ImageIcon imgIcon) {
		ImageIcon rescaled;
		Integer width = imgIcon.getIconWidth();
		Integer height = imgIcon.getIconHeight();
		Dimension finalSize = getScaledDimension(new Dimension(width,height));
		
		rescaled = new ImageIcon(imgIcon.getImage().getScaledInstance(finalSize.width,finalSize.height,Image.SCALE_AREA_AVERAGING));

		return rescaled;
	}

	public static Dimension getScaledDimension(Dimension imgSize) { 
		int original_width = imgSize.width; 
		int original_height = imgSize.height; 
		int bound_width = boundary.width; 
		int bound_height = boundary.height; 
		int new_width = original_width; 
		int new_height = original_height; 

		// first check if we need to scale width 
		if (original_width > bound_width) { 
			new_width = bound_width; 
			new_height = (new_width * original_height) / original_width; 
		} 

		// then check if we need to scale even with the new height 
		if (new_height > bound_height) { 
			new_height = bound_height; 
			new_width = (new_height * original_width) / original_height;
		} 
		return new Dimension(new_width, new_height); 
	}

}
