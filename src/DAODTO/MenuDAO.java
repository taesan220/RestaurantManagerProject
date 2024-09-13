package DAODTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MenuDAO {

	// Database connection details
	String driver = "com.mysql.cj.jdbc.Driver"; // JDBC driver for MySQL

	// MySQL 연결을 위한 JDBC URL
	String url = "jdbc:mysql://localhost:3306/restaurant?allowPublicKeyRetrieval=true&useSSL=false";

	// MySQL 사용자 ID 및 비밀번호
	String userid = "test"; // Database user ID
	String passwd = "test"; // Database password

	public int n = 0;

	public MenuDAO() {

//		try {
//
//			Class.forName(driver);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	public ArrayList<MenuDTO> select(String query,String kind, String name) {
		ArrayList<MenuDTO> list = new ArrayList<MenuDTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(query);// ������ �����ڷ� �޾ƿ�
		if(kind != null){
				pstmt.setString(1, kind);
		}
		
			if(name !=null){pstmt.setString(2, name);
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {

				MenuDTO menudto = new MenuDTO();
				
				menudto.setKind(rs.getString("kind"));
				menudto.setName(rs.getString("name"));
				menudto.setNum(rs.getInt("num"));
				menudto.setMeterial(rs.getString("meterial"));
				menudto.setExplain(rs.getString("explain"));
				menudto.setCalorie(rs.getInt("calorie"));
				menudto.setPrice(rs.getInt("price"));
				
				System.out.println(menudto.getMeterial()+"alskdn");
				
				list.add(menudto);

				n++;
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
	public ArrayList<MenuDTO> fororder(String name) {
		ArrayList<MenuDTO> list = new ArrayList<MenuDTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(url, userid, passwd);
			String query = "SELECT * FROM menu WHERE name=?";
			pstmt = con.prepareStatement(query);// ������ �����ڷ� �޾ƿ�
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				MenuDTO menudto = new MenuDTO();
				
				menudto.setName(rs.getString("name"));
				menudto.setKind(rs.getString("kind"));
				menudto.setNum(rs.getInt("num"));
				menudto.setMeterial(rs.getString("meterial"));
				menudto.setExplain(rs.getString("explain"));
				menudto.setCalorie(rs.getInt("calorie"));
				menudto.setPrice(rs.getInt("price"));
				
				
				
				list.add(menudto);

				n++;
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

	public void insertmenu(String kind, int num, String name, String meterial,
			String explain, int calorie, int price) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = DriverManager.getConnection(url, userid, passwd);
			String sql = "INSERT INTO menu (kind,name,meterial,`explain`,calorie,price) VALUES(?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, kind);
			//pstmt.setInt(2, num);
			pstmt.setString(2, name);
			pstmt.setString(3, meterial);
			pstmt.setString(4, explain);
			pstmt.setInt(5, calorie);
			pstmt.setInt(6, price);

			int n = pstmt.executeUpdate();

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
	
	public void updatetmenu(String kind, String name, String meterial,
			String explain, int calorie, int price) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = DriverManager.getConnection(url, userid, passwd);
			String sql = "UPDATE menu SET kind=?,meterial=?,`explain`=?,calorie=?,price=? WHERE name = ?";
			pstmt = con.prepareStatement(sql);
			
			
			pstmt.setString(1, kind);
			pstmt.setString(2, meterial);
			pstmt.setString(3, explain);
			pstmt.setInt(4, calorie);
			pstmt.setInt(5, price);
			pstmt.setString(6, name);
			
			int n = pstmt.executeUpdate();

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