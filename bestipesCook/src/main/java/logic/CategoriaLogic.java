package logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Noticia;
import view.FrmPrincipal;

public class CategoriaLogic {
	private static final String URI = "http://bestipescook.es/app/desktop/noticia/";
	public static ArrayList<Noticia> lstNoticias;
	public static void cargarDatos() {

		try {
			lstNoticias = getNoticias();
			lstNoticias.forEach(noticia -> FrmPrincipal.list.add(noticia.getTituloNoticia()+" - "+noticia.getFechaCreacionNoticia()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

		private static ArrayList<Noticia> getNoticias() throws IOException {
			String url = URI + "lst-noticias.php";
			System.out.println(url);
			String requestHttp = peticionHttp(url);
			return stringToListNoticia(requestHttp);
		}

		private static String peticionHttp(String urlWebService) throws IOException{
			StringBuilder resultado = new StringBuilder();

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

		private static ArrayList<Noticia> stringToListNoticia(String requestHttp) {
			ArrayList<Noticia> lstNoticias = new ArrayList<Noticia>();
			JSONArray jsonArr = new JSONArray(requestHttp);

			for(int i = 0 ; i < jsonArr.length(); i++) {
				JSONObject jsonObj = jsonArr.getJSONObject(i);
				lstNoticias.add(objJsonParseNoticia(jsonObj));
			}

			return lstNoticias;
		}

		private static Noticia objJsonParseNoticia(JSONObject jsonObj) {
			//Extraer los values del objeto JSON
			Integer idNoticia = jsonObj.getInt("idNoticia");
			String fechaCreacionNoticia = jsonObj.getString("fechaCreacionNoticia");
			String tituloNoticia = jsonObj.getString("tituloNoticia");
			String subtituloNoticia = jsonObj.getString("subtituloNoticia");
			String textoNoticia = jsonObj.getString("textoNoticia");
			Integer imagenidImagen = jsonObj.getInt("imagenidImagen");

			return new Noticia(idNoticia,fechaCreacionNoticia,tituloNoticia,subtituloNoticia,textoNoticia,imagenidImagen);
		}


	}
