import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Todouser;;

/**
 * Servlet implementation class AddComment
 */
@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String user_name = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String photolink = request.getParameter("photolink");
		Todouser tempUser=new Todouser();
		
		Pattern r = Pattern.compile("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z]{2,4}\\b");

	    // Now create matcher object.
	    Matcher m = r.matcher(email);
	    System.out.println(m.matches());
	    if(!m.matches()){
	    	String alert = "Email not valid!";
	    	request.setAttribute("alert", alert);
			
			getServletContext().getRequestDispatcher("/successful.jsp")
			.forward(request, response);
	    	
	    }else{
	    	tempUser.setUsername(user_name);
			tempUser.setPassword(password);
			tempUser.setEmail(email);
			tempUser.setPhotolink(photolink);
			
			ToDoUserDB.insert(tempUser);
			
			String alert="User created!";
			request.setAttribute("alert", alert);
			
			getServletContext().getRequestDispatcher("/successful.jsp")
			.forward(request, response);
			}
		
	    }
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
