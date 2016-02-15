   
package telemedic.lab.ru.life.web.app;

import java.util.ArrayList;
import javax.swing.table.TableModel;
import telemedic.lab.ru.life.client.front.LifeCell;

/**
 *
 * @author UT5
 */
public class LifeCickle {

    private ArrayList<ArrayList> model;
    private ArrayList<ArrayList> newModel;

    public ArrayList getModel() {
        return newModel;
    }

    public void setModel(ArrayList<ArrayList> model) {
        this.model = model;
    }

    void doIteration(){
        if (model == null){
            return;
        }
        newModel = new ArrayList<>();
        for(int row=0;row<model.size();row++){
            ArrayList rowList = model.get(row);
            ArrayList newRowList = new ArrayList();
            for(int col=0;col<rowList.size();col++){
                    int lifes = getLifeCellCount(row, col);
                    if ((char)rowList.get(col)=='0' && lifes==3){
                        newRowList.add('1');
                        continue;
                    }
                    if ((char)rowList.get(col)=='1'){
                        if (lifes==2 || lifes==3){
                            newRowList.add('1');
                            continue;
                        }
                    }
                    newRowList.add('0');
            }
            newModel.add(newRowList);
        }
    }
    
    private int getLifeCellCount(int row, int col){
        Integer lifeCell = 0;
        if (row == 0){
            lifeCell = iterateLifeCell(lifeCell, (char) model.get(model.size()-1).get(col));
        }else{
            lifeCell = iterateLifeCell(lifeCell, (char) model.get(row-1).get(col));
        }

        if (row == 0){
            lifeCell = iterateLifeCell(lifeCell, (char) model.get(model.size()-1).get(col==model.get(model.size()-1).size()-1?0:col+1));
        }else{
            lifeCell = iterateLifeCell(lifeCell, (char) model.get(row-1).get(col==model.get(model.size()-1).size()-1?0:col+1));
        }
        
        if (col == model.get(row).size()-1){
            lifeCell = iterateLifeCell(lifeCell, (char) model.get(row).get(0));
        }else{
            lifeCell = iterateLifeCell(lifeCell, (char) model.get(row).get(col+1));
        }
        
        if (row == model.size()-1){
            lifeCell = iterateLifeCell(lifeCell, (char) model.get(0).get(col==model.get(model.size()-1).size()-1?0:col+1));
        }else{
            lifeCell = iterateLifeCell(lifeCell, (char) model.get(row+1).get(col==model.get(model.size()-1).size()-1?0:col+1));
        }
        
        if (row == model.size()-1){
            lifeCell = iterateLifeCell(lifeCell, (char) model.get(0).get(col));
        }else{
            lifeCell = iterateLifeCell(lifeCell, (char) model.get(row+1).get(col));
        }
        
        if (row == model.size()-1){
            lifeCell = iterateLifeCell(lifeCell, (char) model.get(0).get(col==0?model.get(model.size()-1).size()-1:col-1));
        }else{
            lifeCell = iterateLifeCell(lifeCell, (char) model.get(row+1).get(col==0?model.get(model.size()-1).size()-1:col-1));
        }
        
        if (col == 0){
            lifeCell = iterateLifeCell(lifeCell, (char) model.get(row).get(model.get(model.size()-1).size()-1));
        }else{
            lifeCell = iterateLifeCell(lifeCell, (char) model.get(row).get(col-1));
        }
        
        if (row == 0){
            lifeCell = iterateLifeCell(lifeCell, (char) model.get(model.size()-1).get(col==0?model.get(model.size()-1).size()-1:col-1));
        }else{
            lifeCell = iterateLifeCell(lifeCell, (char) model.get(row-1).get(col==0?model.get(model.size()-1).size()-1:col-1));
        }
        
        return lifeCell;
    }
    
    private int iterateLifeCell(Integer lifeCellCount, char value){
        if (value == '1'){
            lifeCellCount++;
        }
        return lifeCellCount;
    }
    
}
