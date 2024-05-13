package omok;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/omoklogin")
public class OmokLogin extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doHandle(request, response);
    }

    protected boolean doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // id, password를 request로부터 꺼내온다.
        String _id = request.getParameter("id");
        String _pwd = request.getParameter("pwd");
        

        // DAO 객체를 생성한다.
        // DAO 객체는 DB와 연결하여 데이터를 주고 받는 객체이다.
        // 이미 MemberDAO Login 메소드를 구현해 놓으셨기 때문에 그것을 사용하면 됩니다
        MemberDAO dao = new MemberDAO();
        boolean isLogin = dao.login(_id, _pwd);
        
        

        // 로그인 호출 결과를 받는다.
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        boolean result = dao.login(_id,_pwd);
		PrintWriter out = response.getWriter();
		
		//
		
		if (result) {
			// 사용자 이름 가져오기
		    String userName = dao.getUserNameById(_id);
			
			out.print("<html><body>");
			out.print( _id + "님, 로그인 되었습니다.");
			out.print("</table></body></html>");
			
			//쿠키를 선언해주고 로그아웃만든 다음에 쿠키 삭제하고
			// user.value 에 데이터 값을 넣어줘서 
			// 쿠키 생성 및 설정
			Cookie userCookie = new Cookie("user", URLEncoder.encode(userName, "UTF-8")); 
			// _id 대신 userName 사용
		    userCookie.setMaxAge(60*60*24); // 쿠키 유효 기간을 1일로 설정
		    response.addCookie(userCookie); // 응답에 쿠키 추가

			
			response.sendRedirect("/Omok/main.html"); // 가정하는 URL입니다. 실제 URL에 맞게 수정해주세요.
			
		} else {
			out.print("<html><body>");
			out.print(" 로그인에 실패하였습니다. <br> 아이디 혹은 비밀번호를 다시 확인해주세요<br>");
			out.print("<a href='/Omok/login.html'> 로그인 하기 </a〉");
			out.print("</table></body></html>");
		}
		return result;
    }
   
}
