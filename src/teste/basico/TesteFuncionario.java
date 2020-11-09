package teste.basico;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modelo.basico.Coordenador;
import modelo.basico.Funcionario;
import modelo.basico.Reuniao;
import modelo.basico.Tecnico;
import modelo.enumerados.ReuniaoTipo;

public class TesteFuncionario {
	public static void main(String[] args) {
		
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cras_tcc");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		String dataStr = "10/10/1979";
		Date data = new Date();
		try {
			data = formato.parse(dataStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		Funcionario r = new Funcionario();
//		r.setNome("Recepcionista Fulana");
//		r.setFuncao("Recepcionista");
//		r.setEmail("recepcionista@gmail.com");
//		r.setSenha("senha");
//		r.setNivel(NivelAcesso.RECEPCIONISTA);
//		r.setEscolaridade(Escolaridade.MEDIO);
//		r.setCargaHoraria(40);
//		r.setTelefone("998980000");
//		
//		em.persist(r);
//		
//		Tecnico t = new Tecnico();
//		t.setNome("Tecnica Fulana");
//		t.setFuncao("Assistente Social");
//		t.setEmail("tecnico@gmail.com");
//		t.setSenha("senha");
//		t.setNivel(NivelAcesso.TECNICO);
//		t.setReferencia(TecnicoReferencia.SCFV);
//		t.setCargaHoraria(20);
//		t.setTelefone("998980000");
//		em.persist(t);
//		
//		Funcionario o = new Funcionario();
//		o.setNome("Orientadora Fulana");
//		o.setFuncao("Orientador Social");
//		o.setEmail("orientador@gmail.com");
//		o.setSenha("senha");
//		o.setNivel(NivelAcesso.SEMACESSO);
//		o.setEscolaridade(Escolaridade.MEDIO);
//		o.setCargaHoraria(40);
//		o.setTelefone("998980000");
//		em.persist(o);
		
		Funcionario r = em.find(Funcionario.class, 2L);
		Tecnico t = em.find(Tecnico.class, 3L);
		Funcionario o = em.find(Funcionario.class, 4L);
		Coordenador c = em.find(Coordenador.class, 1L);
		
		List <Funcionario> lista = new ArrayList<>();
		lista.add(r);
		lista.add(t);
		lista.add(o);
		lista.add(c);
		
		Reuniao reu = new Reuniao();
		reu.setTipo(ReuniaoTipo.APOIO);
		reu.setMotivo("Nivelamento de informações");
		reu.setData(data);
		
		String ata = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Porttitor eget dolor morbi non arcu risus. Lacus laoreet non curabitur gravida arcu ac tortor. Risus nullam eget felis eget nunc lobortis mattis aliquam. Ultrices neque ornare aenean euismod elementum nisi. Euismod elementum nisi quis eleifend quam adipiscing vitae proin. Scelerisque varius morbi enim nunc faucibus a pellentesque. Pretium quam vulputate dignissim suspendisse. Augue interdum velit euismod in pellentesque massa placerat. Habitasse platea dictumst quisque sagittis purus sit. Elit duis tristique sollicitudin nibh sit amet commodo nulla. Egestas egestas fringilla phasellus faucibus scelerisque eleifend donec. Pellentesque habitant morbi tristique senectus et netus et malesuada fames. Ultrices dui sapien eget mi proin sed libero enim. Diam vel quam elementum pulvinar. Dis parturient montes nascetur ridiculus mus mauris vitae ultricies leo. Nam at lectus urna duis convallis convallis tellus id. Pulvinar etiam non quam lacus suspendisse faucibus.\r\n" + 
				"\r\n" + 
				"In dictum non consectetur a erat nam at lectus urna. Dapibus ultrices in iaculis nunc sed augue lacus. Lectus sit amet est placerat in egestas. Viverra maecenas accumsan lacus vel facilisis volutpat est velit. Pharetra magna ac placerat vestibulum lectus. Id cursus metus aliquam eleifend mi in nulla. Gravida neque convallis a cras semper. At ultrices mi tempus imperdiet nulla. Ultrices eros in cursus turpis massa tincidunt dui ut ornare. Enim ut tellus elementum sagittis vitae et. Lacus sed turpis tincidunt id aliquet risus feugiat. Velit laoreet id donec ultrices tincidunt arcu non sodales neque. Est placerat in egestas erat imperdiet sed euismod nisi. Egestas quis ipsum suspendisse ultrices gravida dictum fusce ut placerat. Diam vulputate ut pharetra sit amet aliquam id diam. Scelerisque eleifend donec pretium vulputate sapien. Convallis a cras semper auctor. Risus at ultrices mi tempus. Varius duis at consectetur lorem donec.\r\n" + 
				"\r\n" + 
				"Massa tempor nec feugiat nisl pretium fusce id velit. Vulputate eu scelerisque felis imperdiet proin fermentum leo. Consectetur adipiscing elit duis tristique. Amet cursus sit amet dictum sit amet justo donec enim. Ut lectus arcu bibendum at. Proin sagittis nisl rhoncus mattis. Lacus vestibulum sed arcu non. Leo integer malesuada nunc vel risus. Lacus vel facilisis volutpat est velit egestas. Ac tortor vitae purus faucibus ornare suspendisse. Tristique magna sit amet purus. Sit amet volutpat consequat mauris nunc congue. Volutpat maecenas volutpat blandit aliquam etiam erat velit. Accumsan sit amet nulla facilisi morbi. Leo integer malesuada nunc vel risus commodo. Id neque aliquam vestibulum morbi blandit cursus risus. At elementum eu facilisis sed odio morbi quis commodo odio. Hac habitasse platea dictumst quisque sagittis purus sit amet.\r\n" + 
				"\r\n" + 
				"Aenean et tortor at risus viverra adipiscing at in tellus. Interdum varius sit amet mattis vulputate. Nulla aliquet enim tortor at auctor urna nunc id. Habitant morbi tristique senectus et netus et malesuada. Turpis egestas maecenas pharetra convallis posuere morbi leo urna. Sed augue lacus viverra vitae congue eu consequat. Posuere urna nec tincidunt praesent semper feugiat nibh. Malesuada pellentesque elit eget gravida cum. Purus in massa tempor nec feugiat nisl pretium fusce id. Magna ac placerat vestibulum lectus mauris ultrices. Blandit aliquam etiam erat velit scelerisque in dictum non consectetur. Orci eu lobortis elementum nibh tellus molestie nunc. Viverra vitae congue eu consequat ac.\r\n" + 
				"\r\n" + 
				"Tristique magna sit amet purus gravida quis blandit. Nulla aliquet enim tortor at auctor urna nunc. Elit scelerisque mauris pellentesque pulvinar pellentesque habitant morbi. Imperdiet dui accumsan sit amet. Tincidunt id aliquet risus feugiat in ante metus. Amet mauris commodo quis imperdiet massa tincidunt nunc pulvinar sapien. Massa ultricies mi quis hendrerit dolor magna eget est lorem. Vitae aliquet nec ullamcorper sit amet. Sodales ut eu sem integer vitae justo eget magna fermentum. Vel fringilla est ullamcorper eget nulla facilisi etiam dignissim. Etiam sit amet nisl purus in. Dignissim convallis aenean et tortor at risus viverra. Imperdiet proin fermentum leo vel.\r\n" + 
				"\r\n" + 
				"Etiam sit amet nisl purus. Ornare suspendisse sed nisi lacus. Pharetra vel turpis nunc eget. Eget mi proin sed libero enim sed faucibus. Odio ut enim blandit volutpat maecenas volutpat blandit. Sed id semper risus in hendrerit gravida rutrum. Tristique senectus et netus et malesuada fames ac. Consectetur purus ut faucibus pulvinar elementum integer enim. Sed enim ut sem viverra aliquet eget. Viverra maecenas accumsan lacus vel facilisis. At elementum eu facilisis sed odio morbi quis commodo. Vestibulum lectus mauris ultrices eros in cursus. Arcu risus quis varius quam quisque id diam vel quam.\r\n" + 
				"\r\n" + 
				"Gravida quis blandit turpis cursus in hac habitasse. Feugiat in ante metus dictum at tempor commodo. Non blandit massa enim nec dui nunc mattis enim ut. Viverra nibh cras pulvinar mattis nunc sed blandit libero. Id faucibus nisl tincidunt eget nullam. Enim nec dui nunc mattis. Nisl condimentum id venenatis a condimentum vitae sapien pellentesque habitant. Fermentum posuere urna nec tincidunt praesent. Velit ut tortor pretium viverra suspendisse. Morbi tincidunt augue interdum velit euismod in pellentesque. Tempus iaculis urna id volutpat lacus laoreet non curabitur. Nibh praesent tristique magna sit amet purus gravida quis blandit. Condimentum id venenatis a condimentum. Facilisis sed odio morbi quis commodo odio. Fusce ut placerat orci nulla pellentesque dignissim enim sit. Quis hendrerit dolor magna eget est lorem ipsum. Ut faucibus pulvinar elementum integer enim neque volutpat. Magnis dis parturient montes nascetur ridiculus mus mauris.";
		reu.setAta(ata);
		reu.setFuncionarios(lista);
		
		em.persist(reu);
		em.getTransaction().commit();
		em.close();
		emf.close();
		
		
		
		
	}

}
