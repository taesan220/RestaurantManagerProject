package DAODTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class GameDAO {


	// Database connection details
	String driver = "com.mysql.cj.jdbc.Driver"; // JDBC driver for MySQL

	// MySQL 연결을 위한 JDBC URL
	String url = "jdbc:mysql://localhost:3306/restaurant?allowPublicKeyRetrieval=true&useSSL=false";

	// MySQL 사용자 ID 및 비밀번호
	String userid = "test"; // Database user ID
	String passwd = "test"; // Database password

	public int n;

	public GameDAO() {
		try {

			Class.forName(driver);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public ArrayList<GameDTO> selectgame() {
		ArrayList<GameDTO> list = new ArrayList<GameDTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(url, userid, passwd);
			String query = "SELECT * FROM gamequestion  ORDER BY num";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				GameDTO dto = new GameDTO();
				dto.setNum(rs.getInt("num"));
				dto.setQuestion(rs.getString("question"));
				dto.setFinger(rs.getInt("finger"));
				dto.setWord(rs.getString("word"));
				n++;
				list.add(dto);

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
	public void insertgame(int num,String question,int finger,String word) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			String sql = "INSERT INTO gamequestion (num,question,finger,word) VALUES(?,?,?,?)";
			pstmt = con.prepareStatement(sql);

			 pstmt.setInt(1, num);
			 pstmt.setString(2, question);
			pstmt.setInt(3, finger);
			pstmt.setString(4, word);
			
			pstmt.executeUpdate();
			// System.out.println(n+"�� ������");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

}
