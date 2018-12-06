import java.util.*;
import java.io.*;

// gui
import javax.swing.*;
import java.awt.event.*;

public class Dovi extends JFrame {
  
  //////////////////////////////// instance vars /////////////////////////////////////////
  
  private JTextField txtEnter = new JTextField(); // typing area
  private JTextArea txtChat = new JTextArea(); // chat area
  public String user = ""; // private variable to store the username
  public String pass = ""; // private variable to store the password
  private String rv = ""; // return value for method
  
  ///////////////////////////////// ctors ////////////////////////////////////////////////
 
  /**
   * default constuctor, creates GUI environment and checks if text is entered.
   */
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
        
    // initial text/instructions
    txtChat.append("Ms. Dovi: Hi. Welcome to AP Computer Science.\n");
    txtChat.append("Ms. Dovi: My job as a teacher is to ensure that you achieve academic success.\n\nPress 'L' to login to Campus Portal.\n");
    
    // chat entered
    txtEnter.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent arg0) {
        String uText = txtEnter.getText();
        txtChat.append("You: " + uText + "\n");
        
        if(uText.contains("L")) {
          txtEnter.setText("");
          login();
        }
        
        txtEnter.setText("");
      }
    });
  }
  
  ////////////////////////////////// methods /////////////////////////////////////////////
  
  /**
   * creates a new Dovi() object when program runs.
   * see ctor for details.
   */
  public static void main(String[] args) {
    new Dovi();
  }
  
  /**
   * prompts the user for their username and password to login onto IC.
   */
  public void login() {
    System.out.println("Please enter your username: ");
    Scanner userIn = new Scanner(System.in);
    this.user = userIn.nextLine();
    System.out.println("Please enter your password: ");
    Scanner passIn = new Scanner(System.in);
    this.pass = passIn.nextLine();
    IcLogin.attempt(user, pass);
  }

}