package cdi;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.SalesDAO;
import model.SellModel;

public class JPAUtil {

	@Produces
	@ApplicationScoped
	public EntityManagerFactory criarEMF() {
		EntityManagerFactory emf = null;
		try {
			emf = Persistence.createEntityManagerFactory("comercio");
		} catch (Throwable t) {
			t.printStackTrace();
			throw t;
		}
		return emf;
	}

	@Produces
	@RequestScoped
	public EntityManager criarEM(EntityManagerFactory factory) {
		return factory.createEntityManager();
	}
	
	@Produces
	@RequestScoped
	public SalesDAO criarSD() {
		SalesDAO sd = null;
		try {
			sd = new SalesDAO(new SellModel());
		} catch (Throwable t) {
			t.printStackTrace();
			throw t;
		}
		return sd;
	}
	

	public void fecharEM(@Disposes EntityManager em) {
		em.close();
	}

	public void fecharEMF(@Disposes EntityManagerFactory emf) {
		emf.close();
	}

}
