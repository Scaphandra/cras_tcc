package teste.basico;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modelo.basico.Familia;
import modelo.basico.Tecnico;
import modelo.basico.Visita;
import modelo.enumerados.VisitaTipo;

public class TesteVisita {
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
		
		Familia f = em.find(Familia.class, 3L);
		
		Visita v = new Visita();
		
		v.setData(data);
		v.setTecnicos(t);
		v.setFamilia(f);

		v.setTipo(VisitaTipo.DOMICILIAR);
		v.setMotivo("Busca Ativa");

		
		em.persist(v);
		em.getTransaction().commit();
		em.close();
		emf.close();
		
		
		
		
	}

}
