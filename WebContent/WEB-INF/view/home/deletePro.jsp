<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="home.home.model.SignupDAO" %>
<%@ page import="home.home.model.SignupDTO" %>
    <%
    	String sessionId = (String)session.getAttribute("id");
    	if(sessionId == null){
    	response.sendRedirect("main.jsp");
    }
    %>
    
    <jsp:useBean id = "dto" class = "home.home.model.SignupDTO" />
    <jsp:setProperty property="*" name="dto" />
    
    <%
    SignupDAO dao = new SignupDAO();
   	boolean result = dao.loginCheck(dto);
   	if(result){
    	dao.delete(dto);
    	session.invalidate();
    	%>
    	<script>
    		alert("탈퇴되었습니다");
    		window.location = "main.jsp"
    	</script>
   	<%}else{%>
   		<script>
   			alert("비밀번호가 맞지 않습니다.");
   			window.location = "deleteForm.jsp"
   		</script>		
   	<%}%>