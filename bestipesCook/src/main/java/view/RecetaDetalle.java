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
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import ctrl.Ctrl_Imagen;
import ctrl.Ctrl_NoticiaDetalle;
import ctrl.Ctrl_RecetaDetalle;
import model.constantes.InfoData;
import java.awt.Color;

public class RecetaDetalle extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public static JDialog ventana;
	
	public static JList<Object> listIngredientes;
	public static JList<Object> listPasos;
	
	public static JLabel lblTitle;
	public static JLabel lblEstrellas;
	public static TextArea txtTexto;
	public static JTextField txtComensales;
	public static JTextField txtDuracion;
	public static JTextField txtUsuario;
	public static JCheckBox cbEnRevision;
	public static JLabel lblImg;
	
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
		
		lblTitle = new JLabel();
		lblTitle.setText("Título");
		lblTitle.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		lblTitle.setBackground(InfoData.cNaranja);
		lblTitle.setFont(new Font("Yu Gothic UI", Font.PLAIN, 22));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(10, 0, 275, 31);
		pnlPrincipal.add(lblTitle);
		
		JLabel lblComensales = new JLabel("Comensales:");
		lblComensales.setBounds(38, 43, 85, 16);
		pnlPrincipal.add(lblComensales);
		
		JLabel lblDuracion = new JLabel("Duración (horas):");
		lblDuracion.setBounds(10, 71, 114, 16);
		pnlPrincipal.add(lblDuracion);
		
		JLabel lblUsuario = new JLabel("Autor:");
		lblUsuario.setBounds(79, 99, 45, 16);
		pnlPrincipal.add(lblUsuario);
		
		cbEnRevision = new JCheckBox("EN REVISIÓN");
		cbEnRevision.setBounds(412, 67, 128, 23);
		cbEnRevision.setEnabled(false);
		pnlPrincipal.add(cbEnRevision);
		
		
		txtTexto = new TextArea("Descripcion", 3, 100, TextArea.SCROLLBARS_VERTICAL_ONLY);
		txtTexto.setForeground(Color.DARK_GRAY);
		txtTexto.setBounds(136, 127, 772, 112);
		txtTexto.setEditable(false);
		pnlPrincipal.add(txtTexto);
		
		JLabel lblTexto = new JLabel("Texto: ");
		lblTexto.setBounds(78, 175, 45, 16);
		pnlPrincipal.add(lblTexto);
		
		lblImg = new JLabel("");
		lblImg.setIcon(new ImageIcon(
				"src/recursos/img_add.png"));
		lblImg.setBounds(748, 11, 160, 104);
		pnlPrincipal.add(lblImg);
		
		txtComensales = new JTextField();
		txtComensales.setBounds(135, 38, 130, 26);
		txtComensales.setEnabled(false);
		pnlPrincipal.add(txtComensales);
		txtComensales.setColumns(10);
		
		txtDuracion = new JTextField();
		txtDuracion.setBounds(135, 66, 130, 26);
		txtDuracion.setEnabled(false);
		pnlPrincipal.add(txtDuracion);
		txtDuracion.setColumns(10);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(135, 94, 130, 26);
		txtUsuario.setEnabled(false);
		pnlPrincipal.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		lblEstrellas = new JLabel("3.5 ");
		lblEstrellas.setBounds(297, 0, 29, 31);
		pnlPrincipal.add(lblEstrellas);
		
		JLabel lblEstrella = new JLabel("*");
		lblEstrella.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
		lblEstrella.setBounds(323, 7, 61, 31);
		pnlPrincipal.add(lblEstrella);
		
		JLabel lblIngredientes = new JLabel("Ingredientes");
		lblIngredientes.setBounds(146, 245, 85, 16);
		pnlPrincipal.add(lblIngredientes);
		
		JLabel lblPasos = new JLabel("Pasos");
		lblPasos.setBounds(363, 245, 45, 16);
		pnlPrincipal.add(lblPasos);
		
		JPanel pnlIngredientes = new JPanel();
		pnlIngredientes.setBounds(136, 269, 210, 243);
		pnlPrincipal.add(pnlIngredientes);
		pnlIngredientes.setLayout(new BorderLayout(0, 0));
		
		listIngredientes = new JList<Object>();
        JScrollPane psIngredientes = new JScrollPane(listIngredientes);
        listIngredientes.setBackground(new Color(255, 255, 204));    
        pnlIngredientes.add(psIngredientes, BorderLayout.CENTER);
		
		JPanel pnlPasos = new JPanel();
		pnlPasos.setBounds(363, 269, 545, 243);
		pnlPrincipal.add(pnlPasos);
		pnlPasos.setLayout(new BorderLayout(0, 0));
		
		listPasos = new JList<Object>();
        JScrollPane panelScroll = new JScrollPane(listPasos);
        listPasos.setBackground(new Color(255, 255, 204));    
        pnlPasos.add(panelScroll, BorderLayout.CENTER);
		
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
