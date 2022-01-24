package ctrl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import model.InfoData;
import view.NoticiaDetalle;

public class ClienteFTP {

	public static void start() {
		//Creamos el objeto cliente FTP
		FTPClient clienteFTP = new FTPClient();

		//Datos para conectarme al servidor
		String sFtp = "ftp.p4897.webmo.fr";
		String sUsuario = "p4897_bestipes";
		String sPass = "Informedac2021";


		try {
			//Conectando al servidor
			clienteFTP.connect(sFtp);

			//Comprobamos si el usuario es válido
			verificarUsuario(clienteFTP,sUsuario,sPass);

			//Verificamos que estamos conectados con el servidor
			verificarConexion(clienteFTP);

			//Subimos el archivo
			UploadFile(clienteFTP);
			
			//Nos desconectamos del servidor
			clienteFTP.logout();
			clienteFTP.disconnect();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

	}

	public static void verificarUsuario(FTPClient clienteFTP, String sUsuario, String sPass) throws IOException {
		if(clienteFTP.login(sUsuario, sPass)) {
			System.out.println("Usuario válido.");
		}else {
			System.err.println("Usuario inválido.");
		}
	}
	
	public static void verificarConexion(FTPClient clienteFTP) {
		int respuesta = clienteFTP.getReplyCode();
		if(FTPReply.isPositiveCompletion(respuesta))
		{
			System.out.println("Conectado Satisfactoriamente");    
		}else{
			System.err.println("Imposible conectarse al servidor");
		}
	}
	
	public static void UploadFile(FTPClient clienteFTP){
        File firstLocalFile = new File(Ctrl_Imagen.rutaImagenCargada);
       		System.out.println(Ctrl_Imagen.rutaImagenCargada);
	        try {
	        	InputStream inputStream = new FileInputStream(firstLocalFile);
				inputStream.close();
				clienteFTP.changeWorkingDirectory(InfoData.PATH_IMG);
				if (clienteFTP.storeFile(firstLocalFile.getName(), inputStream)) {
				    System.out.println("El archivo se ha subido con éxito.");
				}else {
					System.err.println("No se ha podido subir el archivo.");
				}
			} catch (Exception e) {
				e.getMessage();
			}
	}

}
