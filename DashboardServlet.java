import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("index.html");
            return;
        }
        String username = (String) session.getAttribute("username");
        request.setAttribute("username", username);
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("101", "Introduction to Java", "Dr. Smith"));
        courses.add(new Course("102", "Web Development", "Prof. Johnson"));
        courses.add(new Course("103", "Database Systems", "Dr. Williams"));
        courses.add(new Course("104", "Computer Networks", "Prof. Brown"));
        courses.add(new Course("105", "Algorithms", "Dr. Miller"));
        request.setAttribute("courses", courses);
        @SuppressWarnings("unchecked")
        List<Course> enrolledCourses = (List<Course>) session.getAttribute("enrolledCourses");
        if (enrolledCourses == null) {
            enrolledCourses = new ArrayList<>();
            session.setAttribute("enrolledCourses", enrolledCourses);
        }
        request.setAttribute("enrolledCourses", enrolledCourses);
        RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
        dispatcher.forward(request, response);
    }
}