/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.swing.table.TableModel;

/**
 *
 * @author kolle
 */
public class ServletDispetcher extends HttpServlet {
// Getting a String object from the applet and send it back.

    private LifeCickle lifeRules = new LifeCickle();
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("application/x-java-serialized-object");
            InputStream inputStream = request.getInputStream();
            ObjectInputStream inputFromApplet = new ObjectInputStream(inputStream);
            TableModel model = (TableModel) inputFromApplet.readObject();
            lifeRules.setModel(model);
            lifeRules.doIteration();
            // getting string value and passing to applet
            OutputStream outputStream = response.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(lifeRules.getModel());
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            log(e.getLocalizedMessage());
        }
    }
}
