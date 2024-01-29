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

    // 接続するデータベースの指定
    String dbURL = "jdbc:postgresql://localhost:5432/mydb";
    String username = "postgres";
    String password = "admin";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // パラメーターの取得
        String id = request.getParameter("id");
       
        // データベースへの接続
        try (Connection connection = DriverManager.getConnection(dbURL, username, password)) {
        	
            // 削除のSQL文
            String sql = "DELETE FROM grades WHERE id = ? ";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                
            	// 型の変換
            	int parsedId = Integer.parseInt(id);
            	
                preparedStatement.setInt(1, parsedId);
                
                // 実行
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }

        // 画面遷移
        request.getRequestDispatcher("WEB-INF/jsp/seni.jsp").forward(request, response);
    }
}