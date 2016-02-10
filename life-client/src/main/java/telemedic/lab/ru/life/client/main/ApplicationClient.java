package telemedic.lab.ru.life.client.main;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.GroupLayout;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author kolle
 */
public class ApplicationClient extends JApplet {

    private final JTextField inputField = new JTextField(10);
    private final JLabel inputLabel = new JLabel("Input Your Name");
    private final JLabel outputLabel = new JLabel("Output:");
    private final JTextField resultField = new JTextField(10);
    private final JPanel mainPanel = new JPanel();
    private final JScrollPane scrollPanel = new JScrollPane();
    private final JButton button = new JButton("Отправить");

    @Override
    public void init() {
        GroupLayout layout = new GroupLayout(mainPanel);
        mainPanel.setLayout(layout);
        
        layout.setHorizontalGroup(layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                        .addComponent(inputLabel)
                        .addComponent(outputLabel)
                )
                .addGroup(layout.createSequentialGroup()
                        .addComponent(inputField)
                        .addComponent(resultField)
                )
                .addComponent(button)
        );
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(inputLabel)
                        .addComponent(inputField)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(outputLabel)
                        .addComponent(resultField)
                )
                .addComponent(button)
        );
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SendData();
            }
        });
        resultField.setEditable(false);
        scrollPanel.setViewportView(mainPanel);
        add(scrollPanel);
    }

    // Get a connection to the servlet.
    private URLConnection getServletConnection() throws MalformedURLException,
            IOException {
        URL urlServlet = new URL(getCodeBase(), "applettoservlet");
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
    private void SendData() {
        try {
            String input = inputField.getText();
            // send data to the servlet
            URLConnection con = getServletConnection();
            OutputStream outputStream = con.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(outputStream);
            oos.writeObject(input);
            oos.flush();
            oos.close();
            // receive result from servlet
            InputStream inputStream = con.getInputStream();
            ObjectInputStream inputFromServlet = new ObjectInputStream(
                    inputStream);
            String result = (String) inputFromServlet.readObject();
            inputFromServlet.close();
            inputStream.close();
            // show result
            resultField.setText(result);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.SEVERE, ex.getLocalizedMessage());
        }
    }
}
