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

import model.receta.*;
import model.usuario.Usuario;
import model.Imagen;
import model.categoria.Categoria;
import model.constantes.IConstantes;
import model.constantes.InfoData;

public class RecetaLogic implements InfoData, IConstantes {

	public static ArrayList<Receta> lstRecetas = new ArrayList<Receta>();
	public static ArrayList<Float> lstEstrellas = new ArrayList<Float>();
	public static ArrayList<Imagen> lstImagenesPral = new ArrayList<Imagen>();

	public static ArrayList<Paso> lstPasos = new ArrayList<Paso>();
	public static ArrayList<IngredienteReceta> lstIngredienteRecetas = new ArrayList<IngredienteReceta>();

	public static void cargarDatos() {
		try {
			lstRecetas.clear();
			lstEstrellas.clear();
			lstImagenesPral.clear();

			getRecetas();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void cargarDatosReceta(int iPosicion) {

		int iIdReceta = lstRecetas.get(iPosicion).getiIdReceta();

		try {
			lstPasos.clear();
			lstIngredienteRecetas.clear();

			getRecetaPasos(iIdReceta);
			getRecetaIngredientes(iIdReceta);


		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void getRecetas() throws IOException {
		String url = InfoData.URI + URI_RECETA + URI_LSTRECETAS;
		String requestHttp = peticionHttp(url);
		jsonObjectToRecetas(obtenerJsonArray(requestHttp));

	}

	private static void getRecetaPasos(int iIdReceta) throws IOException {
		String url = InfoData.URI + URI_RECETA + URI_GETRECETAPASOS + iIdReceta;
		String requestHttp = peticionHttp(url);
		jsonObjectToPasos(obtenerJsonArray(requestHttp));

	}

	private static void getRecetaIngredientes(int iIdReceta) throws IOException {
		String url = InfoData.URI + URI_RECETA + URI_GETRECETAINGREDIENTES + iIdReceta;
		String requestHttp = peticionHttp(url);
		jsonObjectToIngredientes(obtenerJsonArray(requestHttp));

	}

	public static boolean delReceta(int iIdReceta) throws IOException {
		boolean booRespuesta = false;
		String url = InfoData.URI + URI_RECETA + URI_DEL_RECETA + iIdReceta;
		String requestHttp = peticionHttp(url);
		
		if (requestHttp.equals("200")) {
			booRespuesta = true;
		}
		
		return booRespuesta;

	}

	public static boolean updReceta(int iIdReceta, boolean booEnRevision) throws IOException {
		boolean booRespuesta = false;
		byte bEnRevision = booleanToInt(booEnRevision);
		
		String url = InfoData.URI + URI_RECETA + URI_UPD_RECETA + iIdReceta + URI_ENREVISION + bEnRevision;
		String requestHttp = peticionHttp(url);
		
		if (requestHttp.equals("200")) {
			booRespuesta = true;
		}
		
		return booRespuesta;

	}

	private static JSONArray obtenerJsonArray(String requestHttp) {
		return new JSONArray(requestHttp);
	}

	private static void jsonObjectToRecetas(JSONArray jsonArr) {

		for (int i = 0; i < jsonArr.length(); i++) {

			JSONObject jsonObj = jsonArr.getJSONObject(i);

			Integer iIdReceta = jsonObj.getInt("idReceta");
			LocalDateTime fechaCreacionReceta = LocalDateTime.parse(jsonObj.getString("fechaCreacionReceta"),
					dateTimeformatterFromDB);
			String sTituloReceta = jsonObj.getString("tituloReceta");
			String sTextoReceta = jsonObj.getString("textoReceta");
			Short shComensalesReceta = (short) jsonObj.getInt("comensalesReceta");
			float fDuracionReceta = (float) jsonObj.getDouble("duracionReceta");
			String sNombreUsuario = jsonObj.getString("usuarionombreUsuario");
			String sEmailUsuario = jsonObj.getString("emailUsuario");
			Integer idCategoria = jsonObj.getInt("idCategoria");
			String sNombreCategoria = jsonObj.getString("nombreCategoria");
			boolean booChallenge = intToBoolean(jsonObj.getInt("challenge"));
			boolean booEnRevision = intToBoolean(jsonObj.getInt("enRevision"));

			lstRecetas.add(new Receta(iIdReceta, fechaCreacionReceta, sTituloReceta, booEnRevision,
					new Usuario(sNombreUsuario, sEmailUsuario), new Categoria(idCategoria, sNombreCategoria, booChallenge),
					sTextoReceta, shComensalesReceta, fDuracionReceta));

			lstEstrellas.add((float) (jsonObj.getDouble("puntuacionMedia")));

			Integer iIdImagen = jsonObj.getInt("idImagen");
			LocalDateTime fechaCreacionImagen = LocalDateTime.parse(jsonObj.getString("fechaCreacionImagen"),
					dateTimeformatterFromDB);
			String sRuta = jsonObj.getString("rutaRelativaImagen");

			lstImagenesPral.add(new Imagen(iIdImagen, fechaCreacionImagen, sRuta));
		}

	}

	private static void jsonObjectToPasos(JSONArray jsonArr) {
		for (int i = 0; i < jsonArr.length(); i++) {

			JSONObject jsonObj = jsonArr.getJSONObject(i);

			Integer iIdPaso = jsonObj.getInt("idPaso");
			String sTextoPaso = jsonObj.getString("textoPaso");
			byte bOrden = (byte) jsonObj.getInt("ordenPaso");
			Receta oReceta = new Receta(jsonObj.getInt("idReceta"));
			Imagen oImagen = new Imagen(jsonObj.getInt("idImagen"),
					LocalDateTime.parse(jsonObj.getString("fechaCreacionImagen"), dateTimeformatterFromDB),
					jsonObj.getString("rutaRelativaImagen"));
			lstPasos.add(new Paso(iIdPaso, sTextoPaso, bOrden, oReceta, oImagen));
		}
	}

	private static void jsonObjectToIngredientes(JSONArray jsonArr) {
		for (int i = 0; i < jsonArr.length(); i++) {

			JSONObject jsonObj = jsonArr.getJSONObject(i);

			Integer iIdReceta = jsonObj.getInt("idReceta");
			Integer iIdIngrediente = jsonObj.getInt("idIngrediente");
			String sNombreIngrediente = jsonObj.getString("nombreIngrediente");
			Integer iCantidadIngrediente = jsonObj.getInt("cantidadIngrediente");
			Integer iMedida = jsonObj.getInt("medida");

			lstIngredienteRecetas.add(new IngredienteReceta(new Receta(iIdReceta),
					new Ingrediente(iIdIngrediente, sNombreIngrediente), iCantidadIngrediente, iMedida));

		}
	}

	private static boolean intToBoolean(int i) {
		boolean boo = false;
		if (i == 1) {
			boo = true;
		}
		return boo;
	}
	
	private static byte booleanToInt(boolean boo) {
		byte bEnRevision = 0;
		if (boo) {
			bEnRevision = 1;
		}
		return bEnRevision;
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

}
