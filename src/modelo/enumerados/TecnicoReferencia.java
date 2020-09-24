package modelo.enumerados;

public enum TecnicoReferencia {
	BPC("BPC"), SCFV("SCFV"), OUTRO("Outro"), NAO_TEM("Não é referência");
	
private String descricao;
	
	private TecnicoReferencia(String descricao) {
		this.descricao = descricao;
	}
	
	public String toString() {
		return this.descricao;
	}
}
