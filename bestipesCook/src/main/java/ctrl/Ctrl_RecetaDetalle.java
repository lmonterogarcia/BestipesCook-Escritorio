package ctrl;

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
		if (RecetaLogic.lstRecetas.get(iPosicion).isBooEnRevision()) {
			RecetaDetalle.lblEnRevision.setVisible(true);
			RecetaDetalle.btnQuitarEnRevision.setVisible(true);
			RecetaDetalle.btnEnRevision.setVisible(false);
		} else {
			RecetaDetalle.lblEnRevision.setVisible(false);
			RecetaDetalle.btnQuitarEnRevision.setVisible(false);
			RecetaDetalle.btnEnRevision.setVisible(true);
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

	public static void delReceta() {
		RecetaLogic.delReceta(RecetaLogic.lstRecetas.get(iPosicion).getiIdReceta());
		cerrarVentanaDetalle();
	}

	public static Object cambiarEstadoRevision(int i) {
		// TODO Auto-generated method stub
		return null;
	}
}
