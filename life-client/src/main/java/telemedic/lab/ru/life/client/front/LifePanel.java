/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telemedic.lab.ru.life.client.front;

import java.awt.MouseInfo;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author UT5
 */
public class LifePanel extends JPanel{
    
    private static final Integer ROW_COUNT = 10;
    private static final Integer COL_COUNT = 20;
    private static final Integer CELL_SIZE = 25;
    
    private TableModel lifeModel;
    private final JTable lifeTable = new JTable(); 
    private final JLabel status = new JLabel();
    private final JButton startButton = new JButton();
    private boolean activeMouseListener = true;
    static private LifePanel instance;
    
    
    synchronized public static  LifePanel getInstance(){
        if(instance == null){
            instance = new LifePanel();
        }
        return instance;
    }
    
    private LifePanel() {
        confLifeTable();
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGap(10)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(status)
                        .addComponent(lifeTable)
                )
                
                .addGap(10)
                .addComponent(startButton,70,70,70)
                .addGap(10)
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                
                .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, Short.MAX_VALUE)
                        .addComponent(status)
                        .addGap(10)
                        .addComponent(lifeTable)
                        .addGap(10, 10, Short.MAX_VALUE)
                )
                .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, Short.MAX_VALUE)
                        .addComponent(startButton,70,70,70)
                        .addGap(10, 10, Short.MAX_VALUE)
                )
        );
       startButton.setAction(new LetsPlayAction(status));
       startButton.setBorderPainted(false); 
        startButton.setContentAreaFilled(false); 
        startButton.setFocusPainted(false); 
        startButton.setOpaque(false);
       lifeTable.addMouseListener(new MouseAdapter() {
           
           @Override
           public void mousePressed(MouseEvent e) {
               if (!activeMouseListener){
                   return;
               }
               Object selValue = lifeTable.getValueAt(lifeTable.getSelectedRow(), lifeTable.getSelectedColumn());
               if (selValue instanceof LifeCell){
                   LifeCell lifeCell = (LifeCell) selValue;
                   
                   lifeCell.setState(lifeCell.getState()==null? true:null);
                   lifeTable.revalidate();
                   lifeTable.repaint();
               }
           }
       });
       status.setText("Задайте  живые клетки");
    }

    private void confModel(){
        for (int row=0;row<lifeModel.getRowCount();row++){
            for (int col=0;col<lifeModel.getColumnCount();col++){   
                lifeModel.setValueAt(new LifeCell(), row, col);
            }
        }
    }
    
     private void confColumnModel(){
        for (int row=0;row<lifeModel.getRowCount();row++){
            for (int col=0;col<lifeModel.getColumnCount();col++){   
                lifeTable.getColumnModel().getColumn(col).setMaxWidth(CELL_SIZE);
                lifeTable.getColumnModel().getColumn(col).setMinWidth(CELL_SIZE);
                lifeTable.getColumnModel().getColumn(col).setPreferredWidth(CELL_SIZE);
                lifeTable.getColumnModel().getColumn(col).setResizable(false);
            }
        }
    }
    
    
    private void confLifeTable(){
        lifeModel = new DefaultTableModel(ROW_COUNT, COL_COUNT){
        
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return LifeCell.class;
            }
        };
        confModel();
        lifeTable.setModel(lifeModel);
        lifeTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        lifeTable.setDefaultRenderer(LifeCell.class, new LifeTableCellRenderer());
        lifeTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lifeTable.setTableHeader(null);
        lifeTable.setRowHeight(CELL_SIZE);
        confColumnModel();
}

    public TableModel getLifeModel() {
        return lifeModel;
    }

    public void setLifeModel(TableModel lifeModel) {
        this.lifeModel = lifeModel;
    }

    public void setActiveMouseListener(boolean activeMouseListener) {
        this.activeMouseListener = activeMouseListener;
    }
}
