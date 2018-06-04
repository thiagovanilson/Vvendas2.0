package controler.bean;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import model.UserDAO;
import model.UserModel;

@ApplicationScoped
@Named

public class Services extends AbstractBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3423399669564859585L;
	@Inject
	private Services serv;
	private UserModel user;// = new UserDAO(null).getUser("1");
	private String cpf;
	private String pass;
	
	public Services() {
		
	}
	public  UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}	
	
	public Services getInstance() {
		
		return serv;
	}
	public boolean isLogged() {
		return user != null;
	}
	public void login() {
		try {
			if(cpf!=null && pass != null) {
				UserModel temp = new UserDAO(null).getUser(cpf);
				if(temp != null && temp.getPass().equals(pass)) {
					user = temp;
					reportarMensagemDeSucesso("Usuario " + user.getName() +" logado." );
					return;
				}			
			}
		}catch(Exception e) {
			reportarMensagemDeErro(e.getMessage());
		}
		reportarMensagemDeErro("Senha ou CPF incorretos!");
	}
	public void login(UserModel um) {
		user = um;
	}
	public String logout() {
		user = null;
		return "index.xhtml";
	}
	private boolean editUser = true;
	
	public boolean getEditUser() {
		return editUser;
	}
	public void changeEditUser() {
		editUser = !editUser;
	}
	public boolean userLoggedIsAdmin() {
		if(user != null)
			return (user.getUsergroup().equalsIgnoreCase("admin"));
		return false;
	}
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
}
