package services;

import controler.bean.AbstractBean;
import model.ProductModel;
import model.dao.ProductDAO;

public class RegisterProductsServices extends AbstractBean{

	private ProductModel pm;
	private boolean isEdition = false;
	
	public ProductModel search(String cod) {
		if(cod == null || cod.equals("")) {
			isEdition = false;
			return null;
		}
		
		ProductDAO pd  = new ProductDAO(null);
		ProductModel p = pd.getProduct(cod);
		
		if(p != null) {
			pm = p;
			
			isEdition = true;
			return pm;
		}
		else {			
			isEdition = false;
		}
		return null;
	}

	public boolean isEdition() {
		return isEdition;
	}

	public boolean save(ProductModel product) {
		try {			
			if(!fieldsValidation(product)){
				return false;
			}
			ProductDAO pd = new ProductDAO(null);
			
			if(isEdition()) {
				if (pd.edit(product)) {
					reportarMensagemDeSucesso("Edições salvas!");
					return true;
				}
				else
					reportarMensagemDeErro("Erro ao alterar dados. Revise os campos!");	
			}else {
				if (pd.save(product)) {
					reportarMensagemDeSucesso("Produto salvo!");
					return true;
				}
				else
					reportarMensagemDeErro("Erro ao salvar. Revise os campos!");			
			}
			isEdition = false;
		} catch (Exception e) {
			reportarMensagemDeErro(e.getMessage());
		}
		return false;
		
	}

	public boolean delete(ProductModel p) {
		ProductDAO pd = new ProductDAO(null);	
		
		if (pd.delete(p.getId())) {
			reportarMensagemDeSucesso("Excluido com sucesso!");
			isEdition = false;
			return true;
		}
		else
			reportarMensagemDeErro("Falha ao excluir.");
		return false;
	}
	public boolean fieldsValidation(ProductModel pm) {
		String msg = "";
		boolean isValid = true;
		
		if(pm.getPrice() <= 0 ) {
			isValid = false;
			msg = "O preço do produto deve ser positivo!\n";
			reportarMensagemDeErro(msg);
		}
		
		if(pm.getMedida() <= 0 ) {
			isValid = false;
			msg = "A medida deve ser um valor maior que 0.\n";
			reportarMensagemDeErro(msg);
		}
		
		if(pm.getQuantity() <= 0) {
			isValid = false;
			msg = "A quantidade precisa ser maior que 0.\n";
			reportarMensagemDeErro(msg);
		}		
			
		return isValid;
	}
}
