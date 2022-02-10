package model.constantes;

import java.awt.Color;

public interface InfoData {
	//####################### COLORES #############################
	public static final Color cRositaPalo = new Color (252,236,221);
	public static final Color cNaranjaPalo = new Color (255,255,204);
	public static final Color cNaranja = new Color (255,204,102);
	public static final Color cBlack = new Color (38,38,38);
	public static final Color cWhite = new Color (255,255,255);
	
	//####################### DATOS HOSTING #############################
	public final String URI = "https://thecrewdevelopers.com/bestipes/app/desktop/";
	public final String URI_MEDIA = "https://thecrewdevelopers.com/bestipes/app/media/imagenes/";
	public final String PATH_IMG = "//app//media//imagenes";
	
	// ## RECETA ##
	public final String URI_RECETA = "receta/";
	public final String URI_LSTRECETAS = "lst-recetas.php";
	public final String URI_GETPUNTUACIONESTRELLA = "get-puntuacionestrella.php?txtIdReceta=";
	public final String URI_GETIMAGENBYIDRECETA = "get-imagenbyidreceta.php?txtIdReceta=";
	public final String URI_GETRECETAPASOS = "get-receta-paso.php?txtIdReceta=";
	public final String URI_GETRECETAINGREDIENTES = "get-receta-ingrediente.php?txtIdReceta=";
	public final String URI_DEL_RECETA = "del-receta.php&?txtIdReceta=";
	public final String URI_UPD_RECETA = "upd-receta.php?txtIdReceta=";
	public final String URI_ENREVISION = "&txtEnRevision=";
	
}
