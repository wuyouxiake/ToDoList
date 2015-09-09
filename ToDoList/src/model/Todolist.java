package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the TODOLIST database table.
 * 
 */
@Entity
@NamedQuery(name="Todolist.findAll", query="SELECT t FROM Todolist t")
public class Todolist implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long listid;

	@Temporal(TemporalType.DATE)
	private Date completedate;

	private String description;

	@Temporal(TemporalType.DATE)
	private Date duedate;

	private String priority;

	private String status;

	private String title;

	//bi-directional many-to-one association to Todouser
	@ManyToOne
	@JoinColumn(name="USERID")
	private Todouser todouser;

	public Todolist() {
	}

	public long getListid() {
		return this.listid;
	}

	public void setListid(long listid) {
		this.listid = listid;
	}

	public Date getCompletedate() {
		return this.completedate;
	}

	public void setCompletedate(Date completedate) {
		this.completedate = completedate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDuedate() {
		return this.duedate;
	}

	public void setDuedate(Date duedate) {
		this.duedate = duedate;
	}

	public String getPriority() {
		return this.priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Todouser getTodouser() {
		return this.todouser;
	}

	public void setTodouser(Todouser todouser) {
		this.todouser = todouser;
	}

}