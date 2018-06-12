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
import model.UserDAO;
import model.UserModel;

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
	public SalesDAO criarSD() {
		SalesDAO sd = new SalesDAO(null);
		
		return sd;
	}
	@Produces
	public UserDAO criarUserDAO(UserModel um) {
		return new UserDAO(um);
	}
	
	public void fecharUserDAO(@Disposes UserDAO ud) {
		ud = null;
	}
	public void fecharEM(@Disposes SalesDAO sd) {
		sd = null;
	}
	public void fecharEM(@Disposes EntityManager em) {
		em.close();
	}

	public void fecharEMF(@Disposes EntityManagerFactory emf) {
		emf.close();
	}

}
