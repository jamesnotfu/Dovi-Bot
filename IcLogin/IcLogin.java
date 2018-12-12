import java.util.*;
import java.io.*;

// jsoup for remote login
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

public class IcLogin {
  
  //////////////////////////////// main method /////////////////////////////////////
  
  // remove after testing
  public static void main(String[] args) {
    // add username and password below to test login
    attempt("57548", "thispasswordissecurelinuxuser101");
  }
  
  //////////////////////////////// methods /////////////////////////////////////////
  
  /**
   * attempts remote login onto IC; helper method for the Dovi.java class.
   * you may Have to manually change the gradesView URL for it to work for your user specifically.
   * @param username and password in a string format.
   */
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

      HashMap<String, String> cookies = new HashMap<>();
      HashMap<String, String> formData = new HashMap<>();
      
      Connection.Response loginForm = Jsoup
        .connect(loginFormUrl)
        .method(Connection.Method.GET)
        .userAgent(USER_AGENT)
        .execute();

      Document loginDoc = loginForm.parse();
      
      for(Map.Entry<String, String> cookie : loginForm.cookies().entrySet()) {
        cookies.put(cookie.getKey(), cookie.getValue());
      }
      
      formData.put("appName", "dublin");
      formData.put("portalUrl", "portal/dublin.jsp");
      formData.put("username", user);
      formData.put("password", pass);

      Connection.Response homePage = Jsoup
        .connect(loginActionUrl)
        .cookies(cookies)
        .data(formData)
        .method(Connection.Method.POST)
        .userAgent(USER_AGENT)
        .execute();
      
      // adding the cookies from the homepage
      for(Map.Entry<String, String> cookie : homePage.cookies().entrySet()) {
        cookies.put(cookie.getKey(), cookie.getValue());
      }

      System.out.println(homePage.parse().html());
      
      Connection.Response gradesView = Jsoup
        .connect("https://icampus.dublinusd.org/campus/portal/portal.xsl?x=portal.PortalOutline&lang=en&X-XSRF-TOKEN=1512dfbb-f9c4-4441-8de1-f4fd900c8c58&personType=student&context=9176-247-230&personID=9176&studentFirstName=James&lastName=Fu&firstName=James&schoolID=7&calendarID=247&structureID=230&calendarName=18-19%20Dublin%20High%20School&mode=grades&x=portal.PortalGrades&X-XSRF-TOKEN=1512dfbb-f9c4-4441-8de1-f4fd900c8c58")
        .cookies(cookies)
        .method(Connection.Method.POST)
        .userAgent(USER_AGENT)
        .execute();

      String grades = gradesView.parse().html();
      
      Document gradeFind = Jsoup.parse(grades);

      Element table = gradeFind.select("table").first();
   
      table.select("td[class=inProgressGrade]").iterator();
      
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
}