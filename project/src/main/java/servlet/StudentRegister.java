package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/StudentRegister")
public class StudentRegister extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/mydb";
    private static final String JDBC_USER = "postgres";
    private static final String JDBC_PASSWORD = "admin";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String subject = request.getParameter("subject");
        String point = request.getParameter("point");

        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            String sql = "INSERT INTO grades (id, name, subject, point) VALUES (?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                int parsedId = Integer.parseInt(id);
                int parsedPoint = Integer.parseInt(point);

                preparedStatement.setInt(1, parsedId);
                preparedStatement.setString(2, name);
                preparedStatement.setString(3, subject);
                preparedStatement.setInt(4, parsedPoint);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    // Insert successful
                    // Redirect to a success page or display a success message
                    request.getRequestDispatcher("WEB-INF/jsp/seni.jsp").forward(request, response);
                } else {
                    // Insert failed
                    // Handle accordingly, e.g., redirect to an error page
                    response.sendRedirect("error.jsp");
                }
            }
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
    }
}
