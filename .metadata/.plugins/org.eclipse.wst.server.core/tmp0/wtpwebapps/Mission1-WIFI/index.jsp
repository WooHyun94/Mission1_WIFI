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
	<h1>와이파이 정보 구하기</h1>
	<br>
	<a href=''>홈</a>
	|
	<a href=''>위치 히스토리 목록</a>
	|
	<a href=''>Open API 와이파이 정보 가져오기</a>
	|
	<a href=''>즐겨 찾기 보기</a>
	|
	<a href=''>즐겨 찾기 그룹 관리</a>
	<br><br>
	LAT: <input type="text" id="lat">, 
	LNT: <input type="text" id="lnt"> 
	<button>내 위치 가져오기</button>
	<button>근처 WIFI 정보 보기</button>
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
	  
	</table>
</body>
</html>