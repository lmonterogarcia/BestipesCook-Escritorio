package logic;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.common.hash.Hashing;

import ctrl.Libreria;
import model.constantes.InfoData;
import view.Login;

public class LoginLogic {

	public static boolean verificarAdmin(String sUser, String sha256hexPass) throws IOException {
		boolean booPasar = false;
		String sUrl = InfoData.URI + InfoData.URI_LOGIN;
		
		JSONObject jsonObj = new JSONObject();

		jsonObj.put("usuario", sUser);
		jsonObj.put("pass", sha256hexPass);
		
		String sUserBack = Libreria.peticionHttpPostJson(sUrl, jsonObj);
		String sUserHash = Hashing.sha256()
		  .hashString(sUser, StandardCharsets.UTF_8)
		  .toString();
		
		if (sUserBack.equals(sUserHash) && !sUserBack.equals("")) {
			booPasar = true;
		}
		
//		if (sUserBack.equals(sUser) && !sUserBack.equals("")) {
//			booPasar = true;
//		}
		
		return booPasar;
	}
	
}
