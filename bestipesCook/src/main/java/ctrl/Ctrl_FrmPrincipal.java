package ctrl;

import logic.*;
import view.FrmPrincipal;

public class Ctrl_FrmPrincipal {
	private static byte bMenu;

	public static void noticiaVentana() {
		bMenu = 0;
		FrmPrincipal.list.removeAll();
		NoticiaLogic.cargarDatos();
	}

	public static void retoVentana() {
		bMenu = 1;
		FrmPrincipal.list.removeAll();
		RetoLogic.cargarDatos();
	}

	public static void categoriaVentana() {
		bMenu = 2;
		FrmPrincipal.list.removeAll();
		CategoriaLogic.cargarDatos();
	}

	public static void recetaVentana() {
		bMenu = 3;
		FrmPrincipal.list.removeAll();
		RecetaLogic.cargarDatos();
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
			Ctrl_RecetaDetalle.inicio(boNuevaFila);
			break;
		}
	}

}
