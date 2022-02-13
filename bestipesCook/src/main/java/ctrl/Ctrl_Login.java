package ctrl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.swing.JOptionPane;

import com.google.common.hash.Hashing;

import view.Login;
import view.RecetaDetalle;

public class Ctrl_Login {

	public static void conectar() {
		
		String sha256hexPass = Hashing.sha256()
				  .hashString(String.valueOf(Login.txtPassword.getPassword()), StandardCharsets.UTF_8)
				  .toString().toUpperCase();
		
		try {
			if (logic.LoginLogic.verificarAdmin(Login.txtUsuario.getText(), sha256hexPass)) {
				Login.window.dispose();
			} else {
				JOptionPane.showMessageDialog(Login.window,
						"Usuario o contraseña incorrectos",
						"Gestión de Login", JOptionPane.PLAIN_MESSAGE);
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(Login.window,
					"No se ha podido verificar las credenciales, si sigue ocurriendo contacte con el equipo de desarrollo",
					"Gestión de Login", JOptionPane.PLAIN_MESSAGE);
			e.printStackTrace();
		}
		
	}
}
