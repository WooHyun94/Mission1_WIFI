<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#customers {
  font-family: Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

#customers td, #customers th {
  border: 1px solid #ddd;
  padding: 8px;
}

#customers tr:nth-child(even){background-color: #f2f2f2;}

#customers tr:hover {background-color: #ddd;}

#customers th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: #04AA6D;
  color: white;
}
</style>
</head>
<body>
<h1>위치 히스토리 목록</h1>
<br>
	<a href='index.jsp'>홈</a>
	|
	<a href='history.jsp'>위치 히스토리 목록</a>
	|
	<a href=''>Open API 와이파이 정보 가져오기</a>
	|
	<a href=''>즐겨 찾기 보기</a>
	|
	<a href=''>즐겨 찾기 그룹 관리</a>
	<br><br>
	
	<table id="customers">
	  <tr>
	    <th>ID</th>
	    <th>X좌표</th>
	    <th>Y좌표</th>
	    <th>조회일자</th>
	    <th>비고</th>
	  </tr>
	  
	  <%
	  wifi.DbTest db = new wifi.DbTest();
	  java.util.List<wifi.HistoryMember> list = db.historySelect();  
	  for(wifi.HistoryMember member : list){
		  out.print("<tr>");
		  out.print("<td>" + Integer.toString(member.getId()) + "</td>");
		  out.print("<td>" + Double.toString(member.getLat()) + "</td>");
		  out.print("<td>" + Double.toString(member.getLnt()) + "</td>");
		  out.print("<td>" + member.getDttm() + "</td>");
		  out.print("</tr>");
	  }
	  %>
	  
	</table>
	
</body>
</html>