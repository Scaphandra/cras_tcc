package controlador;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import gui.util.Alerta;
import gui.util.MaskFieldUtil;
import gui.util.Util;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import modelo.basico.Familia;
import modelo.basico.Pessoa;
import modelo.dao.FamiliaDAO;
import modelo.dao.PessoaDAO;

public class FormDesligamentoFamiliaControlador implements Initializable{

	@FXML
	private Label rf;
	
	@FXML
	private Label codigo;
	
	@FXML 
	private TextField txtData;
	
	@FXML
	private TextArea txtMotivo;
	
	private Familia entidade;

	
	public void setFamilia(Familia familia) {
		this.entidade = familia;
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		MaskFieldUtil.dateField(this.txtData);
	
		txtData.setText(converteData(new Date()));
		
		
		
	}
	
	public void carregarLabel(Familia f) {
		
		codigo.setText("Código Familiar: " + entidade.getId().toString());
		rf.setText("Referência Familiar: "+ entidade.getPesReferencia().getNome());
	}
	
	@FXML
	public void clicarCancelar(ActionEvent event) {
		
		Util.atual(event).close();
		
	}
	
	
	protected String converteData(Date dt) {
		if (dt == null) {
			return "";
		}
		SimpleDateFormat formatBra;
		formatBra = new SimpleDateFormat("dd/MM/yyyy");

		return formatBra.format(dt).toString().replace("/", "");
	}
	
	@FXML 
	public void clicarDesligar(ActionEvent event) {
		
		desligar();
		
		Util.atual(event).close();
	}
	
	public void desligar() {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		String dataStr = txtData.getText();
		Date data = new Date();
		try {
			data = formato.parse(dataStr);
		} catch (Exception e) {
			Alerta.showAlert("Campo Data", "Data inválida", e.getMessage(), AlertType.WARNING);
			e.printStackTrace();
		}
		FamiliaDAO dao = new FamiliaDAO();
		dao.abrirTransacao();
		
		ValidationSupport validarTxt = new ValidationSupport();
		validarTxt.registerValidator(txtMotivo, Validator.createEmptyValidator("Campo Obrigatório"));
		
		entidade = dao.obterPorID(this.entidade.getId());
		for(Pessoa p: entidade.getPessoas()) {
			PessoaDAO daop = new PessoaDAO();
			p = daop.obterPorID(p.getId());
			p.setAtivo(false);
		}
		entidade.setAtivo(false);
		entidade.setDataDesligamento(data);
		entidade.setMotivoDesligamento(txtMotivo.getText());
		dao.atualizar(entidade);
		dao.fecharTransacao().fechar();
	}

}
