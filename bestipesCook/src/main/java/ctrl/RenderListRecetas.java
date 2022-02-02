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

import ctrl.RenderListRecetas.ListRenderer;
import logic.RecetaLogic;
import model.constantes.InfoData;
import view.FrmPrincipal;

public class RenderListRecetas {

	private final HashMap<String, ImageIcon> imageMap;

	public RenderListRecetas() {
		String[] recetaList = new String[RecetaLogic.lstRecetas.size()];
		DefaultListModel<Object> listModel = new DefaultListModel<Object>();

		for (int i = 0; i < recetaList.length; i++) {
			listModel.addElement(
					"  " + RecetaLogic.lstRecetas.get(i).getsTituloReceta() + " - " + RecetaLogic.lstEstrellas.get(i));
		}

		imageMap = createImageMap();

		// imageMap.forEach((key, value) -> System.out.println("[Key] : " + key + "
		// [Value] : " + value));

		FrmPrincipal.list.setModel(listModel);
		FrmPrincipal.list.setCellRenderer(new ListRenderer());
	}

	public class ListRenderer extends DefaultListCellRenderer {

		Font font = new Font("Yu Gothic UI Light", Font.BOLD, 24);

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
			label.setFont(font);
			return label;
		}
	}

	private HashMap<String, ImageIcon> createImageMap() {
		HashMap<String, ImageIcon> map = new HashMap<>();

		for (int i = 0; i < RecetaLogic.lstRecetas.size(); i++) {
			try {
				map.put("  " + RecetaLogic.lstRecetas.get(i).getsTituloReceta() + " - "
						+ RecetaLogic.lstEstrellas.get(i),
						new ImageIcon(new URL(
								InfoData.URI_MEDIA + RecetaLogic.lstImagenesPral.get(i).getRutaRelativaImagen())));
			} catch (MalformedURLException e) {

				e.printStackTrace();
			}
		}

		return map;
	}

}
