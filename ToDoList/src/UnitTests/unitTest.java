package UnitTests;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;

import model.Todolist;
import model.Todouser;

import org.junit.* ;

import customTools.DBUtil;
import static org.junit.Assert.* ;

public class unitTest {
   @Test
   public void testLogin() {
     System.out.println("Test if user can login with valid/invalid username and password.....") ;
     Todouser user = new Todouser();
     String username = "yang";
     String qString = "select t from Todouser t where t.username = ?1";
		TypedQuery<Todouser> q = DBUtil.createQuery(qString, Todouser.class);
		q.setParameter(1, username);
		user = q.getSingleResult();
		String password = user.getPassword();
		assertTrue(user.getPassword().equals("123"));
   }
   
   @Test
   public void testGetList(){
	   System.out.println("Test if user can see their to-do list......") ;
	   Todouser user = new Todouser();
	   String username = "yang";
	  
	   String qString = "select t from Todouser t where t.username = ?1";
		TypedQuery<Todouser> q = DBUtil.createQuery(qString, Todouser.class);
		q.setParameter(1, username);
		user = q.getSingleResult();
		
		TypedQuery<Todolist> query2 = DBUtil.createQuery("SELECT t FROM Todolist t where t.todouser = ?1", Todolist.class).setParameter(1, user);
		List<Todolist> myList = null;
		myList = query2.getResultList();
		assertTrue(myList != null);
		
   }
   
}