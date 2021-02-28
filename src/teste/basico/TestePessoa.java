package teste.basico;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modelo.basico.Endereco;
import modelo.basico.Pessoa;
import modelo.enumerados.CorRaca;
import modelo.enumerados.EnderecoTipo;
import modelo.enumerados.Genero;
import modelo.enumerados.PessoaEstado;
import modelo.enumerados.Sexo;

public class TestePessoa {
public static void main(String[] args) {
	
	EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("cras_tcc");
	EntityManager em = emf.createEntityManager();

	Endereco end = new Endereco(EnderecoTipo.RUA, "Francisco Portela", 500, "Sobrado", "Botafogo", "24435-200");
	SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	String dataStr = "10/10/2001";
	Date data = new Date();
	try {
		data = formato.parse(dataStr);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	em.getTransaction().begin();
	Pessoa pes1 = em.find(Pessoa.class, 7L);
	Pessoa pes2 = em.find(Pessoa.class, 2L);
	Pessoa pes3 = em.find(Pessoa.class, 5L);
	Pessoa pes4 = em.find(Pessoa.class, 6L);
	Pessoa pes5 = em.find(Pessoa.class, 7L);
	

	
	pes1.setEstado(PessoaEstado.P);
	pes2.setEstado(PessoaEstado.P);

	pes1.setRenda(550.00);


	
	em.merge(pes1);
	em.getTransaction().commit();
	em.close();
	emf.close();
}
}
