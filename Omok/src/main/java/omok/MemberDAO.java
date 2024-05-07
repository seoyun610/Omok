package omok;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import omok.MemberVO;

public class MemberDAO {
	private Connection con;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	public MemberDAO() {
        try {
            InitialContext ctx = new InitialContext();
            dataFactory = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
	
	public void addMember(MemberVO vo) {
		try { 
			con = dataFactory.getConnection();
			String id = vo.getId();
			String pwd = vo.getPwd();
			String name = vo.getName();
			String tel = vo.getTel();
			
			String query = "insert into omok";
			query += " (id, pwd, name, tel)";
			query += " values(?, ?, ?, ?)";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, tel);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	



	 public boolean login(String id, String pwd) {
	     Connection con = null;
	     PreparedStatement pstmt = null;
	     ResultSet rs = null;
	     
			boolean result = false;
			try {
				con = dataFactory.getConnection();
				String query = "select decode(count(*), 1, 'true', 'false') as result from omok";
				query += " where id=? and pwd=?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, id);
				pstmt.setString(2, pwd);
				rs = pstmt.executeQuery();
				rs.next();
				result = Boolean.parseBoolean(rs.getString("result"));
				pstmt.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally { // 오류가 있더라도 무조건 종료를 해야 하니까 finally에 작성
				try { rs.close(); } catch(Exception e) {}
				try { pstmt.close(); } catch(Exception e) {}
				try { con.close(); } catch(Exception e) {} 
			}
			
			return result;

	     
	}
	 
	 
	 public String findId(String name, String tel) {
	        ResultSet rs = null;
	        String id = "";

	        try {
	        	con = dataFactory.getConnection();
	            String query = "SELECT id FROM omok WHERE name = ? AND tel = ?";
	            pstmt = con.prepareStatement(query);
	            pstmt.setString(1, name);
	            pstmt.setString(2, tel);
	            rs = pstmt.executeQuery();
	            if (rs.next()) {
	            	id = rs.getString("id");
		            System.out.println(id);
	            }
	            
	            return id;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return "";
	        } finally {
	            try {
	                if (rs != null) rs.close();
	                if (pstmt != null) pstmt.close();
	                if (con != null) con.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    
	    public String findPassword(String name, String tel) {
	        ResultSet rs = null;
	        String pwd = "";

	        try {
	        	con = dataFactory.getConnection();
	            String query = "SELECT pwd FROM omok WHERE name = ? AND tel = ?";
	            pstmt = con.prepareStatement(query);
	            pstmt.setString(1, name);
	            pstmt.setString(2, tel);
	            rs = pstmt.executeQuery();
	            if (rs.next()) {
					pwd = rs.getString("pwd");
				} 

	            return pwd;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return "";
	        } finally {
	            try {
	                if (rs != null) rs.close();
	                if (pstmt != null) pstmt.close();
	                if (con != null) con.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
}



