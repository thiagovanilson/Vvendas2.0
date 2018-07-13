package controler.bean;


import javax.enterprise.context.RequestScoped;
import javax.inject.Named;


@RequestScoped
@Named("login")

public class Login extends AbstractBean {	
	
	private String err = "";
	
	
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