package services;

import java.util.ArrayList;

import model.UserModel;
import model.dao.UserDAO;

public class SearchUserServices {
	
	private String warning = "";
	private boolean empty = true;
	
	public ArrayList<UserModel> search(String cpf, String name) {
		warning = "";
		ArrayList<UserModel> usuarios = new ArrayList<UserModel>();

		if(cpf!=null && !cpf.equals("") && cpf.trim().length()>=13) {
			UserModel p = new UserDAO(null).getUser(cpf);
			
			if(p != null) {
				usuarios.add(p);
				empty = false;
			}
			else {
				empty = true;
				warning = "Não foram encontrados resultados. :(";
			}
		}else {
			if(name!=null && !name.equals("")) {
				for(UserModel u: new UserDAO(null).getUsers(name)){
					usuarios.add(u);
					empty = false;
	
				}
			}else {
				warning = "Nenhum campo preenchido!";
				return null;
			}
		}
		return usuarios;
	}

	public String getWarning() {
		return warning;
	}
	public boolean hasItens() {
		return !empty;
	}
}
