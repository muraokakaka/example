

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		// 接続するデータベースの指定
		String dbURL = "jdbc:postgresql://localhost:5432/mydb";
		String username = "postgres";
		String password = "admin";

		// 初期化
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;
		
//生徒毎の科目と点数一覧----------------------------------------------------------------
		
		List<Integer> list = new ArrayList<>();
		// データベースの接続
	        try {
	            Class.forName("org.postgresql.Driver");
	            conn = DriverManager.getConnection(dbURL, username, password);
	            String query = "SELECT point FROM grades ";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(query);

	            while (rs.next()) {
	            	
	            	list.add(rs.getInt(1));
	            	
	            	}
	            	for (int i = 0; i < list.size(); i++) {
	            		System.out.println(list.get(i));
	            		}
	            
	            rs.close();
	            stmt.close();
	            
	        }catch (Exception e){
	            System.out.println("error occurred."+e.getMessage());
	        }
	        request.setAttribute("list", list);
	        request.getRequestDispatcher("WEB-INF/jsp/test.jsp").forward(request, response);
	}
}
