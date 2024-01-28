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

@WebServlet("/DeleteStudent")
public class DeleteStudent extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database connection parameters
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/mydb";
    private static final String JDBC_USER = "postgres";
    private static final String JDBC_PASSWORD = "admin";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set character encoding
        request.setCharacterEncoding("UTF-8");

        // Get form parameters
        String id = request.getParameter("id");
       
        // Database connection
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            // SQL query to insert data
            String sql = "DELETE FROM grades WHERE id = ? ";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                // Set values to the prepared statement
            	int parsedId = Integer.parseInt(id);
            	
                preparedStatement.setInt(1, parsedId);
                
                // Execute the query
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        // Redirect to a success page or display a success message
        request.getRequestDispatcher("WEB-INF/jsp/seni.jsp").forward(request, response);
    }
}