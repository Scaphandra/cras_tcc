package teste.basico;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modelo.basico.Encaminhamento;
import modelo.basico.Pessoa;
import modelo.basico.Tecnico;
import modelo.enumerados.EncaminhamentoTipo;

public class TesteEncaminhamento {

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
		
		Pessoa p = em.find(Pessoa.class, 3L);
		Tecnico t = em.find(Tecnico.class, 3L);
		
		Encaminhamento e = new Encaminhamento();
		
		e.setTecnico(t);
		e.setPessoa(p);
		e.setData(data);
		e.setTipo(EncaminhamentoTipo.SAU);	
		
		
		em.persist(e);
		em.getTransaction().commit();
		em.close();
		emf.close();
		
	}
}
