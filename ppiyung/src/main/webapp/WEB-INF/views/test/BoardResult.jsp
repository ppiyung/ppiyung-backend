<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>전체 게시글 리스트</h2>

	<c:forEach var="board" items="${board}">

		<tr>
			<!-- 한 행  -->
			<td>${board.boardNum}</td>
			<td>${board.boardTitle}</td>
			<td>${board.boardContent}</td>
			<td>${board.boardAuthor}</td>
			<td>${board.commentCount}</td>
			<td>${board.boardDate}</td>
			<td>${board.boardLike}</td>
		<tr>
	</c:forEach>

</body>
</html>