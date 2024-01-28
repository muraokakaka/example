<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.util.List" %>
<%@ page import="object.Customer" %>

<% List<String> k_list = (List<String>)request.getAttribute("klist"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
</head>
<body>
<div class="mx-auto text-center" style="width: 70%;">
	<h2 class="text-center mb-3">生徒毎の科目と点数一覧</h2>
	<table class="table table-striped">
		<thead>
			 <tr>
			 	<th scope="col">ID</th>
			 	<th scope="col">生徒名</th>
			 	<th scope="col">科目</th>
			 	<th scope="col">点数</th>
			 </tr>
		</thead>
		<tbody>

				<% for (int i = 0; i < k_list.size(); i++) {	            		
    				out.println("<td>" + k_list.get(i) + "</td>");
    				if (i % 4 == 3) {
        				out.println("</tr><tr>");
    					}
					}
				%>
			
		</tbody>
	</table>
	<a href="#" onclick="window.history.back(); return false;" class="btn btn-primary mt-3">戻る
</body>
</html>