<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<style>
	table{	border :  3px solid black;}
</style>
    
    
<%
	int num=0,ref=1,re_step=1,re_level=0;
		try{
		if(request.getParameter("num") != null){
			// 답글일경우 원래글에서 값을 전달받아 저장
			num=Integer.parseInt(request.getParameter("num"));
			ref=Integer.parseInt(request.getParameter("ref"));
			re_step=Integer.parseInt(request.getParameter("re_step"));
			re_level=Integer.parseInt(request.getParameter("re_level"));
	}
%>

<body>
<br>
<form method = "post" name = "writeform" action = "writePro.jsp">
	<%-- 히든타입으로 만든 값을 pro페이지로 전송 --%>
	<input type="hidden" name="num" value="<%=num%>">
	<input type="hidden" name="ref" value="<%=ref%>">
	<input type="hidden" name="re_step" value="<%=re_step%>">
	<input type="hidden" name="re_level" value="<%=re_level%>">
	
	<table>
		<tr>
			<td>
				<a href="list.jsp">글목록</a>
			</td>
		</tr>
		<tr>
			<td>이름</td>
			<td><input type="text" size ="10" maxlength="10" name="writer"></td>			
		</tr>
		<tr>
			<td>제목</td>		
			<td>
			<%if(request.getParameter("num")==null) {%> 
				<%-- 글 쓰기  --%>
				<input type="text" size="40" maxlength="40" name="subject"></td>
			<%}else{ %>
				<%-- 답변 쓰기  --%>
				<input type="text" size="40" maxlength="40" name="subject" value="[답변]"></td>
			<%} %>
		</tr>
		<tr>
			<td>Email</td>		
			<td><input type="text" size="40" maxlength="30" name="email" ></td>
		</tr>
		<tr>
			<td>내용</td>		
			<td><textarea name="content" rows="13" cols="40"></textarea></td>
		</tr>
		<tr>
			<td>비밀번호</td>		
			<td><input type="password" size="8" maxlength="12" name="passwd"></td>
		</tr>
		<tr>
			<td>
				<input type="submit" value="글쓰기" >  
  				<input type="reset" value="다시작성">
  				<input type="button" value="목록보기" OnClick="window.location='list.jsp'">
			</td>		
		</tr>										
	</table>
	<%
		}catch(Exception e){}
	%>
</form>
</body>