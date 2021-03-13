package controlador;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import modelo.basico.Tecnico;

public class AgendaControlador implements Initializable{

	@FXML
    private DatePicker data;

    @FXML
    private TextField qtd;

    @FXML
    private ComboBox <Tecnico> comboTecnico;

    @FXML
    void clicarAgendar(ActionEvent event) {
    	
    	LocalDate d = data.getValue();
		System.out.println(d.toString());
    }

    @FXML
    void clicarCancelar(ActionEvent event) {

    }
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		carregarData();
		
		
	}
	
	private void carregarData() {
		
	}
	
	
}
