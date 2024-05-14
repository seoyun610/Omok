package omok;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@WebServlet("/GamePage/*")
public class GameController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private GameDAO dao;
	
	//처음 실행할 때 단 한 번만 실행 
	public void init() throws ServletException {
		dao = new GameDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String page = null;
		String action = request.getPathInfo();

		if ("/Create.jsp".equals(action)) {
			String gamename = request.getParameter("gamename");
			dao.addGame(gamename);
			page = "/OmokGame.jsp";
			System.out.println(gamename);
		} else if ("/Select.do".equals(action)) {
			List<GameVO> list = dao.findGame();
			request.setAttribute("gameList", list);
			page = "/Roomselect.jsp";
			System.out.println(list);
		} // else if ("/call.do".equals(action)) {
//			
//			
//			page = "/OmokGame.jsp";
//			System.out.println(gamename);
//		}
		
		
		request.getRequestDispatcher(page).forward(request, response);
		
	}

}
