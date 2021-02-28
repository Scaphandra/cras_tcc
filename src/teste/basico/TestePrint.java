package teste.basico;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modelo.basico.Pessoa;

public class TestePrint {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cras_tcc");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
//		PessoaDAO dao = new PessoaDAO();
//		dao.abrirTransacao();
		
		Pessoa p = em.find(Pessoa.class, 70L);
		p.setNome("SADSDSSDSF");
		em.merge(p);
		em.getTransaction().commit();
		em.close();
		emf.close();
		System.out.println(p.toString());
			System.out.println("Fim");

	}
	
}
