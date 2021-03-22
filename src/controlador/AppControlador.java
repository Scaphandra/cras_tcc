package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import aplicacao.App;
import gui.listeners.DataChangeListener;
import gui.util.Alerta;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import modelo.basico.Familia;
import modelo.basico.Funcionario;
import modelo.basico.Pessoa;
import modelo.basico.Unidade;
import modelo.dao.FuncionarioDAO;
import modelo.dao.UnidadeDAO;

public class AppControlador implements Initializable, DataChangeListener{

	
	@FXML
	private MenuItem menuPessoa;
	
	@FXML
	private MenuItem menuFamilia;
	
	@FXML
	private MenuItem menuAtendimento;

	@FXML
	private MenuItem menuAjuda;
	@FXML
	private MenuItem menuRegistro;
	
	private Funcionario func;
	
	private int unidade = 1;
	

	public void setUnidade(int u) {
		this.unidade = u;
		
	}
	
	
	public void setFuncionario(Funcionario f) {
		this.func = f;
		System.out.println(f.toString());
	}
	
	
	@FXML
	private void clicarFamilia() {
		System.out.println("familia");
		rodarTela("../gui/listaFamilia.fxml",(ListaFamiliaControlador controlador)-> {
			
			controlador.setUnidade(unidade);
			controlador.setFamilia(new Familia());
			controlador.carregarFamilia();
			controlador.inscreverListener(this);
			
		});
	
	}
	
	
	@FXML
	private void clicarPessoa() {
		//Utilizando a mesma funcão rodarTela vamos incluir um novo parâmetro que é uma função lambda
		rodarTela("../gui/listaPessoa.fxml",(ListaPessoaControlador controlador)-> {
			controlador.setPessoa(new Pessoa());
			controlador.carregarPessoas();
		});
	}
	
	@FXML
	private void clicarMenuAtendimento() {
		FuncionarioDAO dao = new FuncionarioDAO();
		Funcionario f = dao.obterPorID(3L);
		rodarTela("../gui/menuAtendimento.fxml", (MenuAtendimentoControlador controlador) ->{
			controlador.setFuncionario(f);
		});
	}
	
	@FXML
	private void clicarAgenda() {
		
	}
	
	@FXML
	private void clicarMenuSobre() {
		rodarTela("../gui/sobre.fxml", x->{});
		System.out.println("Sobre");
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
				
	}
	
	private synchronized <T> void rodarTela(String arquivoFXML, Consumer<T> initializingAction) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(arquivoFXML));
			
			VBox novoVBox = loader.load();
			
			Scene cena = App.getCena();
			VBox appVBox = (VBox) ((ScrollPane) cena.getRoot()).getContent();
			
			Node appMenu = appVBox.getChildren().get(0);
			appVBox.getChildren().clear();
			appVBox.getChildren().add(appMenu);
			appVBox.getChildren().addAll(novoVBox.getChildren());
			T controlador = loader.getController();
			initializingAction.accept(controlador);
		} catch (IOException e) {
			Alerta.showAlert("IO Exception", "Erro ao carregar a tela", e.getMessage(), AlertType.ERROR);
			e.printStackTrace();
		}
	}


	@Override
	public void onDataChanged() {
		clicarFamilia();
		
	}


}
