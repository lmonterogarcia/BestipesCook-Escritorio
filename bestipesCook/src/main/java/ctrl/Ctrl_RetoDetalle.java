package ctrl;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Date;

import javax.swing.JFormattedTextField;

import org.jdesktop.swingx.JXDatePicker;

import logic.RetoLogic;
import model.Reto;
import model.constantes.InfoData;
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
	
	 public static DateTimeFormatter dateTimeformatterFromDB = new DateTimeFormatterBuilder()
             .appendPattern("yyyy-MM-dd HH:mm:ss")
             .optionalStart()
             .appendPattern(".")
             .appendFraction(ChronoField.MICRO_OF_SECOND, 1, 6, false)
             .optionalEnd()
             .toFormatter(); //LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
	
	
	 public static Date convertToDateViaSqlTimestamp(LocalDateTime dateToConvert) {
		    return Timestamp.valueOf(dateToConvert);
	}
	 
	public static String formatDate(JXDatePicker datePicker) {

		JFormattedTextField editor = datePicker.getEditor();
		Date dateInDatePicker = (Date) editor.getValue();
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		String strDate = dateFormat.format(dateInDatePicker);
		
		return strDate;
	}	

	private static void cargarDatos(Reto oReto) {
		RetoDetalle.txtTitle.setText(oReto.getTituloReto());
		RetoDetalle.txtSubTitle.setText(oReto.getSubtituReto());
		RetoDetalle.txtDate.setText(oReto.getFechaCreacionReto()+" - "+oReto.getFechaFinalizacionReto());
		RetoDetalle.txtDescripcion.setText(oReto.getTextoReto());
		RetoDetalle.picker.setDate(convertToDateViaSqlTimestamp(oReto.getFechaFinalizacionReto()));		
		Ctrl_Imagen.cargarImgReto(InfoData.URI_MEDIA+oReto.getoImagen().getRutaRelativaImagen());
	}

	public static void habilitarEdicion() {
		RetoDetalle.boEdit=true;
		RetoDetalle.txtTitle.setEditable(true);
		RetoDetalle.txtSubTitle.setEditable(true);
		RetoDetalle.txtDescripcion.setEditable(true);
		RetoDetalle.picker.setEditable(true);
		RetoDetalle.fechaFinalizacion.setVisible(true);
		RetoDetalle.btnEditar.setVisible(true);

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
			RetoDetalle.boEdit=false;
			RetoDetalle.fechaFinalizacion.setVisible(false);
			RetoDetalle.txtTitle.setEditable(false);
			RetoDetalle.txtSubTitle.setEditable(false);
			RetoDetalle.txtDescripcion.setEditable(false);
			RetoDetalle.picker.setEditable(false);
			RetoDetalle.btnEditar.setVisible(false);

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
		Ctrl_FrmPrincipal.ventanaPrincipal(Ctrl_FrmPrincipal.bMenu);
	}

	public static void delReto() {
		RetoLogic.delRetoPHP(oReto);
		cerrarVentanaDetalle();
	}
	
	
}
