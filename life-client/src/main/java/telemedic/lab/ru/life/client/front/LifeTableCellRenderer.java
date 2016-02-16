
package telemedic.lab.ru.life.client.front;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author UT5
 */
public class LifeTableCellRenderer extends DefaultTableCellRenderer{

    private final Color lifeColor = new Color(0x00ff00);
    private final Color defColor = new Color(0xffffff);

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if(!isOpaque()){
            setOpaque(true);
        } 
        if (value instanceof LifeCell){
            LifeCell lifeCell = (LifeCell) value;
            if (lifeCell.isLiving() == null || lifeCell.isLiving() == false){
                setBackground(defColor);
            }else{
                setBackground(lifeColor);
            } 
            return this;
        }
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.
    }

}
