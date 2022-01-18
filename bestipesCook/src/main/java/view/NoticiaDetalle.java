package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ctrl.Ctrl_NoticiaDetalle;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class NoticiaDetalle extends JDialog {

	private final JPanel contentPanel = new JPanel();

	public NoticiaDetalle() {
		setTitle("Noticia detalle");
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 434, 228);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(10, 102, 414, 115);
		contentPanel.add(textArea_1);
		
		JLabel lblNewLabel = new JLabel("New label");
		
		try {
			BufferedImage buffImg = ImageIO.read(new File("src//recursos//img_icon.png"));
			lblNewLabel.setIcon(new ImageIcon(Ctrl_NoticiaDetalle.resizeImage(buffImg, 50, 50)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		lblNewLabel.setBounds(265, 11, 159, 80);
		contentPanel.add(lblNewLabel);

		JPanel buttonPane = new JPanel();
		buttonPane.setBounds(0, 228, 434, 33);
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane);

		JButton okButton = new JButton("OK");
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(0, 0, 5, 22);
		getContentPane().add(textArea);

		setVisible(true);
	}
}
