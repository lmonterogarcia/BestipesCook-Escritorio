package logic;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import ctrl.Utils;
import ctrl.RenderListCategoria;
import model.categoria.Categoria;
import model.constantes.InfoData;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import view.CategoriaDetalle;

public class CategoriaLogic implements InfoData{
	public static ArrayList<Categoria> lstCategorias;
	
	public static void cargarDatos() {

		try {
			lstCategorias = getCategorias();
			new RenderListCategoria();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static ArrayList<Categoria> getCategorias() throws IOException {
		String url = InfoData.URI + "categoria/lst-categorias.php";
		String requestHttp = Utils.peticionHttp(url);
		return stringToListCategoria(requestHttp);
	}


	private static ArrayList<Categoria> stringToListCategoria(String requestHttp) {
		ArrayList<Categoria> lstCategorias = new ArrayList<Categoria>();
		JSONArray jsonArr = new JSONArray(requestHttp);

		for(int i = 0 ; i < jsonArr.length(); i++) {
			JSONObject jsonObj = jsonArr.getJSONObject(i);
			lstCategorias.add(objJsonParseCategoria(jsonObj));
		}

		return lstCategorias;
	}
	

	private static Categoria objJsonParseCategoria(JSONObject jsonObj) {
		//Extraer los values del objeto JSON
		Integer idCategoria = jsonObj.getInt("idCategoria");
		String nombreCategoria = jsonObj.getString("nombreCategoria");
		String challengueGet = jsonObj.getString("challenge");
		Boolean challengue;
		if ("1".equals(challengueGet)) {
			challengue = true;
		}else {
			challengue = false;
		}

		return new Categoria(idCategoria,nombreCategoria,challengue);
	}

	public static void updCategoriaPHP(Categoria oCategoria) {

		String sChallenge;
		if (CategoriaDetalle.checkBoxChallenge.isSelected()) {
			sChallenge = "1";				
		}else {
			sChallenge = "0";
		}

		String url = InfoData.URI + "categoria/upd-categoria.php?txtNombreCategoria="+CategoriaDetalle.txtTitle.getText()
		+"&txtChallenge="+sChallenge
		+"&txtIdCategoria="+oCategoria.getIdCategoria();
		try {
			Utils.peticionHttp(url);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	public static void insCategoriaPHP() {

		String sChallenge;
		if (CategoriaDetalle.checkBoxChallenge.isSelected()) {
			sChallenge = "1";				
		}else {
			sChallenge = "0";
		}

		String url = InfoData.URI + "categoria/ins-categoria.php?txtNombreCategoria="+CategoriaDetalle.txtTitle.getText()
		+"&txtChallenge="+sChallenge;
		try {
			Utils.peticionHttp(url);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	public static void delCategoriaPHP(Categoria oCategoria) {
		String url = InfoData.URI + "categoria/del-categoria.php?txtIdCategoria="+oCategoria.getIdCategoria();
		try {
			Utils.peticionHttp(url);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}


}
