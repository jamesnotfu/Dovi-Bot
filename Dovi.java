import java.util.*;
import java.io.*;

// gui
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.*;
import java.awt.image.*;
import java.awt.event.*;

public class Dovi extends JFrame {
  
  //////////////////////////////// instance vars /////////////////////////////////////////
  
  private JTextField txtEnter = new JTextField(); // typing area
  private JTextArea txtChat = new JTextArea(); // chat area
  private String user = ""; // private variable to store the username
  private String pass = ""; // private variable to store the password
  
  ///////////////////////////////// ctors ////////////////////////////////////////////////
  
  public Dovi() {
    
    // general gui config
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(600, 615);
    this.setVisible(true);
    this.setResizable(false);
    this.setLayout(null);
    this.setTitle("Dovi");
    
    txtEnter.setLocation(15, 540);
    txtEnter.setSize(560, 30);
    txtChat.setLocation(15, 15);
    txtChat.setSize(560, 510);
    txtChat.setEditable(false);
    
    this.add(txtEnter);
    this.add(txtChat);
        
    // chatbot stuff
    txtChat.append("Ms. Dovi: Hi. Welcome to AP Computer Science.\n");
    txtChat.append("Ms. Dovi: My job as a teacher is to ensure that you achieve academic success.\n\nEnter 'L' to login to Campus Portal.\n"); 
    // chat entered
    txtEnter.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent arg0) {
        String uText = txtEnter.getText();
        txtChat.append("You: " + uText + "\n");
        
        if(uText.contains("L")) login();
        
        txtEnter.setText("");
      }
    });
  }
  
  ////////////////////////////////// methods /////////////////////////////////////////////
  
  public static void main(String[] args) {
    new Dovi();
  }
  
  public void login() {
    IcLogin.attempt(user, pass);
  }

}