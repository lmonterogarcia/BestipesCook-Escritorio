package ctrl;

import logic.CategoriaLogic;
import model.InfoData;
import model.Categoria;
import view.CategoriaDetalle;
import view.FrmPrincipal;

public class Ctrl_CategoriaDetalle {
	public static Categoria oCategoria;
	public static void inicio(boolean boNuevaFila) {
		if(boNuevaFila) {
			new CategoriaDetalle();
			habilitarEdicion();
		}else {
			oCategoria = CategoriaLogic.lstCategorias.get(FrmPrincipal.list.getSelectedIndex());
			new CategoriaDetalle();
			cargarDatos(oCategoria);
		}

	}

	private static void cargarDatos(Categoria oCategoria) {
		CategoriaDetalle.txtTitle.setText(oCategoria.getNombreCategoria());
		CategoriaDetalle.checkBoxChallenge.setSelected(oCategoria.isChallengue());
	}

	public static void habilitarEdicion() {
		CategoriaDetalle.txtTitle.setEditable(true);
		CategoriaDetalle.checkBoxChallenge.setEnabled(true);

		CategoriaDetalle.txtTitle.setBackground(InfoData.cWhite);
		CategoriaDetalle.checkBoxChallenge.setBackground(InfoData.cWhite);

		CategoriaDetalle.btnGuardar.setVisible(true);
		if(oCategoria != null) {
			CategoriaDetalle.btnBorrar.setVisible(true);
		}
		
	}

	public static void deshabilitarEdicion() {
		if(oCategoria != null && CategoriaDetalle.txtTitle.isEditable()) {
			CategoriaDetalle.txtTitle.setEditable(false);
			CategoriaDetalle.checkBoxChallenge.setEnabled(false);

			CategoriaDetalle.txtTitle.setBackground(InfoData.cNaranja);
			CategoriaDetalle.checkBoxChallenge.setBackground(InfoData.cNaranja);

			CategoriaDetalle.btnGuardar.setVisible(false);
			CategoriaDetalle.btnBorrar.setVisible(false);
		}else{
			cerrarVentanaDetalle();
		}
	}

	public static void updCategoria() {
		deshabilitarEdicion();
		if(oCategoria == null) {
			CategoriaLogic.insCategoriaPHP();
		}else {
			CategoriaLogic.updCategoriaPHP(oCategoria);
		}
		cerrarVentanaDetalle();
	}

	public static void cerrarVentanaDetalle() {
		oCategoria = null;
		CategoriaDetalle.ventana.dispose();
		Ctrl_FrmPrincipal.categoriaVentana();
	}

	public static void delCategoria() {
		CategoriaLogic.delCategoriaPHP(oCategoria);
		cerrarVentanaDetalle();
	}

}
