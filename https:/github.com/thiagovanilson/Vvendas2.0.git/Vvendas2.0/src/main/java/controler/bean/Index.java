package controler.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import model.Persist;
import model.UserModel;

@RequestScoped
@ManagedBean(name="index")
public class Index {	
	
	UserModel um = new UserModel();
	
	public String filtrar() {
		
		return null;
	}
	
	public Index() {
		um = new UserModel();
	}
	
//	public String ok() {
//		String nome = "Vanilson", temp = "";
//		
//		for(int cont = 0; cont < nome.length(); cont++) {
//			temp += nome.toCharArray()[cont];
//			System.out.println(temp);			
//		}
//
//		return "c.xhtml";
//	}
	public String btUserAction() {
		System.out.println("Useraction " + um.getName());
		new Persist().save(um);
		return null;
	}
	
	
	public void setCpf(String cpf) {
		um.setCpf(cpf);
	}
	public String getCpf() {
		return um.getCpf();
	}
	public void setName(String name) {
		um.setName(name);
	}
	public String getName() {
		return um.getName();
	}
	public void setPass(String pass) {
		um.setPass(pass);
	}
	public String getPass() {
		return um.getPass();
	}
	public boolean checkCpf(String cpf) {
		return cpf.length() == 11;
	}
	public void setType(String type) {
		um.setUsergroup(type);
	}
	public String getType() {
		return um.getUsergroup();
	}
	public String[] types() {
		return new String[] {"admin","user"};
	}
}