package ctrl;

import view.Login;

public class Ctrl_Login {

	public static void conectar() {
		if(Login.txtUsuario.getText().equals("") && String.valueOf(Login.txtPassword.getPassword()).equals("")) {
			Login.window.dispose();
		}
	}

	public static void insertUser() {
		Login.window.dispose();
	}

}
