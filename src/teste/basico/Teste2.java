package teste.basico;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modelo.basico.Coordenador;
import modelo.basico.Endereco;
import modelo.basico.Unidade;
import modelo.enumerados.EnderecoTipo;
import modelo.enumerados.Escolaridade;
import modelo.enumerados.Vinculo;

public class Teste2 {
	
	public static void main(String[] args) {
		
	EntityManagerFactory emf;
	EntityManager em;
	emf = Persistence.createEntityManagerFactory("cras_tcc");
	em = emf.createEntityManager();
	em.getTransaction().begin();
	
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

	Coordenador coo = new Coordenador();
	
	coo.setNome("Coordenador da Unidade");
	coo.setCpf("01099876544");
	coo.setEscolaridade(Escolaridade.SUPERIOR);
	coo.setEmail("coordenador@gmail.com");
	coo.setSenha("senha");
	coo.setCargaHoraria(40);
	coo.setVinculo(Vinculo.CONCURSADO);
	coo.setEndereco(end);
	coo.setTelefone("999990909");
	
	em.persist(coo);
	
	Unidade unidade = new Unidade();
	List <String> area = new ArrayList<>();
	area.add("Botafogo");
	area.add("Novo Botafogo");
	area.add("Malvina");
	area.add("Nova Malvina");
	area.add("Fora da abrangência");
	unidade.setAreaAbrangencia(area);
	unidade.setIdCRAS("0330403987439874");
	unidade.setNomeCRAS("CRAS Botafogo");
	unidade.setTelCRAS("(22)2759-0854");
	unidade.setDataImplement(data);
	unidade.setCapacidade(5000);
	unidade.setHorarioFuncionamento("De 8h as 17h");
	unidade.setEndereco(end);
	unidade.setCoordenador(coo);

	em.persist(unidade);
	
	em.getTransaction().commit();
	em.close();
	emf.close();
	
	}	
}

