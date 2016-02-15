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
    
    public static ArrayList stringToArray(String model) {
        ArrayList<ArrayList> rowList = new ArrayList();
        int index = 1;
        while(index<model.length()){
            ArrayList row = new ArrayList();
            while(model.charAt(index-1) !=']'){
                row.add(model.charAt(index));
                index += 2;
            }
            index +=2;
            rowList.add(row);
        }
        return rowList;
    }
    
    
    public static String modelToString(TableModel model) {
        StringBuilder builder = new StringBuilder();
        for (int row=0;row<model.getRowCount();row++){
            builder.append("[");
            for (int col=0;col<model.getColumnCount();col++){
                LifeCell lifeCell = (LifeCell) model.getValueAt(row, col);
                if (lifeCell.getState() == null){
                    builder.append("0");
                }else{
                     builder.append(lifeCell.getState()?"1":"0");
                }
                if (col + 1 <model.getColumnCount()){
                    builder.append(",");
                }
            }
            builder.append("]");
            if(row + 1<model.getRowCount()){
                builder.append(",");
            }
        }
        builder.append("#");
        return builder.toString();
    }
    
     public static String arrayToString(ArrayList<ArrayList> model) {
        StringBuilder builder = new StringBuilder();
        for (int row=0;row<model.size();row++){
            builder.append("[");
            ArrayList rowList = model.get(row);
            for (int col=0;col<rowList.size();col++){
                    builder.append(rowList.get(col));
                if (col + 1 <rowList.size()){
                    builder.append(",");
                }
            }
            builder.append("]");
            if(row + 1<model.size()){
                builder.append(",");
            }
        }
        builder.append("#");
        return builder.toString();
    }
    
    public static void listToModel(TableModel model, ArrayList<ArrayList> listModel){
        for (int row = 0; row<model.getRowCount();row++){
            ArrayList rowList = listModel.get(row);
            for (int col = 0; col<model.getColumnCount();col++){
                model.setValueAt(new LifeCell(rowList.get(col).equals('1')), row, col);
            }
        }
    }
     
}
