package logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import ctrl.RenderListRecetas;
import model.receta.*;
import model.usuario.Usuario;
import model.Imagen;
import model.constantes.IConstantes;
import model.constantes.InfoData;

public class RecetaLogic implements InfoData, IConstantes {

	public static ArrayList<Receta> lstRecetas;
	public static ArrayList<Float> lstEstrellas = new ArrayList<Float>();
	public static ArrayList <Imagen> lstImagenesPral = new ArrayList<Imagen>();

	public static void cargarDatos() {

		try {
			lstRecetas = getRecetas();
			lstRecetas.forEach(r -> {
				try {
					lstEstrellas.add(getEstrella(r.getiIdReceta()));
					lstImagenesPral.add(logic.ImagenLogic.getImagenByIdReceta(r.getiIdReceta()));
				} catch (IOException e) {
					
					e.printStackTrace();
				}	
			});
			new RenderListRecetas();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	private static ArrayList<Receta> getRecetas() throws IOException {
		String url = InfoData.URI + URI_RECETA + URI_LSTRECETAS;
		String requestHttp = peticionHttp(url);
		return stringToListReceta(requestHttp);
	}
	
	private static Float getEstrella(int idReceta) throws IOException {
		String url = InfoData.URI + URI_RECETA + URI_GETPUNTUACIONESTRELLA + idReceta;
		String requestHttp = peticionHttp(url);
		return stringToFloat(requestHttp);
	}

	private static String peticionHttp(String urlWebService) throws IOException {
		StringBuilder resultado = new StringBuilder();

		// Realizar la petición HTTP

		URL url = new URL(urlWebService);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");

		// Recoger los datos de respuesta
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String linea;
		while ((linea = rd.readLine()) != null) {
			resultado.append(linea);
		}
		conn.disconnect();
		return resultado.toString();
	}

	private static Float stringToFloat(String requestHttp) {
		
		float fN;
		
		JSONObject jsonObj = new JSONObject(requestHttp);

		fN = (float)jsonObj.getDouble("puntuacionMedia");
		
		return fN;
	}
	
	private static ArrayList<Receta> stringToListReceta(String requestHttp) {
		ArrayList<Receta> lstRecetas = new ArrayList<Receta>();
		JSONArray jsonArr = new JSONArray(requestHttp);

		for (int i = 0; i < jsonArr.length(); i++) {
			JSONObject jsonObj = jsonArr.getJSONObject(i);
			lstRecetas.add(objJsonParseReceta(jsonObj));
		}

		return lstRecetas;
	}

	private static Receta objJsonParseReceta(JSONObject jsonObj) {
		// Extraer los values del objeto JSON
		Integer iIdReceta = jsonObj.getInt("idReceta");
		LocalDateTime fechaCreacionReceta = LocalDateTime.parse(jsonObj.getString("fechaCreacionReceta"),
				dateTimeformatterFromDB);
		String sTituloReceta = jsonObj.getString("tituloReceta");
		String sTextoReceta  = jsonObj.getString("textoReceta");
		Short shComensalesReceta = (short) jsonObj.getInt("comensalesReceta");
		float fDuracionReceta = (float) jsonObj.getDouble("duracionReceta");
		String sNombreUsuario = jsonObj.getString("usuarionombreUsuario");
		boolean booEnRevision = intToBoolean(jsonObj.getInt("enRevision"));

		return new Receta(iIdReceta, fechaCreacionReceta, sTituloReceta, booEnRevision, new Usuario(sNombreUsuario),
				sTextoReceta, shComensalesReceta, fDuracionReceta);
	}
	
	private static boolean intToBoolean(int i) {
		boolean boo = false;
		if (i == 1) {
			boo = true;
		}
		return boo;
	}
}
