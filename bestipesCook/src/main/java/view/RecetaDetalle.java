package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import ctrl.Ctrl_Imagen;
import ctrl.Ctrl_NoticiaDetalle;
import ctrl.Ctrl_RecetaDetalle;
import model.constantes.InfoData;

public class RecetaDetalle extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public static JDialog ventana;
	
	public static JTextField txtTitle;
	public static JTextField txtTexto;
	public static JTextField txtComensales;
	public static JTextField txtDuracion;
	public static JTextField txtUsuario;
	
	public static JButton btnEditar;
	public static JButton btnCancelar;
	public static JButton btnGuardar;
	public static JButton btnBorrar;
	

	public RecetaDetalle() {
		
		ventana = this;
		setTitle("Receta - Edición");
		setBounds(100, 100, 950, 600);
		getContentPane().setLayout(new BorderLayout(0, 0));
		contentPanel.setBackground(InfoData.cNaranja);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlPrincipal = new JPanel();
		pnlPrincipal.setBackground(InfoData.cNaranja);
		contentPanel.add(pnlPrincipal, BorderLayout.CENTER);
		pnlPrincipal.setLayout(null);
		
		txtTitle = new JTextField();
		txtTitle.setText("Titulo");
		txtTitle.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtTitle.setBackground(InfoData.cNaranja);
		txtTitle.setFont(new Font("Yu Gothic UI", Font.PLAIN, 22));
		txtTitle.setEditable(false);
		txtTitle.setHorizontalAlignment(SwingConstants.CENTER);
		txtTitle.setBounds(10, 0, 275, 31);
		pnlPrincipal.add(txtTitle);
		txtTitle.setColumns(10);
		
		JPanel pnlInferior = new JPanel();
		pnlInferior.setBackground(InfoData.cNaranja);
		contentPanel.add(pnlInferior, BorderLayout.SOUTH);
		
		btnGuardar = new JButton("GUARDAR");
		btnGuardar.setVisible(false);
		btnGuardar.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
		btnGuardar.setContentAreaFilled(false);
		btnGuardar.setBorderPainted(false);
		pnlInferior.add(btnGuardar);

		btnBorrar = new JButton("BORRAR");
		btnBorrar.setVisible(false);
		btnBorrar.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
		btnBorrar.setContentAreaFilled(false);
		btnBorrar.setBorderPainted(false);
		pnlInferior.add(btnBorrar);

		btnEditar = new JButton("EDITAR");
		btnEditar.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
		btnEditar.setContentAreaFilled(false);
		btnEditar.setBorderPainted(false);
		pnlInferior.add(btnEditar);
		getRootPane().setDefaultButton(btnEditar);

		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
		btnCancelar.setContentAreaFilled(false);
		btnCancelar.setBorderPainted(false);
		pnlInferior.add(btnCancelar);
		
		setResizable(false);
		setVisible(true);

		// EVENTOS
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				Ctrl_RecetaDetalle.cerrarVentanaDetalle();
				dispose();
			}
		});

		
	}

}
