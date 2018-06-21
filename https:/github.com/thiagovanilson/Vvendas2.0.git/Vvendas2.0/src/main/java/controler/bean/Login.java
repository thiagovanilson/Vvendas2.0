package controler.bean;


import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import model.dao.ProductDAO;
import model.dao.UserDAO;


@RequestScoped
@Named("login")

public class Login extends AbstractBean {	
	
	private String err;
	
	public String getErr() {
		return err;
	}

	public void setErr(String err) {
		this.err = err;
	}
	public void checkErr() {
		reportarMensagemDeErro("Login ou senha incorreta!");
	}
}