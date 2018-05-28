package controler.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import model.Facade;
import model.Persist;
import model.ProductDAO;
import model.UserDAO;
import model.UserModel;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean
public class CadastrarUsuario extends AbstractBean{	
	
	private String name, cpf, type, pass, email;
	private boolean isEdition = false;
	
	public String filtrar() {
		
		return null;
	}
	
	public CadastrarUsuario() {
		
	}

	public void save() {

		try {
			UserDAO ud  = new UserDAO(null);
			UserModel u = new UserModel();
			
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
				
				if(new Persist().save(new Facade().createUser(cpf, name, pass, type, email))) {
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
}