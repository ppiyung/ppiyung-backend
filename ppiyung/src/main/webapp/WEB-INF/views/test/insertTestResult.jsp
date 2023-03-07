<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Login</title>
</head>
<body>
<h1>
	<c:choose>
		<c:when test="${ result }">
			<h1>축하합니다</h1>
			회원가입에 성공하였습니다. <br>
			<a href="/ppiyung/test">메인으로</a>
		</c:when>
		<c:otherwise>
			<h1>무언가 잘못되었습니다.</h1>
			회원가입에 실패하였습니다. 다시 시도해보세요.<br>
			<a href="/ppiyung/test/insert">회원가입 페이지로</a>
		</c:otherwise>
	</c:choose>
</h1>

</body>
</html>