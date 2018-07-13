package controler.bean;
import org.omnifaces.cdi.ViewScoped;

import javax.inject.Named;

import model.ProviderModel;
import services.ProviderServices;
import java.io.Serializable;
import java.util.List;

@ViewScoped
@Named("providers")

public class Providers implements Serializable {	
	
	public String getSearchName() {
		return searchName;
	}
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
	private String cnpj;
	private String tel;
	private String name;
	private String email;
	
	private String searchName;	
	
	private ProviderServices ps = new ProviderServices();
	
	public void save() {
		ProviderModel pm = new ProviderModel();
		
		pm.setCnpj(cnpj);
		pm.setEmail(email);
		pm.setName(name);
		pm.setTel(tel);
		
		ps.save(pm);
	}
	public void delete() {
		if(ps.delete(cnpj)) {
			clean();
		}			
	}
	public boolean hasItens() {
		return ps.hasItens();
	}
	public boolean exist() {
		return ps.exist(cnpj);
	}
	public void searchByName() {
		ps.searchByName(searchName);
		cnpj = "";
	}
	public void search() {
		ProviderModel temp = ps.search(cnpj);
		
		if(temp != null) {
			tel  = temp.getTel();
			name = temp.getName();
			email= temp.getEmail();
			
		}else {
			clean();
		}
	}
	public List<ProviderModel> getProviders() {
		return ps.getProviders();
	}		
	public boolean cnpjIsValid() {
		return ps.cnpjIsValid(cnpj);
	}
	private void clean() {
		tel  = "";
		name = "";
		email= "";
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}