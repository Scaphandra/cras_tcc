package modelo.excecao;

import java.util.HashMap;
import java.util.Map;

public class ValidacaoExcecao extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//coleção de pares chaves/valor
	
	private Map<String, String> erros = new HashMap<>();
	
	public ValidacaoExcecao(String msg) {
		super(msg);
	}

	public Map<String, String> getErros() {
		return erros;
	}

	public void addErros(String nomeCampo, String erroMsg) {
		erros.put(nomeCampo, erroMsg);
	}
	

}
