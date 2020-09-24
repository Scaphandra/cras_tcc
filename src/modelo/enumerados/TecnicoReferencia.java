package modelo.enumerados;

public enum TecnicoReferencia {
	BPC("BPC"), SCFV("SCFV"), OUTRO("Outro"), NAO_TEM("N�o � refer�ncia");
	
private String descricao;
	
	private TecnicoReferencia(String descricao) {
		this.descricao = descricao;
	}
	
	public String toString() {
		return this.descricao;
	}
}
