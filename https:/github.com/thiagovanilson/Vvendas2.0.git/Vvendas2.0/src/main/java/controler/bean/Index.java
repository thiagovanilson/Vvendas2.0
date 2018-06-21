package controler.bean;


import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import model.dao.ProductDAO;
import model.dao.UserDAO;


@RequestScoped
@Named("index")

public class Index {	
	
	@Inject
	private Services serv;
	@Inject
	private ProductDAO pd;
	
	@Inject 
	private UserDAO ud;
	
	public void refreshUser() {
		
		serv.login();
	}
	public String goToIndex() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		return (ec.getApplicationContextPath() + "/user/index.xhtml");
	}
	public String goToInfo() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		return (ec.getApplicationContextPath() + "/info.xhtml");
	}
	public int qtdProducts() {
		return pd.qtdProducts();
	}
	public int qtdUsers() {
		return ud.qtdUsers();
	}
}