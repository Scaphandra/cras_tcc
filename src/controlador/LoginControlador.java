package controlador;

import java.util.List;

import org.controlsfx.control.Notifications;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import modelo.basico.Funcionario;
import modelo.dao.DAO;

public class LoginControlador {
	
	@FXML
	private TextField campoCpf;
	
	@FXML
	private PasswordField campoSenha;
	
	public void rodarTela(String caminhoArquivo) {
		try{
			FXMLLoader loader = new FXMLLoader(getClass().getResource(caminhoArquivo));
			VBox novoVBox = loader.load();
		}catch(Exception e) {
			//lançar um alerta
			
		}
	}
	
	public void entrar() {
		
		DAO<Funcionario> dao = new DAO<>(Funcionario.class);
		
		List<Funcionario> lista = dao.obterTodos();	 
		
		for(Funcionario f: lista) {
			if(campoCpf.getText() == f.getCpf() && campoSenha.getText()== f.getSenha()) {
				
			}
		}
		/*EntityManagerFactory emf;
		EntityManager em;
		emf = Persistence.createEntityManagerFactory("cras_tcc");
		em = emf.createEntityManager();*/
		
		/*TypedQuery<Funcionario> query = em.createQuery("Select u from Funcionario "
				+ "u Where cpf_funcionario='"+ campoCpf.getText()+
				"' and senha='"+ campoSenha.getText()+"'", Funcionario.class);*/
		
		
		
		if(campoCpf != null){
			Notifications.create()
			.position(Pos.TOP_RIGHT)
			.title("Login FXML")
			.text("Login efetuado com sucesso!")
			.showInformation();
		}else {
			Notifications.create()
			.position(Pos.TOP_RIGHT)
			.title("Login FXML")
			.text("Usuário e/ou senha inválidos")
			.showError();
			
		}
		System.out.println(campoCpf.getText());
		System.out.println(campoSenha.getText());
	}

	
}
