/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telemedic.lab.ru.life.client.main;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author kolle
 */
public class NewMain {

    private static void createGUI(){
        ApplicationClient client = new ApplicationClient();
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
