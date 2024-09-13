package DAODTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ManagerDAO {

	// Database connection details
	String driver = "com.mysql.cj.jdbc.Driver"; // JDBC driver for MySQL

	// MySQL 연결을 위한 JDBC URL
	String url = "jdbc:mysql://localhost:3306/restaurant?allowPublicKeyRetrieval=true&useSSL=false";

	// MySQL 사용자 ID 및 비밀번호
	String userid = "test"; // Database user ID
	String passwd = "test"; // Database password

	public int n;

	public ManagerDAO() {
		try {

			Class.forName(driver);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public ArrayList<AnorderDTO> selectime(String query) {
		ArrayList<AnorderDTO> list = new ArrayList<AnorderDTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(query);
		//	pstmt.setInt(1, time);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				AnorderDTO anorderdto = new AnorderDTO();

				anorderdto.setNum(rs.getInt("num"));
				anorderdto.setTablecode(rs.getInt("tablecode"));
				anorderdto.setKind(rs.getString("kind"));
				anorderdto.setName(rs.getString("name"));
				anorderdto.setAmount(rs.getInt("amount"));
				anorderdto.setAnorder(rs.getInt("anorder"));
				anorderdto.setTime(rs.getDate("time"));
				anorderdto.setCondition(rs.getInt("condition"));
				n++;
				list.add(anorderdto);

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
