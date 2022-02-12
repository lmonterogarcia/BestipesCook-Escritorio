package ctrl;

import logic.*;
import view.FrmPrincipal;

public class Ctrl_FrmPrincipal {
	public static byte bMenu;
	
	@SuppressWarnings("static-access")
	public static void ventanaPrincipal(byte i) {
		FrmPrincipal.ventana.setCursor(FrmPrincipal.ventana.getCursor().getPredefinedCursor(FrmPrincipal.ventana.getCursor().WAIT_CURSOR));
		eleccionElemento(i);
		FrmPrincipal.ventana.setCursor(FrmPrincipal.ventana.getCursor().getDefaultCursor());
	}
	
	public static void eleccionElemento(byte i) {
		bMenu = i;
		FrmPrincipal.list.removeAll();
		FrmPrincipal.btnAdd.setVisible(true);
		switch(i) {
		case 0:
			FrmPrincipal.ventana.setTitle("Noticias");
			NoticiaLogic.cargarDatos();
			break;
		case 1:
			FrmPrincipal.ventana.setTitle("Retos");
			RetoLogic.cargarDatos();
			break;
		case 2:
			FrmPrincipal.ventana.setTitle("Categorias");
			CategoriaLogic.cargarDatos();
			break;
		case 3:
			FrmPrincipal.btnAdd.setVisible(false);
			FrmPrincipal.ventana.setTitle("Recetas");
			RecetaLogic.cargarDatos();
			new RenderListRecetas();
			break;
		}
	}

	public static void ventanaDetalle(boolean boNuevaFila) {
		switch(bMenu) {
		case 0:
			Ctrl_NoticiaDetalle.inicio(boNuevaFila);
			break;
		case 1:
			Ctrl_RetoDetalle.inicio(boNuevaFila);
			break;
		case 2:
			Ctrl_CategoriaDetalle.inicio(boNuevaFila);
			break;
		case 3:
			Ctrl_RecetaDetalle.inicio();
			break;
		}
	}
}
