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
			
			String query = "insert into users";
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
				String query = "select decode(count(*), 1, 'true', 'false') as result from users";
				query += " where id=? and pwd=?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, id);
				pstmt.setString(2, pwd);
				rs = pstmt.executeQuery();
				rs.next();
				result = Boolean.parseBoolean(rs.getString("result"));
				pstmt.close();
				
				//쿠키 선언하고 그 쿠키에 n
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally { // 오류가 있더라도 무조건 종료를 해야 하니까 finally에 작성
				try { rs.close(); } catch(Exception e) {}
				try { pstmt.close(); } catch(Exception e) {}
				try { con.close(); } catch(Exception e) {} 
			}
			
			return result;

	     
	}
	 public List<MemberVO> listUsers(String searchType, String searchWord) {
		 List<MemberVO> list = new ArrayList<>();
		 ResultSet rs = null;
		 try {
			 con = dataFactory.getConnection();
			 String query = "select * from users";
			 String whereQuery = "";
			 if (searchWord != null && !"".equals(searchWord)) {
				if ("all".equals(searchType)) {
					whereQuery = " where id like '%"+searchWord+"%' or name like '%"+searchWord+"%' or email like '%"+searchWord+"%'";
				} else {
					whereQuery = " where "+searchType+" like '%"+searchWord+"%'";
				}
			}
			 query += whereQuery;
			 pstmt = con.prepareStatement(query);
			 rs = pstmt.executeQuery(query);
			 while(rs.next()) {
				 MemberVO vo = new MemberVO();
				 vo.setId(rs.getString("id"));
				 vo.setPwd(rs.getString("pwd"));
				 vo.setName(rs.getString("name"));
				 vo.setTel(rs.getString("tel"));
				 list.add(vo);
			 }
		 } catch (Exception e) {
			 e.printStackTrace();
		 } finally {
			try {rs.close();}catch(Exception e) {}
			try {pstmt.close();}catch(Exception e) {}
			try {con.close();}catch(Exception e) {}
		 }
		 return list;
	 }
	 
	 public String getUserNameById(String id) {
		    String name = null;
		    try {
		        con = dataFactory.getConnection();
		        String query = "select name from users where id=?";
		        pstmt = con.prepareStatement(query);
		        pstmt.setString(1, id);
		        ResultSet rs = pstmt.executeQuery();
		        if(rs.next()) {
		            name = rs.getString("name");
		        }
		        rs.close();
		        pstmt.close();
		        con.close();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return name;
		}

	 
	 
	 public String findId(String name, String tel) {
	        ResultSet rs = null;
	        String id = "";

	        try {
	        	con = dataFactory.getConnection();
	            String query = "SELECT id FROM users WHERE name = ? AND tel = ?";
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
	            String query = "SELECT pwd FROM users WHERE name = ? AND tel = ?";
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



