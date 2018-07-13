package controler.bean;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import model.SellModel;
import model.UserModel;
import model.dao.SalesDAO;
import services.ReportServices;

import java.io.Serializable;

@Named
@ViewScoped

public class RelatoriosDeVendas implements Serializable {		
	
	private ReportServices rs = new ReportServices();
	
	public String getRelatorio() {
		return rs.getRelatorio();
	}

	public int getDays() {
		return rs.getDays();
	}

	public void setDays(int days) {
		rs.setDays(days);
	}
	public ArrayList<UserModel> getUsers(){
		return rs.getUsers();
	}
	public void setCpfUser(String cpf) {
		rs.setCpfUser(cpf);
	}
	public String getCpfUser() {
		return rs.getCpfUser();
	}
}