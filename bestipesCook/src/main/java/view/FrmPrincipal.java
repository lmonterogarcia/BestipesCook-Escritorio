package view;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ctrl.Ctrl_FrmPrincipal;
import model.Colors;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.List;
import java.awt.Toolkit;
import java.awt.GridLayout;

public class FrmPrincipal extends JFrame {

	private JPanel contentPane;
	public static List list;
	
	public FrmPrincipal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("src\\recursos\\MyBestCookRecipe2.png"));
		setTitle("Bestipes Cook");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(5, 0));
		setContentPane(contentPane);
		
		JPanel left_pnl = new JPanel();
		left_pnl.setBackground(new Color(255, 204, 102));
		contentPane.add(left_pnl, BorderLayout.WEST);
		left_pnl.setLayout(new GridLayout(8, 1, 0, -25));
		
		
		JButton btnNoticias = new JButton(new ImageIcon("src\\recursos\\noticia_icon.png"));
		btnNoticias.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnNoticias.setContentAreaFilled(false);
		btnNoticias.setBorderPainted(false);
		btnNoticias.setSize(100,100);
		left_pnl.add(btnNoticias);
		
		JLabel lblNoticias = new JLabel("Noticias");
		lblNoticias.setForeground(Colors.cBlack);
		lblNoticias.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 20));
		lblNoticias.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNoticias.setHorizontalAlignment(SwingConstants.CENTER);
		left_pnl.add(lblNoticias);
		
		JButton btnRetos = new JButton(new ImageIcon("src\\recursos\\reto_icon.png"));
		btnRetos.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnRetos.setContentAreaFilled(false);
		btnRetos.setBorderPainted(false);
		btnRetos.setHorizontalTextPosition(SwingConstants.CENTER);
		left_pnl.add(btnRetos);
		
		JLabel lblRetos = new JLabel("Retos");
		lblRetos.setForeground(Colors.cBlack);
		lblRetos.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 20));
		lblRetos.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblRetos.setHorizontalAlignment(SwingConstants.CENTER);
		left_pnl.add(lblRetos);
		
		JButton btnCategorias = new JButton(new ImageIcon("src\\recursos\\categoria_icon.png"));
		btnCategorias.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnCategorias.setContentAreaFilled(false);
		btnCategorias.setBorderPainted(false);
		left_pnl.add(btnCategorias);
		
		JLabel lblCategorias = new JLabel("Categorías");
		lblCategorias.setForeground(Colors.cBlack);
		lblCategorias.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 20));
		lblCategorias.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblCategorias.setHorizontalAlignment(SwingConstants.CENTER);
		left_pnl.add(lblCategorias);
		
		JButton btnRecetas = new JButton(new ImageIcon("src\\recursos\\receta_icon.png"));
		btnRecetas.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnRecetas.setContentAreaFilled(false);
		btnRecetas.setBorderPainted(false);
		left_pnl.add(btnRecetas);
		
		JLabel lblRecetas = new JLabel("Recetas");
		lblRecetas.setForeground(Colors.cBlack);
		lblRecetas.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 20));
		lblRecetas.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblRecetas.setHorizontalAlignment(SwingConstants.CENTER);
		left_pnl.add(lblRecetas);
		
		JPanel center_pnl = new JPanel();
		center_pnl.setBackground(new Color(255, 255, 153));
		contentPane.add(center_pnl, BorderLayout.CENTER);
		center_pnl.setLayout(new BorderLayout(0, 0));
		
		list = new List();
		list.setBackground(new Color(255, 255, 204));
		list.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 40));
		center_pnl.add(list, BorderLayout.CENTER);
		
		JButton btnAdd = new JButton("");
		btnAdd.setIcon(new ImageIcon("src\\recursos\\add_icon.png"));
		btnAdd.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 20));
		btnAdd.setContentAreaFilled(false);
		btnAdd.setBorderPainted(false);
		center_pnl.add(btnAdd, BorderLayout.SOUTH);
		
		btnNoticias.addActionListener(e -> Ctrl_FrmPrincipal.noticiaVentana());
		btnRetos.addActionListener(e -> Ctrl_FrmPrincipal.retoVentana());
		btnCategorias.addActionListener(e -> Ctrl_FrmPrincipal.categoriaVentana());
		btnRecetas.addActionListener(e -> Ctrl_FrmPrincipal.recetaVentana());
		btnAdd.addActionListener(e -> Ctrl_FrmPrincipal.ventanaDetalle(true));
		
		list.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseClicked(java.awt.event.MouseEvent e) {
		        if (e.getClickCount() == 2) {
		        	Ctrl_FrmPrincipal.ventanaDetalle(false);
		        }
		    }
		});
		
		setVisible(true);
	}

}
