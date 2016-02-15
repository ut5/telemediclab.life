package telemedic.lab.ru.life.client.main;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
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
