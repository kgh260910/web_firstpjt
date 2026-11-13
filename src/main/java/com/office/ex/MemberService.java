package com.office.ex;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MemberService {
	
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

}
