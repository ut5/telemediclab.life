
package telemedic.lab.ru.life.web.app;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import telemedic.lab.ru.life.client.front.Utils;

/**
 *
 * @author kolle
 */
public class ServletDispetcher extends HttpServlet {
// Getting a String object from the applet and send it back.

    private final LifesRules lifeRules = new LifesRules();
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("application/x-java-serialized-object");
            InputStream inputStream = request.getInputStream();
            ObjectInputStream inputFromApplet = new ObjectInputStream(inputStream);
            final String model = (String) inputFromApplet.readObject();

            Runnable task = new Runnable() {

                @Override
                public void run() {
                    lifeRules.setModel(Utils.stringToArray(model));
                    lifeRules.doIteration();
                }
            };
            try{
                task.run();
            }finally{
                OutputStream outputStream = response.getOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                objectOutputStream.writeObject(Utils.arrayToString(lifeRules.getModel()));
                objectOutputStream.flush();
                objectOutputStream.close();
            }            
        } catch (IOException | ClassNotFoundException e) {
            log(e.getLocalizedMessage());
        }
    }
}
