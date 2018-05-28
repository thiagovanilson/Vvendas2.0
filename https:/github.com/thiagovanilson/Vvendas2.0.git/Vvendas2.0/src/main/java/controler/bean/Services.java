package controler.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.UserDAO;
import model.UserModel;

@SessionScoped
@ManagedBean

public class Services implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3423399669564859585L;
	private static Services serv;
	private UserModel user = new UserDAO(null).getUser("1");
	
	
	public Services() {
		
	}
	public  UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}	
	
	public static Services getInstance() {
		if(serv == null)
			serv = new Services();
		return serv;
	}
	
	private boolean editUser = true;
	public boolean getEditUser() {
		return editUser;
	}
	public void changeEditUser() {
		editUser = !editUser;
	}
	public boolean userLoggedIsAdmin() {
		return (user.getUsergroup().equalsIgnoreCase("admin"));
	}
}
