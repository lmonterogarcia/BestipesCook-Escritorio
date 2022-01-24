package logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Imagen;
import model.InfoData;
import model.Noticia;
import view.NoticiaDetalle;

public class ImagenLogic {
	
	private static String peticionHttp(String urlWebService) throws IOException{
		StringBuilder resultado = new StringBuilder();

		//Formatear espacios
		if(urlWebService.contains(" "))
			urlWebService = urlWebService.replace(" ", "%20");
		
		//Realizar la petición HTTP
		URL url = new URL(urlWebService);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		
		conn.setRequestMethod("GET");

		//Recoger los datos de respuesta
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String linea;
		while((linea = rd.readLine()) != null) {
			resultado.append(linea);
		}
		conn.disconnect();
		return resultado.toString();
	}
	
	public static void insImagenPHP(String ruta) {
		String url = InfoData.URI + "imagen/ins-imagen.php?txtrutaRelativaImagen="+ruta;
		try {
			peticionHttp(url);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
/*
	public static void delImagenPHP(Imagen oImagen) {
		String url = InfoData.URI + "imagen/del-imagen.php?txtTituloNoticia="+"&txtIdNoticia="+oNoticia.getIdNoticia();
		try {
			peticionHttp(url);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	*/
	public static Imagen getImagen(Integer imagenidImagen) throws IOException {
		String url = InfoData.URI + "imagen/get-imagen.php?txtidImagen="+imagenidImagen;
		String requestHttp = peticionHttp(url);
		
		Imagen oImagen = new Imagen();
		JSONObject jsonObj = new JSONObject(requestHttp);
		oImagen = objJsonParseImagen(jsonObj);
		
		return oImagen;
	}
	
	private static Imagen objJsonParseImagen(JSONObject jsonObj) {
		//Extraer los values del objeto JSON
		Integer idImagen = jsonObj.getInt("idImagen");
		String fechaCreacionImagen = jsonObj.getString("fechaCreacionImagen");
		String rutaRelativaImagen = jsonObj.getString("rutaRelativaImagen");

		return new Imagen(idImagen,fechaCreacionImagen,rutaRelativaImagen);
	}

	public static Imagen getImagenByUrl(String rutaImagen) throws IOException {
		String url = InfoData.URI + "imagen/getByUrl-imagen.php?txtrutaRelativaImagen="+rutaImagen;
		String requestHttp = peticionHttp(url);
		
		Imagen oImagen = new Imagen();
		JSONObject jsonObj = new JSONObject(requestHttp);
		oImagen = objJsonParseImagen(jsonObj);
		
		return oImagen;
	}
}
