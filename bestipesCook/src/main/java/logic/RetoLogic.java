package logic;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import ctrl.ClienteFTP;
import ctrl.Ctrl_Imagen;
import ctrl.RenderListRetos;
import model.Imagen;
import model.InfoData;
import model.Reto;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import view.RetoDetalle;

public class RetoLogic implements InfoData{
	public static ArrayList<Reto> lstRetos;
	public static void cargarDatos() {

		try {
			lstRetos = getRetos();
			new RenderListRetos();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

		private static ArrayList<Reto> getRetos() throws IOException {
			String url = InfoData.URI + "reto/lst-retos.php";
			String requestHttp = peticionHttp(url);
			return stringToListRetos(requestHttp);
		}

		private static ArrayList<Reto> stringToListRetos(String requestHttp) throws IOException {
			ArrayList<Reto> lstRetos = new ArrayList<Reto>();
			JSONArray jsonArr = new JSONArray(requestHttp);

			for(int i = 0 ; i < jsonArr.length(); i++) {
				JSONObject jsonObj = jsonArr.getJSONObject(i);
				lstRetos.add(objJsonParseRetos(jsonObj));
			}

			return lstRetos;
		}
		
		private static String peticionHttp(String urlWebService) throws IOException{
			OkHttpClient client = new OkHttpClient();
			Request request = new Request.Builder().url(urlWebService).build();

			Response response = client.newCall(request).execute();
			
			return response.body().string();
		}

		private static Reto objJsonParseRetos(JSONObject jsonObj) throws IOException {
			//Extraer los values del objeto JSON
			Integer idReto = jsonObj.getInt("idReto");
			String fechaCreacionReto = jsonObj.getString("fechaCreacionReto");
			String tituloReto = jsonObj.getString("tituloReto");
			String subtituloReto = jsonObj.getString("subtituloReto");
			String textoReto = jsonObj.getString("textoReto");
			String fechaFinalizacionReto = jsonObj.getString("fechaFinalizacionReto");
			Integer imagenidImagen = jsonObj.getInt("imagenidImagen");

			return new Reto(idReto,fechaCreacionReto,tituloReto,subtituloReto,textoReto,fechaFinalizacionReto,ImagenLogic.getImagen(imagenidImagen));
		}
		
		public static void updRetoPHP(Reto oReto){
			String url = "";
			if(Ctrl_Imagen.rutaImagenCargada.equals("")||oReto.getoImagen().getRutaRelativaImagen().equals(new File(Ctrl_Imagen.rutaImagenCargada).getName())) {
				url = InfoData.URI + "reto/upd-reto.php?txtTituloReto="+RetoDetalle.txtTitle.getText()
				+"&txtSubtituloReto="+RetoDetalle.txtSubTitle.getText()
				+"&txtTextoReto="+RetoDetalle.txtDescripcion.getText()
				+"&txtFechaFinalizacionReto="+RetoDetalle.datePicker.getModel().getValue()
				+"&txtIdReto="+oReto.getIdReto()
				+"&txtidImagen="+oReto.getoImagen().getIdImagen();
				
				try {
					peticionHttp(url);
				} catch (IOException e) {
					System.err.println(e.getMessage());
				}
				
			}else {
				//Creamos la nueva imagen
				//Tranferencia del archivo imagen al servidor FTP
				ClienteFTP.start(true);

				//Creacion de la url de la imagen subida al servidor FTP
				String rutaImagen = new File(Ctrl_Imagen.rutaImagenCargada).getName();

				//Inserción en la tabla imagen de la BD el dato imagen con su ubicacion
				ImagenLogic.insImagenPHP(rutaImagen);

				//Peticion del id generado para la tupla generada
				Imagen oImagen = null;
				try {
					oImagen = ImagenLogic.getImagenByUrl(rutaImagen);
				} catch (IOException e) {
					e.getMessage();
				}

				url = InfoData.URI + "reto/upd-reto.php?txtTituloReto="+RetoDetalle.txtTitle.getText()
				+"&txtSubtituloReto="+RetoDetalle.txtSubTitle.getText()
				+"&txtTextoReto="+RetoDetalle.txtDescripcion.getText()
				+"&txtFechaFinalizacionReto="+RetoDetalle.datePicker.getModel().getValue()
				+"&txtIdReto="+oReto.getIdReto()
				+"&txtidImagen="+oImagen.getIdImagen();

				try {
					peticionHttp(url);
				} catch (IOException e) {
					System.err.println(e.getMessage());
				}

				//Borramos la imagen antigua
				//Eliminamos los datos de la imagen en la BD
				ImagenLogic.delImagenPHP(oReto.getoImagen().getIdImagen());
				
				//Eliminamos la imagen del servidor FTP
				Ctrl_Imagen.rutaImagenCargada = InfoData.PATH_IMG+"/"+oReto.getoImagen().getRutaRelativaImagen();
				ClienteFTP.start(false);
			}

		}

		public static void insRetoPHP() throws IOException {
			//Tranferencia del archivo imagen al servidor FTP
			ClienteFTP.start(true);

			//Creacion de la url de la imagen subida al servidor FTP
			String rutaImagen = new File(Ctrl_Imagen.rutaImagenCargada).getName();

			//Inserción en la tabla imagen de la BD el dato imagen con su ubicacion
			ImagenLogic.insImagenPHP(rutaImagen);

			//Peticion del id generado para la tupla generada
			Imagen oImagen = ImagenLogic.getImagenByUrl(rutaImagen);

			//Insertar reto
			String url = InfoData.URI + "reto/ins-reto.php?txtTituloReto="+RetoDetalle.txtTitle.getText()
			+"&txtSubtituloReto="+RetoDetalle.txtSubTitle.getText()
			+"&txtTextoReto="+RetoDetalle.txtDescripcion.getText()
			+"&txtFechaFinalizacionReto="+RetoDetalle.datePicker.getModel().getValue()
			+"&txtImg="+oImagen.getIdImagen();
			try {
				peticionHttp(url);
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		}

		public static void delRetoPHP(Reto oReto) {
			String url = InfoData.URI + "reto/del-reto.php?txtIdReto="+oReto.getIdReto();
			try {
				peticionHttp(url);
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
			//Eliminamos los datos de la imagen en la BD
			ImagenLogic.delImagenPHP(oReto.getoImagen().getIdImagen());
			//Eliminamos la imagen del servidor FTP
			Ctrl_Imagen.rutaImagenCargada = InfoData.PATH_IMG+"/"+oReto.getoImagen().getRutaRelativaImagen();
			ClienteFTP.start(false);
		}


	}
