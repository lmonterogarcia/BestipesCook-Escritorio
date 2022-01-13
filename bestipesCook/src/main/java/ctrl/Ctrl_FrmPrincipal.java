package ctrl;

import view.FrmPrincipal;

public class Ctrl_FrmPrincipal {
	private static byte bMenu;

	public static void noticiaVentana() {
		bMenu = 0;
		FrmPrincipal.list.removeAll();
		for(int i = 1; i <= 20; i++) {
			FrmPrincipal.list.add("Noticia "+i);
		}
	}

	public static void retoVentana() {
		bMenu = 1;
		FrmPrincipal.list.removeAll();
		for(int i = 1; i <= 20; i++) {
			FrmPrincipal.list.add("Reto "+i);
		}
	}

	public static void categoriaVentana() {
		bMenu = 2;
		FrmPrincipal.list.removeAll();
		for(int i = 1; i <= 20; i++) {
			FrmPrincipal.list.add("Categoría "+i);
		}
	}

	public static void recetaVentana() {
		bMenu = 3;
		FrmPrincipal.list.removeAll();
		for(int i = 1; i <= 20; i++) {
			FrmPrincipal.list.add("Receta "+i);
		}
	}

	public static void ventanaDetalle() {
		switch(bMenu) {
		case 0:
			Ctrl_NoticiaDetalle.inicio();
			break;
		case 1:
			Ctrl_RetoDetalle.inicio();
			break;
		case 2:
			Ctrl_CategoriaDetalle.inicio();
			break;
		case 3:
			Ctrl_RecetaDetalle.inicio();
			break;
		}
	}

	public static void crearContenido() {
		ventanaDetalle();
	}

}
