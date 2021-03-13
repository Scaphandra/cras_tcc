package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import aplicacao.App;
import gui.util.Alerta;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.basico.Funcionario;

public class LoginControlador implements Initializable{
	
	@FXML
	private TextField cpf;
	
	@FXML
	private PasswordField senha;
	
	Funcionario func = new Funcionario();
	//private Scene cena;
	
//	public synchronized <T> void rodarTela(String arquivoFXML, Consumer<T> initializingAction) {
//		
//		try{
//			Stage primaryStage = new Stage();
//			FXMLLoader loader = new FXMLLoader(getClass().getResource(arquivoFXML));
//			ScrollPane novoVBox = loader.load();
//			
//			Scene cena = App.getCena();
//			ScrollPane appVBox = cena.getRoot()).getContent();
//			
//			Node appMenu = appVBox.getChildren().get(0);
//			appVBox.getChildren().clear();
//			appVBox.getChildren().add(appMenu);
//			appVBox.getChildren().addAll(novoVBox.getChildren());
//			T controlador = loader.getController();
//			initializingAction.accept(controlador);
//		} catch (IOException e) {
//			Alerta.showAlert("IO Exception", "Erro ao carregar a tela", e.getMessage(), AlertType.ERROR);
//			e.printStackTrace();
//		}
//	}
	
	public void entrar() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cras_tcc");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		String jpql = "Select e from Funcionario e";
		TypedQuery<Funcionario> query = em.createQuery(jpql, Funcionario.class);
		List <Funcionario> funcionarios = query.getResultList();
		
		for(Funcionario f : funcionarios) {
			if(f.getCpf()!=null) {
				if(f.getCpf().equals(cpf.getText())) {
					this.func = em.find(Funcionario.class, f.getId());
					break;
				}
			}
		}
		if(func==null) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("CPF inválido");
			//alert.setHeaderText("Esse CPF não corresponde ao de nenhum funcionário cadastrado.");
			alert.setContentText("Esse CPF não corresponde "
					+ "\n ao de um funcionário cadastrado.");
			alert.show();
			return;
		}
		if(func.getSenha().equals(senha.getText())) {
//			rodarTela("../gui/app.fxml",(AppControlador controlador)-> {
//				controlador.setFuncionario(func);
//			});
		}else {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Senha inválida");
			//alert.setHeaderText("Esse CPF não corresponde ao de nenhum funcionário cadastrado.");
			alert.setContentText("A senha está incorreta.");
			alert.show();
			return;
		}
		
		
		System.out.println("Entrar");
	}
		


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	
}
