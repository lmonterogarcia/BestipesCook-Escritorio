package logic;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import ctrl.Libreria;
import model.constantes.InfoData;

public class LoginLogic {

	public static boolean verificarAdmin(String sUser, String sha256hexPass) throws IOException {
		boolean booPasar = false;
		String sUrl = InfoData.URI + InfoData.URI_LOGIN;
		
		JSONObject jsonObj = new JSONObject();

		jsonObj.put("usuario", sUser);
		jsonObj.put("pass", sha256hexPass);
		
		String sUserBack = Libreria.peticionHttpPostJson(sUrl, jsonObj);
		
		if (jsonObjectToUser(obtenerJsonArray(sUserBack)).equals(sUser)) {
			booPasar = true;
		}
		
		return booPasar;
	}
	
	private static String jsonObjectToUser(JSONArray jsonArr) {
		String sUser = "";
		for (int i = 0; i < jsonArr.length(); i++) {

			JSONObject jsonObj = jsonArr.getJSONObject(i);

			sUser = jsonObj.getString("nombreUsuario");

		}
		return sUser;
	}
	
	private static JSONArray obtenerJsonArray(String requestHttp) {
		return new JSONArray(requestHttp);
	}

}
