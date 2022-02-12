	package model.constantes;

import java.awt.Font;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public interface IConstantes {

	public static DateTimeFormatter dateTimeformatterFromDB = new DateTimeFormatterBuilder() //LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
            .appendPattern("yyyy-MM-dd HH:mm:ss")
            .optionalStart()
            .appendPattern(".")
            .appendFraction(ChronoField.MICRO_OF_SECOND, 1, 6, false)
            .optionalEnd()
            .toFormatter(); 
	
	public static DateTimeFormatter dateTimeformatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    public static DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public static DateTimeFormatter timeformatter = DateTimeFormatter.ofPattern("HH:mm");
    
    Font FONTLISTASDETALLE = new Font("Yu Gothic UI Light", Font.BOLD , 13);
    
    // MAIL INFO
    public final String MAIL_SERVER_HOST = "smtp.gmail.com";
    public final String USER = "bestipescook@gmail.com";
    public final String PASSWORD = "Informedac2021";
    public final String MAIL_FROM = "bestipescook@gmail.com";
    public final String MAIL_SUBJECT = "BestipesCook te envia un mensaje";
    
    
    // MAIL PLANTILLAS
    
    public final byte BMAILPONERREVISION = 0;
    public final byte BMAILQUITARREVISION = 1;
    public final byte BMAILBORRAR = 2;
    
    public final String MAILDEFAULT = "<p>Parece que te hemos enviado ete email por error</p><p>Estamos trabajando en ello para que no vuelva a suceder</p> ";
    
    public final String MAILHOLA = "<p>Hola ";
    public final String MAILPOSTHOLA = ",\n\n</p>";
    public final String MAILRECETANOMBRE = "<p>Despues de revisar tu receta \"";
    public final String MAILPOSTRECETANOMBRE = "\", ";
    
    public final String MAILMSGPONERREVISION = "hemos detectado algunos errores por lo que ahora no aparece en las busquedas de la app.</p>";
    public final String MAILMSGPOSTPONERREVISION = "<p>Por favor revisela para que un administrador pueda volver a publicarla.</p>";
    public final String MAILMSGQUITARENREVISION = "hemos detectado que has arreglado los errores y volvera a aparecer en las busquedas de la app.</p>";
    public final String MAILMSGBORRAR = "hemos procedido al borrado de la receta. Esto se debe que entro en revisión y no se han hecho cambios en la receta mpor su parte en mas de 30 días.</p>";
    
    public final String MAILGRACIAS = "<p>Muchas gracias, y a seguir llenando el mundo de recetas maravillosas.</p>";
    
    
    public final String PREMAILBODY = "<html lang=\"es\" xmlns=\"https://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\">\n"
    		+ "<head>\n"
    		+ "    <meta charset=\"UTF-8\">\n"
    		+ "    <meta name=\"viewport\" content=\"width=device-width,initial-scale=1\">\n"
    		+ "    <meta name=\"x-apple-disable-message-reformatting\">\n"
    		+ "    <title></title>\n"
    		+ "    <noscript>\n"
    		+ "        <xml>\n"
    		+ "            <o:OfficeDocumentSettings>\n"
    		+ "                <o:PixelsPerInch>96</o:PixelsPerInch>\n"
    		+ "            </o:OfficeDocumentSettings>\n"
    		+ "        </xml>\n"
    		+ "    </noscript>\n"
    		+ "    <style>\n"
    		+ "        table, td, div, h1, p {font-family: Arial, sans-serif;}\n"
    		+ "    </style>\n"
    		+ "</head>\n"
    		+ "<body style=\"margin:0;padding:0;\">\n"
    		+ "    <table role=\"presentation\" align=\"center\" style=\"width:90%;border-collapse:collapse;border:0px solid #cccccc;border-spacing:0;text-align:left;\">\n"
    		+ "        <tr>\n"
    		+ "            <td align=\"center\" style=\"padding:40px 0 30px 0;background:#ffcc66;\">\n"
    		+ "                <img src=\"https://thecrewdevelopers.com/bestipes/app/media/imagenes/logo_sinFondo.png\" alt=\"\" width=\"150\" style=\"height:auto;display:block;\" />\n"
    		+ "            </td>\n"
    		+ "        </tr>\n"
    		+ "        <tr>\n"
    		+ "            <td style=\"padding:36px 30px 42px 30px;background:#f7f7f7\">\n";
    public final String POSTMAILBODY = "</td>\n"
    		+ "        </tr>\n"
    		+ "        <tr>\n"
    		+ "            <table role=\"presentation\" align=\"center\" style=\"width:90%;background:#ffffcc;border-collapse:collapse;border:1 solid #cccccc;border-spacing:0;\">\n"
    		+ "                <tr>\n"
    		+ "                    <td style=\"padding:0;width:50%;\" align=\"left\">\n"
    		+ "                        <p><br>&nbsp;&nbsp;Bestipes Cook Team<br></p>\n"
    		+ "                    </td>\n"
    		+ "                    <td style=\"padding:0;width:50%;\" align=\"right\">\n"
    		+ "                        <p><br>&reg;2021&nbsp;&nbsp;<br></p>\n"
    		+ "                    </td>\n"
    		+ "                </tr>\n"
    		+ "            </table>\n"
    		+ "        </tr>\n"
    		+ "    </table>\n"
    		+ "</body>";
    
}
