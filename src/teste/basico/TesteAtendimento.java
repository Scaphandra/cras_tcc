package teste.basico;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modelo.basico.Atendimento;
import modelo.basico.Pessoa;
import modelo.basico.Tecnico;
import modelo.enumerados.AtendimentoTipo;
import modelo.enumerados.DemandaAtendimento;

public class TesteAtendimento {
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
		
		Atendimento a = new Atendimento();
		
		a.setData(data);
		a.setFuncionario(t);
		a.setPessoa(p);
		a.setTipo(AtendimentoTipo.T);
		a.setDemanda(DemandaAtendimento.ACOMPANHAMENTO);

		
		em.persist(a);
		em.getTransaction().commit();
		em.close();
		emf.close();
		
		
		
		
	}

}
