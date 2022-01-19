package ctrl;



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
	}

	public static void deshabilitarEdicion() {
		NoticiaDetalle.txtTitle.setEditable(false);
		NoticiaDetalle.txtSubTitle.setEditable(false);
		NoticiaDetalle.txtDescripcion.setEditable(false);
		NoticiaDetalle.btnEditar.setVisible(true);
		
		NoticiaDetalle.txtTitle.setBackground(InfoData.cNaranja);
		NoticiaDetalle.txtSubTitle.setBackground(InfoData.cNaranja);
		NoticiaDetalle.txtDescripcion.setBackground(InfoData.cRositaPalo);
		
		NoticiaDetalle.btnGuardar.setVisible(false);
	}

	public static void updNoticia() {
		deshabilitarEdicion();
		NoticiaLogic.updNoticiaPHP(oNoticia);
	}
}
