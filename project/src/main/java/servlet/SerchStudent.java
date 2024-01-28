package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SerchStudent")
public class SerchStudent extends HttpServlet {
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
        
        List<String> klist = new ArrayList<>();

        // Database connection
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            // SQL query to retrieve data
            String sql = "SELECT * FROM grades WHERE id = ? ORDER BY id ASC";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                // Set values to the prepared statement
                int parsedId = Integer.parseInt(id);
               

                preparedStatement.setInt(1, parsedId);

                // Execute the query
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    // Process the result set if needed
                    // For example, you can iterate through the result set and do something with the data
                    while (resultSet.next()) {
                        // Process each row of the result set
                        // For example, retrieve values using resultSet.getString(columnName) or resultSet.getInt(columnName)
                    	klist.add(resultSet.getString(1));
    	            	klist.add(resultSet.getString(2));
    	            	klist.add(resultSet.getString(3));
    	            	klist.add(resultSet.getString(4));
                    }
                }
            }
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        // Redirect to a success page or display a success message
        request.setAttribute("klist", klist);
        request.getRequestDispatcher("WEB-INF/jsp/serch_result.jsp").forward(request, response);
    }
}
