<%@page import="wifi.DbTest"%>
<%@page import="wifi.WifiMember"%>
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

</style>
</head>
<body>
<h1>와이파이 정보 구하기</h1>
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
	
	  <select>
	    <option value="none">북마크 그룹 이름 선택</option>
	    
	  </select>
	
	<button value="">즐겨찾기 추가하기</button>
	<br><br>
	<table id=customers>
	  <tr>
	    <th>거리(Km)</th>
	    <th>관리번호</th>
	    <th>자치구</th>
	    <th>와이파이명</th>
	    <th>도로명주소</th>
	    <th>상세주소</th>
	    <th>설치위치(층)</th>
	    <th>설치우형</th>
	    <th>설치기관</th>
	    <th>서비스구분</th>
	    <th>망종류</th>
	    <th>설치년도</th>
	    <th>실내외구분</th>
	    <th>WIFI접속환경</th>
	    <th>X좌표</th>
	    <th>Y좌표</th>
	    <th>작업일자</th>
	  </tr>
	  <%
	  out.print("<tr>");
	  String mgr_no = request.getParameter("MGR_NO");
	  System.out.println(mgr_no);
	  DbTest db = new DbTest();
	  WifiMember member = db.wifiSelect3(mgr_no);
	  
	  	out.print("<td> " + Double.toString(member.getDistance())+"</td>");
	  	out.print("<td> " + member.getNo()+"</td>");
	  	out.print("<td> " + member.getWrdofc() + "</td>");
	  	out.print("<td><a href=\"detail.jsp?MGR_NO="+member.getNo()+"\">" + member.getName() + "</a></td>");
	  	out.print("<td> " + member.getAdd1() + "</td>");
	  	out.print("<td> " + member.getAdd2() + "</td>");
	  	out.print("<td> " + member.getFloor() + "</td>");
	  	out.print("<td> " + member.getTy() + "</td>");
	  	out.print("<td> " + member.getMby() + "</td>");
	  	out.print("<td> " + member.getSe() + "</td>");
	  	out.print("<td> " + member.getCmcwr() + "</td>");
	  	out.print("<td> " + member.getYear() + "</td>");
	  	out.print("<td> " + member.getDoor() + "</td>");
	  	out.print("<td> " + member.getRemars3() + "</td>");
	  	out.print("<td> " + Double.toString(member.getLat()) + "</td>");
	  	out.print("<td> " + Double.toString(member.getLnt()) + "</td>");
	  	out.print("<td> " + member.getDttm() + "</td>");
	  
	  out.print("</tr>");
	  %>
	</table>
</body>
</html>