
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import telemedic.lab.ru.life.client.front.LifePanel;

/**
 *
 * @author UT5
 */
public class Controller {
    
    private static Controller instnase;
    private JApplet applet;
    
    private Controller(){
    }
    
    public static Controller getController(){
        if (instnase == null){
            instnase = new Controller();
        }
        return instnase;
    }

    public void setApplet(JApplet applet) {
        this.applet = applet;
    }
    
    public void doIteration(){
        TableModel inputModel = LifePanel.getInstance().getLifeModel();
        TableModel outputModel = sendData(inputModel);
        LifePanel.getInstance().setLifeModel(outputModel);
    }
    
    // Get a connection to the servlet.
    private URLConnection getServletConnection() throws MalformedURLException,
            IOException {

        if (applet == null){
            return null;
        }
        URL urlServlet = new URL(applet.getCodeBase(), "lwa");
        URLConnection con = urlServlet.openConnection();
        con.setDoInput(true);
        con.setDoOutput(true);
        con.setUseCaches(false);
        con.setRequestProperty("Content-Type",
                "application/x-java-serialized-object");
        return con;
    }

  // Send the inputField data to the servlet and show the result in the
    // outputField.
    private TableModel sendData(TableModel model) {
        try {
            URLConnection con = getServletConnection();
            OutputStream outputStream = con.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(outputStream);
            oos.writeObject(model);
            oos.flush();
            oos.close();
            // receive result from servlet
            InputStream inputStream = con.getInputStream();
            ObjectInputStream inputFromServlet = new ObjectInputStream(
                    inputStream);
            TableModel result = (TableModel) inputFromServlet.readObject();
            inputFromServlet.close();
            inputStream.close();
            // show result
            return result;
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.SEVERE, ex.getLocalizedMessage());
            return null;
    }
    
}
}