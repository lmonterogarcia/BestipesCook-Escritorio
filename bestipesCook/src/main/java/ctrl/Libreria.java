package ctrl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.FormBody.Builder;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Libreria {

	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

	public static String peticionHttp(String urlWebService) throws IOException {
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder().url(urlWebService).build();

		Response response = client.newCall(request).execute();

		return response.body().string();
	}

	public static String peticionHttpPostJson(String urlWebService, JSONObject json) throws IOException {
		OkHttpClient client = new OkHttpClient();
		String sKey;

		// EJEMPLOS
		//RequestBody body = RequestBody.create(json.toString(), JSON);
		//RequestBody body = new FormBody.Builder().add("usuario", json.getString("usuario")).add("pass", json.getString("pass")).build();
		
		Builder buildrBody = new FormBody.Builder();
		@SuppressWarnings("unchecked")
		Iterator<String> siKey = json.keys();
		for (int i = 0; i < json.length(); i++) {
			sKey = siKey.next().toString();
			buildrBody.add(sKey, json.getString(sKey));
			
		}
		RequestBody body = buildrBody.build();
		
		
		
		Request request = new Request.Builder().url(urlWebService).post(body).build();

		Response response = client.newCall(request).execute();

		return response.body().string();
	}

	public static String peticionHttp2(String urlWebService) throws IOException {
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
