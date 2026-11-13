package com.office.ex;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("*.mem")
public class MemberController extends HttpServlet {  // 서블릿이 되기 위해 HttpServlet을 상속
	private static final long serialVersionUID = 1L;
       

    public MemberController() {
        super();
    }


    // 사용자가 Get 방식으로 요청
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 사용자 요청을 알아내기 위한 코드 3줄
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();	
		String command = RequestURI.substring(contextPath.length()); // substring : 문자열을 잘라내는 메서드

		
		MemberService memberService = null;
		String nextPage = null;
		
		switch (command) {
		case "/signup.mem":
			nextPage = "view/member/signup_form.jsp";      // 응답페이지 만들기
			break;
			
		case "/signup_confirm.mem":
			memberService = new MemberService();
			
			int resultForSignUp = memberService.signupConfirm(request, response);
			
			if (resultForSignUp == 1) {
				nextPage = "view/member/signup_ok.jsp";
			} else {
				nextPage = "view/member/signup_ng.jsp";
			}
			
			break;
			
		case "/signin.mem":
			break;
		}
		
		// dispatcher가 응답해서 페이지를 보여준다.
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);
		
	}

	// 사용자가 Post 방식으로 요청
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		request.setCharacterEncoding("UTF-8");  // 추가해야하는 코드 - 한글등이 깨지는 것을 방지한다.
		doGet(request, response);   // Post로 들어온 요청을 doGet()으로 보낸다.
	}

}
