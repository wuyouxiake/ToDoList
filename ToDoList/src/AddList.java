import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Todolist;
import model.Todouser;;

/**
 * Servlet implementation class AddComment
 */
@WebServlet("/AddList")
public class AddList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String priority = request.getParameter("priority");
		String tempDueDate = request.getParameter("duedate");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date duedate = null;
		try {
			duedate = formatter.parse(tempDueDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Todouser tempUser = (Todouser) session.getAttribute("user");
		Todolist thisList = new Todolist();
		thisList.setTodouser(tempUser);
		thisList.setTitle(title);
		thisList.setDescription(description);
		thisList.setPriority(priority);
		thisList.setDuedate(duedate);
		thisList.setStatus("Not Completed");
		thisList.setCompletedate(null);
		ToDoListDB.insert(thisList);
		
		String alert="To-do created!";
		request.setAttribute("alert", alert);
		
		getServletContext().getRequestDispatcher("/successful.jsp")
		.forward(request, response);
		}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
