package teste.basico;

import java.util.List;

import modelo.basico.Tecnico;
import modelo.dao.DAO;

public class TestePrint {
	public static void main(String[] args) {
//	
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cras_tcc");
//		EntityManager em = emf.createEntityManager();
//		String jpql = "select e from Tecnico e";
//		
//		TypedQuery <Tecnico> query = em.createQuery(jpql, Tecnico.class);
//		
//		List <Tecnico> tecnicos = query.getResultList();
		
		DAO<Tecnico> dao = new DAO<>(Tecnico.class);
		List <Tecnico> tecnicos = dao.obterTodos();

		System.out.println(tecnicos);
		
		
	}
}
