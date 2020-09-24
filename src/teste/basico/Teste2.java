package teste.basico;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modelo.basico.Beneficio;
import modelo.enumerados.BeneficioTipo;

public class Teste2 {
	
	public static void main(String[] args) {
		
	EntityManagerFactory emf;
	EntityManager em;
	emf = Persistence.createEntityManagerFactory("cras_tcc");
	em = emf.createEntityManager();

	
	
	Beneficio bpc = new Beneficio(BeneficioTipo.BPCI, 1045, null);
	
	em.getTransaction().begin();
	em.persist(bpc);
	em.getTransaction().commit();
	em.close();
	
	}	
}

