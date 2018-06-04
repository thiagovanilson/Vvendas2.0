package controler.bean;

import java.util.ArrayList;


import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import model.UserDAO;
import model.UserModel;

@SuppressWarnings("serial")
@ViewScoped
@Named
public class BuscarUsuarios extends AbstractBean{	
	
	private String name, cpf, aviso;
	ArrayList<UserModel> usuarios = new ArrayList<UserModel>();
	
	public String filtrar() {
		
		return null;
	}

	private boolean empty = true;
	
	public void buscar() {
		aviso = "";
		usuarios = new ArrayList<UserModel>();

		if(cpf!=null && !cpf.equals("")) {
			UserModel p = new UserDAO(null).getUser(cpf);
			
			if(p != null) {
				usuarios.add(p);
				empty = false;
			}
			else {
				empty = true;
				aviso = "Não foram encontrados resultados. :(";
			}
		}else {
			//Implementation to find many results
			for(UserModel u: new UserDAO(null).getUsers(name)){
				usuarios.add(u);
				empty = false;

			}
		}
		clean();
	}
	public void clean() {
		name        = "";
		cpf         = "";
		
	}
	public boolean hasItens() {
		return !empty;
	}
	public String getAviso() {
		return aviso;
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

	public void setCpf(String cod) {
		this.cpf = cod;
	}

	public ArrayList<UserModel> getUsers() {
		return usuarios;
	}

}