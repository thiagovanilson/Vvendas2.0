package controler.bean;

import java.io.IOException;
import java.io.Serializable;
import java.security.Principal;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.UserModel;
import model.dao.UserDAO;

@ApplicationScoped
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
	@Inject
	private Index index;
	private UserModel user;
	
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
	
	public boolean hasLogged() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		return externalContext.isUserInRole("admin") || externalContext.isUserInRole("user");
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
		ec.redirect(index.goToIndex());
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
	
		
	public String goToProfile() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		return (ec.getApplicationContextPath() + "/user/profile.xhtml");
	}
}
