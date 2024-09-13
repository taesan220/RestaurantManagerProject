package DAODTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class EvaluationDAO {

	// Database connection details
	String driver = "com.mysql.cj.jdbc.Driver"; // JDBC driver for MySQL

	// MySQL 연결을 위한 JDBC URL
	String url = "jdbc:mysql://localhost:3306/restaurant?allowPublicKeyRetrieval=true&useSSL=false";

	// MySQL 사용자 ID 및 비밀번호
	String userid = "test"; // Database user ID
	String passwd = "test"; // Database password
	public int n;

	public EvaluationDAO() {
		try {

			Class.forName(driver);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public ArrayList<EvaluationDTO> selectevaluation(String query) {
		ArrayList<EvaluationDTO> list = new ArrayList<EvaluationDTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(query);

			rs = pstmt.executeQuery();
			while (rs.next()) {

				EvaluationDTO evaluationdto = new EvaluationDTO();

				evaluationdto.setName(rs.getString("checkname"));
				evaluationdto.setCheckscore(rs.getInt("checkscore"));
				evaluationdto.setChecknum(rs.getInt("checknum"));
				
			
				n++;
				list.add(evaluationdto);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if (con != null)
					con.close();
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;

	}
}
