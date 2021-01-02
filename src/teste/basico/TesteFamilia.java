package teste.basico;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modelo.basico.Familia;
import modelo.basico.Pessoa;

public class TesteFamilia {
public static void main(String[] args) {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("cras_tcc");
	EntityManager em = emf.createEntityManager();
	em.getTransaction().begin();
	
	Familia f = em.find(Familia.class, 1L);
//	
//	Pessoa p1 = em.find(Pessoa.class, 78L);
//	Pessoa p2 = em.find(Pessoa.class, 79L);
//	Pessoa p3 = em.find(Pessoa.class, 52L);
	f.setTotalRenda();
	f.setTotalBeneficio();
	f.setPercapita();
//	f.setPessoas(p3);
	
	em.merge(f);
	em.getTransaction().commit();
	em.close();
	emf.close();
}
}
