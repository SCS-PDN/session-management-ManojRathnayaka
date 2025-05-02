<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Course Dashboard</title>
    <style>
        .success {
            color: green;
            margin-bottom: 15px;
        }
        table {
            border-collapse: collapse;
            width: 80%;
            margin-bottom: 20px;
        }
        th, td {
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        .logout {
            float: right;
            margin-top: 10px;
        }
        .enrolled {
            margin-top: 30px;
        }
        .no-courses {
            font-style: italic;
            color: #666;
        }
    </style>
    <script>
        window.onload = function() {
            const urlParams = new URLSearchParams(window.location.search);
            if (urlParams.has('enrolled')) {
                document.getElementById('success-message').style.display = 'block';
            }
        };
    </script>
</head>
<body>
    <h1>Welcome, ${username}!</h1>
    <a href="LogoutServlet" class="logout">Logout</a>
    
    <div id="success-message" class="success" style="display: none;">
        Course enrollment successful!
    </div>
    
    <h2>Available Courses</h2>
    <table border="1">
        <tr>
            <th>Course ID</th>
            <th>Course Name</th>
            <th>Instructor</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${courses}" var="course">
            <tr>
                <td>${course.id}</td>
                <td>${course.name}</td>
                <td>${course.instructor}</td>
                <td><a href="EnrollServlet?courseId=${course.id}">Enroll</a></td>
            </tr>
        </c:forEach>
    </table>
    
    <div class="enrolled">
        <h2>Your Enrolled Courses</h2>
        <c:choose>
            <c:when test="${empty enrolledCourses}">
                <p class="no-courses">You haven't enrolled in any courses yet.</p>
            </c:when>
            <c:otherwise>
                <ul>
                    <c:forEach items="${enrolledCourses}" var="course">
                        <li>${course.name} (${course.id}) - ${course.instructor}</li>
                    </c:forEach>
                </ul>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>