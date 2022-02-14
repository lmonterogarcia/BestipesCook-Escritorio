package ctrl;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import model.constantes.IConstantes;

public class ClienteSMTP implements IConstantes {

	private String sMailTo;
	private String sMainText;
	

	public ClienteSMTP(String sUsuario, String sTituloReceta, String sMailTo, String sMainText, byte bTipoEmail) {
		this.sMailTo = sMailTo;
		setsMainText(sUsuario, sTituloReceta, sMainText, bTipoEmail);
	}

	public String getsMailTo() {
		return sMailTo;
	}

	public void setsMailTo(String sMailTo) {
		this.sMailTo = sMailTo;
	}

	public String getsMainText() {
		return sMainText;
	}

	public void setsMainText(String sUsuario, String sTituloReceta, String sMainText, byte bTipoEmail) {

		
		this.sMainText = PREMAILBODY + saludoMail(sUsuario) + nombreRecetaMail(sTituloReceta);
		switch (bTipoEmail) {
		case BMAILPONERREVISION:
			this.sMainText += MAILMSGPONERREVISION + "<p>" + sMainText + "</p>" + MAILMSGPOSTPONERREVISION;
			break;
		case BMAILQUITARREVISION:
			this.sMainText += MAILMSGQUITARENREVISION;
			break;
		case BMAILBORRAR:
			this.sMainText += MAILMSGBORRAR;
			break;

		default:
			this.sMainText = PREMAILBODY + MAILDEFAULT + POSTMAILBODY;
			break;
		}
		this.sMainText += MAILGRACIAS + POSTMAILBODY;

	}

	private String saludoMail(String sUsuario) {
		return MAILHOLA + sUsuario + MAILPOSTHOLA;
	}
	
	private String nombreRecetaMail(String sReceta) {
		return MAILRECETANOMBRE + sReceta + MAILPOSTRECETANOMBRE;
	}

	public void sendEmail() throws MessagingException {
		Properties prop = new Properties();

		prop.setProperty("mail.debug", "false");
		prop.setProperty("mail.host", MAIL_SERVER_HOST);
		prop.setProperty("mail.transport.protocol", "smtp");
		prop.setProperty("mail.smtp.auth", "true");
		prop.put("mail.smtp.port", "465");
		prop.put("mail.smtp.socketFactory.port", "465");
		prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		prop.put("mail.smtp.socketFactory.fallback", "false");

		// 1. Crear sesión
		Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(MAIL_FROM, PASSWORD);
			}
		});

		// 2, obtener el objeto de transporte a través de la sesión
		Transport ts = null;
		ts = session.getTransport();
		// 3. Conéctese al servidor de correo
		ts.connect(MAIL_SERVER_HOST, USER, PASSWORD);

		// 4. Crear correo electrónico
		MimeMessage message = new MimeMessage(session);
		// Encabezado del mensaje de correo electrónico
		message.setFrom(new InternetAddress(MAIL_FROM)); // El remitente del mensaje
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(sMailTo)); // El destinatario del mensaje
		message.setSubject(MAIL_SUBJECT); // Asunto del mensaje
		// Cuerpo del mensaje de correo electrónico
		message.setContent(sMainText, "text/html");
		// 5. Enviar correo
		ts.sendMessage(message, message.getAllRecipients());
		ts.close();

	}
}
