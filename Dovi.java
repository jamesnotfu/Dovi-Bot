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
        String uText = txtEnter.getText().toLowerCase();
        txtChat.append("You: " + uText + "\n");
        
if (uText.contains("L")) {
 txtEnter.setText("");
 login();

} else if (uText.contains("hi")) {
 botSay("Hello there!");

} else if (uText.contains("how are you")) {
 int decider = (int)(Math.random() * 3 + 1);
 if (decider == 1) {
  botSay("I'm doing well, thanks");
 } else if (decider == 2) {
  botSay("Not too bad");
 } else if (decider == 3) {
  botSay("I'm doing better than you lol");
 }

} else if (uText.contains("gpa")) {
 int decider = (int)(Math.random() * 3 + 1);
 if (decider == 1) {
  botSay("Do you think your GPA is an accurate reflection of your intellectual abilities?");
  String response = txtEnter.getText().toLowerCase();
  if (response.contains("Yes")) botSay("You sound like a boring person!");
  else if (response.contains("No")) botSay("Me too.");
  else if (response.contains("Maybe")) botSay("That's a no I guess.");
  else botSay("You're entitled to your own opinions.");
 } else if (decider == 2) {
  botSay("Not too bad");
 } else if (decider == 3) {
  botSay("I'm doing better than you lol");
 }


} else if (uText.contains("sad")) {
 botSay("Please don’t be sad because of school! Try to do something fun like taking a hike in death valley!");

} else if (uText.contains("stress")) {
 botSay("School can be stressful at times, but I would recommend meditation or adult coloring books.");

} else if (uText.contains("test")) {
 botSay("Make sure to study!");

} else if (uText.contains("cheat")) {
 botSay("Cheating is not the move. We don’t condone academic dishonesty at Dublin High.");

} else if (uText.contains("college") || uText.contains("university")) {
 botSay("Are you excited for college?");

} else if (uText.contains("grades")) {
 botSay("Grades aren’t everything, but if you want to check them, press L.");

} else if (uText.contains("homework")) {
 botSay("Be sure to keep track of homework!");

} else if (uText.contains("math")) {
 botSay("Math is a huge part of everything -- especially diving.");

} else if (uText.contains("history")) {
 botSay("Yellowstone is full of history!");

} else if (uText.contains("biology")) {
 botSay("Yosemite is full of biology!");

} else if (uText.contains("study")) {
 botSay("Try playing some music to help you study!");

} else if (uText.contains("dovi")) {
 botSay("I love Ms. Dovi too!");

} else if (uText.contains("kaehms")) {
 botSay("Mr. Kaehms is great!");

} else if (uText.contains("computer science")) {
 botSay("I love to code!");

} else if (uText.contains("english")) {
 botSay("que es ingles");

} else if (uText.contains("lunch")) {
 botSay("I love to eat.");

} else if (uText.contains("teacher")) {
 botSay("My favorite teacher is Ms. Dovi.");

} else if (uText.contains("ms") || uText.contains("mrs")) {
 botSay("She sounds like a great teacher.");

} else if (uText.contains("mr")) {
 botSay("He sounds like a cool guy.");

} else if (uText.contains("quiz")) {
 botSay("Be sure to study!");

} else if (uText.contains("project")) {
 botSay("Make sure not to procrastinate.");

} else {
 randomResponse();
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
public void botSay(String s) {
        txtChat.append("Dovi: " + s + "\n");
    }

public void randomResponse() {
	ArrayList<String> list=new ArrayList<String>();
	list.add("I don’t understand.");
	list.add("That is beyond my knowledge.");
	list.add("Try asking a new question.");
	list.add("I am not sure what you mean.");
	list.add("Can you rephrase that?");
	list.add("Please be more specific.");
	list.add("That is out of my understanding.");
	list.add("I didn’t get that.");
	list.add("Can you be more specific?");
	list.add("Please rephrase that.");
int n = rand.nextInt(10);
botSay(list.get(n));
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