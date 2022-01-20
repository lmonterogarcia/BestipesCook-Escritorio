package view;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ctrl.Ctrl_NoticiaDetalle;
import model.InfoData;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.Color;

public class NoticiaDetalle extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public static JTextField txtTitle;
	public static JTextField txtSubTitle;
	public static JTextField txtDate;
	public static TextArea txtDescripcion;
	public static JButton btnEditar;
	public static JButton btnCancelar;
	public static JButton btnGuardar;
	public static JButton btnBorrar;
	public static JDialog ventana;


	public NoticiaDetalle() {
		ventana = this;
		setTitle("Noticia - Edición");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBackground(InfoData.cNaranja);
		contentPanel.setBounds(0, 0, 434, 228);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("IMG");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);


		lblNewLabel.setBounds(265, 11, 159, 80);
		contentPanel.add(lblNewLabel);

		txtTitle = new JTextField();
		txtTitle.setText("Titulo");
		txtTitle.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtTitle.setBackground(InfoData.cNaranja);
		txtTitle.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
		txtTitle.setEditable(false);
		txtTitle.setHorizontalAlignment(SwingConstants.CENTER);
		txtTitle.setBounds(10, 0, 245, 31);
		contentPanel.add(txtTitle);
		txtTitle.setColumns(10);

		txtSubTitle = new JTextField();
		txtSubTitle.setText("Subtitulo");
		txtSubTitle.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtSubTitle.setBackground(InfoData.cNaranja);
		txtSubTitle.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		txtSubTitle.setEditable(false);
		txtSubTitle.setHorizontalAlignment(SwingConstants.CENTER);
		txtSubTitle.setBounds(10, 30, 245, 31);
		contentPanel.add(txtSubTitle);
		txtSubTitle.setColumns(10);

		txtDate = new JTextField();
		txtDate.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtDate.setBackground(InfoData.cNaranja);
		txtDate.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 10));
		txtDate.setEditable(false);
		txtDate.setHorizontalAlignment(SwingConstants.CENTER);
		txtDate.setBounds(10, 61, 245, 31);
		contentPanel.add(txtDate);
		txtDate.setColumns(10);

		txtDescripcion = new TextArea("Descripcion", 3 , 100 , TextArea.SCROLLBARS_VERTICAL_ONLY);
		txtDescripcion.setBackground(InfoData.cRositaPalo);
		txtDescripcion.setEditable(false);
		txtDescripcion.setBounds(10, 97, 414, 131);
		contentPanel.add(txtDescripcion);

		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(InfoData.cNaranja);
		buttonPane.setBounds(0, 228, 434, 33);
		FlowLayout fl_buttonPane = new FlowLayout(FlowLayout.CENTER);
		fl_buttonPane.setHgap(50);
		buttonPane.setLayout(fl_buttonPane);
		getContentPane().add(buttonPane);

		btnGuardar = new JButton("GUARDAR");
		btnGuardar.setVisible(false);
		btnGuardar.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
		btnGuardar.setContentAreaFilled(false);
		btnGuardar.setBorderPainted(false);
		buttonPane.add(btnGuardar);
		
		btnBorrar = new JButton("BORRAR");
		btnBorrar.setVisible(false);
		btnBorrar.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
		btnBorrar.setContentAreaFilled(false);
		btnBorrar.setBorderPainted(false);
		buttonPane.add(btnBorrar);

		btnEditar = new JButton("EDITAR");
		btnEditar.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
		btnEditar.setContentAreaFilled(false);
		btnEditar.setBorderPainted(false);
		buttonPane.add(btnEditar);
		getRootPane().setDefaultButton(btnEditar);

		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
		btnCancelar.setContentAreaFilled(false);
		btnCancelar.setBorderPainted(false);
		buttonPane.add(btnCancelar);


		setResizable(false);
		setVisible(true);
		setModal(true);

		//EVENTOS

		btnEditar.addActionListener(e -> Ctrl_NoticiaDetalle.habilitarEdicion());
		btnCancelar.addActionListener(e -> Ctrl_NoticiaDetalle.deshabilitarEdicion());
		btnGuardar.addActionListener(e -> Ctrl_NoticiaDetalle.updNoticia());
		btnBorrar.addActionListener(e -> Ctrl_NoticiaDetalle.delNoticia());

		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we) {
				Ctrl_NoticiaDetalle.cerrarVentanaDetalle();
			}});
	}
}
