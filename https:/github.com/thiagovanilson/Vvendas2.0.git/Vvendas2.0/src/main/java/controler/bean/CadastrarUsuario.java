package controler.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import model.Facade;
import model.Persist;

@RequestScoped
@ManagedBean
public class CadastrarUsuario extends AbstractBean{	
	
	private String name, cpf, type, pass, email;
	
	public String filtrar() {
		
		return null;
	}
	
	public CadastrarUsuario() {
		
	}

	public void save() {

		try {
			if(new Persist().save(new Facade().createUser(cpf, name, pass, type, email))) {
				clean();
				reportarMensagemDeSucesso("Usuario salvo!");
			}
			else
				reportarMensagemDeErro("Falha ao gravar!");
		} catch (Exception e) {
			reportarMensagemDeErro(e.getMessage());

		}
	}

	public void clean() {
		name = "";
		cpf  = ""; 
		type = "";
		pass = "";
		email= "";
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}	
}