
package telemedic.lab.ru.life.client.main;

import javax.swing.JFrame;

/**
 *
 * @author kolle
 */
public class LifeMain {

    private static void createGUI(){
        ApplicationClient client = new ApplicationClient();
        client.init();
        client.start();
        JFrame frame = new JFrame("Life");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(client);
        frame.pack();
        frame.setVisible(true);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
               public void run() {
                    createGUI();
               }
          });
        
    }
    
}
