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

    private final JTextField inputField = new JTextField();
    private final JLabel inputLabel = new JLabel("Input Your Name");
    private final JLabel outputLabel = new JLabel("Output:");
    private final JTextField resultField = new JTextField();
    private final JPanel mainPanel = new JPanel();
    private final JScrollPane scrollPanel = new JScrollPane();
    private final JButton button = new JButton("Отправить");

    @Override
    public void init() {
//        GroupLayout mainLayout = new GroupLayout(this);
//        getRootPane().getContentPane().setLayout(mainLayout);
        
        GroupLayout layout = new GroupLayout(mainPanel);
        mainPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setHorizontalGroup(layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                        .addGap(10)
                        .addComponent(inputLabel)
                        .addGap(10)
                        .addComponent(inputField,50,GroupLayout.PREFERRED_SIZE,150)
                        .addGap(10)
                )
                .addGroup(layout.createSequentialGroup()
                        .addGap(10)
                        .addComponent(outputLabel)
                        .addGap(10)
                        .addComponent(resultField,50,GroupLayout.PREFERRED_SIZE,150)
                        .addGap(10)
                )
                .addGroup(layout.createSequentialGroup()
                        .addGap(10,10,Short.MAX_VALUE)
                        .addComponent(button)
                        .addGap(10)
                )
                
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGap(10)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(inputLabel)
                        .addComponent(inputField)
                )
                .addGap(10)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(outputLabel)
                        .addComponent(resultField)
                )
                .addGap(10)
                .addComponent(button)
                .addGap(10)
        );
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SendData();
            }
        });
        resultField.setEditable(false);
        scrollPanel.setViewportView(mainPanel);
        add(mainPanel);
//        add(scrollPanel);
//        mainLayout.setHorizontalGroup(mainLayout.createParallelGroup()
//                .addComponent(scrollPanel, GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE,Short.MAX_VALUE)
//        );
//        mainLayout.setVerticalGroup(mainLayout.createSequentialGroup()
//                .addComponent(scrollPanel, GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE,Short.MAX_VALUE)
//        );
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
