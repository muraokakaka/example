package sql;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.DBconfig;
import object.Customer;

public class SelectOneCustomer {

	public Customer get_One_Customer_Info(int id) throws FileNotFoundException {
		// データベースへの接続情報をプロパティファイルから取得
		DBconfig db_info = new DBconfig();
		String url = db_info.getDBinfo().get("url");
		String user = db_info.getDBinfo().get("user");
		String pass = db_info.getDBinfo().get("password");

		// 該当する顧客情報のオブジェクトを作成
		Customer one_customer = new Customer();

		// 実行SQL
		// customer_id（顧客ID）で該当する顧客情報を取得する
		String one_customer_sql = "SELECT * FROM grades WHERE id = ?";

		try(Connection conn = DriverManager.getConnection(url,user,pass)){
			PreparedStatement stmt = conn.prepareStatement(one_customer_sql);

			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				//オブジェクトにデータを一時格納
				one_customer.setId(rs.getInt("id"));
				one_customer.setName(rs.getString("name"));
				one_customer.setSubject(rs.getString("subject"));
				one_customer.setPoint(rs.getInt("point"));
			}
			
		} catch (SQLException e) {
			System.out.println("データベースとの接続を閉じます");
			e.printStackTrace();
		}
		return one_customer;
	}
}