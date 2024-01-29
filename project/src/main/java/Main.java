

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

/**
 * Servlet implementation class main
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
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
		
		List<String> list = new ArrayList<>();
		// データベースの接続
	        try {
	            Class.forName("org.postgresql.Driver");
	            conn = DriverManager.getConnection(dbURL, username, password);
	            String query = "SELECT * FROM grades ORDER BY id ASC";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(query);

	            while (rs.next()) {
	            	
	            	list.add(rs.getString(1));
	            	list.add(rs.getString(2));
	            	list.add(rs.getString(3));
	            	list.add(rs.getString(4));
	            	
	            	}
	            	for (int i = 0; i < list.size(); i++) {
	            		System.out.println(list.get(i));
	            		}
	            
	            rs.close();
	            stmt.close();
	            
	        }catch (Exception e){
	            System.out.println("error occurred."+e.getMessage());
	        }
	        
	        
//国語平均----------------------------------------------------------------
	        
	        
	    List<String> avgk = new ArrayList<>();
	    // データベースの接続
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(dbURL, username, password);
            String query = "SELECT ROUND(AVG(point)) FROM grades \r\n"
            			  +"WHERE subject = '国語' \r\n";
            
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);

            while (rs.next()) {
            	
            	avgk.add(rs.getString("round"));
            	
            	}
            	
            	System.out.println(avgk.get(0));
            
            rs.close();
            stmt.close();
            
        }catch (Exception e){
            System.out.println("error occurred."+e.getMessage());
        }
        
        
//数学平均----------------------------------------------------------------
        
        
        List<String> avgs = new ArrayList<>();
        // データベースの接続
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(dbURL, username, password);
            String query = "SELECT ROUND(AVG(point)) FROM grades \r\n"
            			  +"WHERE subject = '数学' \r\n";
            
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);

            while (rs.next()) {
            	
            	avgs.add(rs.getString("round"));
            	
            	}
            	
            	System.out.println(avgs.get(0));
            
            rs.close();
            stmt.close();
            
        }catch (Exception e){
            System.out.println("error occurred."+e.getMessage());
        }
        
        
//国語合計----------------------------------------------------------------
        
        
        List<Integer> sumk = new ArrayList<>();
        // データベースの接続
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(dbURL, username, password);
            String query = "SELECT point FROM grades \r\n"
            			  +"WHERE subject = '国語' \r\n";
            
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);

            while (rs.next()) {
            	
            	sumk.add(rs.getInt("point"));
            	
            	}
            	
            for (int i = 0; i < sumk.size(); i++) {
        		System.out.println(sumk.get(i));
        		}
            
            rs.close();
            stmt.close();
            
        }catch (Exception e){
            System.out.println("error occurred."+e.getMessage());
        }
        
        
//国語人数----------------------------------------------------------------        
        
        
        List<String> countk = new ArrayList<>();
        // データベースの接続
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(dbURL, username, password);
            String query = "SELECT count(subject) FROM grades \r\n"
            			  +"WHERE subject = '国語' \r\n";
            
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);

            while (rs.next()) {
            	
            	countk.add(rs.getString("count"));
            	
            	}
            	
            	System.out.println(countk.get(0));
            
            rs.close();
            stmt.close();
            
        }catch (Exception e){
            System.out.println("error occurred."+e.getMessage());
        }
        
        
//数学合計----------------------------------------------------------------
        
        
        List<Integer> sums = new ArrayList<>();
        // データベースの接続
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(dbURL, username, password);
            String query = "SELECT point FROM grades \r\n"
            			  +"WHERE subject = '数学' \r\n";
            
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);

            while (rs.next()) {
            	
            	sums.add(rs.getInt("point"));
            	
            	}
            	
            for (int i = 0; i < sums.size(); i++) {
        		System.out.println(sums.get(i));
        		}
            
            rs.close();
            stmt.close();
            
        }catch (Exception e){
            System.out.println("error occurred."+e.getMessage());
        }
        
        
//数学人数----------------------------------------------------------------
        
        
        List<String> counts = new ArrayList<>();
        // データベースの接続
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(dbURL, username, password);
            String query = "SELECT count(subject) FROM grades \r\n"
            			  +"WHERE subject = '数学' \r\n";
            
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);

            while (rs.next()) {
            	
            	counts.add(rs.getString("count"));
            	
            	}
            	
            	System.out.println(counts.get(0));
            
            rs.close();
            stmt.close();
            
        }catch (Exception e){
            System.out.println("error occurred."+e.getMessage());
        }
        
        
//合計人数----------------------------------------------------------------
        
        
        List<String> allcount = new ArrayList<>();
        // データベースの接続
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(dbURL, username, password);
            String query = "SELECT count(id) FROM grades";
            
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);

            while (rs.next()) {
            	
            	allcount.add(rs.getString("count"));
            	
            	}
            	
            	System.out.println(allcount.get(0));
            
            rs.close();
            stmt.close();
            
        }catch (Exception e){
            System.out.println("error occurred."+e.getMessage());
        }
        
        
//値を渡す画面遷移----------------------------------------------------------------
        
        
        request.setAttribute("list", list);
        request.setAttribute("avgk", avgk);
        request.setAttribute("avgs", avgs);
        request.setAttribute("sumk", sumk);
        request.setAttribute("countk", countk);
        request.setAttribute("sums", sums);
        request.setAttribute("counts", counts);
        request.setAttribute("allcount", allcount);
        request.getRequestDispatcher("WEB-INF/jsp/student.jsp").forward(request, response);
        
	}

}