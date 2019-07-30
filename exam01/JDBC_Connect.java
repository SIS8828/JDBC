package exam01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC_Connect {
	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		
		/* My-SQL JDBC Driver
		 * String driver = "com.mysql.jdbc.Driver";
		 * String url = "jdbc:mysql://localhost/academy";
		 */

		Connection con = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, "scott", "tiger");
		
			System.out.println("데이터베이스 연결 성공~~~");
		} catch (Exception e) {
			System.out.println("데이터베이스 연결 실패 ㅠㅠ");
			e.printStackTrace();
		}finally {
			try {
				if(con != null) {	con.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}





