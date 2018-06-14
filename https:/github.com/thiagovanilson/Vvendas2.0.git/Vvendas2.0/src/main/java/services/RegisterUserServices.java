package services;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import controler.bean.AbstractBean;
import model.UserDAO;
import model.UserModel;

public class RegisterUserServices extends AbstractBean{

	private boolean isEdition = false;
	
	public boolean save(UserModel userModel ) {

		try {
			UserDAO ud  = new UserDAO(userModel);

			MessageDigest md;
			try {
				md = MessageDigest.getInstance("SHA-256");
				md.update(userModel.getPass().getBytes("UTF-8"));
				byte[] digest = md.digest();
				String output = Base64.getEncoder().encodeToString(digest);
				userModel.setPass(output);
			} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
				
			}
			if(isEdition) {
				if (ud.edit(userModel)) {
					reportarMensagemDeSucesso("Edições salvas!");
					isEdition = false;
					return true;
				}
				else
					reportarMensagemDeErro("Erro ao alterar dados. Revise os campos!");	
			}else {
				
				if(ud.save(userModel)) {
					reportarMensagemDeSucesso("Usuario salvo!");
					isEdition = false;
					return true;
				}
				else
					reportarMensagemDeErro("Falha ao gravar!");
			}
		} catch (Exception e) {
			reportarMensagemDeErro(e.getMessage());

		}
		return false;
	}

	public UserModel search(String cpf) {
		UserDAO ud  = new UserDAO(null);
		UserModel u = ud.getUser(cpf);
		
		if(u == null)
			isEdition = false;
		else
			isEdition = true;
		
		return u;
	}

	public boolean isEdition() {
		return isEdition;
	}

	public boolean delete(UserModel um) {
		UserDAO ud = new UserDAO(null);	
		
		if (ud.delete(um)) {
			reportarMensagemDeSucesso("Excluido com sucesso!");
			isEdition = false;
			return true;
		}
		else
			reportarMensagemDeErro("Falha ao excluir.");	
		return false;
		
	}

}