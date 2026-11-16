package com.office.ex;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MemberService {
	
	final public static int SIGN_IN_OK = 1;
	final public static int SIGN_IN_NG = 0;
	
	MemberDao memberDao = new MemberDao();

	public int signupConfirm(HttpServletRequest request, HttpServletResponse response) {
		
		String memId = request.getParameter("memId");   //signup_form.jsp에서 만든것들의
		String memPw = request.getParameter("memPw");     // name을 가져왔습니다.
		String memMail = request.getParameter("memMail");
		String memPhone = request.getParameter("memPhone");
		
		MemberDto memberDto = new MemberDto(memId, memPw, memMail, memPhone);
		
		int result = memberDao.insertNewMember(memberDto);
		
		return result;
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
		if (selectedMemberDto != null && selectedMemberDto.getMemPw().equals(memPw)) 
			return SIGN_IN_OK;
	
		return SIGN_IN_NG; 
	}
}
