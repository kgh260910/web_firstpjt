<%@page import="com.office.ex.member.MemberConfig"%>
<%@page import="com.office.ex.Config"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%
		Object object = session.getAttribute(Config.SIGNINED_MEMBER_ID);  // 한줄로 간단히
		
		if (object != null) {
	%>
		<%-- <%= 내용%>은 자바에서 가져온 내용을 print하는것과 동일하다. --%>
			<a href="<%= request.getContextPath().concat(Config.HOME) %>">home</a> 
			&nbsp;&nbsp; | &nbsp;&nbsp;
			<a href="<%= request.getContextPath().concat(MemberConfig.MEMBER_SIGNOUT_CONFIRM) %>">sign-out</a>
			&nbsp;&nbsp; | &nbsp;&nbsp;
			<a href="<%= request.getContextPath().concat(MemberConfig.MEMBER_MODIFY_CONFIRM) %>">modify</a>
			&nbsp;&nbsp; | &nbsp;&nbsp;
			<a href="<%= request.getContextPath().concat(MemberConfig.MEMBER_DELETE_CONFIRM) %>">delete</a>
	<%
		} else {
	%>
			<a href="<%= request.getContextPath().concat(Config.HOME) %>">home</a>  
			&nbsp;&nbsp; | &nbsp;&nbsp;
			<a href="<%= request.getContextPath().concat(MemberConfig.MEMBER_SIGNUP_CONFIRM) %>">sign-up</a>
			&nbsp;&nbsp; | &nbsp;&nbsp;
			<a href="<%= request.getContextPath().concat(MemberConfig.MEMBER_SIGNIN_CONFIRM) %>">sign-in</a>
	<%
		}
	%>