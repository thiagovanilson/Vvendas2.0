package controler.bean;

import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;


import model.UserDAO;
import model.UserModel;

@SuppressWarnings("serial")
@ViewScoped
@Named
public class CadastrarUsuario extends AbstractBean{	
	
	private String name, cpf, type, pass, email,securityCod = "Vanilson";
	private boolean isEdition = false;
	@Inject
	private Services serv;
	
	public String filtrar() {
		
		return null;
	}
	
	public CadastrarUsuario() {
		
	}

	public void save() {

		try {
			UserModel u = new UserModel();
			UserDAO ud  = new UserDAO(u);
			
			u.setCpf(cpf);
			u.setEmail(email);
			u.setName(name);
			u.setPass(pass);
			u.setUsergroup(type);
			
			if(isEdition) {
				if (ud.edit(u)) {
					clean();
					reportarMensagemDeSucesso("Edições salvas!");
				}
				else
					reportarMensagemDeErro("Erro ao alterar dados. Revise os campos!");	
			}else {
				
				if(ud.save(u)) {
					clean();
					reportarMensagemDeSucesso("Usuario salvo!");
				}
				else
					reportarMensagemDeErro("Falha ao gravar!");
			}
		} catch (Exception e) {
			reportarMensagemDeErro(e.getMessage());

		}
	}
	
	public String saveFirst() {

		try {
			UserModel u = new UserModel();
			UserDAO ud  = new UserDAO(u);
			
			u.setCpf(cpf);
			u.setEmail(email);
			u.setName(name);
			u.setPass(pass);
			u.setUsergroup("admin");			
				
			if(ud.save(u)) {
				clean();
				reportarMensagemDeSucesso("Usuario salvo!");
				serv.login(u);
				return "index.xhtml";
			}
			else {
				reportarMensagemDeErro("Falha ao gravar!");
				return null;
			}
			
		} catch (Exception e) {
			reportarMensagemDeErro(e.getMessage());
		}
		return null;
	}
	
	public void search() {
		UserDAO ud  = new UserDAO(null);
		UserModel u = ud.getUser(cpf);
		
		if(u != null) {
			name       = u.getName();
			cpf  = u.getCpf();
			email= u.getEmail();
			pass = u.getPass();
			type = u.getUsergroup();
			
			isEdition = true;
		}
		else {
			clean();
			
			isEdition = false;
		}
	}
	
	public void delete() {
		UserDAO ud = new UserDAO(null);	
		
		if (ud.delete(cpf)) {
			clean();
			reportarMensagemDeSucesso("Excluido com sucesso!");
		}
		else
			reportarMensagemDeErro("Falha ao excluir.");	
		
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
		if(cpf != null && !cpf.equals(""))
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

	public boolean isEdition() {
		return isEdition;
	}

	public void setEdition(boolean isEdition) {
		this.isEdition = isEdition;
	}

	public String getSecurityCod() {
		return securityCod;
	}

	public void setSecurityCod(String securityCod) {
		this.securityCod = securityCod;
	}	
}