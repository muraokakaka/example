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

@WebServlet("/UpdateStudent")
public class UpdateStudent extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/mydb";
    private static final String JDBC_USER = "postgres";
    private static final String JDBC_PASSWORD = "admin";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Provide the values for update
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String subject = request.getParameter("subject");
        String point = request.getParameter("point");

        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            // Create SQL update query
            String updateQuery = "UPDATE grades SET name = ?, subject = ?, point = ? WHERE id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                // Set values for parameters in the update query
                int parsedId = Integer.parseInt(id);
                int parsedPoint = Integer.parseInt(point);

                preparedStatement.setString(1, name);
                preparedStatement.setString(2, subject);
                preparedStatement.setInt(3, parsedPoint);
                preparedStatement.setInt(4, parsedId);

                // Execute the update query
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Update successful");
                    request.getRequestDispatcher("WEB-INF/jsp/seni.jsp").forward(request, response);
                } else {
                    System.out.println("No records updated");
                }
            }
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
    }
}
