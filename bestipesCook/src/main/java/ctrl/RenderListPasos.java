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

import ctrl.RenderListPasos.ListRenderer;
import logic.RecetaLogic;
import model.constantes.IConstantes;
import model.constantes.InfoData;
import view.FrmPrincipal;
import view.RecetaDetalle;

public class RenderListPasos implements IConstantes{

	private final HashMap<String, ImageIcon> imageMap;

	public RenderListPasos() {
		DefaultListModel<Object> listModelPasos = new DefaultListModel<Object>();

		logic.RecetaLogic.lstPasos.forEach(p -> listModelPasos.addElement(p.getbOrdenPaso() + ". " + p.getsTextoPaso()));

		imageMap = createImageMap();

		RecetaDetalle.listPasos.setModel(listModelPasos);
		RecetaDetalle.listPasos.setCellRenderer(new ListRenderer());
	}

	public class ListRenderer extends DefaultListCellRenderer {

		@Override
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

			try {
				URL url = new URL(imageMap.get((String) value) + "");
				Image image = ImageIO.read(url);
				ImageIcon imgIcon = new ImageIcon(image);
				imgIcon = Ctrl_Imagen.optimizarImagen(imgIcon);
				label.setIcon(imgIcon);
			} catch (IOException e) {
				e.getMessage();
			}

			label.setHorizontalTextPosition(JLabel.RIGHT);
			label.setFont(FONTLISTASDETALLE);
			return label;
		}
	}

	private HashMap<String, ImageIcon> createImageMap() {
		HashMap<String, ImageIcon> map = new HashMap<>();

		for (int i = 0; i < RecetaLogic.lstRecetas.size(); i++) {
			
				logic.RecetaLogic.lstPasos.forEach(p -> {
					try {
						map.put(p.getbOrdenPaso() + ". " + p.getsTextoPaso(),new ImageIcon(new URL(
								InfoData.URI_MEDIA + p.getImagen().getRutaRelativaImagen())));
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
		}

		return map;
	}

}
