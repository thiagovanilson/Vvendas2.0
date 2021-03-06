package model;

import model.dao.Persist;

public class Facade {

	private UserModel um;
	private Persist pe;
	
	public Facade(){
		um  = new UserModel();
		pe = new Persist();
		
	}
	
	public UserModel createUser(String string, String name, String pass, String group, String email) throws Exception {
		UserModel u = new UserModel();
		//u.createNewUser(cpf, name, pass, group);
		u.setName(name);
		u.setCpf(string);
		u.setPass(pass);
		u.setUsergroup(group);
		u.setEmail(email);
		return u;
	}
	public boolean saveUser(UserModel u) {
		return pe.save(u);
	}
}
