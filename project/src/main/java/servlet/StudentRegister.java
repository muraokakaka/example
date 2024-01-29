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

    // 接続するデータベースの指定
    String dbURL = "jdbc:postgresql://localhost:5432/mydb";
    String username = "postgres";
    String password = "admin";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            			  throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String subject = request.getParameter("subject");
        String point = request.getParameter("point");

        // データベースへの接続
        try (Connection connection = DriverManager.getConnection(dbURL, username, password)) {
            String sql = "INSERT INTO grades (id, name, subject, point) VALUES (?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                int parsedId = Integer.parseInt(id);
                int parsedPoint = Integer.parseInt(point);

                preparedStatement.setInt(1, parsedId);
                preparedStatement.setString(2, name);
                preparedStatement.setString(3, subject);
                preparedStatement.setInt(4, parsedPoint);

                // 反映
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                   
                    request.getRequestDispatcher("WEB-INF/jsp/seni.jsp").forward(request, response);
                    
                } else {
                   
                    response.sendRedirect("error.jsp");
                    
                }
            }
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
    }
}
