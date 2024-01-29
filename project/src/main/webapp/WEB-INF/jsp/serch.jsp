<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<title>登録</title>
</head>
<body>
	<div class="mx-auto" style="width: 300px;">
		<h1 class="mb-3" style="text-align: center">検索画面</h1>
		<form action="/project/SerchStudent" method="post">
		  <div class="mb-3">
		    <label for="id" class="form-label">ID</label>
		    <input type="integer" class="form-control" id="id" name="id" >
		  </div>
		  <button type="submit" class="btn btn-primary">検索する</button>
		</form>
		<!-- 前のページへ戻る -->
		<a href="#" onclick="window.history.back(); return false;" class="btn btn-primary mt-3">顧客一覧画面へ</a>
	</div>
</body>
</html>