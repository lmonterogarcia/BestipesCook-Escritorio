package logic;

import java.io.IOException;

import org.json.JSONObject;

import ctrl.Libreria;
import model.Imagen;
import model.constantes.InfoData;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ImagenLogic {
	
//	private static String peticionHttp(String urlWebService) throws IOException {
//		OkHttpClient client = new OkHttpClient();
//		Request request = new Request.Builder().url(urlWebService).build();
//
//		Response response = client.newCall(request).execute();
//
//		return response.body().string();
//	}
	
	public static void insImagenPHP(String ruta) {
		String url = InfoData.URI + "imagen/ins-imagen.php?txtrutaRelativaImagen="+ruta;
		try {
			Libreria.peticionHttp(url);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	public static void delImagenPHP(Integer idImagen) {
		String url = InfoData.URI + "imagen/del-imagen.php?txtidImagen="+idImagen;
		try {
			Libreria.peticionHttp(url);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	public static Imagen getImagen(Integer imagenidImagen) throws IOException {
		String url = InfoData.URI + "imagen/get-imagen.php?txtidImagen="+imagenidImagen;
		String requestHttp = Libreria.peticionHttp(url);
		
		Imagen oImagen = new Imagen();
		JSONObject jsonObj = new JSONObject(requestHttp);
		oImagen = objJsonParseImagen(jsonObj);
		
		return oImagen;
	}
	
	public static Imagen getImagenByIdReceta(Integer idReceta) throws IOException {
		String url = InfoData.URI + InfoData.URI_RECETA + InfoData.URI_GETIMAGENBYIDRECETA + idReceta;
		String requestHttp = Libreria.peticionHttp(url);
		
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
		String requestHttp = Libreria.peticionHttp(url);
		
		Imagen oImagen = new Imagen();
		JSONObject jsonObj = new JSONObject(requestHttp);
		oImagen = objJsonParseImagen(jsonObj);
		
		return oImagen;
	}
}
