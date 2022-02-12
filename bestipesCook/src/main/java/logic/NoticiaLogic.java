package logic;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import ctrl.ClienteFTP;
import ctrl.Ctrl_Imagen;
import ctrl.Libreria;
import ctrl.RenderListNoticias;
import model.Imagen;
import model.Noticia;
import model.constantes.InfoData;
import view.NoticiaDetalle;

public class NoticiaLogic implements InfoData{
	public static ArrayList<Noticia> lstNoticias;
	
	public static void cargarDatos() {
		
		try {
			lstNoticias = getNoticias();
			new RenderListNoticias();
			//ONSUCCESS
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static ArrayList<Noticia> getNoticias() throws IOException {
		String url = InfoData.URI + "noticia/lst-noticias.php";
		String requestHttp = Libreria.peticionHttp(url);
		return stringToListNoticia(requestHttp);
	}

	private static ArrayList<Noticia> stringToListNoticia(String requestHttp) throws IOException {
		ArrayList<Noticia> lstNoticias = new ArrayList<Noticia>();
		JSONArray jsonArr = new JSONArray(requestHttp);

		for(int i = 0 ; i < jsonArr.length(); i++) {
			JSONObject jsonObj = jsonArr.getJSONObject(i);
			lstNoticias.add(objJsonParseNoticia(jsonObj));
		}

		return lstNoticias;
	}

	private static Noticia objJsonParseNoticia(JSONObject jsonObj) throws IOException {
		//Extraer los values del objeto JSON
		Integer idNoticia = jsonObj.getInt("idNoticia");
		String fechaCreacionNoticia = jsonObj.getString("fechaCreacionNoticia");
		String tituloNoticia = jsonObj.getString("tituloNoticia");
		String subtituloNoticia = jsonObj.getString("subtituloNoticia");
		String textoNoticia = jsonObj.getString("textoNoticia");
		Integer imagenidImagen = jsonObj.getInt("imagenidImagen");

		return new Noticia(idNoticia,fechaCreacionNoticia,tituloNoticia,subtituloNoticia,textoNoticia,ImagenLogic.getImagen(imagenidImagen));
	}

	public static void updNoticiaPHP(Noticia oNoticia){
		String url = "";
		if(Ctrl_Imagen.rutaImagenCargada.equals("")||oNoticia.getoImagen().getRutaRelativaImagen().equals(new File(Ctrl_Imagen.rutaImagenCargada).getName())) {
			url = InfoData.URI + "noticia/upd-noticia.php?txtTituloNoticia="+NoticiaDetalle.txtTitle.getText()
			+"&txtSubtituloNoticia="+NoticiaDetalle.txtSubTitle.getText()
			+"&txtTextoNoticia="+NoticiaDetalle.txtDescripcion.getText()
			+"&txtIdNoticia="+oNoticia.getIdNoticia()
			+"&txtidImagen="+oNoticia.getoImagen().getIdImagen();
			
			try {
				Libreria.peticionHttp(url);
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
			
		}else {
			//Creamos la nueva imagen
			//Tranferencia del archivo imagen al servidor FTP
			ClienteFTP.start(true);

			//Creacion de la url de la imagen subida al servidor FTP
			String rutaImagen = new File(Ctrl_Imagen.rutaImagenCargada).getName();

			//Inserción en la tabla imagen de la BD el dato imagen con su ubicacion
			ImagenLogic.insImagenPHP(rutaImagen);

			//Peticion del id generado para la tupla generada
			Imagen oImagen = null;
			try {
				oImagen = ImagenLogic.getImagenByUrl(rutaImagen);
			} catch (IOException e) {
				e.getMessage();
			}

			url = InfoData.URI + "noticia/upd-noticia.php?txtTituloNoticia="+NoticiaDetalle.txtTitle.getText()
			+"&txtSubtituloNoticia="+NoticiaDetalle.txtSubTitle.getText()
			+"&txtTextoNoticia="+NoticiaDetalle.txtDescripcion.getText()
			+"&txtIdNoticia="+oNoticia.getIdNoticia()
			+"&txtidImagen="+oImagen.getIdImagen();

			try {
				Libreria.peticionHttp(url);
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}

			//Borramos la imagen antigua
			//Eliminamos los datos de la imagen en la BD
			ImagenLogic.delImagenPHP(oNoticia.getoImagen().getIdImagen());
			//Eliminamos la imagen del servidor FTP
			Ctrl_Imagen.rutaImagenCargada = InfoData.PATH_IMG+"/"+oNoticia.getoImagen().getRutaRelativaImagen();
			ClienteFTP.start(false);
		}

	}

	public static void insNoticiaPHP() throws IOException {
		//Tranferencia del archivo imagen al servidor FTP
		ClienteFTP.start(true);

		//Creacion de la url de la imagen subida al servidor FTP
		String rutaImagen = new File(Ctrl_Imagen.rutaImagenCargada).getName();

		//Inserción en la tabla imagen de la BD el dato imagen con su ubicacion
		ImagenLogic.insImagenPHP(rutaImagen);

		//Peticion del id generado para la tupla generada
		Imagen oImagen = ImagenLogic.getImagenByUrl(rutaImagen);

		//Insertar noticia
		String url = InfoData.URI + "noticia/ins-noticia.php?txtTituloNoticia="+NoticiaDetalle.txtTitle.getText()
		+"&txtSubtituloNoticia="+NoticiaDetalle.txtSubTitle.getText()
		+"&txtTextoNoticia="+NoticiaDetalle.txtDescripcion.getText()
		+"&txtImg="+oImagen.getIdImagen();
		try {
			Libreria.peticionHttp(url);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
//	public static void insNoticiaPHP() throws IOException {
//
//
//		//Insertar noticia
//		String url = "http://10.192.120.86/holamundo/ws.php?txtTituloNoticia="+NoticiaDetalle.txtTitle.getText()
//		+"&txtSubtituloNoticia="+NoticiaDetalle.txtSubTitle.getText()
//		+"&txtTextoNoticia="+NoticiaDetalle.txtDescripcion.getText();
//		try {
//			System.out.println(peticionHttp(url));
//		} catch (IOException e) {
//			System.err.println(e.getMessage());
//		}
//	}

	public static void delNoticiaPHP(Noticia oNoticia) {
		String url = InfoData.URI + "noticia/del-noticia.php?txtIdNoticia="+oNoticia.getIdNoticia();
		try {
			Libreria.peticionHttp(url);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		//Eliminamos los datos de la imagen en la BD
		ImagenLogic.delImagenPHP(oNoticia.getoImagen().getIdImagen());
		//Eliminamos la imagen del servidor FTP
		Ctrl_Imagen.rutaImagenCargada = InfoData.PATH_IMG+"/"+oNoticia.getoImagen().getRutaRelativaImagen();
		ClienteFTP.start(false);
	}

}
