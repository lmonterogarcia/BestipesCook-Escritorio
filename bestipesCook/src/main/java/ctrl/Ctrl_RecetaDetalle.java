package ctrl;

import java.awt.HeadlessException;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.swing.JOptionPane;

import logic.RecetaLogic;
import model.constantes.InfoData;
import view.FrmPrincipal;
import view.RecetaDetalle;

public class Ctrl_RecetaDetalle {

	private static int iPosicion;

	public Ctrl_RecetaDetalle(int iPosicion) {
		this.iPosicion = iPosicion;
	}

	public static void inicio() {
		new RecetaDetalle();
		iPosicion = FrmPrincipal.list.getSelectedIndex();
		cargarDatos();
		cambioEnRevision();

	}

	private static void cambioEnRevision() {
		if (RecetaLogic.lstRecetas.get(iPosicion).isBooEnRevision()) {
			RecetaDetalle.lblEnRevision.setVisible(true);
			RecetaDetalle.btnQuitarEnRevision.setVisible(true);
			RecetaDetalle.btnEnRevision.setVisible(false);
			RecetaDetalle.btnBorrar.setVisible(true);
		} else {
			RecetaDetalle.lblEnRevision.setVisible(false);
			RecetaDetalle.btnQuitarEnRevision.setVisible(false);
			RecetaDetalle.btnEnRevision.setVisible(true);
			RecetaDetalle.btnBorrar.setVisible(false);
		}
	}

	private static void cargarDatos() {

		RecetaDetalle.lblTitle.setText(RecetaLogic.lstRecetas.get(iPosicion).getsTituloReceta());
		RecetaDetalle.lblEstrellas.setText(String.valueOf(RecetaLogic.lstEstrellas.get(iPosicion)));
		RecetaDetalle.txtTexto.setText(RecetaLogic.lstRecetas.get(iPosicion).getsTextoReceta());
		RecetaDetalle.txtComensales.setText("" + RecetaLogic.lstRecetas.get(iPosicion).getShComensalesReceta());
		RecetaDetalle.txtDuracion.setText("" + RecetaLogic.lstRecetas.get(iPosicion).getfDuracionReceta());
		RecetaDetalle.txtUsuario.setText(RecetaLogic.lstRecetas.get(iPosicion).getUsuario().getsNombreUsuraio());
		RecetaDetalle.lblCategoria.setText(RecetaLogic.lstRecetas.get(iPosicion).getCategoria().getNombreCategoria());
		Ctrl_Imagen.cargarImgReceta(
				InfoData.URI_MEDIA + RecetaLogic.lstImagenesPral.get(iPosicion).getRutaRelativaImagen());
		logic.RecetaLogic.cargarDatosReceta(iPosicion);
		new RenderListIngredientes();
		new RenderListPasos();

	}

	public static void cerrarVentanaDetalle() {
		RecetaDetalle.ventana.dispose();
		Ctrl_FrmPrincipal.ventanaPrincipal(Ctrl_FrmPrincipal.bMenu);
	}

	public static void delReceta(byte bTipoEmail) {

		try {
			if (RecetaLogic.delReceta(RecetaLogic.lstRecetas.get(iPosicion).getiIdReceta())) {
				Ctrl_Imagen.rutaImagenCargada = InfoData.PATH_IMG + "/"
						+ RecetaLogic.lstImagenesPral.get(iPosicion).getRutaRelativaImagen();
				ClienteFTP.start(false);
				RecetaLogic.lstPasos.forEach(p -> {
					Ctrl_Imagen.rutaImagenCargada = InfoData.PATH_IMG + "/" + p.getImagen().getRutaRelativaImagen();
					ClienteFTP.start(false);
				});
				try {
					new ClienteSMTP(RecetaLogic.lstRecetas.get(iPosicion).getUsuario().getsNombreUsuraio(),
							RecetaLogic.lstRecetas.get(iPosicion).getsTituloReceta(),
							RecetaLogic.lstRecetas.get(iPosicion).getUsuario().getsEmailUsuario(), "", bTipoEmail)
									.sendEmail();
				} catch (MessagingException e) {
					JOptionPane.showMessageDialog(RecetaDetalle.ventana, "Error al enviar el email al usuario",
							"Gesti??n de Recetas", JOptionPane.PLAIN_MESSAGE);
					e.printStackTrace();
				}
				cerrarVentanaDetalle();
			} else {
				msgErrorDel();
			}
		} catch (HeadlessException e) {
			msgErrorDel();
			e.printStackTrace();
		} catch (IOException e) {
			msgErrorDel();
			e.printStackTrace();
		}

	}

	private static void msgErrorDel() {
		JOptionPane.showMessageDialog(RecetaDetalle.ventana, "Error al eliminar la receta", "Gesti??n de Recetas",
				JOptionPane.PLAIN_MESSAGE);
		
	}

	public static void cambiarEstadoRevision(boolean booEnRevision, byte bTipoEmail) {
		String sCuerpoMail = "";
		try {
			if (RecetaLogic.updReceta(RecetaLogic.lstRecetas.get(iPosicion).getiIdReceta(), booEnRevision)) {
				RecetaLogic.lstRecetas.get(iPosicion).setBooEnRevision(booEnRevision);
				cambioEnRevision();
				try {
					if (booEnRevision) {
						sCuerpoMail = JOptionPane.showInputDialog(RecetaDetalle.ventana,"Explique los errores al usuario", "Gestion de Recetas", JOptionPane.WARNING_MESSAGE) + "\n\n";
					}
					new ClienteSMTP(RecetaLogic.lstRecetas.get(iPosicion).getUsuario().getsNombreUsuraio(),
							RecetaLogic.lstRecetas.get(iPosicion).getsTituloReceta(),
							RecetaLogic.lstRecetas.get(iPosicion).getUsuario().getsEmailUsuario(), sCuerpoMail, bTipoEmail)
									.sendEmail();
				} catch (MessagingException e) {
					JOptionPane.showMessageDialog(RecetaDetalle.ventana, "Error al enviar el email al usuario",
							"Gesti??n de Recetas", JOptionPane.PLAIN_MESSAGE);
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(RecetaDetalle.ventana,
						"Se ha cambiado el estado de la revisi??n y se ha enviado un email al usuario",
						"Gesti??n de Recetas", JOptionPane.PLAIN_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(RecetaDetalle.ventana, "Error al cambiar el estado de revisi??n",
						"Gesti??n de Recetas", JOptionPane.PLAIN_MESSAGE);
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(RecetaDetalle.ventana, "Error al cambiar el estado de revisi??n",
					"Gesti??n de Recetas", JOptionPane.PLAIN_MESSAGE);
			e.printStackTrace();
		}
	}

}
