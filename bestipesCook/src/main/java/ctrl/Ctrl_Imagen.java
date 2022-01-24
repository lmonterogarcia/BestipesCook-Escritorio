package ctrl;

import java.awt.Image;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import model.InfoData;
import view.NoticiaDetalle;

public class Ctrl_Imagen {
	public static String rutaImagenCargada = "";

	public static void cargarImg(String direccion) {
		try {
			URL url = new URL(direccion);
			Image image = ImageIO.read(url);
			ImageIcon imgIcon = new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(159,80,Image.SCALE_DEFAULT));
			NoticiaDetalle.lblImg.setIcon(imgIcon);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void previsualizarImg(String direccion) {
		try {
			ImageIcon imgIcon = new ImageIcon(new ImageIcon(direccion).getImage().getScaledInstance(159,80,Image.SCALE_DEFAULT));
			NoticiaDetalle.lblImg.setIcon(imgIcon);
			rutaImagenCargada = direccion;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void subirImagen() {
		
	}
}
