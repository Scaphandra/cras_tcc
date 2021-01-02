package teste.basico;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modelo.basico.Endereco;
import modelo.basico.Familia;
import modelo.basico.PesReferencia;
import modelo.basico.Pessoa;
import modelo.enumerados.CorRaca;
import modelo.enumerados.EnderecoTipo;
import modelo.enumerados.Genero;
import modelo.enumerados.Sexo;
import modelo.enumerados.SituacaoFamilia;

public class Teste {
	/*
	 * Toda classe que faz o CRUD tem que ter um main
	 * O EntityManager é um gerente das entidades -> faz o CRUD-> recebe o objeto, lê seu mapeamento 
	 * e converte em dados SQL (inserção, alteção, exclusão, consulta...) -> encapsula a conexão
	 * -> está ligado a um banco de dados específico, se precisar mexer em outro banco deve ter outro em
	 * EntityManagerFactory -> montar o entityManager
	 */
	
	public static void main(String[] args) {
		
		//Pessoa pes = new Pessoa();
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("cras_tcc");
		EntityManager em = emf.createEntityManager();
		//DAO <Unidade> dao = new DAO <> (Unidade.class);
		
		//pes = dao.obterPorID(Pessoa.class, 4L);
		
		
		Endereco end = new Endereco(EnderecoTipo.MORRO, null, 500, "Sobrado", null, null);
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		String dataStr = "10/10/1989";
		Date data = new Date();
		try {
			data = formato.parse(dataStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		em.getTransaction().begin();
		
//		PesReferencia pes1 = em.find(PesReferencia.class, 7L);
//		Pessoa pes2 = new Pessoa();
//		pes2.setNome("Teste parA o banco");
//		pes2.setDataNascimento(data);
//		pes2.setSexo(Sexo.F);
//		pes2.setGenero(Genero.F);
//		pes2.setNomeMae("Teste Maria");
//		pes2.setCor(CorRaca.PRETA);
		
//		List<Beneficio> bList = new ArrayList<>();
//		
//		Beneficio pbf = new Beneficio(BeneficioTipo.PBF, 120.0, pes1);
//		bList.add(pbf);
//		Beneficio bpc = new Beneficio(BeneficioTipo.BPCDEF, 1045.0, pes1);
//		bList.add(bpc);
//		
//		pes1.setBeneficios(bList);
		
//		System.out.println(pes1.getTotalBeneficio());
		
		Pessoa pes2 = em.find(Pessoa.class, 78L);
		Pessoa pes3 = em.find(Pessoa.class, 79L);
		Pessoa pes4 = em.find(Pessoa.class, 80L);
		Pessoa pes5 = em.find(Pessoa.class, 5L);
//		
//		pes2.setNome("Fulano");
//		pes2.setDataNascimento(data);
//		pes2.setSexo(Sexo.M);
//		pes2.setGenero(Genero.M);
//		pes2.setNomeMae("Ana");
//		pes2.setCor(CorRaca.BRANCA);
//		em.merge(pes2);
		
		
		List<Pessoa> lista = new ArrayList<>();
//		lista.add(pes1);
		lista.add(pes2);
		lista.add(pes3);
		lista.add(pes4);
		lista.add(pes5);

//		Unidade unidade = em.find(Unidade.class, 1L);
//		List <String> area = new ArrayList<>();
//		area.add("Botafogo");
//		area.add("Novo Botafogo");
//		area.add("Malvina");
//		area.add("Nova Malvina");
//		area.add("Fora da abrangência");
//		
//		unidade.setAreaAbrangencia(area);
//		em.merge(unidade);
		
		Familia fam = em.find(Familia.class, 2L);
		
		//fam.setPesReferencia(pes1);
		
		
//		String telefone = "(22) 998989898";
//		String telefone2 = "(22) 998980000";
//		fam.setEndereco(end);
//		fam.setDataEntrada(data);
//		List<String> tels = new ArrayList<>();
//		tels.add(telefone);
//		tels.add(telefone2);
//		fam.setTelefone(tels);
//		fam.setPessoas(lista);
//		fam.setSituacao(SituacaoFamilia.ACOMP);
		
//		pes1.setFamilia(fam);
//		pes2.setFamilia(fam);
		
		em.merge(fam);
		em.getTransaction().commit();
		em.close();
		emf.close();
		
//		System.out.println(fam.getPessoas());
		
//		Pessoa pes2 = em.find(Pessoa.class, 5L);
		
//		em.merge(pes1);
//		em.merge(pes2);
//		em.merge(pes3);
//		
//		em.getTransaction().commit();
//		em.close();
//		emf.close();
		
	
		
//		dao.atualizar(unidade);
//		dao.fecharTransacao();
//		dao.fechar();
		
//			System.out.println(pes);
//			SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy");
//			System.out.println(form.format(pes.getDataNascimento()).toString());
		
		
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
