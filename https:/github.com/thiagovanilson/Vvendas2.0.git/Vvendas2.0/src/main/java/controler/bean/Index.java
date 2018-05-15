package controler.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.UserDAO;
import model.UserModel;

@SessionScoped
@ManagedBean(name="index")

public class Index {	
	
	private static UserModel user = new UserDAO(null).getUser("1");
	
	public String filtrar() {
		
		return null;
	}
	
	
	public  UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		Index.user = user;
	}	
	
}