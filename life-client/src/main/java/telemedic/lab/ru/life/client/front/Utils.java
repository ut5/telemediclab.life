/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telemedic.lab.ru.life.client.front;

import java.awt.List;
import java.util.ArrayList;
import javax.swing.table.TableModel;

/**
 *
 * @author UT5
 */
public class Utils {
    
    public static String stringToArray(String model) {
        ArrayList<List> rowList = new ArrayList();
        int index = 0;
        char c = 'q';
        while(c!=']'){
            
        }
            for (int col=0;col<model.getColumnCount();col++){
                LifeCell lifeCell = (LifeCell) model.getValueAt(row, col);
                if (lifeCell == null){
                    builder.append("0");
                }
                builder.append(lifeCell.getState()?"1":"0");
                if (col + 1 <model.getColumnCount()){
                    builder.append(",");
                }
            }
            builder.append("]");
            if(row + 1<model.getRowCount()){
                builder.append(",");
            }
        }
        return builder.toString();
    }
    
    
    public static String modelToString(TableModel model) {
        StringBuilder builder = new StringBuilder();
        for (int row=0;row<model.getRowCount();row++){
            builder.append("[");
            for (int col=0;col<model.getColumnCount();col++){
                LifeCell lifeCell = (LifeCell) model.getValueAt(row, col);
                if (lifeCell == null){
                    builder.append("0");
                }
                builder.append(lifeCell.getState()?"1":"0");
                if (col + 1 <model.getColumnCount()){
                    builder.append(",");
                }
            }
            builder.append("]");
            if(row + 1<model.getRowCount()){
                builder.append(",");
            }
        }
        return builder.toString();
    }
}
