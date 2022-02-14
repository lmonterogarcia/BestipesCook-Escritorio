package view;


import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import ctrl.Ctrl_CategoriaDetalle;
import ctrl.Utils;
import model.constantes.IConstantes;
import model.constantes.InfoData;

import javax.swing.JCheckBox;

public class CategoriaDetalle extends JDialog implements IConstantes{

	private final JPanel contentPanel = new JPanel();
	public static JTextField txtTitle;
	public static JCheckBox checkBoxChallenge;
	public static JButton btnEditar;
	public static JButton btnCancelar;
	public static JButton btnGuardar;
	public static JButton btnBorrar;
	public static JDialog ventana;

	public CategoriaDetalle() {
		ventana = this;
		setTitle("Categoria - Edición");
		Utils.centarlVentana(ventana, iAnchoCat, iAltoCat);
		getContentPane().setLayout(null);
		contentPanel.setBackground(InfoData.cNaranja);
		contentPanel.setBounds(0, 0, 430, 60);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);

		txtTitle = new JTextField();
		txtTitle.setText("Titulo");
		txtTitle.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtTitle.setBackground(InfoData.cNaranja);
		txtTitle.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
		txtTitle.setEditable(false);
		txtTitle.setHorizontalAlignment(SwingConstants.CENTER);
		txtTitle.setBounds(10, 11, 245, 31);
		contentPanel.add(txtTitle);
		txtTitle.setColumns(10);
		
		checkBoxChallenge = new JCheckBox();
		checkBoxChallenge.setEnabled(false);
		checkBoxChallenge.setText("Reto");
		checkBoxChallenge.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		checkBoxChallenge.setBackground(InfoData.cNaranja);
		checkBoxChallenge.setFont(new Font("Yu Gothic UI", Font.PLAIN, 20));
		checkBoxChallenge.setHorizontalAlignment(SwingConstants.CENTER);
		checkBoxChallenge.setBounds(256, 11, 150, 31);
		contentPanel.add(checkBoxChallenge);

		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(InfoData.cNaranja);
		buttonPane.setBounds(0, 54, 434, 33);
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
		
		txtTitle.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				ctrl.Utils.caractesMaxTF(e, ventana, btnGuardar, txtTitle, 30);
			}
		});

		btnEditar.addActionListener(e -> Ctrl_CategoriaDetalle.habilitarEdicion());
		btnCancelar.addActionListener(e -> Ctrl_CategoriaDetalle.deshabilitarEdicion());
		btnGuardar.addActionListener(e -> Ctrl_CategoriaDetalle.updCategoria());
		btnBorrar.addActionListener(e -> Ctrl_CategoriaDetalle.delCategoria());

		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we) {
				Ctrl_CategoriaDetalle.cerrarVentanaDetalle();
			}});
	}
}
