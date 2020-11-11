package teste.basico;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modelo.basico.Acompanhamento;
import modelo.basico.Familia;
import modelo.basico.Tecnico;

public class TesteAcompanhamento {
	public static void main(String[] args) {
		
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cras_tcc");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		String dataStr = "10/10/1979";
		Date data = new Date();
		try {
			data = formato.parse(dataStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Tecnico t = em.find(Tecnico.class, 3L);
		
		Familia f = em.find(Familia.class, 1L);
		
		Acompanhamento a = new Acompanhamento();
		a.setFamilia(f);
		a.setDataEntrada(data);
		a.setTecnico(t);

		
		em.persist(a);
		em.getTransaction().commit();
		em.close();
		emf.close();
		
		
		
		
	}

}
