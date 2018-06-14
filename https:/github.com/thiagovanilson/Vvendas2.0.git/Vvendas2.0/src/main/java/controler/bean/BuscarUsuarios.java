package controler.bean;

import java.util.ArrayList;


import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import model.UserModel;
import services.SearchUserServices;

@SuppressWarnings("serial")
@ViewScoped
@Named
public class BuscarUsuarios extends AbstractBean{	
	
	private String name, cpf;
	private ArrayList<UserModel> usuarios = new ArrayList<UserModel>();
	private SearchUserServices sus = new SearchUserServices();
	
	public void buscar() {
		usuarios = sus.search(cpf, name);
		clean();
	}
	public void clean() {
		name = "";
		cpf  = "";		
	}
	public boolean hasItens() {
		return sus.hasItens();
	}
	public String getAviso() {
		return sus.getWarning();
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