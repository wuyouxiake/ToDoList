import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import customTools.DBUtil;
import model.Todolist;
import model.Todouser;

/**
 * Servlet implementation class AddComment
 */
@WebServlet("/GetListDetail")
public class GetListDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetListDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);

		String alert;

		Todouser user = (Todouser) session.getAttribute("user");
		String fullList = "";
		SimpleDateFormat ft = new SimpleDateFormat("MM/dd/yyyy");
		//
		long listid = Long.parseLong(request.getParameter("listid"));
		TypedQuery<Todolist> query2 = DBUtil
				.createQuery(
						"SELECT t FROM Todolist t where t.listid = ?1 and t.todouser = ?2",
						Todolist.class).setParameter(1, listid)
				.setParameter(2, user);
		Todolist thisTodo;

		thisTodo = query2.getSingleResult();
		String cd;
		if (thisTodo == null) {
			alert = "The to-do detail is not available!";
			// Set response content type
			response.setContentType("text/html");
			request.setAttribute("alert", alert);
			getServletContext().getRequestDispatcher("/error.jsp").include(
					request, response);
			alert = "";
		} else {
			if (thisTodo.getCompletedate() == null) {
				cd = "Task not completed";
			} else {

				cd = ft.format(thisTodo.getCompletedate());
			}
			String thisDuedate = ft.format(thisTodo.getDuedate());
			fullList = "<tr><td>" + thisTodo.getTitle()
					+ "</a></td>" 
					+ "<td>" + thisTodo.getDescription() + "</td>"
					+"<td>" + thisDuedate + "</td>" + "<td>"
					+ cd + "</td>" 
					+ "<td>" + thisTodo.getPriority() + "</td>"
					+ "<td>" + thisTodo.getStatus() + "</td>"
					+"</tr>";
		}

		// Set response content type
		response.setContentType("text/html");

		request.setAttribute("fullList", fullList);

		getServletContext().getRequestDispatcher("/TodoDetail.jsp").forward(
				request, response);
		fullList = "";

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}