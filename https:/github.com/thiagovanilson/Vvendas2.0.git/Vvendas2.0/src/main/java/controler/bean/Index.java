package controler.bean;


import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;


@RequestScoped
@Named("index")

public class Index {	
	
	@Inject
	private Services serv;
	
	public void refreshUser() {
		
		serv.login();
	}
	public String goToIndex() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		return (ec.getApplicationContextPath() + "/index.xhtml");
	}
	
	
}