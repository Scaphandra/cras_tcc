package modelo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

//Data Access Object
public class DAO <E> {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Class<E> classe;
	
	static {
		try {
			emf = Persistence.createEntityManagerFactory("cras_tcc");
			
		}catch (Exception e) {
			Logger logger = Logger.getLogger("modelo.dao.DAO");
			logger.info("Bloco estático de criação do EntityManagerFactory");
			logger.debug("Criando EntityManager para o banco cras"); 
		}
		
	}
	
	public DAO() {
		this(null);
	}
	//construtor recebe a classe que é a entidade que quisermos trabalhar
	public DAO(Class<E> classe) {
		
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
	//estava Object id ao invés de Long id
	//public E obterPorID(Object id) {
	public E obterPorID(Class<E> classe, Object id) { 
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
		return obterTodos(200, 0);
	}
	
	public List<E> obterTodos(int qtde, int deslocamento){
//		if (classe == null) {
//			throw new UnsupportedOperationException("Classe nula");
//		}
		
		String jpql = "select e from " + classe.getName() + " e";
		
		TypedQuery<E> query = em.createQuery(jpql, classe);
		
		query.setMaxResults(qtde);
		query.setFirstResult(deslocamento);
		
		return query.getResultList();
	}
	
	public void removerPorID(Class<E> classe, Object id) {
		E entidade = obterPorID(classe, id);
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
	
	public void fechar() {
		em.close();
	}
	
}
