package controler.bean;

public class Services {

	private static Services serv;
	
	private Services() {
		
	}
	public static Services getInstance() {
		if(serv == null)
			serv = new Services();
		return serv;
	}
	
	private boolean editUser = true;
	public boolean getEditUser() {
		return editUser;
	}
	public void changeEditUser() {
		editUser = !editUser;
	}
}
