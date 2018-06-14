package services;

import controler.bean.AbstractBean;
import model.ProductDAO;
import model.ProductModel;

public class RegisterProductsServices extends AbstractBean{

	private ProductModel pm;
	private boolean isEdition;
	
	public ProductModel search(String cod) {
		if(cod == null || cod.equals(""))
			return null;
		
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

}
