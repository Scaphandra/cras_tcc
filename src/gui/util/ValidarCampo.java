package gui.util;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

public class ValidarCampo {
	private static final Tooltip t = new Tooltip("Campo obrigatório");
	
	public static boolean checarCampoVazio(Node ... no) {
		
		List<Node> camposFalha = new ArrayList<>();
		
		t.setStyle(
				"-fx-border-color: linear-gradient(#c21500,#ffc500);"
				+ " -fx-font-weight: bold;");
		
		ValidaExibeTooltip.tempoT(t);
		
		for(Node n: no) {
			if(n instanceof TextField) {
				//criar um objeto para o node
				TextField txt = (TextField) n;
				//capturar caracter digitado e retirar a borda de vazio
				
				txt.textProperty().addListener((obs, ovalue, nvalue)->{
					ValidaExibeTooltip.removerCorBorda(txt, t);
				});
				
				//se o campo estiver vazio adiciono a borda acusando o erro
				
				if(txt.getText().trim().equals("")) {
					camposFalha.add(n);
					ValidaExibeTooltip.adicionarCorBorda(txt, t);
				}
			}
		}
		
		
		return camposFalha.isEmpty();
	}
}
