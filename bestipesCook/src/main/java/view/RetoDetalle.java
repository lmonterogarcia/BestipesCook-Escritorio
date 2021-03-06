package view;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.jdesktop.swingx.JXDatePicker;

import ctrl.Ctrl_Imagen;
import ctrl.Ctrl_RetoDetalle;
import model.constantes.InfoData;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.TextArea;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFileChooser;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.ImageIcon;

public class RetoDetalle extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public static Boolean boEdit = false;
	public static JTextField txtTitle;
	public static JTextField txtSubTitle;
	public static JTextField txtDate;
	public static JLabel fechaFinalizacion;
	public static JXDatePicker picker;
	public static TextArea txtDescripcion;
	public static JButton btnEditar;
	public static JButton btnCancelar;
	public static JButton btnGuardar;
	public static JButton btnBorrar;
	public static JDialog ventana;
	public static JLabel lblImg;
	

	public RetoDetalle() {
		ventana = this;
		setTitle("Reto - Edición");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBackground(InfoData.cNaranja);
		contentPanel.setBounds(0, 0, 434, 228);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);

		lblImg = new JLabel("");
		lblImg.setIcon(new ImageIcon(
				"C:\\Users\\Usuario\\Documents\\BestipesCook-Escritorio\\bestipesCook\\src\\recursos\\img_add.png"));
		lblImg.setBounds(295, 11, 120, 99);
		contentPanel.add(lblImg);

		txtTitle = new JTextField();
		txtTitle.setText("Titulo");
		txtTitle.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtTitle.setBackground(InfoData.cNaranja);
		txtTitle.setFont(new Font("Yu Gothic UI", Font.PLAIN, 22));
		txtTitle.setEditable(false);
		txtTitle.setHorizontalAlignment(SwingConstants.CENTER);
		txtTitle.setBounds(10, 0, 275, 31);
		contentPanel.add(txtTitle);
		txtTitle.setColumns(10);

		txtSubTitle = new JTextField();
		txtSubTitle.setText("Subtitulo");
		txtSubTitle.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtSubTitle.setBackground(InfoData.cNaranja);
		txtSubTitle.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		txtSubTitle.setEditable(false);
		txtSubTitle.setHorizontalAlignment(SwingConstants.CENTER);
		txtSubTitle.setBounds(10, 35, 275, 31);
		contentPanel.add(txtSubTitle);
		txtSubTitle.setColumns(10);

		txtDate = new JTextField();
		txtDate.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtDate.setBackground(InfoData.cNaranja);
		txtDate.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		txtDate.setEditable(false);
		txtDate.setHorizontalAlignment(SwingConstants.CENTER);
		
		picker = new JXDatePicker();
	    picker.setDate(Calendar.getInstance().getTime());
	    picker.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
	    picker.setBounds(10, 84, 265, 26);
	    picker.setEditable(false);

	    contentPanel.add(picker);

		txtDescripcion = new TextArea("Descripcion", 3, 100, TextArea.SCROLLBARS_VERTICAL_ONLY);
		txtDescripcion.setBackground(InfoData.cRositaPalo);
		txtDescripcion.setEditable(false);
		txtDescripcion.setBounds(10, 116, 414, 112);
		contentPanel.add(txtDescripcion);

		fechaFinalizacion = new JLabel("Fecha de finalización:");
		fechaFinalizacion.setBounds(10, 70, 147, 14);
		contentPanel.add(fechaFinalizacion);

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

		// EVENTOS

		btnEditar.addActionListener(e -> Ctrl_RetoDetalle.habilitarEdicion());
		btnCancelar.addActionListener(e -> Ctrl_RetoDetalle.deshabilitarEdicion());
		btnGuardar.addActionListener(e -> Ctrl_RetoDetalle.updReto());
		btnBorrar.addActionListener(e -> Ctrl_RetoDetalle.delReto());

		
			
			lblImg.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (boEdit) {
						JFileChooser chooser = new JFileChooser();
						chooser.showOpenDialog(null);
						File f = chooser.getSelectedFile();
						Ctrl_Imagen.previsualizarImgReto(f.getAbsolutePath());
						/*
						 * try { JFileChooser chooser = new JFileChooser();
						 * chooser.showOpenDialog(null); File f = chooser.getSelectedFile(); path = f +
						 * “”; filename = f.getAbsolutePath(); ImageIcon imgThisImg = new ImageIcon(new
						 * ImageIcon(filename) .getImage().getScaledInstance(280, 187,
						 * Image.SCALE_DEFAULT)); jLabel1.setIcon(imgThisImg); File image = new
						 * File(filename); FileInputStream fis = new FileInputStream(image);
						 * ByteArrayOutputStream bos = new ByteArrayOutputStream(); byte[] buf = new
						 * byte[1024]; for (int readNum; (readNum = fis.read(buf)) != -1;) {
						 * bos.write(buf, 0, readNum); } person_image = bos.toByteArray(); } catch
						 * (HeadlessException | IOException e) { JOptionPane.showMessageDialog(null, e,
						 * “Error”, JOptionPane.ERROR_MESSAGE); }
						 */
					}
				}
			});

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				Ctrl_RetoDetalle.cerrarVentanaDetalle();
				dispose();
			}
		});
	}
}
