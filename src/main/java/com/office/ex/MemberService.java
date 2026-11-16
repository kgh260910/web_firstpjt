package com.office.ex;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class MemberService {
	
	final public static int SIGN_UP_OK = 1;
	final public static int SIGN_UP_NG = 0;
	final public static int SIGN_IN_OK = 1;
	final public static int SIGN_IN_NG = 0;
	
	
	MemberDao memberDao = new MemberDao();

	public int signupConfirm(HttpServletRequest request, HttpServletResponse response) {
		
		String memId = request.getParameter("memId");   //signup_form.jsp에서 만든것들의
		String memPw = request.getParameter("memPw");     // name을 가져왔습니다.
		String memMail = request.getParameter("memMail");
		String memPhone = request.getParameter("memPhone");
		
		MemberDto memberDto = new MemberDto(memId, memPw, memMail, memPhone);
			
		return memberDao.insertNewMember(memberDto);
	}

	public int signinConfirm(HttpServletRequest request, HttpServletResponse response) {
		
		String memId = request.getParameter("memId");
		String memPw = request.getParameter("memPw");
		
		MemberDto selectedMemberDto = memberDao.selectMemberByMemIdAndMemPw(memId);
		
		// Dao에서 주석처리된 아이디,비밀번호 확인 로직을 여기서 진행합니다.
//		if (selectedMemberDto != null && selectedMemberDto.getMemPw().equals(memPw)) {
//			return SIGN_IN_OK;
//		} else {
//			return SIGN_IN_NG;
//		}
		// 리팩토링
		if (selectedMemberDto != null && selectedMemberDto.getMemPw().equals(memPw))  {
			 
			HttpSession session = request.getSession();  // 세션객체 받기 (세션 생성은 서버에 접속했을 때 된다.)
//			session.setAttribute("signinedMemberId", selectedMemberDto.getMemId());    
			session.setAttribute(Config.SIGNINED_MEMBER_ID, selectedMemberDto.getMemId()); //윗줄을 Config로 리팩토링
			session.setMaxInactiveInterval(60 * 30);  // 세션유지시간 : 초단위 * 30 = 30분
			
			return SIGN_IN_OK;
		}
	
		return SIGN_IN_NG; 
	}

	public void signoutConfirm(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		session.removeAttribute(Config.SIGNINED_MEMBER_ID); //세션 객체는 살리고 ID는 제거
		
		// 또 다른 방법
//		session.invalidate();  // 세션 객체 자체를 제거  
		
	}
}
