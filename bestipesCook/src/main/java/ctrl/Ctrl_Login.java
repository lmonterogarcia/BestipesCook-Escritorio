package ctrl;

import view.Login;

public class Ctrl_Login {

	public static void conectar() {
		if(Login.txtUsuario.getText().equals("admin") && String.valueOf(Login.txtPassword.getPassword()).equals("admin")) {
			Login.window.dispose();
		}
	}

	public static void registrar() {
		Login.btnRecordarPassword.setVisible(false);
		Login.btnRegistrarse.setVisible(false);
		Login.btn_pnl.setVisible(true);
	}

	public static void insertUser() {
		Login.window.dispose();
	}

	public static void back() {
		Login.btnRecordarPassword.setVisible(true);
		Login.btnRegistrarse.setVisible(true);
		Login.btn_pnl.setVisible(false);
	}

}
