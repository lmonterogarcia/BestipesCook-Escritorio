package ctrl;

import java.io.IOException;

import logic.RetoLogic;
import model.InfoData;
import model.Reto;
import view.FrmPrincipal;
import view.RetoDetalle;

public class Ctrl_RetoDetalle {
	public static Reto oReto;
	public static void inicio(boolean boNuevaFila) {
		if(boNuevaFila) {
			new RetoDetalle();
			habilitarEdicion();
		}else {
			oReto = RetoLogic.lstRetos.get(FrmPrincipal.list.getSelectedIndex());
			new RetoDetalle();
			cargarDatos(oReto);
		}

	}

	private static void cargarDatos(Reto oReto) {
		RetoDetalle.txtTitle.setText(oReto.getTituloReto());
		RetoDetalle.txtSubTitle.setText(oReto.getSubtituReto());
		RetoDetalle.txtDate.setText(oReto.getFechaCreacionReto()+" - "+oReto.getFechaFinalizacionReto());
		RetoDetalle.txtDescripcion.setText(oReto.getTextoReto());
		Ctrl_Imagen.cargarImg(InfoData.URI_MEDIA+oReto.getoImagen().getRutaRelativaImagen());
	}

	public static void habilitarEdicion() {
		RetoDetalle.txtTitle.setEditable(true);
		RetoDetalle.txtSubTitle.setEditable(true);
		RetoDetalle.txtDescripcion.setEditable(true);
		RetoDetalle.btnEditar.setVisible(false);

		RetoDetalle.txtTitle.setBackground(InfoData.cWhite);
		RetoDetalle.txtSubTitle.setBackground(InfoData.cWhite);
		RetoDetalle.txtDescripcion.setBackground(InfoData.cWhite);

		RetoDetalle.btnGuardar.setVisible(true);
		if(oReto != null) {
			RetoDetalle.btnBorrar.setVisible(true);
		}

	}

	public static void deshabilitarEdicion() {
		if(oReto != null && RetoDetalle.txtTitle.isEditable()) {
			RetoDetalle.txtTitle.setEditable(false);
			RetoDetalle.txtSubTitle.setEditable(false);
			RetoDetalle.txtDescripcion.setEditable(false);
			RetoDetalle.btnEditar.setVisible(true);

			RetoDetalle.txtTitle.setBackground(InfoData.cNaranja);
			RetoDetalle.txtSubTitle.setBackground(InfoData.cNaranja);
			RetoDetalle.txtDescripcion.setBackground(InfoData.cRositaPalo);

			RetoDetalle.btnGuardar.setVisible(false);
			RetoDetalle.btnBorrar.setVisible(false);
		}else{
			cerrarVentanaDetalle();
		}
	}

	public static void updReto() {
		if(oReto == null) {
			try {
				if(Ctrl_Imagen.rutaImagenCargada.equals("") || RetoDetalle.txtTitle.getText().equals("")
						|| RetoDetalle.txtSubTitle.getText().equals("")
						|| RetoDetalle.txtDescripcion.getText().equals("")){
					System.err.println("Hay que completar todos los campos");
				}else {
					RetoLogic.insRetoPHP();
					cerrarVentanaDetalle();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			RetoLogic.updRetoPHP(oReto);
			cerrarVentanaDetalle();
		}
		
	}

	public static void cerrarVentanaDetalle() {
		oReto = null;
		RetoDetalle.ventana.dispose();
		Ctrl_FrmPrincipal.noticiaVentana();
	}

	public static void delReto() {
		RetoLogic.delRetoPHP(oReto);
		cerrarVentanaDetalle();
	}
}
