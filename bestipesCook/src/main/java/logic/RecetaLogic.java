package logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Receta;
import model.constantes.InfoData;
import view.FrmPrincipal;

public class RecetaLogic implements InfoData{
	public static ArrayList<Receta> lstRecetas;
	public static void cargarDatos() {

		try {
			lstRecetas = getRecetas();
			lstRecetas.forEach(receta -> FrmPrincipal.list.add(receta.getTituloReceta()+" - "+receta.getFechaCreacionReceta()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

		private static ArrayList<Receta> getRecetas() throws IOException {
			String url = InfoData.URI + "lst-recetas.php";
			System.out.println(url);
			String requestHttp = peticionHttp(url);
			return stringToListReceta(requestHttp);
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

		private static ArrayList<Receta> stringToListReceta(String requestHttp) {
			ArrayList<Receta> lstRecetas = new ArrayList<Receta>();
			JSONArray jsonArr = new JSONArray(requestHttp);

			for(int i = 0 ; i < jsonArr.length(); i++) {
				JSONObject jsonObj = jsonArr.getJSONObject(i);
				lstRecetas.add(objJsonParseReceta(jsonObj));
			}

			return lstRecetas;
		}

		private static Receta objJsonParseReceta(JSONObject jsonObj) {
			//Extraer los values del objeto JSON
			Integer idReceta = jsonObj.getInt("idReceta");
			String fechaCreacionReceta = jsonObj.getString("fechaCreacionReceta");
			String tituloReceta = jsonObj.getString("tituloReceta");
			String subtituloReceta = jsonObj.getString("subtituloReceta");
			String textoReceta = jsonObj.getString("textoReceta");
			Integer imagenidImagen = jsonObj.getInt("imagenidImagen");

			return new Receta(idReceta,fechaCreacionReceta,tituloReceta,subtituloReceta,textoReceta,imagenidImagen);
		}


	}
