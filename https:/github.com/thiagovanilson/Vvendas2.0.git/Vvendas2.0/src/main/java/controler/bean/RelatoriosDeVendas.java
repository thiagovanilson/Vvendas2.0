package controler.bean;

import java.util.ArrayList;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import model.SalesDAO;
import model.SellModel;
import model.UserModel;


@Named
@RequestScoped

public class RelatoriosDeVendas {	
	
	
	private int days = 0;
	SalesDAO sd = new SalesDAO(null);
	private String cpfUser = "";
	
	public String getRelatorio() {
		if(days == 0)
			return "";
		float soma = 0;
		if(cpfUser != null && !cpfUser.equals("")) {
			for(SellModel s : new SalesDAO(null).getSales(days, cpfUser)) {
				soma += s.getPrice();
				System.out.println("Data aparente do registro: " + s.getDate());
			}
			return "Total arecadado nos ultimos " + days + " dias do usuario "+ cpfUser+": R$" + soma;
		}else {
			
			for(SellModel s : new SalesDAO(null).getSales(days)) {
				soma += s.getPrice();
				System.out.println("Data aparente do registro: " + s.getDate());
			}
			return "Total arecadado nos ultimos " + days + " dias: R$" + soma;
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