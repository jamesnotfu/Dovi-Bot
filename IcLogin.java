import java.util.*;
import java.io.*;

// jsoup for remote login
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

public class IcLogin {
  
  //////////////////////////////// methods /////////////////////////////////////////
  
  public static void attempt(String user, String pass) {
    try {
      Connection.Response initial = Jsoup
        .connect("https://icampus.dublinusd.org/campus/portal/dublin.jsp")
        .method(Connection.Method.GET)
        .execute();
      final String USER_AGENT = "\"Mozilla/5.0 (Windows NT\" +\n" +
        "          \" 6.1; WOW64) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.120 Safari/535.2\"";
      
      String loginFormUrl = "https://icampus.dublinusd.org/campus/portal/dublin.jsp";
      String loginActionUrl = "https://icampus.dublinusd.org/campus/verify.jsp?nonBrowser=true&username=" + user + "&password=" + pass + "&appName=" + "dublin";

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
      formData.put("username", user);
      formData.put("password", pass);

      System.out.println("\n-----------------   Cookie Information   ------------------\n");

      for (Map.Entry < String, String > entry: cookies.entrySet()) {
        System.out.println(entry.getKey() + ": " + entry.getValue().toString());
      }
      
      for (Map.Entry < String, String > entry: formData.entrySet()) {
        System.out.println(entry.getKey() + ": " + entry.getValue().toString());
      }

      System.out.println("\n-----------------------------------------------------------\n");
      
      Connection.Response homePage = Jsoup
        .connect(loginActionUrl)
        .cookies(cookies)
        .data(formData)
        .method(Connection.Method.POST)
        .userAgent(USER_AGENT)
        .execute();

      System.out.println(homePage.parse().html());

      Connection.Response gradesView = Jsoup
        .connect("https://icampus.dublinusd.org/campus/prism?nonBrowser=true&x=portal.PortalOutline&lang=en&personType=student&personID=9176&studentFirstName=James&lastName=Fu&firstName=James&schoolID=7&calendarID=247&structureID=230&calendarName=18-19%20Dublin%20High%20School&mode=grades&x=portal.PortalGrades")
        .cookies(cookies)
        .method(Connection.Method.POST)
        .userAgent(USER_AGENT)
        .execute();

      System.out.println(gradesView.parse().html());

  } catch (IOException e) {
      e.printStackTrace();
  }
}
}