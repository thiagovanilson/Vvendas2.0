package services;

import java.util.ArrayList;
import java.util.List;

import controler.bean.AbstractBean;
import model.ProviderModel;
import model.dao.ProvidersDAO;

public class ProviderServices extends AbstractBean {

	ProvidersDAO pd = new ProvidersDAO();
	private List<ProviderModel> providers = new ArrayList<ProviderModel>();
	
	public List<ProviderModel> getProviders() {
		return providers;
	}
	public ProviderModel getProvider(String cnpj) {
		providers.clear();
		providers.add(pd.getProvider(cnpj));
		return providers.get(0);
	}
	public boolean exist(String cnpj) {
		if(cnpjIsValid(cnpj)) {
			return pd.getProvider(cnpj) != null;
		}
		return false;
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
		providers.removeAll(providers);
		
		if(cnpjIsValid(cnpj)) {			
			ProviderModel temp = getProvider(cnpj);
			
			return temp;			
		}
		return null;
	}
	public boolean hasItens() {
		return (providers != null && providers.size() > 0 &&  providers.get(0) != null);
	}
	public void searchByName(String name) {
    	providers.clear();
    	if(name != null && name.length() > 0) {
    		
    	List<ProviderModel> temp = pd.getByName(name);
    	
    	if(temp != null)
    		providers = temp;
    	}
		
	}
	public boolean cnpjIsValid(String cnpj) {
		if(cnpj == null)
			return false;
		return !cnpj.contains("_");
	}
}
