package services;

import java.util.ArrayList;

import model.UserModel;
import model.dao.UserDAO;

public class SearchUserServices {
	
	private String warning = "";
	private boolean empty = true;
	private ArrayList<UserModel> usuarios;
	
	public ArrayList<UserModel> search(String cpf, String name) {
		warning = "";
		usuarios = new ArrayList<UserModel>();

		if(cpfIsValid(cpf)) {
			UserModel p = new UserDAO(null).getUser(cpf);
			
			if(p != null) {
				usuarios.add(p);
			}
			
		}else {
			if(name!=null && !name.equals("")) {
				usuarios = new UserDAO(null).getUsers(name);
					
			}else {
				warning = "Nenhum campo preenchido!";
				return null;
			}
		}
		if(usuarios.size() == 0)
			warning = "Não foram encontrados resultados. :(";
		return usuarios;
	}
	public boolean cpfIsValid(String cpf) {
		return cpf!=null && !cpf.contains("_") && cpf.trim().length() > 0;
	}
	public String getWarning() {
		return warning;
	}
	public boolean hasItens() {
		return (usuarios != null && usuarios.size()>0 && usuarios.get(0) != null) ;
	}
}
