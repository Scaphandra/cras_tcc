package teste.basico;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modelo.basico.Funcionario;
import modelo.basico.GrupoSCFV;
import modelo.basico.Pessoa;
import modelo.basico.Tecnico;
import modelo.enumerados.FaixaEtaria;

public class TesteGrupo {
	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cras_tcc");
		EntityManager em = emf.createEntityManager();

//	Endereco end = new Endereco(EnderecoTipo.RUA, "Francisco Portela", 500, "Sobrado", "Botafogo", "24435-200");
//	SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
//	String dataStr = "10/10/1989";
//	Date data = new Date();
//	try {
//		data = formato.parse(dataStr);
//	} catch (Exception e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
		em.getTransaction().begin();
		Pessoa pes1 = em.find(Pessoa.class, 19L);
		Pessoa pes2 = em.find(Pessoa.class, 17L);
		Pessoa pes3 = em.find(Pessoa.class, 8L);
		Tecnico t = em.find(Tecnico.class, 3L);
		Funcionario f = em.find(Funcionario.class, 4L);

		GrupoSCFV g = new GrupoSCFV();
		g.setNome("Provida2");
		g.setTecnico(t);
		g.setOrientador(f);
		g.setQuantidadeMax(30);
		g.setPessoas(pes1);
		g.setPessoas(pes2);
		g.setPessoas(pes3);
		
		g.setFaixa(FaixaEtaria.CRIANCA);
		g.setObservacoes("Grupo funciona a tarde no horário de 13:30 a 16:30");
		
		
		pes1.setGrupo(g);
		pes2.setGrupo(g);
		pes3.setGrupo(g);
		
		g.excluirPessoa(pes1);
		

		em.persist(g);
		em.getTransaction().commit();
		em.close();
		emf.close();
	}
}
