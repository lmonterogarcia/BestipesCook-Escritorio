package logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Categoria;
import model.InfoData;
import model.Noticia;
import view.CategoriaDetalle;
import view.FrmPrincipal;
import view.NoticiaDetalle;

public class CategoriaLogic implements InfoData{
	public static ArrayList<Categoria> lstCategorias;
	public static void cargarDatos() {

		try {
			lstCategorias = getCategorias();
			lstCategorias.forEach(categoria -> FrmPrincipal.list.add(categoria.getNombreCategoria()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

		private static ArrayList<Categoria> getCategorias() throws IOException {
			String url = InfoData.URI + "categoria/lst-categorias.php";
			String requestHttp = peticionHttp(url);
			return stringToListCategoria(requestHttp);
		}

		private static String peticionHttp(String urlWebService) throws IOException{
			StringBuilder resultado = new StringBuilder();
			
			//Formatear espacios
			if(urlWebService.contains(" "))
				urlWebService = urlWebService.replace(" ", "%20");

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
				peticionHttp(url);
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
				peticionHttp(url);
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		}

		public static void delCategoriaPHP(Categoria oCategoria) {
			String url = InfoData.URI + "categoria/del-categoria.php?txtIdCategoria="+oCategoria.getIdCategoria();
			try {
				peticionHttp(url);
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		}


	}
