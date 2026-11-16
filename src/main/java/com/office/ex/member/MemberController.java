package com.office.ex.member;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.office.ex.Config;

@WebServlet("*.mem")
public class MemberController extends HttpServlet {  // 서블릿이 되기 위해 HttpServlet을 상속
	private static final long serialVersionUID = 1L;
       
	MemberService memberService = new MemberService();

    public MemberController() {
        super();
    }


    // 사용자가 Get 방식으로 요청
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 사용자 요청을 알아내기 위한 코드 3줄
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();	
		String command = RequestURI.substring(contextPath.length()); // substring : 문자열을 잘라내는 메서드

//		MemberService memberService = new MemberService(); 여기다 객체생성을하면 요청할때마다 객체가 생성됩니다.
		String nextPage = null;
		
		switch (command) {
		case MemberConfig.MEMBER_SIGNUP_CONFIRM:
			nextPage = generateView("/signup_form");      // 응답페이지 만들기
			break;
			
		case "/signup_confirm.mem":
//			memberService = new MemberService();
			
			int resultForSignUp = memberService.signupConfirm(request, response);
			
			if (resultForSignUp == MemberService.SIGN_UP_OK) {
//				nextPage = "view/member/signup_ok.jsp";
				nextPage = generateView("/signup_ok");
			} else {
//				nextPage = "view/member/signup_ng.jsp"; 
				nextPage = generateView("/signup_ng");
			}
			
			break;
			
		case MemberConfig.MEMBER_SIGNIN_CONFIRM:
//			nextPage = "view/member/signin_form.jsp";
			nextPage = generateView("/signin_form");
			
			break;
			
		case "/signin_confirm.mem":
//			memberService = new MemberService();
			int resultForSignIn = memberService.signinConfirm(request, response);
			
			if (resultForSignIn == MemberService.SIGN_IN_OK) {
//				nextPage = "view/member/signin_ok.jsp";
				nextPage = generateView("/signin_ok");
			} else {
//				nextPage = "view/member/signin_ng.jsp";
				nextPage = generateView("/signin_ng");
			}
			
			break;
			
		case MemberConfig.MEMBER_SIGNOUT_CONFIRM:
			
			memberService.signoutConfirm(request, response);
			
			nextPage = request.getContextPath().concat(Config.HOME);
			
			break;
			
		}
		
		// dispatcher가 응답해서 페이지를 보여준다.
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);
		
	}
	
	// 리팩토링
	private String generateView(String viewName) {
		
		String view = MemberConfig.VIEW_PREFIX
				.concat(viewName)
				.concat(MemberConfig.VIEW_SURFIX);
		return view;
	}

	// 사용자가 Post 방식으로 요청
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		request.setCharacterEncoding("UTF-8");  // 추가해야하는 코드 - 한글등이 깨지는 것을 방지한다.
		doGet(request, response);   // Post로 들어온 요청을 doGet()으로 보낸다.
	}

}
