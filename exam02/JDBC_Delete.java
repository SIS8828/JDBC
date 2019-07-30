package exam02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBC_Delete {

	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, "scott", "tiger");
		
			// System.out.println("데이터베이스 연결 성공~~~");
			
			Scanner sc = new Scanner(System.in);
			
			
			System.out.println("테이블 값 삭제하기");
			System.out.print("삭제할 번호를 입력하세요:");
			int no = sc.nextInt();
			
			String sql = "delete from customer where no = ?";
			pstmt = con.prepareStatement(sql); // 객체를 반환해줘라 
			pstmt.setInt(1, no);
			
			pstmt.executeUpdate(); //수정한 레코드 갯수를 반환해준다.
			
		} catch (Exception e) {
			System.out.println("데이터베이스 연결 실패 ㅠㅠ");
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) {pstmt.close();}
				if(con != null) {	con.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	}


