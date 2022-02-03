package ctrl;

import java.awt.Component;
import java.awt.Font;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;

import logic.CategoriaLogic;
import view.FrmPrincipal;

public class RenderListCategoria {

    public RenderListCategoria() {
    	String[] nameList = new String[CategoriaLogic.lstCategorias.size()];
    	DefaultListModel<Object> listModel = new DefaultListModel<Object>();
    	
    	for(int i = 0; i < nameList.length; i++) {
    		listModel.addElement("  "+CategoriaLogic.lstCategorias.get(i).getNombreCategoria());
		}
        
        //imageMap.forEach((key, value) -> System.out.println("[Key] : " + key + " [Value] : " + value));
        
        FrmPrincipal.list.setModel(listModel);
        FrmPrincipal.list.setCellRenderer(new ListRenderer());
    }

    public class ListRenderer extends DefaultListCellRenderer {

        Font font = new Font("Yu Gothic UI Light", Font.BOLD, 24);

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index,boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            
            label.setHorizontalTextPosition(JLabel.RIGHT);
            label.setFont(font);
            return label;
        }
    }
}