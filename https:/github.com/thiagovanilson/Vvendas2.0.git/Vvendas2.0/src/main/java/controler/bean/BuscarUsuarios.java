package controler.bean;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import model.Persist;
import model.ProductDAO;
import model.ProductModel;
import model.UserDAO;
import model.UserModel;

@SuppressWarnings("serial")
@RequestScoped
@ManagedBean
public class BuscarUsuarios extends AbstractBean{	
	
	private String name, cpf, aviso;
	ArrayList<UserModel> usuarios = new ArrayList<UserModel>();
	
	public String filtrar() {
		
		return null;
	}

	private boolean empty = true;
	
	public void buscar() {
		aviso = "";
		
		if(cpf!=null && !cpf.equals("")) {
			UserModel p = new UserDAO(null).getUser(cpf);
			
			if(p != null) {
				usuarios.add(p);
				empty = false;
			}
			else {
				empty = true;
				clean();
				aviso = "Não foram encontrados resultados. :(";
			}
		}else {
			//Implementation to find many results
//			for(UserModel p: ){
//				usuarios.add(p);
//				empty = false;
//
//			}
		}
	}
	public void clean() {
		name        = "";
		cpf         = "";
		usuarios = new ArrayList<UserModel>();
		
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