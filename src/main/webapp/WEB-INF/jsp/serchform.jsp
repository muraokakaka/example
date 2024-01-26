<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>絞込みデータを表示する</title>
</head>
<body>
<div style="text-align:center">
<h2>検索ID入力画面</h2>
<hr style="height:3; background-color:#0000ff" />
<br>
IDに含まれる文字を入力してください。
<form action="<%=request.getContextPath() %>/SelectServlet2">
<input type="text" name="id">
<input type="submit" value="検索">
</form>
<br>
</div>
</body>
</html>