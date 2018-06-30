package controler.bean;

import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import model.UserModel;
import services.RegisterUserServices;

@SuppressWarnings("serial")
@ViewScoped
@Named
public class CadastrarUsuario extends AbstractBean{	
	
	private String name, cpf, type, pass, email, securityCod = ""; //"Vanilson";
	private RegisterUserServices rus = new RegisterUserServices();
	private UserModel um;
	
	public void save() {
		UserModel u = new UserModel();
		
		u.setCpf(cpf);
		u.setEmail(email);
		u.setName(name);
		u.setUsergroup(type);	
		u.setPass(pass);

		rus.save(u);
	}
	
	public String update() {
		save();
		return "/user/index.xhtml?faces-redirect=true";
	}
	public String saveFirst() {
		
		UserModel u = new UserModel();
		
		u.setCpf(cpf);
		u.setEmail(email);
		u.setName(name);
		u.setPass(pass);
		u.setUsergroup("admin");			
		
		if(rus.search(cpf) != null) {
			reportarMensagemDeErro("Usuario Já cadastrado!");
			return null;
		}
		if(rus.save(u)) {
			clean();
//			serv.login(u);
		}
		return "/info.xhtml";
//		else {
//			return null;
//		}		
	}
	public void search(String cpf) {
		this.cpf = cpf;
		search();
	}
	public void search() {
		
		um = rus.search(cpf);
		
		if(um != null && cpf != null && cpf.length() > 0) {
			name = um.getName();
			cpf  = um.getCpf();
			email= um.getEmail();
			pass = um.getPass();
			type = um.getUsergroup();
		}
		else {
			clean();
		}
	}
	
	public void delete() {
		if(um != null)
			if(rus.delete(um))
				clean();
	}
	
	public void clean() {
		name = "";
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
		if(pass != null && pass.length() > 0)
			this.pass = pass;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEdition() {
		return rus.isEdition();
	}
	
	public String getSecurityCod() {
		return securityCod;
	}

	public void setSecurityCod(String securityCod) {
		this.securityCod = securityCod;
	}
	
	public boolean cpfIsValid() {
		return cpf != null && !cpf.equals("___.___.___-__");
	}
}