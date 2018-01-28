/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elms;

import elms.form.LoginForm;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author TP041331
 */
public class ELMS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        LoginForm a = new LoginForm();
        a.setVisible(true);
        
        String filePath = System.getProperty("user.dir")+"\\";
        System.out.println(filePath);
        String fileName = "db.txt";
        try {
            JsonFileManager fileManager = new JsonFileManager(filePath, fileName);
        } catch (FileNotFoundException ex) {
            PrintWriter outputFile = null;
            try {
                JOptionPane.showMessageDialog(new JFrame(), "First startup detected...Creating database for you!");
                outputFile = new PrintWriter(filePath + fileName);
                outputFile.println("[[],[],[],[{\"ID\":1,\"username\":\"Admin\",\"password\":\"Admin\",\"position\":\"HR\"}],[]]");
                outputFile.close();
                JOptionPane.showMessageDialog(new JFrame(), "Database created!\nDefault username:Admin\nDefault password:Admin");
            } catch (FileNotFoundException ex1) {
                JOptionPane.showMessageDialog(new JFrame(), "File Not Found,failed to create new file...Exiting Program.");
                System.exit(0);
            } finally {
                outputFile.close();
            }
                try {
                    JsonFileManager fileManager = new JsonFileManager(filePath, fileName);
                } catch (Exception ex1) {
                    Logger.getLogger(ELMS.class.getName()).log(Level.SEVERE, null, ex1);
                }
            
        } catch (java.lang.IllegalStateException ex) {
            PrintWriter outputFile = null;
            try {
                JOptionPane.showMessageDialog(new JFrame(), "First startup detected...Creating database for you!");
                outputFile = new PrintWriter(filePath + fileName);
                outputFile.println("[[],[],[],[{\"ID\":1,\"username\":\"Admin\",\"password\":\"Admin\",\"position\":\"HR\"}],[]]");
                outputFile.close();
                JOptionPane.showMessageDialog(new JFrame(), "Database created!\nDefault username:Admin\nDefault password:Admin");
            } catch (FileNotFoundException ex1) {
                JOptionPane.showMessageDialog(new JFrame(), "FileNotFound,failed to create new file...Exiting Program.");
                System.exit(0);
            } finally {
                outputFile.close();
            }
                try {
                    JsonFileManager fileManager = new JsonFileManager(filePath, fileName);
                } catch (Exception ex1) {
                    Logger.getLogger(ELMS.class.getName()).log(Level.SEVERE, null, ex1);
                }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(new JFrame(), "FATAL ERROR:Please check debugger for more info.");
            System.exit(0);
        }
    }
    
}
