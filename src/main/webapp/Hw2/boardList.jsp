<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
<title>방명록</title>
</head>
<body>
	<div class="container w-75 mt-5 mx-auto">
		<h2>방명록 목록</h2>
		<hr>
	    <table class="table">
	        <thead>
	          <tr>
	            <th scope="col">번호</th>
	            <th scope="col">작성자</th>
	            <th scope="col">이메일</th>
	            <th scope="col">작성일</th>
	            <th scope="col">제목</th>
	          </tr>
	        </thead>
	        <tbody>
	          <c:forEach var="board" items="${boardlist}" varStatus="status">
	            <tr>
	                <th scope="row">${status.count}</th>
	                <td>${board.writer}</td>
					<td>${board.email}</td>
	                <td>${board.date}</td>
	                <td><a href="board.nhn?action=getBoard&aid=${board.aid}">${board.title}</a></td>
	            </tr>
	          </c:forEach>
	        </tbody>
	    </table>
		<hr>
		<c:if test="${error != null}">
		<div class="alert alert-danger alert-dismissible fade show mt-3">
			에러 발생: ${error}
			<button type="button" class="btn-close" data-bs-dismiss="alert"></button> 
		</div>
		</c:if>
		<a href="board.nhn?action=addBoard" class="btn btn-primary"> 입력 </a>
	</div>
</body>
</html>