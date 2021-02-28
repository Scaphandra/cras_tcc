package modelo.dao;

import modelo.basico.Pessoa;

public class PessoaDAO extends DAO<Pessoa>{

	public PessoaDAO() {
		super(Pessoa.class);
	}
	
//	public List<Pessoa> consultar2(String descricao){
//		
//		List<Pessoa> lista = new ArrayList<>();
//		
//		em = super.getEM();
//		if(descricao.isEmpty()) {
//			em.getTransaction().begin();
//			lista = em.createQuery(" from Pessoa ").getResultList();
//			
//		}else {
//			lista = em.createQuery(" from Pessoa p where p.descricao like "+"'"+ descricao+"%' limit 1 ").getResultList();
//		}
//		em.getTransaction().commit();
//		
//		return lista;
//	}
}
