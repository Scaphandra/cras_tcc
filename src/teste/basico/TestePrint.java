package teste.basico;

import java.util.List;

import modelo.basico.Familia;
import modelo.basico.Pessoa;
import modelo.dao.FamiliaDAO;
import modelo.dao.PessoaDAO;

public class TestePrint {
	public static void main(String[] args) {
//	
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cras_tcc");
//		EntityManager em = emf.createEntityManager();
//		String jpql = "select e from Tecnico e";
//		
//		TypedQuery <Tecnico> query = em.createQuery(jpql, Tecnico.class);
//		
//		List<Tecnico> tecnicos = query.getResultList();
		
//		List <Tecnico> tecnicos = query.getResultList();
//		
//		List <Tecnico> tecnicos = dao.obterTodos();
//		em.getTransaction().begin();
//
//		Familia f = em.find(Familia.class, 6L);
//		List<Pessoa> p = f.getPessoas();
//		
//		PesReferencia p = em.find(PesReferencia.class, 47L);
//		
//		Pessoa p1 = (Pessoa) p;
//		
//		String jpql = "update pessoa set tipo='P' where e.nome_pessoa='"+p1.getNome()+" e";
//		
//		TypedQuery<Pessoa> query = em.createQuery(jpql, Pessoa.class);
//		
//		em.refresh(p1);
		
//		FuncionarioDAO dao = new FuncionarioDAO();
//		
//		
//		
//		List<Funcionario> tecs = dao.obterTodos();
//		
//		
//		
//		for(Funcionario t: tecs)
//		{
//			System.out.println(t);
//		}
//		
//		
		
//		for(Atendimento a: p.getAtendimentos()) {
//			System.out.println(a);
//		}

//		f.apagarFamilia();
//		em.merge(f);
//		em.remove(f);
		FamiliaDAO dao = new FamiliaDAO();
		PessoaDAO daop = new PessoaDAO();
		
		Familia f = dao.obterPorID(1L);
		List <Pessoa> pes = daop.obterCondicao("id_familia", f.getId().toString());
		
		
		for(Pessoa p: pes) {
			System.out.println(p.getNome());
		}
		System.out.println(f.getTotalRenda());
		System.out.println(f.getRendaReferencia());
		System.out.println(f.getPercapita());
		
		
		
		
			System.out.println("Fim");

//		em.getTransaction().commit();
//		em.close();
//		emf.close();
		
	}
}
