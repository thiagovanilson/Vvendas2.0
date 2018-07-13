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
		cpf = "";
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cod) {
		this.cpf = cod;
		name = "";
	}

	public ArrayList<UserModel> getUsers() {
		return usuarios;
	}
}