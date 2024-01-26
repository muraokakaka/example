<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.util.List" %>
<%@ page import="sql.Register" %>

<% List<String> student_list = (List<String>)request.getAttribute("list"); %>
<% List<String> avgk_list = (List<String>)request.getAttribute("avgk"); %>
<% List<String> avgs_list = (List<String>)request.getAttribute("avgs"); %>
<% List<String> sumk_list = (List<String>)request.getAttribute("sumk"); %>
<% List<String> sums_list = (List<String>)request.getAttribute("sums"); %>

<!DOCTYPE html>
<html>
<head>

<form action="/project/Seni" method="post">
<button type="submit" class="btn btn-primary">ログイン</button>
</form>

<meta charset="UTF-8">
<title>顧客一覧画面</title>
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

				<% for (int i = 0; i < student_list.size(); i++) {	            		
    				out.println("<td>" + student_list.get(i) + "</td>");
    				if (i % 4 == 3) {
        				out.println("</tr><tr>");
    					}
					}
				%>
			
		</tbody>
	</table>
	
	<div class="mx-auto text-center" style="width: 70%;">
	<h2 class="text-center mb-3">平均</h2>
	<table class="table table-striped">
		<thead>
			 <tr>
			 	<th scope="col">国語</th>
			 	<th scope="col">数学</th>
			 </tr>
		</thead>
		<tbody>
		
			<%
    			if (avgk_list != null && !avgk_list.isEmpty()) {
        			out.println("<td>" + avgk_list.get(0) + "</td>");
    			} else {
        			out.println("<td>No data available</td>");
    			}
			%>


			<%
    			if (avgs_list != null && !avgs_list.isEmpty()) {
        			out.println("<td>" + avgs_list.get(0) + "</td>");
    			} else {
        			out.println("<td>No data available</td>");
    			}
			%>


		</tbody>
	</table>
	</div>
	
	<div class="mx-auto text-center" style="width: 70%;">
	<h2 class="text-center mb-3">合計</h2>
	<table class="table table-striped">
		<thead>
			 <tr>
			 	<th scope="col">国語</th>
			 	<th scope="col">数学</th>
			 </tr>
		</thead>
		<tbody>
		
			<%
    			if (sumk_list != null && !sumk_list.isEmpty()) {
        			out.println("<td>" + sumk_list.get(0) + "</td>");
    			} else {
        			out.println("<td>No data available</td>");
    			}
			%>


			<%
    			if (sums_list != null && !sums_list.isEmpty()) {
        			out.println("<td>" + sums_list.get(0) + "</td>");
    			} else {
        			out.println("<td>No data available</td>");
    			}
			%>


		</tbody>
	</table>
	</div>
	
</body>
</html>