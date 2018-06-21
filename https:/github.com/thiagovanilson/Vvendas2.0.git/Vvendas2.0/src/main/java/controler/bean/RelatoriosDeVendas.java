package controler.bean;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import model.SellModel;
import model.UserModel;
import model.dao.SalesDAO;

import java.io.Serializable;

@Named
@ViewScoped

public class RelatoriosDeVendas implements Serializable {		
	
	private int days = 0;
	@Inject
	SalesDAO sd ;//= new SalesDAO(null);
	private String cpfUser = "";
	
	public String getRelatorio() {
		if(days == 0)
			return "";
		float soma = 0;
		if(cpfUser != null && !cpfUser.equals("")) {
			for(SellModel s : sd.getSales(days, cpfUser)) {
				soma += s.getPrice();
			}
			return String.format("<h3>Total arecadado nos ultimos " + days + " dias do usuario "+ cpfUser+": R$ %.2f</h3>" , soma);
		}else {
			String output = "<h3>";
			for(UserModel um : getUsers()) {
				float subTotal = 0;
				for(SellModel s : sd.getSales(days, um.getCpf())) {
					if(s!=null)
						subTotal += s.getPrice();
				}
				if(um != null) {					
					soma += subTotal;
					output += String.format("Total arecadado nos ultimos " + days + " dias do usuario "+ um.getCpf() +": R$ %.2f<br />" , subTotal);
				}
			}
			output += String.format("<br />Total: %.2f </h3>",  soma) ;
			return output;
		}
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}
	public ArrayList<UserModel> getUsers(){
		return sd.getUsers();
	}
	public void setCpfUser(String cpf) {
		cpfUser = cpf;
	}
	public String getCpfUser() {
		return cpfUser;
	}
}