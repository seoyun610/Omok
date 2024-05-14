package omok;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import omok.MemberVO;

public class GameDAO {
	private Connection con;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	public GameDAO() {
        try {
        	Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
	
	public void addGame(String gamename) {
		try { 
			con = dataFactory.getConnection();

			
			String query = "insert into GAMEROOMS (gamename) values (?)";
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, gamename);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally { 
			try { pstmt.close(); } catch(Exception e) {}
			try { con.close(); } catch(Exception e) {} 
		}
	}
	
	 
	 public List<GameVO> findGame() {
		 List<GameVO> list = new ArrayList<>();
	     ResultSet rs = null;

	     try {
	        con = dataFactory.getConnection();
	        String query = "SELECT gamename FROM GAMEROOMS WHERE PPL = 1";
	        pstmt = con.prepareStatement(query);
	        rs = pstmt.executeQuery();
	        while (rs.next()) {
	        	GameVO vo = new GameVO();
				vo.setGamename(rs.getString("gamename"));
				list.add(vo);
	        }
	     } catch (SQLException e) {
	        e.printStackTrace();
	     } finally { // 오류가 있더라도 무조건 종료를 해야 하니까 finally에 작성
			try { rs.close(); } catch(Exception e) {}
			try { pstmt.close(); } catch(Exception e) {}
			try { con.close(); } catch(Exception e) {} 
			}
		return list;
	  }
	    
//	 public void joinGame(String gamename) {
//			try { 
//				con = dataFactory.getConnection();
//				
//				String query = "insert into GAMEROOMS (PPL) values (?) where GAMENAME=?";
//				pstmt = con.prepareStatement(query);
//				
//				pstmt.setInt(1, 2);
//				pstmt.setString(2, gamename);
//				pstmt.executeUpdate();
//				pstmt.close();
//			} catch (Exception e) {
//				e.printStackTrace();
//			} finally { 
//				try { pstmt.close(); } catch(Exception e) {}
//				try { con.close(); } catch(Exception e) {} 
//			}
//		}
}

		
	    
	  




