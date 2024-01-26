
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
public class Week extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ResultSet rs01 = null;
        ResultSet rset01 = null;
        ResultSet rset02 = null;

        //接続文字列
        String url = "jdbc:postgresql://localhost:5432/mydb";
        String user = "postgres";
        String password = "admin";
        
        List<String> dummy = new ArrayList<>();
        List<String> jlist = new ArrayList<>();
        List<String> weekgendercount = new ArrayList<>();
        List<String> agecount = new ArrayList<>();
        
//ダミー-----------------------------------------------------------------------------------------------
//チケット番号数---------------------------------------------------------------------------------------------------------
              
              try{
                  //PostgreSQLへ接続
                  conn = DriverManager.getConnection(url, user, password);

                  //自動コミットOFF
                  conn.setAutoCommit(false);

                  //SELECT文の実行
                  stmt = conn.createStatement();
                  String json = "SELECT ticketid FROM ticket";
                  rs01 = stmt.executeQuery(json);
                  
                  //SELECT結果の受け取り
                  while(rs01.next()){
                	  dummy.add(rs01.getString("ticketid"));
                  }
                  
                  System.out.println(dummy);
              }
                  catch (SQLException e){
                      e.printStackTrace();
              }
                  finally {
                      try {
                          if(rs01 != null)rs01.close();
                          if(stmt != null)stmt.close();
                          if(conn != null)conn.close();
                      }
                      catch (SQLException e){
                          e.printStackTrace();
                          
                      }

                  }
                  

//男女別の週別来場者数-----------------------------------------------------------------------------------------------
//来場者---------------------------------------------------------------------------------------------------------
        
        try{
            //PostgreSQLへ接続
            conn = DriverManager.getConnection(url, user, password);

            //自動コミットOFF
            conn.setAutoCommit(false);

            //SELECT文の実行
            stmt = conn.createStatement();
            String json = "SELECT total FROM reservetable";
            rs = stmt.executeQuery(json);
            
            //SELECT結果の受け取り
            while(rs.next()){
                jlist.add(rs.getString("total"));
            }
            
            System.out.println(jlist);
        }
            catch (SQLException e){
                e.printStackTrace();
        }
            finally {
                try {
                    if(rs != null)rs.close();
                    if(stmt != null)stmt.close();
                    if(conn != null)conn.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                    
                }

            }
            
//来場者---------------------------------------------------------------------------------------------------------
//男女別の週別来場者数-----------------------------------------------------------------------------------------------        
        
        try{
            //PostgreSQLへ接続
            conn = DriverManager.getConnection(url, user, password);

            //自動コミットOFF
            conn.setAutoCommit(false);

            //SELECT文の実行
            stmt = conn.createStatement();
            String countmen = "SELECT COUNT(gender), gender FROM membertable \r\n"
        					+ "INNER JOIN reservetable \r\n"
        					+ "ON membertable.memberid = reservetable.memberid \r\n"
        					+ "GROUP BY gender";
            rset01 = stmt.executeQuery(countmen);
            while(rset01.next()){
            
            weekgendercount.add(rset01.getString("count"));
          }
          System.out.println(weekgendercount);
        
       }
          catch (SQLException e){
              e.printStackTrace();
       }
          finally {
            try {
                if(rset01 != null)rset01.close();
                if(stmt != null)stmt.close();
                if(conn != null)conn.close();
            }
            catch (SQLException e){
                e.printStackTrace();
                
            }

        }
        
//来場者---------------------------------------------------------------------------------------------------------
//年齢別の週別来場者数-----------------------------------------------------------------------------------------------  
        
        try{
            //PostgreSQLへ接続
            conn = DriverManager.getConnection(url, user, password);

            //自動コミットOFF
            conn.setAutoCommit(false);

            //SELECT文の実行
            stmt = conn.createStatement();
            String countage = "SELECT COUNT(reserveid) AS ad,CONCAT(FLOOR(EXTRACT(YEAR FROM AGE(TO_DATE(birth, 'YYYY年mm月dd日'))) / 10) * 10, ' ') AS age_group \r\n"
            				+ "FROM membertable \r\n"
            				+ "INNER JOIN reservetable \r\n"
            				+ "ON membertable.memberid = reservetable.memberid \r\n"
            				+ "GROUP BY age_group \r\n"
            				+ "ORDER BY age_group ASC \r\n";
            rset02 = stmt.executeQuery(countage);
            while(rset02.next()){
            
            agecount.add(rset02.getString("ad"));
          }
          System.out.println(agecount);
        
       }
          catch (SQLException e){
              e.printStackTrace();
       }
          finally {
            try {
                if(rset02 != null)rset02.close();
                if(stmt != null)stmt.close();
                if(conn != null)conn.close();
            }
            catch (SQLException e){
                e.printStackTrace();
                
            }

        }
        
        request.setAttribute("jlist", jlist);
        request.setAttribute("weekgendercount", weekgendercount);
        request.setAttribute("agecount", agecount);
        request.getRequestDispatcher("WEB-INF/jsp/weekchart.jsp").forward(request, response);
        
	}

}
