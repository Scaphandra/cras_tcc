package teste.basico;

import java.text.SimpleDateFormat;

import modelo.basico.Pessoa;
import modelo.dao.DAO;

public class Teste {
	/*
	 * Toda classe que faz o CRUD tem que ter um main
	 * O EntityManager é um gerente das entidades -> faz o CRUD-> recebe o objeto, lê seu mapeamento 
	 * e converte em dados SQL (inserção, alteção, exclusão, consulta...) -> encapsula a conexão
	 * -> está ligado a um banco de dados específico, se precisar mexer em outro banco deve ter outro em
	 * EntityManagerFactory -> montar o entityManager
	 */
	
	public static void main(String[] args) {
		
		Pessoa pes = new Pessoa();
		
		DAO <Pessoa> dao = new DAO <> (Pessoa.class);
		
		pes = dao.obterPorID(Pessoa.class, 4L);
		
		
			
			System.out.println(pes);
			SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy");
			System.out.println(form.format(pes.getDataNascimento()).toString());
		
		
		//Pessoa p = dao.obterPorID(4L);
		
		

		/*
		Endereco end = new Endereco(EnderecoTipo.RUA, "Francisco Portela", 500, "Sobrado", "Botafogo", "24435-200");
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		String dataStr = "10/10/1979";
		Date data = new Date();
		
		
		try {
			data = formato.parse(dataStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Pessoa p = new Pessoa();
		
		p.setNome_pes("Marcelo");
		p.setDataNascimento(data);
		p.setSexo(Sexo.M);
		p.setGenero(Genero.M);
		p.setNomeMae("Joana Maria Ana");
		p.setCor(CorRaca.PARDA);
		
		dao.processarTudo(p);
		dao.fechar();
		
		
		 * 
		 * Tecnico coo = new Tecnico();
		daoTec.abrirTransacao();
		coo = daoTec.obterPorID(1L);
		daoTec.fecharTransacao().fechar();
		
		Pessoa p = new Pessoa();
		
		p.setNome("Maria");
		p.setDataNascimento(data);
		p.setSexo(Sexo.F);
		p.setGenero(Genero.F);
		p.setNomeMae("Maria Maria");
		p.setCor(CorRaca.PRETA);
		

		
		 * EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("exercicios-jpa");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();//como vamos alterar a tabela precisa abrir a trnasação
		Usuario usuario = em.find(Usuario.class, 8L);
		usuario.setNome("Leonardo Leitão");
		usuario.setEmail("leonardoLeitao@langue.com.br");
		System.out.println(usuario.getEmail());
		
		em.merge(usuario);//merge é responsável por fazer um update
		em.getTransaction().commit();
		em.close();
		emf.close();
		 */
		
		/*
		 * Endereco end = new Endereco(EnderecoTipo.RUA, "Francisco Portela", 500, "Sobrado", "Botafogo", "24435-200");
		 * Telefone tel = new Telefone("022", "2772-5293");
		 * 	SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			String dataStr = "02/10/2006";
			Date data;
				try {
					data = formato.parse(dataStr);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				DAO<Tecnico> daoTec = new DAO<>(Tecnico.class);
		Tecnico coo = new Tecnico();
		daoTec.abrirTransacao();
		coo = daoTec.obterPorID(1L);
		daoTec.fecharTransacao().fechar();
		
		
		SimpleDateFormat formato1 = new SimpleDateFormat("dd/MM/yyyy");
		String dataStr1 = "02/10/2006";
		Date data1 = new Date();
		 */
		
	}
}
