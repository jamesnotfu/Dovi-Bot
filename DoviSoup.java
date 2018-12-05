import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

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

        try {
            Connection.Response initial = Jsoup
                .connect("https://icampus.dublinusd.org/campus/portal/dublin.jsp")
                .method(Connection.Method.GET)
                .execute();

            final String USER_AGENT = "\"Mozilla/5.0 (Windows NT\" +\n" +
                "          \" 6.1; WOW64) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.120 Safari/535.2\"";
            
            String username = "";
            String password = "";

            String loginFormUrl = "https://icampus.dublinusd.org/campus/portal/dublin.jsp";
            String loginActionUrl = "https://icampus.dublinusd.org/campus/verify.jsp?appName=dublin&screen=&username=" + username +" + &password=" + password + "&x=52&y=9&useCSRFProtection=true";
              
            HashMap <String, String> cookies = new HashMap<>();
            HashMap <String, String> formData = new HashMap<>();

            Connection.Response loginForm = Jsoup
                .connect(loginFormUrl)
                .method(Connection.Method.GET)
                .userAgent(USER_AGENT)
                .execute();

            Document loginDoc = loginForm.parse();
            
            cookies.putAll(loginForm.cookies());
            formData.put("appName", "dublin");
            formData.put("portalUrl", "portal/dublin.jsp");
            formData.put("username", username);
            formData.put("password", password);
            formData.put("x", "52");
            formData.put("y", "9");
            formData.put("url", "portal/main.xsl");
            formData.put("lang", "en");
            formData.put("useCSRFProtection", "true");
            formData.put("hide", "Hide");
            formData.put("forgotUsername", "Forgot your username?");
            formData.put("forgotPassword", "Forgot your password?");
            formData.put("problems", "Problems logging in?");

            System.out.println("\n-----------------   Cookie Information   ------------------\n");
                                 
            for (Map.Entry<String, String> entry: cookies.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue().toString());
            }
            for (Map.Entry<String, String> entry: formData.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue().toString());
            }
            
            System.out.println("\n-----------------------------------------------------------\n");
            
            Connection.Response homePage = Jsoup.connect(loginActionUrl)
                .cookies(cookies)
                .data(formData)
                .method(Connection.Method.POST)
                .userAgent(USER_AGENT)
                .execute();

            System.out.println(homePage.parse().html());
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}