<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.util.List" %>

<!-- 受け取るリストの定義 -->
<% List<String> student_list = (List<String>)request.getAttribute("list"); %>
<% List<String> avgk_list = (List<String>)request.getAttribute("avgk"); %>
<% List<String> avgs_list = (List<String>)request.getAttribute("avgs"); %>
<% List<Integer> sumk_list = (List<Integer>)request.getAttribute("sumk"); %>
<% List<String> countk_list = (List<String>)request.getAttribute("countk"); %>
<% List<Integer> sums_list = (List<Integer>)request.getAttribute("sums"); %>
<% List<String> counts_list = (List<String>)request.getAttribute("counts"); %>
<% List<String> allcount_list = (List<String>)request.getAttribute("allcount"); %>

<!DOCTYPE html>
<html>
<head>

<header>

<table class="table table-striped">

<thead>

<tr>

<th>
<form action="/project/Register" method="post">
<button type="submit" class="btn btn-primary" style="margin:5px">登録</button>
</form>
</th>

<th>
<form action="/project/Update" method="post">
<button type="submit" class="btn btn-primary" style="margin:5px">編集</button>
</form>
</th>

<th>
<form action="/project/Delete" method="post">
<button type="submit" class="btn btn-primary" style="margin:5px">削除</button>
</form>
</th>

<th>
<form action="/project/Serch" method="post">
<button type="submit" class="btn btn-primary" style="margin:5px">検索</button>
</form>
</th>

<th>
<div style="text-align: right;" class="example"><a href="#">ページトップへ戻る</a></div>
<th>

<th>
<div style="text-align: right;" class="example"><a href="#abc">平均</a></div>
</th>

<th>
<div style="text-align: right;" class="example"><a href="#def">合計</a></div>
</th>

 </tr>

</thead>

</table>

<meta charset="UTF-8">
<title>顧客一覧画面</title>
<SCRIPT src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></SCRIPT>

<!-- CSS始め -->
<style type="text/css">
button {
  display       : inline-block;
  border-radius : 5%;          /* 角丸       */
  font-size     : 14pt;        /* 文字サイズ */
  text-align    : center;      /* 文字位置   */
  cursor        : pointer;     /* カーソル   */
  padding       : 12px 12px;   /* 余白       */
  background    : #ffffff;     /* 背景色     */
  color         : #ffffff;     /* 文字色     */
  line-height   : 1em;         /* 1行の高さ  */
  transition    : .3s;         /* なめらか変化 */
  box-shadow    : 6px 6px 3px #666666;  /* 影の設定 */
  border        : 1px solid #000066;    /* 枠の指定 */
}
button:hover {
  box-shadow    : none;        /* カーソル時の影消去 */
  color         : #ffffff;     /* 背景色     */
  background    : #ffffff;     /* 文字色     */
}
header {
 margin: 90;
  position: sticky;
  z-index: 999;
  top: 0;
  left: 0;
  width: 100%;
  background: rgba(0, 0, 0, .1);
  z-index: 100;
  display: flex;
  justify-content: space-between;
}
div{
border-top-left-radius: 10px;
border-top-right-radius: 20px;
border-bottom-right-radius: 30px;
border-bottom-left-radius: 30px;
}
.example{
  display       : inline-block;
  border-radius : 5%;          /* 角丸       */
  font-size     : 12pt;        /* 文字サイズ */
  text-align    : right;      /* 文字位置   */
  cursor        : pointer;     /* カーソル   */
  padding       : 12px 12px;   /* 余白       */
  background    : #ffffff;     /* 背景色     */
  color         : #ffffff;     /* 文字色     */
  line-height   : 1em;         /* 1行の高さ  */
  transition    : .3s;         /* なめらか変化 */
  box-shadow    : 6px 6px 3px #666666;  /* 影の設定 */
  border        : 1px solid #000066;  
}
.example:hover{
  box-shadow    : none;        /* カーソル時の影消去 */
  color         : #000000;     /* 背景色     */
  background    : #ffffff;     /* 文字色     */
}
#targeArea{
    display: none;
}
#search-result__list span{
    display: block;
}
</style>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<!-- CSS終わり -->

</header>
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
	<h2 class="text-center mb-3" id="abc">平均</h2>
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
	<h2 class="text-center mb-3" id="def">合計</h2>
	<table class="table table-striped">
		<thead>
			 <tr>
			 	<th scope="col">国語</th>
			 	<th scope="col">人数</th>
			 	<th scope="col">数学</th>
			 	<th scope="col">人数</th>
			 </tr>
		</thead>
		
		<tbody>
		
			<SCRIPT type="text/javascript">
				var kanziArray = [<%
    				for (int i = 0; i < sumk_list.size(); i++) {
        				out.print("'" + sumk_list.get(i) + "'");
        				if (i < sumk_list.size() - 1) {
            				out.print(",");
          				}
      				}
    			%>];
			var kanziresult = kanziArray.map(Number);
			var kanzitotal = kanziresult.reduce(function(sum, element){
  			return sum + element;
			}, 0);

			console.log(kanzitotal);
			
			window.onload = function(){
		        // documentはブラウザに読み込まれたウェブページを表す
		        // getElementByIdでHTML上で使うidを参照する
		        // innerHTMLではHTML要素の内容を書き変える
		        document.getElementById("kokugo").innerHTML
		          = kanzitotal;
		      }
			</SCRIPT>
		
			<td id="kokugo">kokugo</td>
		

			<%
    			if (countk_list != null && !countk_list.isEmpty()) {
        			out.println("<td>" + countk_list.get(0) + "</td>");
    			} else {
        			out.println("<td>No data available</td>");
    			}
			%>


			<SCRIPT type="text/javascript">
				var sugakuArray = [<%
    				for (int i = 0; i < sums_list.size(); i++) {
        				out.print("'" + sums_list.get(i) + "'");
        				if (i < sums_list.size() - 1) {
            				out.print(",");
          				}
      				}
    			%>];
			var sugakuresult = sugakuArray.map(Number);
			var sugakutotal = sugakuresult.reduce(function(sum, element){
  			return sum + element;
			}, 0);

			console.log(sugakutotal);

			window.onload = function(){
		        // documentはブラウザに読み込まれたウェブページを表す
		        // getElementByIdでHTML上で使うidを参照する
		        // innerHTMLではHTML要素の内容を書き変える
		        document.getElementById("sugaku").innerHTML
		          = sugakutotal;
		      }
			</SCRIPT>
		
			<td id="sugaku">sugaku</td>


			<%
    			if (counts_list != null && !counts_list.isEmpty()) {
        			out.println("<td>" + counts_list.get(0) + "</td>");
    			} else {
        			out.println("<td>No data available</td>");
    			}
			%>


		</tbody>
	</table>
</div>

</body>
</html>