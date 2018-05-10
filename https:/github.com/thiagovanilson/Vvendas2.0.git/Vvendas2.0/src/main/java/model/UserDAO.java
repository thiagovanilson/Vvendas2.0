package model;

public class UserDAO {

	private UserModel um;
	
	public UserDAO(UserModel u){
		um = u;
	}
	
	public boolean save() {
		return new Persist().save(um);
	}
	public boolean delete(String id) {
		Persist p = new Persist();
		um = p.getUser(id);

		return delete();
	}
	public boolean delete() {
		if (um != null)
			return new Persist().delete(um);
		return false;
	}
	public boolean edit(String name, String pass, String tipo) {
		
		um.setName(name);
		um.setPass(pass);
		um.setUsergroup(tipo);
		
		return new Persist().edit(um);
	}
	public UserModel getUser(String cpf) {
		return new Persist().getUser(cpf);
	}
}
