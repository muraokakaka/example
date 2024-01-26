package sql;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import config.DBconfig;

public class Register {

	public void customer_register(int id, String name, String subject, int point) throws FileNotFoundException {

		// データベースへの接続情報をプロパティファイルから取得
		DBconfig db_info = new DBconfig();
		String url = db_info.getDBinfo().get("url");
		String user = db_info.getDBinfo().get("user");
		String pass = db_info.getDBinfo().get("password");

		// 実行SQL
		String register_sql = "insert into grades"
				+ "(id, name, subject, point) values(?,?,?,?)";

		// データベースへの接続
		// try〜catch〜resources構文を使用
		try(Connection conn = DriverManager.getConnection(url,user,pass)){
			// オートコミット機能を無効化
			conn.setAutoCommit(false);

			try(PreparedStatement stmt = conn.prepareStatement(register_sql)){
				// 変数register_sqlの一番目の?にdmin_idをセット
				stmt.setInt(1, id);
				// 変数register_sqlの一番目の?にnameをセット
				stmt.setString(2, name);
				// 変数register_sqlの一番目の?にsubjectをセット
				stmt.setString(3, subject);
				// 変数register_sqlの一番目の?にpointをセット
				stmt.setInt(4, point);
				 // SQLの実行
				stmt.executeUpdate();

				// コミット
				conn.commit();
				System.out.println("コミット処理を行いました");
			} catch (SQLException e) {
				conn.rollback();
				System.out.println("ロールバック処理を行いました");
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}
