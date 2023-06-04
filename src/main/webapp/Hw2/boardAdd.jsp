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
        <h2>방명록 등록</h2>
        <hr>
        <form method="post" action="/jwbook/board.nhn?action=insertBoard" id="modForm">
           <div class="form-group row">
               <label for="writer" class="col-sm-2 col-form-label">작성자</label>
               <div class="col-sm-10">
                   <input type="text" class="form-control" id="writer" name="writer" required>
               </div>
           </div>
           <div class="form-group row">
               <label for="email" class="col-sm-2 col-form-label">이메일</label>
               <div class="col-sm-10">
                   <input type="email" class="form-control" id="email" name="email" required>
               </div>
           </div>
           <div class="form-group row">
               <label for="title" class="col-sm-2 col-form-label">제목</label>
               <div class="col-sm-10">
                   <input type="text" class="form-control" id="title" name="title" required>
               </div>
           </div>
           <div class="form-group row">
               <label for="password" class="col-sm-2 col-form-label">비밀번호</label>
               <div class="col-sm-10">
                   <input type="password" class="form-control" id="password" name="password" required>
               </div>
           </div>
           <div class="form-group row">
               <textarea cols="50" rows="5" name="content" id="content" class="form-control" required></textarea>
           </div>
           <div>
               <button type="submit" class="btn btn-success mt-3">저장</button>
               <a href="javascript:reset(event);" class="btn btn-primary" id="btnCancel">취소</a>
               <a href="board.nhn?action=listBoard" class="btn btn-primary"> 목록 </a>
           </div>
        </form>
   </div>
    <script type="text/javascript">
       function reset() {
          const writer = document.getElementById('writer');
          const email = document.getElementById('email');
          const title = document.getElementById('title');
          const password = document.getElementById('password');
          const content = document.getElementById('content');
          
          writer.value="";
          email.value="";
          title.value="";
          password.value="";
          content.value="";
       }
   </script>
</body>
</html>