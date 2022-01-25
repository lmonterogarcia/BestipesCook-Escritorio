package ctrl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import logic.NoticiaLogic;
import model.InfoData;
import view.FrmPrincipal;

public class RenderList {

    private final HashMap<String, ImageIcon> imageMap;

    public RenderList() {
    	String[] nameList = new String[NoticiaLogic.lstNoticias.size()];
    	DefaultListModel listModel = new DefaultListModel();
    	
    	for(int i = 0; i < nameList.length; i++) {
    		listModel.addElement("  "+NoticiaLogic.lstNoticias.get(i).getTituloNoticia()+"  -  "+NoticiaLogic.lstNoticias.get(i).getSubtituloNoticia());
			//nameList[i] = NoticiaLogic.lstNoticias.get(i).getTituloNoticia();
		}
    	
        imageMap = createImageMap(nameList);
        
        //imageMap.forEach((key, value) -> System.out.println("[Key] : " + key + " [Value] : " + value));
        
        FrmPrincipal.list.setModel(listModel);
        FrmPrincipal.list.setCellRenderer(new ListRenderer());
        /*
        JScrollPane scroll = new JScrollPane(list);
        scroll.setPreferredSize(new Dimension(300, 400));

        JFrame frame = new JFrame();
        frame.add(scroll);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        */
    }

    public class ListRenderer extends DefaultListCellRenderer {

        Font font = new Font("Yu Gothic UI Light", Font.BOLD, 24);

        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index,boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            
			try {
				URL url = new URL(imageMap.get((String) value)+"");
				Image image = ImageIO.read(url);
				ImageIcon imgIcon = new ImageIcon(image);
				imgIcon = Ctrl_Imagen.optimizarImagen(imgIcon);
				label.setIcon(imgIcon);
			} catch (IOException e) {
				e.getMessage();
			}

            label.setHorizontalTextPosition(JLabel.RIGHT);
            label.setFont(font);
            return label;
        }
    }

    private HashMap<String, ImageIcon> createImageMap(String[] list) {
    	HashMap<String, ImageIcon> map = new HashMap<>();
		NoticiaLogic.lstNoticias.forEach(noticia -> {
			try {
				map.put("  "+noticia.getTituloNoticia()+"  -  "+noticia.getSubtituloNoticia(), new ImageIcon(new URL(InfoData.URI_MEDIA+noticia.getoImagen().getRutaRelativaImagen())));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		return map;
    }
}