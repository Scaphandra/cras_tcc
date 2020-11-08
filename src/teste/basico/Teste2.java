package teste.basico;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modelo.basico.Unidade;

public class Teste2 {
	
	public static void main(String[] args) {
		
	EntityManagerFactory emf;
	EntityManager em;
	emf = Persistence.createEntityManagerFactory("cras_tcc");
	em = emf.createEntityManager();
	em.getTransaction().begin();

	Unidade unidade = em.find(Unidade.class, 1L);
	List <String> area = new ArrayList<>();
	area.add("Botafogo");
	area.add("Novo Botafogo");
	area.add("Malvina");
	area.add("Nova Malvina");
	area.add("Fora da abrangência");
	
	unidade.setAreaAbrangencia(area);

	em.merge(unidade);
	
	em.getTransaction().commit();
	em.close();
	
	}	
}

