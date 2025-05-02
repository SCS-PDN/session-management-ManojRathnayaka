import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final Map<String, String> VALID_USERS = new HashMap<>();
    static {
        VALID_USERS.put("student1", "pass1");
        VALID_USERS.put("student2", "pass2");
        VALID_USERS.put("admin", "admin123");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (isValidUser(username, password)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("username", username);
            session.setMaxInactiveInterval(30 * 60);
            Cookie usernameCookie = new Cookie("username", username);
            usernameCookie.setMaxAge(30 * 60);
            response.addCookie(usernameCookie);
            response.sendRedirect("DashboardServlet");
        } else {
            response.sendRedirect("index.html?error=1");
        }
    }
    
    private boolean isValidUser(String username, String password) {
        return username != null && password != null && 
               VALID_USERS.containsKey(username) && 
               VALID_USERS.get(username).equals(password);
    }
}