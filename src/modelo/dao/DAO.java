package modelo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

//Data Access Object
public class DAO <E> {

	private static EntityManagerFactory emf;
	protected EntityManager em;
	private Class<E> classe;
	private List<E> lista = new ArrayList<>();
	
	static {
		try {
			emf = Persistence.createEntityManagerFactory("cras_tcc");
			
		}catch (Exception e) {
			Logger logger = Logger.getLogger("modelo.dao.DAO");
			logger.info("Bloco estático de criação do EntityManagerFactory");
			logger.debug("Criando EntityManager para o banco cras"); 
			System.out.println(e.getMessage());
		}
		
	}
	
	//construtor recebe a classe que é a entidade que quisermos trabalhar
	public DAO(Class<E> classe) {
		this.classe = classe;
		em = emf.createEntityManager();
	
	}
	
	//como temos um método que retorna a própria classe poderemos fazer encadeamento de chamadas
	
	public DAO<E> abrirTransacao(){
		em.getTransaction().begin();
		return this;
	}
	
	public DAO<E> fecharTransacao(){
		em.getTransaction().commit();
		return this;
	}
	//substituido por incluirAtualizar
	public DAO<E> incluir(E entidade){
		em.persist(entidade);
		return this;
	}
	
	public DAO<E> processarTudo(E entidade){
		
		return this.abrirTransacao().incluir(entidade).fecharTransacao();
	}
	
	public E obterPorID(Object id) { 
		return em.find(classe, id);
	}
	
	public void incluirAtualizar(E entidade) {
		
		if(entidade==null) {
			em.persist(entidade);//cria uma nova linha
			
		}else {
			em.merge(entidade);//atualiza a tabela
		}
	}
	
	public void atualizar(E entidade) {
		em.merge(entidade);
	}
	public void remover(E entidade) {
		em.remove(entidade);
	}
	
	public List<E> obterTodos(){
		
		return obterTodos(10000, 0);
	}
	
	public List<E> obterPorOrdem(String atributo){
		
		if (classe == null) {
			throw new UnsupportedOperationException("Classe nula");
		}
		
		String jpql = "select e from " + classe.getName() + " e order by "+ atributo + " DESC";
		
		TypedQuery<E> query = em.createQuery(jpql, classe);
		
		return query.getResultList();
	}
	
	public List<E> obterTodos(int qtde, int deslocamento){
		if (classe == null) {
			throw new UnsupportedOperationException("Classe nula");
		}
		
		String jpql = "select e from " + classe.getName() + " e";
		
		TypedQuery<E> query = em.createQuery(jpql, classe);
		
		query.setMaxResults(qtde);
		query.setFirstResult(deslocamento);
		
		return query.getResultList();
	}
	public List<E> obterPrimeiros(String descricao, String nomeDoAtributo){
		
		if (classe == null) {
			throw new UnsupportedOperationException("Classe nula");
		}
		if(descricao.equals("")) {
			
			lista = obterTodos();
			
		}else {
			String jpql = " from " + classe.getName() + " p"+" where p."+ nomeDoAtributo+" like "+"'"+ descricao + "%'";
			
			TypedQuery<E> query = em.createQuery(jpql, classe);
			
			//a única opção para inserir limites no hibernate é o setMaxResults
			query.setMaxResults(200);
			
			lista = query.getResultList();
		}
		
		return lista;
	}
	
	public List<E> obterCondicao(String nomeDoAtributo, String condicao){
		String jpql = "select p from "+ classe.getName() + " p where "+nomeDoAtributo+"='"+condicao+"'";
		TypedQuery<E> query = em.createQuery(jpql, classe);
		query.setMaxResults(200);
		return query.getResultList();
		
	}
	public List<E> obterCondicao2(String nome1, String condicao1, String nome2, String condicao2){
		String jpql = "select p from "+ classe.getName() + " p where "+nome1+"='"+condicao1+"' and "+nome2+"='"+condicao2+"'";
		TypedQuery<E> query = em.createQuery(jpql, classe);
		query.setMaxResults(200);
		return query.getResultList();
		
	}
	
	
	public void removerPorID(Object id) {
		E entidade = obterPorID(id);
		em.remove(entidade);
			
	}
	
	public List<E> consultar(String nomeConsulta, Object... params){
		
		TypedQuery<E> query = em.createNamedQuery(nomeConsulta, classe);
		
		for(int i = 0; i < params.length; i+=2) {
			query.setParameter(params[i].toString(), params[i+1]);
		}
		return query.getResultList();
	}
	
	public E consultar1(String nomeConsulta, Object... params){
		List<E> lista = consultar(nomeConsulta, params);
		return lista.isEmpty() ? null : lista.get(0);
	
	}
	
	public EntityManager getEM() {
		return this.em;
	}
	
	public void fechar() {
		em.close();
	}
	
}
