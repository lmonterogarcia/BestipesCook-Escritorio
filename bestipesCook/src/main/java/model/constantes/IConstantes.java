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
    public final String MAIL_SUBJECT = "Hemos encontrado errores en tu receta";
}
