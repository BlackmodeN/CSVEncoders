package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.C3P0Connection;

public class Data {
	public static String Find(String query) throws SQLException {
		Connection conn = C3P0Connection.getInstance().getConnection();
		String sql = "SELECT * FROM DATABASE$ WHERE 主机ip = ?";
		String result = "无数据";
		if(query==null){
			return result;
		}
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
			if (rs.getString(" 项目") != null)
				result = rs.getString(" 项目");
			

			}		
		} catch (Exception e) {
			e.printStackTrace();
		} 
		conn.close();
		return result;

	}

}
