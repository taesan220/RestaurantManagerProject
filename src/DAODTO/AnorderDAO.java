package DAODTO;

import java.awt.Container;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.print.attribute.standard.PresentationDirection;
import javax.xml.crypto.Data;

public class AnorderDAO {

	// Database connection details
	String driver = "com.mysql.cj.jdbc.Driver"; // JDBC driver for MySQL

	// MySQL 연결을 위한 JDBC URL
	String url = "jdbc:mysql://localhost:3306/restaurant?allowPublicKeyRetrieval=true&useSSL=false";

	// MySQL 사용자 ID 및 비밀번호
	String userid = "test"; // Database user ID
	String passwd = "test"; // Database password

	public int n;

	public AnorderDAO() {
		try {

			Class.forName(driver);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public ArrayList<AnorderDTO> selectorder(String query, int condition) {
		ArrayList<AnorderDTO> list = new ArrayList<AnorderDTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, condition);
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
				System.out.println("name");

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

	public void insertorder(String sql, int tablecode, String kind,
			String name, int amount, int anorder, int condition) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(sql);

			// pstmt.setInt(1, num);
			pstmt.setInt(1, tablecode);
			pstmt.setString(2, kind);
			pstmt.setString(3, name);
			pstmt.setInt(4, amount);
			pstmt.setInt(5, anorder);
			pstmt.setInt(6, condition);

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

	public ArrayList<AnorderDTO> selectevaluation(String query, int tablecode) {

		ArrayList<AnorderDTO> list = new ArrayList<AnorderDTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(query);
			 pstmt.setInt(1, tablecode);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				AnorderDTO anorderdto = new AnorderDTO();
				anorderdto.setKind(rs.getString("kind"));
				anorderdto.setName(rs.getString("name"));
				// anorderdto.setCondition(rs.getInt("condition"));

				// anorderdto.setTablecode(rs.getInt("tablecode"));

				// anorderdto.setNum(rs.getInt("num"));

				// anorderdto.setAmount(rs.getInt("amount"));
				// anorderdto.setAnorder(rs.getInt("anorder"));
				// anorderdto.setTime(rs.getDate("time"));

				n++;

				list.add(anorderdto);

				System.out.println(anorderdto.getName());
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

	public ArrayList<AnorderDTO> showorder(String query) {
		ArrayList<AnorderDTO> list = new ArrayList<AnorderDTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(query);
			// pstmt.setInt(1, condition);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				AnorderDTO anorderdto = new AnorderDTO();

				// anorderdto.setNum(rs.getInt("num"));
				anorderdto.setTablecode(rs.getInt("tablecode"));
				// anorderdto.setKind(rs.getString("kind"));
				// anorderdto.setName(rs.getString("name"));
				// anorderdto.setAmount(rs.getInt("amount"));
				// anorderdto.setAnorder(rs.getInt("anorder"));
				// anorderdto.setTime(rs.getDate("time"));
				// anorderdto.setCondition(rs.getInt("condition"));
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

	public ArrayList<AnorderDTO> showbill(String query, int tablecode) {
		ArrayList<AnorderDTO> list = new ArrayList<AnorderDTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, tablecode);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				AnorderDTO anorderdto = new AnorderDTO();

				anorderdto.setTablecode(rs.getInt("tablecode"));
				anorderdto.setNum(rs.getInt("num"));
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

	public void updateshowbill(String query, int num,int condition) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, condition);
			pstmt.setInt(2, num);
			// AnorderDTO anorderdto = new AnorderDTO();

			// anorderdto.setName(rs.getString("name"));
			/*
			 * anorderdto.setTablecode(rs.getInt("tablecode"));
			 * anorderdto.setNum(rs.getInt("num"));
			 * anorderdto.setKind(rs.getString("kind"));
			 * anorderdto.setAmount(rs.getInt("amount"));
			 * anorderdto.setAnorder(rs.getInt("anorder"));
			 * anorderdto.setTime(rs.getDate("time"));
			 * anorderdto.setCondition(rs.getInt("condition"));
			 */
//			n++;
			pstmt.executeUpdate();

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
	
	public void deletecontent(String query, int num) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, num);
			
			pstmt.executeUpdate();

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
	public ArrayList<AnorderDTO> selectchart(String query,String condution) {
		ArrayList<AnorderDTO> list = new ArrayList<AnorderDTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(query);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {

				AnorderDTO anorderdto = new AnorderDTO();
			
				anorderdto.setName(rs.getString("name"));
				if(condution !=null){
				anorderdto.setAmount(rs.getInt("amount"));
				}
				if(condution==null){
				anorderdto.setAmount(rs.getInt("amount"));
				anorderdto.setNum(rs.getInt("num"));
				anorderdto.setTablecode(rs.getInt("tablecode"));
				anorderdto.setKind(rs.getString("kind"));

				anorderdto.setAnorder(rs.getInt("anorder"));
				anorderdto.setTime(rs.getDate("time"));
				anorderdto.setCondition(rs.getInt("condition"));	
				}
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
}
