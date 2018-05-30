package controler.bean;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import model.Persist;
import model.SellModel;


@Named
@RequestScoped

public class RelatoriosDeVendas {	
	
	
	private int days = 0;
	
	public String getRelatorio() {
		if(days == 0)
			return "";
		float soma = 0;
		for(SellModel s : new Persist().getSales(days)) {
			soma += s.getPrice();
			System.out.println("Data aparente do registro: " + s.getDate());
		}
		return "Total arecadado nos ultimos " + days + " dias: R$" + soma;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}
	
}