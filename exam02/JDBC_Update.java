package exam02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBC_Update {

	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		
		/* My-SQL JDBC Driver
		 * String driver = "com.mysql.jdbc.Driver";
		 * String url = "jdbc:mysql://localhost/academy";
		 */

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, "scott", "tiger");
		
			// System.out.println("데이터베이스 연결 성공~~~");
			
			Scanner sc = new Scanner(System.in);
			
			
			System.out.println("테이블 값 수정하기");
			System.out.print("수정할 번호를 입력하세요:");
			int no = sc.nextInt();
			sc.nextLine(); //dummy 코드
			// int 와 string 타입을 연결해서 사용할떄 같은 타입일때는 잘수행되지만 
			//숫자와 문자가 만날떄 엔터에 대한 처리가 잘되지 않기 때문에 더미코드를 
			//추가해주어 오류발생을 방지한다.
			System.out.print("수정할 이름을 입력하세요: ");
			String name = sc.nextLine();
			System.out.print("수정할 이메일을 입력하세요: ");
			String email = sc.nextLine();
			System.out.print("수정할 전화번호을 입력하세요: ");
			String tel = sc.nextLine();
			
			String sql = "update customer set name = ?, email = ?, tel = ? "
					+ "where no = ?";
			pstmt = con.prepareStatement(sql); // 객체를 반환해줘라 
			pstmt.setNString(1, name);
			pstmt.setNString(2, email);
			pstmt.setNString(3, tel);
			pstmt.setInt(4, no);
			
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
