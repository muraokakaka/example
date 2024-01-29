<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.List" %>
<%@ page import="javax.naming.*, javax.sql.*" %>
<% List<Integer> student_list = (List<Integer>)request.getAttribute("list"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<SCRIPT src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></SCRIPT>
<title>Insert title here</title>
</head>
<body>
<SCRIPT type="text/javascript">
var javascriptArray = [<%
    for (int i = 0; i < student_list.size(); i++) {
        out.print("'" + student_list.get(i) + "'");
        if (i < student_list.size() - 1) {
            out.print(",");
          }
      }
    %>];
var result = javascriptArray.map(Number);
var total = result.reduce(function(sum, element){
  return sum + element;
}, 0);

console.log(total);
</SCRIPT>
<table class="table table-striped">
		<thead>
			 <tr>
			 	<th scope="col">国語</th>
			 </tr>
		</thead>
		
		<tbody>
		
		<p id="insert title here">insert title here</p>
		
			<SCRIPT type="text/javascript">
				var javascriptArray = [<%
    			for (int i = 0; i < student_list.size(); i++) {
        		out.print("'" + student_list.get(i) + "'");
        			if (i < student_list.size() - 1) {
            		out.print(",");
          		}
      			}
    			%>];
			var result = javascriptArray.map(Number);
			var total = result.reduce(function(sum, element){
  			return sum + element;
			}, 0);

			console.log(total);

			window.onload = function(){
		        // documentはブラウザに読み込まれたウェブページを表す
		        // getElementByIdでHTML上で使うidを参照する
		        // innerHTMLではHTML要素の内容を書き変える
		        document.getElementById("insert title here").innerHTML
		          = total;
		      }
			
			</SCRIPT>


		</tbody>
	</table>
</body>
</html>