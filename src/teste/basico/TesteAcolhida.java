package teste.basico;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modelo.basico.Acolhida;
import modelo.basico.Pessoa;
import modelo.basico.Tecnico;

public class TesteAcolhida {
	public static void main(String[] args) {
		
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cras_tcc");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		String dataStr = "10/10/2020";
		Date data = new Date();
		try {
			data = formato.parse(dataStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Tecnico t = em.find(Tecnico.class, 3L);
		
		Pessoa p = em.find(Pessoa.class, 5L);
		Pessoa p2 = em.find(Pessoa.class, 1L);
		Pessoa p3 = em.find(Pessoa.class, 21L);
		
		Acolhida a = new Acolhida();
		
		a.setDataPrevista(data);
		a.setTecnico(t);
		a.setPessoas(p);
		a.setPessoas(p2);
		a.setPessoas(p3);

//		
//		Acolhida a = em.find(Acolhida.class, 1L);
//		
//		List<Pessoa> pes = new ArrayList<>();
//		pes = a.getPessoas();
//		if(pes == null) {
//			System.out.println("Lista vazia");
//		}
//		for(Pessoa p: pes) {
//			System.out.println(p);
//		}
		
		em.persist(a);
		em.getTransaction().commit();
		em.close();
		emf.close();
		
		
		
		
	}

}
