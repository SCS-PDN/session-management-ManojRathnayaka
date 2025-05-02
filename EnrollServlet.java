import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/EnrollServlet")
public class EnrollServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String courseId = request.getParameter("courseId");
        if (courseId == null || courseId.isEmpty()) {
            response.sendRedirect("DashboardServlet?error=nocourse");
            return;
        }
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("index.html");
            return;
        }
        @SuppressWarnings("unchecked")
        List<Course> enrolledCourses = (List<Course>) session.getAttribute("enrolledCourses");
        if (enrolledCourses == null) {
            enrolledCourses = new ArrayList<>();
            session.setAttribute("enrolledCourses", enrolledCourses);
        }
        List<Course> availableCourses = getAvailableCourses();
        Course selectedCourse = null;
        for (Course course : availableCourses) {
            if (course.getId().equals(courseId)) {
                selectedCourse = course;
                break;
            }
        }
        if (selectedCourse != null && !enrolledCourses.contains(selectedCourse)) {
            enrolledCourses.add(selectedCourse);
            session.setAttribute("enrolledCourses", enrolledCourses);
        }
        response.sendRedirect("DashboardServlet?enrolled=" + courseId);
    }
    private List<Course> getAvailableCourses() {
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("101", "Introduction to Java", "Dr. Smith"));
        courses.add(new Course("102", "Web Development", "Prof. Johnson"));
        courses.add(new Course("103", "Database Systems", "Dr. Williams"));
        courses.add(new Course("104", "Computer Networks", "Prof. Brown"));
        courses.add(new Course("105", "Algorithms", "Dr. Miller"));
        return courses;
    }
}