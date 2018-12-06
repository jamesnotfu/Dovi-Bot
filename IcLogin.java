import java.util.*;
import java.io.*;

// jsoup for remote login
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

public class IcLogin {
  
  public static void attempt(String user, String pass) {
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
      String loginActionUrl = "https://icampus.dublinusd.org/campus/verify.jsp?nonBrowser=true&username=" + username + "&password=" + password + "&appName=" + "dublin";

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
        .connect("https://icampus.dublinusd.org/campus/prism?x=portal.PortalOutline&lang=en&X-XSRF-TOKEN=1c0ba929-f45e-4647-b21b-89a58ef1b73b&personType=student&context=9176-247-230&personID=9176&studentFirstName=James&lastName=Fu&firstName=James&schoolID=7&calendarID=247&structureID=230&calendarName=18-19%20Dublin%20High%20School&mode=grades&x=portal.PortalGrades&X-XSRF-TOKEN=1c0ba929-f45e-4647-b21b-89a58ef1b73b")
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