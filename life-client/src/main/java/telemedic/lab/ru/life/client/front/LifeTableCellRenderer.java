/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telemedic.lab.ru.life.client.front;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author UT5
 */
public class LifeTableCellRenderer extends DefaultTableCellRenderer{

    private final Color lifeColor = new Color(0x00ff00);
    private final Color deadColor = new Color(0xcccccc);
    private final Color defColor = new Color(0xffffff);
    private final Dimension cellSize = new Dimension(25, 25);

//    @Override
//    public Dimension getPreferredSize() {
//        return cellSize;
//    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if(!isOpaque()){
            setOpaque(true);
        } 
        if (value instanceof LifeCell){
            LifeCell lifeCell = (LifeCell) value;
            if (lifeCell.getState() == null){
                setBackground(defColor);
            }else if(lifeCell.getState()){
                setBackground(lifeColor);
            }else{
                setBackground(deadColor);
            } 
            return this;
        }
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.
    }

}
