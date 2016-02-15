package telemedic.lab.ru.life.client.front;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import telemedic.lab.ru.life.client.main.Controller;

/**
 *
 * @author UT5
 */
public class LetsPlayAction extends AbstractAction{

    private boolean state = false;
    private final Integer INTERVAL = 500;
    private Timer timer;
    private final JLabel status;
    private final Controller controller = Controller.getController();
    private final ImageIcon playIcon = new ImageIcon(getClass().getClassLoader().getResource("imgs/play.png"));
    private final ImageIcon pauseIcon = new ImageIcon(getClass().getClassLoader().getResource("imgs/pause.png"));

    public LetsPlayAction(JLabel status) {
        putValue(Action.SMALL_ICON, playIcon);
        this.status = status;
        
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        state = !state;
        if (timer == null){
            timer = new Timer();
        }
        if (state){
            LifePanel.getInstance().setActiveMouseListener(false);
           status.setText("Клетки начали эволюцию");
//           putValue(Action.NAME, "Стоп");
           putValue(Action.SMALL_ICON, pauseIcon);
           timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    controller.doIteration();
                }
            }, INTERVAL, INTERVAL);
        }else{
            LifePanel.getInstance().setActiveMouseListener(true);
            status.setText("Можно внести корректировки");
//            putValue(Action.NAME, "Поехали");
            putValue(Action.SMALL_ICON, playIcon);
            timer.cancel();
            timer = null;
        }
        
    }
    
}
