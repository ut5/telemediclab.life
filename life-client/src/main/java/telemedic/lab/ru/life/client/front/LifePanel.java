
package telemedic.lab.ru.life.client.front;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.AbstractAction;
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
    
    private static final int [][]PLANNER_MASK = {{0,0,1},
                                                 {1,0,1},
                                                 {0,1,1}};
    
    private static final Integer ROW_COUNT = 10;
    private static final Integer COL_COUNT = 20;
    private static final Integer CELL_SIZE = 25;
    
    private TableModel lifeModel;
    private final JTable lifeTable = new JTable(); 
    private final JLabel status = new JLabel();
    private final JButton startButton = new JButton();
    private final JButton plannerButton = new JButton();
    private final JButton clearButton = new JButton();
    private boolean activePanel = true;
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
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(clearButton,120,120,120)
                        .addComponent(plannerButton,120,120,120)
                        .addComponent(startButton,70,70,70)
                )
                
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
                        .addComponent(clearButton,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE)
                        .addGap(10)
                        .addComponent(plannerButton,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE)
                        .addGap(10)
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
               if (!activePanel){
                   return;
               }
               Object selValue = lifeTable.getValueAt(lifeTable.getSelectedRow(), lifeTable.getSelectedColumn());
               if (selValue instanceof LifeCell){
                   LifeCell lifeCell = (LifeCell) selValue;
                   
                   lifeCell.setLife((lifeCell.isLiving()==null || lifeCell.isLiving() == false));
                   lifeTable.revalidate();
                   lifeTable.repaint();
               }
           }
       });
       
       plannerButton.setAction(new AbstractAction("Планнер") {
           
           @Override
           public void actionPerformed(ActionEvent e) {
              clearModel();
              for (int row=0;row<PLANNER_MASK.length;row++) {
                  for (int col=0;col<PLANNER_MASK.length;col++) {
                      lifeModel.setValueAt(new LifeCell(PLANNER_MASK[row][col]==1), row, col);
                   }
              }
           }
       });
       
       clearButton.setAction(new AbstractAction("Очистить") {

            @Override
            public void actionPerformed(ActionEvent e) {
                clearModel();
            }
        });
       
       status.setText("Задайте  живые клетки");
    }

    
    private void clearModel(){
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
        clearModel();
        lifeTable.setModel(lifeModel);
        lifeTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        lifeTable.setDefaultRenderer(LifeCell.class, new LifeTableCellRenderer());
        lifeTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lifeTable.setTableHeader(null);
        lifeTable.setRowHeight(CELL_SIZE);
        confColumnModel();
}

    public synchronized TableModel getLifeModel() {
        return lifeModel;
    }

    public synchronized void setLifeModel(TableModel lifeModel) {
        this.lifeModel = lifeModel;
    }

    public void setActivePanel(boolean activePanel) {
        this.activePanel = activePanel;
        plannerButton.setEnabled(activePanel);
        clearButton.setEnabled(activePanel);
    }
}
