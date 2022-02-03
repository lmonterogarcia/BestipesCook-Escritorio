package ctrl;

import logic.RecetaLogic;
import model.receta.*;
import view.FrmPrincipal;
import view.RecetaDetalle;

public class Ctrl_RecetaDetalle {
	
	public static Receta oReceta;
	
	public static void inicio(boolean boNuevaFila) {
		
		new RecetaDetalle();
		cargarDatos();


	}

	private static void cargarDatos() {
		RecetaDetalle.txtTitle.setText(oReceta.getTituloReceta());
		RecetaDetalle.txtSubTitle.setText(oReceta.getSubtituloReceta());
		RecetaDetalle.txtDate.setText(oReceta.getFechaCreacionReceta());
		RecetaDetalle.txtDescripcion.setText(oReceta.getTextoReceta());
		Ctrl_Imagen.cargarImgReceta(InfoData.URI_MEDIA+oReceta.getoImagen().getRutaRelativaImagen());
	}

//	public static void habilitarEdicion() {
//		RecetaDetalle.boEdit = true;
//		RecetaDetalle.txtTitle.setEditable(true);
//		RecetaDetalle.txtSubTitle.setEditable(true);
//		RecetaDetalle.txtDescripcion.setEditable(true);
//		RecetaDetalle.btnEditar.setVisible(false);
//
//		RecetaDetalle.txtTitle.setBackground(InfoData.cWhite);
//		RecetaDetalle.txtSubTitle.setBackground(InfoData.cWhite);
//		RecetaDetalle.txtDescripcion.setBackground(InfoData.cWhite);
//
//		RecetaDetalle.btnGuardar.setVisible(true);
//		if(oReceta != null) {
//			RecetaDetalle.btnBorrar.setVisible(true);
//		}
//
//	}

//	public static void deshabilitarEdicion() {
//		if(oReceta != null && RecetaDetalle.txtTitle.isEditable()) {
//			RecetaDetalle.boEdit = false;
//			RecetaDetalle.txtTitle.setEditable(false);
//			RecetaDetalle.txtSubTitle.setEditable(false);
//			RecetaDetalle.txtDescripcion.setEditable(false);
//			RecetaDetalle.btnEditar.setVisible(true);
//
//			RecetaDetalle.txtTitle.setBackground(InfoData.cNaranja);
//			RecetaDetalle.txtSubTitle.setBackground(InfoData.cNaranja);
//			RecetaDetalle.txtDescripcion.setBackground(InfoData.cRositaPalo);
//
//			RecetaDetalle.btnGuardar.setVisible(false);
//			RecetaDetalle.btnBorrar.setVisible(false);
//		}else{
//			cerrarVentanaDetalle();
//		}
//	}

//	public static void updReceta() {
//		if(oReceta == null) {
//			try {
//				if(Ctrl_Imagen.rutaImagenCargada.equals("") || RecetaDetalle.txtTitle.getText().equals("")
//						|| RecetaDetalle.txtSubTitle.getText().equals("")
//						|| RecetaDetalle.txtDescripcion.getText().equals("")){
//					System.err.println("Hay que completar todos los campos");
//				}else {
//					RecetaLogic.insRecetaPHP();
//					cerrarVentanaDetalle();
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}else {
//			RecetaLogic.updRecetaPHP(oReceta);
//			cerrarVentanaDetalle();
//		}
//		
//	}

	public static void cerrarVentanaDetalle() {
		oReceta = null;
		RecetaDetalle.ventana.dispose();
		Ctrl_FrmPrincipal.ventanaPrincipal(Ctrl_FrmPrincipal.bMenu);
	}

//	public static void delReceta() {
//		RecetaLogic.delRecetaPHP(oReceta);
//		cerrarVentanaDetalle();
//	}
}
