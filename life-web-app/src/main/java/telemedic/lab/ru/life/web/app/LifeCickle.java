
package telemedic.lab.ru.life.web.app;

import javax.swing.table.TableModel;
import telemedic.lab.ru.life.client.front.LifeCell;

/**
 *
 * @author UT5
 */
public class LifeCickle {

    private TableModel model;

    public TableModel getModel() {
        return model;
    }

    public void setModel(TableModel model) {
        this.model = model;
    }

    void doIteration(){
        if (model == null){
            return;
        }
        for(int row=0;row<model.getRowCount();row++){
            for(int col=0;col<model.getRowCount();col++){
                Object value = model.getValueAt(row, col);
                if (value instanceof LifeCell){
                    LifeCell lifeCell = (LifeCell) value;
                    if (lifeCell.getState() == null || lifeCell.getState() == Boolean.FALSE){
                        lifeCell.setState(Boolean.TRUE);
                    }else{
                        lifeCell.setState(Boolean.FALSE);
                    }
                }
            }
        }
    }
}
