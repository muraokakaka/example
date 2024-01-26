package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import object.Customer;
import sql.Register;

/**
 * Servlet implementation class CustomerRegisterServlet
 */
@WebServlet("/CustomerRegisterServlet")
public class CustomerRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	// 顧客登録画面を表示させる
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("WEB-INF/jsp/customer_register.jsp");
		dispatcher.forward(request, response);
	}

	// 顧客登録画面より入力された値をもとにデータベースへ顧客情報を登録する
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字コードの設定
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		// 顧客登録画面で入力された値を取得
		int id = request.getParameter("id");
		String name = request.getParameter("name");
		String subject = request.getParameter("subject");
		String point = request.getParameter("point");


		Register register = new Register();

		// 顧客情報登録処理を実行
		register.customer_register(id, name, subject, point);

		List<Customer> customer = null;

		// データベースから取得した顧客情報を格納
		customer = login.getCustomerInfo(String.valueOf(admin.getId()));

		// 格納した顧客情報を遷移先の画面に渡す
		request.setAttribute("customer", customer);

		RequestDispatcher dispatcher =
				request.getRequestDispatcher("WEB-INF/jsp/student.jsp");
		dispatcher.forward(request, response);
	}
}