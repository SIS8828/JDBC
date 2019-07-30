package exam02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBC_Insert {
	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, "scott", "tiger");
		
			//System.out.println("데이터베이스 연결 성공~~~");
			Scanner keyboard = new Scanner(System.in);
			
			System.out.println("customer 테이블에 값 입력하기.");
			System.out.print("저장할 번호를 입력하세요: ");
			String no = keyboard.nextLine();
			
			System.out.print("이름을 입력하세요: ");
			String name = keyboard.nextLine();
			
			System.out.print("이메일 입력하세요: ");
			String email = keyboard.nextLine();
			
			System.out.print("전화번호를 입력하세요: ");
			String tel = keyboard.nextLine();
			
			int intNo = Integer.parseInt(no);
			
			//String sql = "insert into customer values("
			//					+no+", '"+name+"', '"+email+"', '"+tel+"')";
			// Statement stmt = con.createStatement();
					
			String sql = "insert into customer values(?,?,?,?)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, intNo);
			pstmt.setString(2, name);
			pstmt.setString(3, email);
			pstmt.setString(4, tel);
			
			int result = pstmt.executeUpdate();
			
			if(result == 1) {
				System.out.println("저장 성공 ~");
			}else {
				System.out.println("저장 실패 ㅠ");
			}
			
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

