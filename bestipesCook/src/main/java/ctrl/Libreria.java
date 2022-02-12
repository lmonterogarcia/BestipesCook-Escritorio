package ctrl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Libreria {

	
	public static String peticionHttp(String urlWebService) throws IOException{
		OkHttpClient client = new OkHttpClient();
		
		
		// #################### POST #################
		
		//RequestBody formBody = new FormBody.Builder().add("hola", "qwerty").build();
		
		
		//Request request = new Request.Builder().url(urlWebService).post(formBody).build();
		
		
		// #################### POST #################
		
		
		Request request = new Request.Builder().url(urlWebService).build();

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
