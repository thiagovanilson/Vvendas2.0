package model;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.persistence.*;

@Entity
@Table(name="usuarios")  

public class UserModel implements Cloneable, Serializable {
	@Id
	private String cpf;
	private String name;
	private String pass;
	private String usergroup;
	private String email;
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
		
	}
	public String getUsergroup() {
		return usergroup;
	}
	public void setUsergroup(String usergroup) {
		this.usergroup = usergroup;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public boolean equals(Object another) {
		if(another == null)
			return false;
		UserModel user = (UserModel) another;
		return this.getCpf().equals(user.getCpf());
		
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((usergroup == null) ? 0 : usergroup.hashCode());
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((pass == null) ? 0 : pass.hashCode());
		return result;
	}
	public boolean checkPass(String pass) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(pass.getBytes("UTF-8"));
			byte[] digest = md.digest();
			String output = Base64.getEncoder().encodeToString(digest);
			
			System.out.printf("Comparando %s com %s", output, this.pass);
			return (this.pass.equals(output));
//			return true;
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			System.out.println(e.getStackTrace());
			return false;
		}
		//return this.pass.equals(pass);
	}
}
