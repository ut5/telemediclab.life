package telemedic.lab.ru.life.client.main;

import javax.swing.JApplet;
import telemedic.lab.ru.life.client.front.LifePanel;

/**
 *
 * @author kolle
 */
public class ApplicationClient extends JApplet {

    @Override
    public void init() {
        add(LifePanel.getInstance());
        Controller.getController().setApplet(this);
    }
}
