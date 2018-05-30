package controler.bean;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;


@ApplicationScoped
@Named("index")

public class Index {	
	
	
	public String filtrar() {
		
		return null;
	}
	public String goToIndex() {
		return "index.xhtml";
	}
	
	
}