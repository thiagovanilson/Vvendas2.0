package controler.bean;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.Base64;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.UserDAO;
import model.UserModel;

@RequestScoped
@Named

public class Services extends AbstractBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3423399669564859585L;
	@Inject
	private Services serv;
	@Inject
	private UserDAO ud;
	private UserModel user;// = new UserDAO(null).getUser("1");
	private String cpf;
	private String pass;
	
	public Services() {
		
	}
	public  UserModel getUser() {
		return user;
	}
//	public String getUserName() {
//		FacesContext facesContext = FacesContext.getCurrentInstance();
//		ExternalContext externalContext = facesContext.getExternalContext();
//		Principal userPrincipal = externalContext.getUserPrincipal();
//		if (userPrincipal == null) {
//			return "";
//		}
//		
//		return userPrincipal.getName();
//	}
	public void setUser(UserModel user) {
		this.user = user;
	}	
	
	public Services getInstance() {
		
		return serv;
	}
	
	public void login() {
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		Principal userPrincipal = externalContext.getUserPrincipal();
		if (userPrincipal == null) {
			return ;
		}
		user = ud.getUser(userPrincipal.getName());
		if(user == null)
			return;
		
	}
	public void login(UserModel um) {
		user = um;
		cpf  = um.getCpf();
	}
	public void logout() throws IOException, ServletException {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		session.invalidate();
		// XXX Chamada #logout() abaixo necessária, pois: https://stackoverflow.com/a/26421775/4023351
		// Além disso, se removermos um usuário que estava logado no sistema, após este usuário deslogar,
		// o JAAS não atualiza as informações e impede que ele entre no sistema caso este cenário seja 
		// tentado logo após a remoção na base de dados. Com a chamada abaixo, este problema é resolvido.
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		request.logout();
		user = null;
		ec.redirect(ec.getApplicationContextPath() + "/index.xhtml");
	}

	private boolean editUser = true;
	
	public boolean getEditUser() {
		return editUser;
	}
	public void changeEditUser() {
		editUser = !editUser;
	}
	public boolean userLoggedIsAdmin() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		return externalContext.isUserInRole("admin");
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
//		MessageDigest md;
//		try {
//			md = MessageDigest.getInstance("SHA-256");
//			md.update(pass.getBytes("UTF-8"));
//			byte[] digest = md.digest();
//			String output = Base64.getEncoder().encodeToString(digest);
//			this.pass = output;
//		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
//			
//		}
	}
}
