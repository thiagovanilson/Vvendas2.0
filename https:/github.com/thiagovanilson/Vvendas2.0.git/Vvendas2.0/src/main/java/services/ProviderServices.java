package services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import controler.bean.AbstractBean;
import model.ProductModel;
import model.ProviderModel;
import model.dao.ProvidersDAO;

public class ProviderServices extends AbstractBean {

	ProvidersDAO pd = new ProvidersDAO();
	private List<ProviderModel> providers = new ArrayList<ProviderModel>();
	
	public List<ProviderModel> getProviders() {
		return providers;
	}
	public ProviderModel getProvider(String cnpj) {
		return pd.getProvider(cnpj);
	}
	
	public void save(ProviderModel pm) {
		ProviderModel temp = getProvider(pm.getCnpj());
		
		if(temp == null && pd.save(pm) )
			reportarMensagemDeSucesso("Empresa salva!");
		else if(temp != null && pd.edit(pm))
			reportarMensagemDeSucesso("Empresa editada!");
		else
			reportarMensagemDeErro("Falha ao registrar empresa!");


	}

	public boolean delete(String cnpj) {
		ProviderModel pm = pd.getProvider(cnpj);
		if(pm == null)
			return false;
		boolean result =  pd.delete(pm);
		
		if(result)
			reportarMensagemDeSucesso("Fornecedor excluido!");
		else
			reportarMensagemDeErro("Falha ao remover! :(");
		
		return result;
	}

	public ProviderModel search(String cnpj) {
		providers.clear();
		
		if(cnpj != null ) {	
			ProviderModel temp = getProvider(cnpj);
			providers.add(temp);
			return temp;
		}
		return null;
	}
	public boolean hasItens() {
		return (providers.size() > 0);
	}
	public void searchByName(String name) {
    	providers.clear();
    	if(name != null && name.length() >0) {
    		
    	List<ProviderModel> temp = pd.getByName(name);
    	
    	if(temp != null)
    		providers = temp;
    	}
		
	}
}
