package exam02;

import java.sql.*;

public class JDBC_Select {
	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		
		Connection con = null;
		
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, "scott", "tiger");
		
			// System.out.println("데이터베이스 연결 성공~~~");
			
			String sql = "select * from customer";
			pstmt = con.prepareStatement(sql); // sql 명령문을 db에 전송
			rs = pstmt.executeQuery(); // 값을 받아와서 잘 저장해둠
			
			// db에 데이터 존재 유무
			System.out.println("번호\t이름\t이메일\t전화번호");
			System.out.println("------------------------------");
			
			while(rs.next()) {
			int no = rs.getInt("no"); // db에서 no를 int로 가져와달라
			String name = rs.getString("name");
			String email = rs.getString("email"); 
			String tel = rs.getString("tel"); 
			
			System.out.printf("%d\t%s\t%s\t%s\n",no,name,email,tel);
			}
			
		} catch (Exception e) {
			System.out.println("데이터베이스 연결 실패 ㅠㅠ");
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) {	con.close();}
				if(pstmt != null) {	con.close();}
				if(con != null) {	con.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}

