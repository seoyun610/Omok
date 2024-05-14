package omok;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/omokfind")
public class omokfind extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	
	
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		MemberDAO dao = new MemberDAO();
		PrintWriter out = response.getWriter();
		String command = request.getParameter("command");
		String name = request.getParameter("name");
        String tel = request.getParameter("tel");
		String result = null;
        if ("findId".equals(command)) {
            result = dao.findId(name, tel);
            if (result != null && !result.isEmpty()) {
                out.println(name + " 님의 아이디는 [" + result + "] 입니다.");
                out.print("<br><a href='/Omok/Login.jsp'> 로그인 하기 </a〉");
                out.print("<br><br><a href='/Omok/Find.jsp'> 비밀번호 찾기 </a〉");
            } else {
                out.println("ID를 찾을 수 없습니다.");
            }
        } else {
            result = dao.findPassword(name, tel);
            if (result != null && !result.isEmpty()) {
                out.println(name + " 님의 비밀번호는 [" + result + "] 입니다.");
                out.print("<br><a href='/Omok/Login.jsp'> 로그인 하기 </a〉");
                out.print("<br><br><a href='/Omok/Find.jsp'> 비밀번호 찾기 </a〉");
            } else {
                out.println("비밀번호를 찾을 수 없습니다.");
            }
        }
        out.println("</body></html>");
        out.close();
    }
}