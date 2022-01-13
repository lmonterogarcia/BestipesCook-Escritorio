package ctrl;

import view.Login;

public class Ctrl_Login {

	public static void conectar() {
		if(Login.txtUsuario.getText().equals("admin") && String.valueOf(Login.txtPassword.getPassword()).equals("admin")) {
			Login.window.dispose();
		}
	}

	public static void insertUser() {
		Login.window.dispose();
	}

}
