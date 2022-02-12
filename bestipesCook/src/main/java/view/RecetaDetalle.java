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
import static javax.swing.ScrollPaneConstants.*;

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
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import ctrl.Ctrl_Imagen;
import ctrl.Ctrl_NoticiaDetalle;
import ctrl.Ctrl_RecetaDetalle;
import model.constantes.IConstantes;
import model.constantes.InfoData;
import java.awt.Color;

public class RecetaDetalle extends JDialog implements InfoData, IConstantes{

	private final JPanel contentPanel = new JPanel();
	public static JDialog ventana;
	
	public static JList<Object> listIngredientes;
	public static JList<Object> listPasos;
	
	public static JLabel lblTitle;
	public static JLabel lblEstrellas;
	public static JLabel lblEnRevision;
	public static JLabel lblCategoria;
	public static TextArea txtTexto;
	public static JTextField txtComensales;
	public static JTextField txtDuracion;
	public static JTextField txtUsuario;
	public static JCheckBox cbEnRevision;
	public static JLabel lblImg;
	
	public static JButton btnEnRevision;
	public static JButton btnCancelar;
	public static JButton btnQuitarEnRevision;
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
		
		txtTexto = new TextArea("Descripcion", 3, 100, TextArea.SCROLLBARS_VERTICAL_ONLY);
		txtTexto.setForeground(Color.DARK_GRAY);
		txtTexto.setFont(new Font("Yu Gothic UI", Font.PLAIN, 12));
		txtTexto.setBackground(cNaranjaPalo);
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
		txtComensales.setBackground(cNaranjaPalo);
		pnlPrincipal.add(txtComensales);
		txtComensales.setColumns(10);
		
		txtDuracion = new JTextField();
		txtDuracion.setBounds(135, 66, 130, 26);
		txtDuracion.setEnabled(false);
		txtDuracion.setBackground(cNaranjaPalo);
		pnlPrincipal.add(txtDuracion);
		txtDuracion.setColumns(10);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(135, 94, 130, 26);
		txtUsuario.setEnabled(false);
		txtUsuario.setBackground(cNaranjaPalo);
		pnlPrincipal.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		lblEstrellas = new JLabel("3.5 ");
		lblEstrellas.setBounds(297, 0, 29, 31);
		pnlPrincipal.add(lblEstrellas);
		
		JLabel lblEstrella = new JLabel("*");
		lblEstrella.setFont(new Font("Yu Gothic UI", Font.PLAIN, 40));
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
		JScrollPane psPasos = new JScrollPane(listPasos);
        listPasos.setBackground(new Color(255, 255, 204));    
        pnlPasos.add(psPasos, BorderLayout.CENTER);
        
        lblEnRevision = new JLabel("EN REVISIÓN");
        lblEnRevision.setForeground(Color.RED);
        lblEnRevision.setFont(new Font("Yu Gothic UI", Font.BOLD, 35));
        lblEnRevision.setHorizontalAlignment(SwingConstants.CENTER);
        lblEnRevision.setBounds(297, 43, 407, 72);
        pnlPrincipal.add(lblEnRevision);
        
        lblCategoria = new JLabel("Categoria");
        lblCategoria.setHorizontalAlignment(SwingConstants.CENTER);
        lblCategoria.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
        lblCategoria.setBounds(363, 0, 160, 31);
        pnlPrincipal.add(lblCategoria);
		
		JPanel pnlInferior = new JPanel();
		pnlInferior.setBackground(InfoData.cNaranja);
		contentPanel.add(pnlInferior, BorderLayout.SOUTH);

		btnBorrar = new JButton("BORRAR");
		btnBorrar.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
		btnBorrar.setContentAreaFilled(false);
		btnBorrar.setBorderPainted(false);
		pnlInferior.add(btnBorrar);
		
		btnEnRevision = new JButton("PONER EN REVISIÓN");
		btnEnRevision.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
		btnEnRevision.setContentAreaFilled(false);
		btnEnRevision.setBorderPainted(false);
		pnlInferior.add(btnEnRevision);
		getRootPane().setDefaultButton(btnEnRevision);

		btnQuitarEnRevision = new JButton("QUITAR DE REVISIÓN");
		btnQuitarEnRevision.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
		btnQuitarEnRevision.setContentAreaFilled(false);
		btnQuitarEnRevision.setBorderPainted(false);
		pnlInferior.add(btnQuitarEnRevision);
		getRootPane().setDefaultButton(btnQuitarEnRevision);

		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
		btnCancelar.setContentAreaFilled(false);
		btnCancelar.setBorderPainted(false);
		pnlInferior.add(btnCancelar);
		
		setResizable(false);
		setVisible(true);

		// EVENTOS
		
		btnCancelar.addActionListener(e -> Ctrl_RecetaDetalle.cerrarVentanaDetalle());
		btnBorrar.addActionListener(e -> Ctrl_RecetaDetalle.delReceta(BMAILBORRAR));
		btnEnRevision.addActionListener(e -> Ctrl_RecetaDetalle.cambiarEstadoRevision(true, BMAILPONERREVISION));
		btnQuitarEnRevision.addActionListener(e -> Ctrl_RecetaDetalle.cambiarEstadoRevision(false, BMAILQUITARREVISION));
		

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				Ctrl_RecetaDetalle.cerrarVentanaDetalle();
				dispose();
			}
		});

		
	}
}
