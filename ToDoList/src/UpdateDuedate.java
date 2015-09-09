import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
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
@WebServlet("/UpdateDuedate")
public class UpdateDuedate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateDuedate() {
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
		Todouser user = (Todouser) session.getAttribute("user");
		long listid = Long.parseLong(request.getParameter("listid"));
		String tempDueDate = request.getParameter("newduedate");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date duedate = null;
		try {
			duedate = formatter.parse(tempDueDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TypedQuery<Todolist> query2 = DBUtil.createQuery("SELECT t FROM Todolist t where t.listid = ?1 and t.todouser = ?2",Todolist.class).setParameter(1, listid).setParameter(2, user);
		Todolist thisTodo = query2.getSingleResult();
		
		
		
		thisTodo.setDuedate(duedate);
		ToDoListDB.update(thisTodo);

		// Set response content type
		response.setContentType("text/html");


		getServletContext().getRequestDispatcher("/GetMyList").forward(
				request, response);
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