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

		
		if (sUserBack.equals(sUser) && !sUserBack.equals("")) {
			booPasar = true;
		}
		
		return booPasar;
	}
	
}
