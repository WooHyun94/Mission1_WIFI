<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
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
<script type="text/javascript">
	function getLocation() {
	    if (navigator.geolocation) {
	        navigator.geolocation.getCurrentPosition(function (position) {
	                var lat = position.coords.latitude;
	                document.getElementById("lat").value = lat ;
	                var lnt = position.coords.longitude;
	                document.getElementById("lnt").value = lnt;
	                
	            },
	            function (error) {
	                console.error(error);
	            },
	            {
	                enableHighAccuracy: false,
	                maximumAge: 0,
	                timeout: Infinity
	            });
	        <%
	        
	        %>
	    } else {
	        alert('현재 위치를 찾을 수 없습니다.');
	    }
	}
	
	function viewNearbyWifi() {
        var lat = document.getElementById("lat").value;
        var lnt = document.getElementById("lnt").value;

        window.location.href = "http://localhost:8080/Mission1-WIFI/index.jsp?lat=" + lat + "&lnt=" + lnt;
    }
</script>
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
	LAT: <input type="text" id="lat">, 
	LNT: <input type="text" id="lnt"> 
	<button onclick="getLocation()">내 위치 가져오기</button>
	<button onclick="viewNearbyWifi()">근처 WIFI 정보 보기</button>
	
	<br><br>
	<table id="customers">
	  <tr>
	    <th>거리<br>(Km)</th>
	    <th>관리번호</th>
	    <th>자치구</th>
	    <th>와이파이명</th>
	    <th>도로명주소</th>
	    <th>상세주소</th>
	    <th>설치위치<br>(층)</th>
	    <th>설치우형</th>
	    <th>설치기관</th>
	    <th>서비스<br>구분</th>
	    <th>망종류</th>
	    <th>설치년도</th>
	    <th>실내외<br>구분</th>
	    <th>WIFI접속<br>환경</th>
	    <th>X좌표</th>
	    <th>Y좌표</th>
	    <th>작업일자</th>
	  </tr>
	  <%
        String latParam = request.getParameter("lat");
		String lntParam = request.getParameter("lnt");
        
        // 데이터베이스 업데이트 함수 호출 (latParam와 lntParam 값을 전달)
        if (latParam != null && lntParam != null) {
            DbTest db = new DbTest();
            db.updateDistance(Double.parseDouble(latParam), Double.parseDouble(lntParam));
            List<WifiMember> list = new ArrayList<>();
            list = db.wifiSelect2();
            
            for(WifiMember member : list){
            	out.print("<tr>");
            	out.print("<td>" + Double.toString(member.getDistance())+"</td>");
            	out.print("<td>" + member.getNo()+"</td>");
            	out.print("<td>" + member.getWrdofc() + "</td>");
            	out.print("<td><a href=\"detail.jsp?MGR_NO=" + member.getNo() + "\">" + member.getName() + "</a></td>");
            	out.print("<td>" + member.getAdd1() + "</td>");
            	out.print("<td>" + member.getAdd2() + "</td>");
            	out.print("<td>" + member.getFloor() + "</td>");
            	out.print("<td>" + member.getTy() + "</td>");
            	out.print("<td>" + member.getMby() + "</td>");
            	out.print("<td>" + member.getSe() + "</td>");
            	out.print("<td>" + member.getCmcwr() + "</td>");
            	out.print("<td>" + member.getYear() + "</td>");
            	out.print("<td>" + member.getDoor() + "</td>");
            	out.print("<td>" + member.getRemars3() + "</td>");
            	out.print("<td>" + Double.toString(member.getLat()) + "</td>");
            	out.print("<td>" + Double.toString(member.getLnt()) + "</td>");
            	out.print("<td>" + member.getDttm() + "</td>");
            	out.print("</tr>");
            }
            db.historyInsert(Double.parseDouble(latParam), Double.parseDouble(lntParam));
        }
        
	    %>
	    <script type="text/javascript">
	    	document.getElementById("lat").value = "<%=latParam%>";
	    	document.getElementById("lnt").value = "<%=lntParam%>";
	    	
	    </script>
	</table>
</body>
</html>