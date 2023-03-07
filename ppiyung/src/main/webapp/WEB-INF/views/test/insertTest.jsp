<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
	<form action="/ppiyung/test/insert" method="post">
		<input type="text" name="memberId" placeholder="아이디"><br>
		<input type="text" name="memberName" placeholder="이름"><br>
		<input type="password" name="memberPw" placeholder="비밀번호">
		<button type="submit">회원가입</button>
	</form>
</body>
</html>