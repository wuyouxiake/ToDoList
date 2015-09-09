import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Todouser;
import customTools.DBUtil;

/**
 * Servlet implementation class ValidateUser
 */
@WebServlet("/ValidateUser")
public class ValidateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ValidateUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String tempName = request.getParameter("username");
		String tempPass = request.getParameter("password");

		//EntityManager em = DBUtil.getEmFactory().createEntityManager();
		
		String alert = null;

		try {
			TypedQuery<Long> query = DBUtil.createQuery("SELECT COUNT(t) FROM Todouser t WHERE t.username = ?1",Long.class);
			query.setParameter(1, tempName);
			long totalUser = query.getSingleResult();
			if (totalUser <= 0) {
				alert = "User does not exist!";
				// Set response content type
				response.setContentType("text/html");

				request.setAttribute("alert", alert);

				getServletContext().getRequestDispatcher("/error.jsp").include(
						request, response);
			} else {
				String qString = "select t from Todouser t where t.username = ?1";
				TypedQuery<Todouser> q = DBUtil.createQuery(qString, Todouser.class);
				q.setParameter(1, tempName);
				Todouser user = new Todouser();
				user = q.getSingleResult();
				//long userid=user.getUserId();
				String password = user.getPassword();
				String username = user.getUsername();
				if (!password.equals(tempPass)) {
					alert = "Password not valid!";
					response.setContentType("text/html");
					request.setAttribute("alert", alert);
					getServletContext().getRequestDispatcher("/error.jsp")
							.forward(request, response);
				} else {
					alert = "Logged in";
					response.setContentType("text/html");
					request.getSession().setAttribute("user", user);
					request.getSession().setAttribute("username", username);
					request.setAttribute("alert", alert);
					getServletContext().getRequestDispatcher("/successful.jsp")
							.forward(request, response);
				}
			}
		} catch (Exception e) {
			System.out.println("Error!");
		} finally {
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
