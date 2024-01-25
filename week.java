package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class test
 */
@WebServlet("/week")
public class week extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;

        //接続文字列
        String url = "jdbc:postgresql://localhost:5432/cinema";
        String user = "postgres";
        String password = "admin";
        
//男女別の週別来場者数-----------------------------------------------------------------------------------------------
//来場者---------------------------------------------------------------------------------------------------------
        //SQLなどの記述の追加の際は各コメントアウトの部分に1つずつ反映させてください---------------------------------
        //List<String> bcount = new ArrayList<>();
        List<String> count = new ArrayList<>();
        try{
            //PostgreSQLへ接続
            conn = DriverManager.getConnection(url, user, password);

            //自動コミットOFF
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
        String countmen = "SELECT COUNT(gender), gender FROM membertable\r\n"
        		+ "INNER JOIN reservetable \r\n"
        		+ "ON membertable.memberid = reservetable.memberid\r\n"
        		+ "GROUP BY gender";
        ResultSet rs = stmt.executeQuery(countmen);
        while(rs.next()){
            String g = rs.getString("count");
            count.add(rs.getString("count"));
          }
        System.out.println(count);
        request.setAttribute("count", count);
        request.getRequestDispatcher("/weekchart.jsp").forward(request, response);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            try {
                if(rset != null)rset.close();
                if(stmt != null)stmt.close();
                if(conn != null)conn.close();
            }
            catch (SQLException e){
                e.printStackTrace();
                
            }

        }
        
//来場者---------------------------------------------------------------------------------------------------------
//男女別の週別来場者数-----------------------------------------------------------------------------------------------        
        
        List<String> jlist = new ArrayList<>();
        try{
            //PostgreSQLへ接続
            conn = DriverManager.getConnection(url, user, password);

            //自動コミットOFF
            conn.setAutoCommit(false);

            //SELECT文の実行
            stmt = conn.createStatement();
            String json = "SELECT total FROM reservetable";
            rset = stmt.executeQuery(json);
            
            //SELECT結果の受け取り
            while(rset.next()){
                String col = rset.getString(1);
                jlist.add(rset.getString("total"));
            }
            System.out.println(jlist);
            request.setAttribute("jlist", jlist);
            request.getRequestDispatcher("/weekchart.jsp").forward(request, response);
            
            //INSERT文の実行
            //sql = "INSERT INTO jdbc_test VALUES (1, 'AAA')";
            //stmt.executeUpdate(sql);
            //conn.commit();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            try {
                if(rset != null)rset.close();
                if(stmt != null)stmt.close();
                if(conn != null)conn.close();
            }
            catch (SQLException e){
                e.printStackTrace();
                
            }

        }

	}

}
