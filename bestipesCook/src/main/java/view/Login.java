package view;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import ctrl.Ctrl_Login;
import ctrl.Utils;
import model.constantes.Colors;
import model.constantes.IConstantes;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.FlowLayout;

public class Login extends JDialog implements IConstantes{

	private final JPanel contentPanel = new JPanel();
	public static JTextField txtUsuario;
	public static JPasswordField txtPassword;
	public static Login window;

	public Login() {
		window = this;
		setTitle("Login");
		setModal(true);
		setResizable(false);
		Utils.centarlVentana(window, iAnchoLog, iAltoLog);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 204, 102));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}});
		
		JButton userBtn = new JButton(new ImageIcon("src/recursos/logo_icon.png"));
		userBtn.setContentAreaFilled(false);
		userBtn.setBorderPainted(false);
		contentPanel.add(userBtn, BorderLayout.NORTH);
		
		JButton conectarBtn = new JButton(new ImageIcon("src/recursos/acceder_icon.png"));
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
		
		
		// EVENTOS
		conectarBtn.addActionListener(e -> Ctrl_Login.conectar());
		txtUsuario.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					Ctrl_Login.conectar();
				}
			
			}
		});
		txtPassword.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					Ctrl_Login.conectar();
				}
			}
		});
		
		setVisible(true);
		
		
	}
}
