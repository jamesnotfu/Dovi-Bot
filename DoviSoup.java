import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// screenscraping stuffs
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import java.io.*;

// java gui
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class DoviSoup extends JFrame {
    //Typing Area:
    private JTextField txtEnter = new JTextField();
    //Chat Area:
    private JTextArea txtChat = new JTextArea();

    public DoviSoup() {
        //Frame Attributes:
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 615);
        this.setVisible(true);
        this.setResizable(false);
        this.setLayout(null);
        this.setTitle("");

        //txtEnter Attributes:
        txtEnter.setLocation(15, 540);
        txtEnter.setSize(560, 30);

        //txtEnter Action Event:
        txtEnter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String uText = txtEnter.getText();
                txtChat.append("You: " + uText + "\n");

                if (uText.contains("hi")) {
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
                } else {
                    int decider = (int)(Math.random() * 3 + 1);
                    if (decider == 1) {
                        botSay("I didn't get that");
                    } else if (decider == 2) {
                        botSay("Please rephrase that");
                    } else if (decider == 3) {
                        botSay("???");
                    }
                }
                txtEnter.setText("");
            }
        });

        //txtChat Attributes:
        txtChat.setLocation(15, 15);
        txtChat.setSize(560, 510);
        txtChat.setEditable(false);

        //Add Items To Frame:
        this.add(txtEnter);
        this.add(txtChat);
    }

    public void botSay(String s) {
        txtChat.append("Dovi: " + s + "\n");
    }

    public static void main(String[] args) {
        Document doc;
        try {

            Connection.Response initial = Jsoup
                .connect("https://icampus.dublinusd.org/campus/portal/dublin.jsp")
                .method(Connection.Method.GET)
                .execute();

            Connection.Response login = Jsoup
                .connect("https://icampus.dublinusd.org/campus/portal/dublin.jsp")
                .timeout(3000)
                .data("appName", "dublin")
                .data("portalUrl", "portal/dublin.jsp?&rID=0.2524237061868373")
                .data("url", "portal/main.xsl?rID=0.5050179116808896")
                .data("username", "enteridhere")
                .data("password", "enterpassword")
                .cookies(initial.cookies())
                .timeout(3000)
                .method(Connection.Method.POST)
                .execute();

            Document page = Jsoup
                .connect("https://icampus.dublinusd.org/campus/portal/main.xsl")
                .timeout(3000)
                .cookies(login.cookies())
                .get();
            
            String title = page.title();
            System.out.println(title);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
