package exam03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbcp2.BasicDataSource;

class ConnPoolDAO { // Java_DB연동(CRUD)
	// 필드
	BasicDataSource ds;
	PreparedStatement pstmt = null;
	Connection con = null; // 디비연결 변수
	ResultSet rs = null; // 결과값 저장

	// 생성자 자바에 의해서 호출되는 용도 ex) new ConnPoolDAO()
	ConnPoolDAO() {
		ds = new BasicDataSource();

		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		ds.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		ds.setUsername("scott");
		ds.setPassword("tiger");

		ds.setInitialSize(5);
	}

	// 메소드(CRUD)
	public void insertDao(ConnPoolDTO dto) {

		try {
			con = ds.getConnection();

			String sql = "insert into customer values(?,?,?,?)";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getNo());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getEmail());
			pstmt.setString(4, dto.getTel());

			pstmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public List<ConnPoolDTO> selectDao() {
		List<ConnPoolDTO> list = new ArrayList<ConnPoolDTO>();

		try {
			con = ds.getConnection();

			String sql = "select * from customer order by no";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) { // rs에 저장한 데이터 읽어오기

				ConnPoolDTO dto = new ConnPoolDTO();

				dto.setNo(rs.getInt("no"));
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("email"));
				dto.setTel(rs.getString("tel"));

				list.add(dto);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					con.close();
				}
				if (pstmt != null) {
					con.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public void updateDAO(ConnPoolDTO dto) {
		try {
			con = ds.getConnection();

			String sql = "update customer set name = ?, email = ?, tel = ? " + "where no = ?";
			pstmt = con.prepareStatement(sql); // 객체를 반환해줘라
			pstmt.setNString(1, dto.getName());
			pstmt.setNString(2, dto.getEmail());
			pstmt.setNString(3, dto.getTel());
			pstmt.setInt(4, dto.getNo());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public void deleteDAO(ConnPoolDTO dto) {
		try {
			con = ds.getConnection();
			String sql = "delete from customer where no = ?";
			pstmt = con.prepareStatement(sql); // 객체를 반환해줘라
			pstmt.setInt(1, dto.getNo());
			pstmt.executeUpdate(); // 수정한 레코드 갯수를 반환해준다.
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public void selectDao2(ConnPoolDTO dto) {

		try {
			con = ds.getConnection();

			String sql = "select * from customer where no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getNo());
			rs = pstmt.executeQuery();

			rs.next();

			dto.setNo(rs.getInt("no"));
			dto.setName(rs.getString("name"));
			dto.setEmail(rs.getString("email"));
			dto.setTel(rs.getString("tel"));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					con.close();
				}
				if (pstmt != null) {
					con.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
