package view;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import ctrl.Ctrl_Login;
import model.Colors;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.FlowLayout;

public class Login extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public static JTextField txtUsuario;
	public static JPasswordField txtPassword;
	public static Login window;
	public static JButton btnRegistrarse;
	public static JButton btnRecordarPassword;
	public static JPanel btn_pnl;

	public Login() {
		window = this;
		setTitle("Login");
		setModal(true);
		setResizable(false);
		setBounds(400, 225, 300, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 204, 102));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}});
		
		JButton userBtn = new JButton(new ImageIcon("src\\recursos\\logo_icon.png"));
		userBtn.setContentAreaFilled(false);
		userBtn.setBorderPainted(false);
		contentPanel.add(userBtn, BorderLayout.NORTH);
		
		JButton conectarBtn = new JButton(new ImageIcon("src\\recursos\\acceder_icon.png"));
		conectarBtn.setContentAreaFilled(false);
		conectarBtn.setBorderPainted(false);
		contentPanel.add(conectarBtn, BorderLayout.SOUTH);
		
		JPanel center_pnl = new JPanel();
		center_pnl.setBackground(new Color(255, 255, 204));
		contentPanel.add(center_pnl, BorderLayout.CENTER);
		center_pnl.setLayout(new BoxLayout(center_pnl, BoxLayout.Y_AXIS));
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setForeground(Colors.cBlack);
		lblUsuario.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 20));
		lblUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
		center_pnl.add(lblUsuario);
		
		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 20));
		txtUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		center_pnl.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblPassword = new JLabel("Contraseña");
		lblPassword.setForeground(Colors.cBlack);
		lblPassword.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 20));
		lblPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
		center_pnl.add(lblPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 20));
		txtPassword.setHorizontalAlignment(SwingConstants.CENTER);
		center_pnl.add(txtPassword);
		txtPassword.setColumns(10);
		
		btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 11));
		btnRegistrarse.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnRegistrarse.setContentAreaFilled(false);
		btnRegistrarse.setBorderPainted(false);
		center_pnl.add(btnRegistrarse);
		
		btnRecordarPassword = new JButton("¿Has olvidado tu contraseña?");
		btnRecordarPassword.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 11));
		btnRecordarPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnRecordarPassword.setContentAreaFilled(false);
		btnRecordarPassword.setBorderPainted(false);
		center_pnl.add(btnRecordarPassword);
		
		btn_pnl = new JPanel();
		btn_pnl.setBackground(new Color(255, 255, 204));
		center_pnl.add(btn_pnl);
		btn_pnl.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 21));
		btnAceptar.setContentAreaFilled(false);
		btnAceptar.setBorderPainted(false);
		btn_pnl.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 21));
		btnCancelar.setContentAreaFilled(false);
		btnCancelar.setBorderPainted(false);
		btn_pnl.add(btnCancelar);
		
		btn_pnl.setVisible(false);
		
		btnAceptar.addActionListener(e -> Ctrl_Login.insertUser());
		btnCancelar.addActionListener(e -> Ctrl_Login.back());
		conectarBtn.addActionListener(e -> Ctrl_Login.conectar());
		btnRecordarPassword.addActionListener(e -> dispose());
		btnRegistrarse.addActionListener(e -> Ctrl_Login.registrar());
		

		setVisible(true);
		
		
	}
}
