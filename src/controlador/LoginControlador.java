package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import aplicacao.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginControlador implements Initializable{
	
	@FXML
	private TextField campoCpf;
	
	@FXML
	private PasswordField campoSenha;
	
	private Scene cena;
	
	public void rodarTela() {
		try{
			Stage primaryStage = new Stage();
			URL arquivoFXML = getClass().getResource("../gui/app.fxml");
			FXMLLoader loader = new FXMLLoader(arquivoFXML);
			ScrollPane raiz = loader.load();
			raiz.setFitToHeight(true);
			raiz.setFitToWidth(true);
			
			cena = new Scene(raiz);
			
			
			primaryStage.setResizable(true);
			primaryStage.setTitle("Centro de Referência da Assistência Social");
			//inicializar a tela com cena
			primaryStage.setScene(cena);
			primaryStage.show();

		}catch(Exception e) {
			e.printStackTrace();
			
		}
	}
	
	public void entrar() {
		
		rodarTela();
		
//		DAO<Funcionario> dao = new DAO<>(Funcionario.class);
//		
//		List<Funcionario> lista = dao.obterTodos();	 
//		
//		for(Funcionario f: lista) {
//			if(campoCpf.getText() == f.getCpf() && campoSenha.getText()== f.getSenha()) {
//				
//			}
//		}
		/*EntityManagerFactory emf;
		EntityManager em;
		emf = Persistence.createEntityManagerFactory("cras_tcc");
		em = emf.createEntityManager();*/
		
		/*TypedQuery<Funcionario> query = em.createQuery("Select u from Funcionario "
				+ "u Where cpf_funcionario='"+ campoCpf.getText()+
				"' and senha='"+ campoSenha.getText()+"'", Funcionario.class);*/
		
		
//		
//		if(campoCpf != null){
//			Notifications.create()
//			.position(Pos.TOP_RIGHT)
//			.title("Login FXML")
//			.text("Login efetuado com sucesso!")
//			.showInformation();
//		}else {
//			Notifications.create()
//			.position(Pos.TOP_RIGHT)
//			.title("Login FXML")
//			.text("Usuário e/ou senha inválidos")
//			.showError();
//			
//		}
//		System.out.println(campoCpf.getText());
//		System.out.println(campoSenha.getText());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	
}
