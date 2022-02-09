package ctrl;

import java.awt.Component;
import java.awt.Font;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;

import logic.CategoriaLogic;
import model.constantes.IConstantes;
import model.receta.IIngredienteReceta;
import view.FrmPrincipal;
import view.RecetaDetalle;

public class RenderListIngredientes implements IIngredienteReceta, IConstantes {

	public RenderListIngredientes() {
		DefaultListModel<Object> listModelIngredientes = new DefaultListModel<Object>();

		logic.RecetaLogic.lstIngredienteRecetas
				.forEach(i -> listModelIngredientes.addElement(i.getoIngrediente().getsNombreIngrediente() + " - "
						+ i.getiCantidadIngrediente() + " " + AMEDIDASDIMINUTIVO[i.getiMedida()]));

		RecetaDetalle.listIngredientes.setModel(listModelIngredientes);
		RecetaDetalle.listIngredientes.setCellRenderer(new ListRenderer());
	}

	public class ListRenderer extends DefaultListCellRenderer {

		@Override
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

			label.setHorizontalTextPosition(JLabel.RIGHT);
			label.setFont(FONTLISTASDETALLE);
			return label;
		}
	}
}