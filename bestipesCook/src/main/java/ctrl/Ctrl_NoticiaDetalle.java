package ctrl;

import java.io.IOException;

import logic.NoticiaLogic;
import model.InfoData;
import model.Noticia;
import view.FrmPrincipal;
import view.NoticiaDetalle;

public class Ctrl_NoticiaDetalle {
	public static Noticia oNoticia;
	public static void inicio(boolean boNuevaFila) {
		if(boNuevaFila) {
			new NoticiaDetalle();
			habilitarEdicion();
		}else {
			oNoticia = NoticiaLogic.lstNoticias.get(FrmPrincipal.list.getSelectedIndex());
			new NoticiaDetalle();
			cargarDatos(oNoticia);
		}

	}

	private static void cargarDatos(Noticia oNoticia) {
		NoticiaDetalle.txtTitle.setText(oNoticia.getTituloNoticia());
		NoticiaDetalle.txtSubTitle.setText(oNoticia.getSubtituloNoticia());
		NoticiaDetalle.txtDate.setText(oNoticia.getFechaCreacionNoticia());
		NoticiaDetalle.txtDescripcion.setText(oNoticia.getTextoNoticia());
		Ctrl_Imagen.cargarImg(InfoData.URI_MEDIA+oNoticia.getoImagen().getRutaRelativaImagen());
	}

	public static void habilitarEdicion() {
		NoticiaDetalle.txtTitle.setEditable(true);
		NoticiaDetalle.txtSubTitle.setEditable(true);
		NoticiaDetalle.txtDescripcion.setEditable(true);
		NoticiaDetalle.btnEditar.setVisible(false);

		NoticiaDetalle.txtTitle.setBackground(InfoData.cWhite);
		NoticiaDetalle.txtSubTitle.setBackground(InfoData.cWhite);
		NoticiaDetalle.txtDescripcion.setBackground(InfoData.cWhite);

		NoticiaDetalle.btnGuardar.setVisible(true);
		if(oNoticia != null) {
			NoticiaDetalle.btnBorrar.setVisible(true);
		}

	}

	public static void deshabilitarEdicion() {
		if(oNoticia != null && NoticiaDetalle.txtTitle.isEditable()) {
			NoticiaDetalle.txtTitle.setEditable(false);
			NoticiaDetalle.txtSubTitle.setEditable(false);
			NoticiaDetalle.txtDescripcion.setEditable(false);
			NoticiaDetalle.btnEditar.setVisible(true);

			NoticiaDetalle.txtTitle.setBackground(InfoData.cNaranja);
			NoticiaDetalle.txtSubTitle.setBackground(InfoData.cNaranja);
			NoticiaDetalle.txtDescripcion.setBackground(InfoData.cRositaPalo);

			NoticiaDetalle.btnGuardar.setVisible(false);
			NoticiaDetalle.btnBorrar.setVisible(false);
		}else{
			cerrarVentanaDetalle();
		}
	}

	public static void updNoticia() {
		if(oNoticia == null) {
			try {
				if(Ctrl_Imagen.rutaImagenCargada.equals("") || NoticiaDetalle.txtTitle.getText().equals("")
						|| NoticiaDetalle.txtSubTitle.getText().equals("")
						|| NoticiaDetalle.txtDescripcion.getText().equals("")){
					System.err.println("Hay que completar todos los campos");
				}else {
					NoticiaLogic.insNoticiaPHP();
					cerrarVentanaDetalle();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			NoticiaLogic.updNoticiaPHP(oNoticia);
			cerrarVentanaDetalle();
		}
		
	}

	public static void cerrarVentanaDetalle() {
		oNoticia = null;
		NoticiaDetalle.ventana.dispose();
		Ctrl_FrmPrincipal.noticiaVentana();
	}

	public static void delNoticia() {
		NoticiaLogic.delNoticiaPHP(oNoticia);
		cerrarVentanaDetalle();
	}
}
