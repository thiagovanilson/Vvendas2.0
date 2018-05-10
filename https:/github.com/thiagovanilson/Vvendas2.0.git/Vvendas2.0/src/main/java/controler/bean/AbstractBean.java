package controler.bean;


import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;


public abstract class AbstractBean implements Serializable {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 7887186144461468149L;

	protected void reportarMensagemDeErro(String detalhe) {
		reportarMensagem(true, detalhe);

	}

	protected void reportarMensagemDeSucesso(String detalhe) {
		reportarMensagem(false, detalhe);
	}

	private void reportarMensagem(boolean isErro, String detalhe) {
		String tipo = "Sucesso!";
		Severity severity = FacesMessage.SEVERITY_INFO;
		
		if (isErro) {
			tipo = "Eror!";
			severity = FacesMessage.SEVERITY_ERROR;
			FacesContext.getCurrentInstance().validationFailed();
		}

		FacesMessage msg = new FacesMessage(severity, tipo + " " + detalhe, null);

		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.setKeepMessages(true);
		flash.setRedirect(true);
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}
	
//	public Group[] getGroups() {
//		return Group.values();
//	}
}
