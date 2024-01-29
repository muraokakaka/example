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

    // 接続するデータベースの指定
    String dbURL = "jdbc:postgresql://localhost:5432/mydb";
    String username = "postgres";
    String password = "admin";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    					  throws ServletException, IOException {
       
        request.setCharacterEncoding("UTF-8");

        // パラメーターの取得
        String id = request.getParameter("id");
        
        List<String> klist = new ArrayList<>();

        // データベースへの接続
        try (Connection connection = DriverManager.getConnection(dbURL, username, password)) {
            // IDによる検索文
            String sql = "SELECT * FROM grades WHERE id = ? ORDER BY id ASC";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                // 型変換
                int parsedId = Integer.parseInt(id);
               

                preparedStatement.setInt(1, parsedId);

                // 実行
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    
                    while (resultSet.next()) {
                        
                    	klist.add(resultSet.getString(1));
    	            	klist.add(resultSet.getString(2));
    	            	klist.add(resultSet.getString(3));
    	            	klist.add(resultSet.getString(4));
    	            	
                    		}
                		}
            		}
        		} catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
        	}

        // 値を渡す画面遷移
        request.setAttribute("klist", klist);
        request.getRequestDispatcher("WEB-INF/jsp/serch_result.jsp").forward(request, response);
    }
    
}
