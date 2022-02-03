package ctrl;

import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;

import logic.RetoLogic;
import model.constantes.InfoData;
import view.FrmPrincipal;

public class RenderListRetos {

    private final HashMap<String, ImageIcon> imageMap;

    public RenderListRetos() {
    	String[] nameList = new String[RetoLogic.lstRetos.size()];
    	DefaultListModel<Object> listModel = new DefaultListModel<Object>();
    	
    	for(int i = 0; i < nameList.length; i++) {
    		listModel.addElement("  "+RetoLogic.lstRetos.get(i).getTituloReto()+"  -  "+RetoLogic.lstRetos.get(i).getSubtituReto());
		}
    	
        imageMap = createImageMap(nameList);
        
        //imageMap.forEach((key, value) -> System.out.println("[Key] : " + key + " [Value] : " + value));
        
        FrmPrincipal.list.setModel(listModel);
        FrmPrincipal.list.setCellRenderer(new ListRenderer());
    }

    public class ListRenderer extends DefaultListCellRenderer {

        Font font = new Font("Yu Gothic UI Light", Font.BOLD, 24);

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index,boolean isSelected, boolean cellHasFocus) {
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
    	RetoLogic.lstRetos.forEach(reto -> {
			try {
				map.put("  "+reto.getTituloReto()+"  -  "+reto.getSubtituReto(), new ImageIcon(new URL(InfoData.URI_MEDIA+reto.getoImagen().getRutaRelativaImagen())));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		return map;
    }
}