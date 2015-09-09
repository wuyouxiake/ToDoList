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
@WebServlet("/GetMyList")
public class GetMyList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetMyList() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		long count;
		String alert;

		Todouser user =  (Todouser) session.getAttribute("user");
		String fullList = "";
		 SimpleDateFormat ft = new SimpleDateFormat ("MM/dd/yyyy");
		//
		TypedQuery<Todolist> query2 = DBUtil.createQuery("SELECT t FROM Todolist t where t.todouser = ?1", Todolist.class).setParameter(1, user);
		List<Todolist> myList;
		try {
			myList = query2.getResultList();
			String cd;
			if (myList == null || myList.isEmpty()) {
				myList = null;
				alert = "You don't have any to-do list!";
				// Set response content type
				response.setContentType("text/html");

				request.setAttribute("alert", alert);

				getServletContext().getRequestDispatcher("/error.jsp").include(
						request, response);
				alert = "";
			} else {
				for (int i = 0; i < myList.size(); i++) {
					if(myList.get(i).getCompletedate()==null){
						cd="Task not completed";
					}else{
						
						cd = ft.format(myList.get(i).getCompletedate());
					}
					String thisDuedate = ft.format(myList.get(i).getDuedate());
					fullList += "<tr><td><a href=\"GetListDetail?listid="
							+ myList.get(i).getListid()
							+ "\">"
							+ myList.get(i).getTitle()
							+ "</a></td>"
							+ "<td><a href=\"Jump?listid="
							+ myList.get(i).getListid()
							+ "\">"+thisDuedate+"</a></td>"
							+ "<td>"+cd+"</td>"
							+ "<td>"+myList.get(i).getPriority()+"</td>"
							+ "<td>"+myList.get(i).getStatus()+"</td>"
							+"<td><a href=\"DeleteList?listid="
							+ myList.get(i).getListid()
							+ "\">Delete</a>"+"		";
							
					if(myList.get(i).getCompletedate()==null){
						fullList+="<a href=\"MarkComplete?listid="
								+ myList.get(i).getListid()
								+ "\">Completed</a>"
								+ "</td></tr>";
					}else{
						fullList+="<a href=\"MarkIncomplete?listid="
								+ myList.get(i).getListid()
								+ "\">Incompleted</a>"
								+ "</td></tr>";
					}
							
				}
				

				// Set response content type
				response.setContentType("text/html");

				request.setAttribute("fullList", fullList);

				getServletContext().getRequestDispatcher("/myList.jsp")
						.forward(request, response);
				fullList = "";
			}
		} catch (Exception e) {
			System.out.println(e);
		}
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