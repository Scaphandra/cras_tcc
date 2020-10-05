package modelo.enumerados;

public enum Escolaridade {
	NAOESC("N�o escolarizada"), FUNDAMENTAL("Fundamental"), MEDIO("M�dio"), SUPERIOR("Superior");
	
private String descricao;
	
	private Escolaridade(String descricao) {
		this.descricao = descricao;
	}
	
	public String toString() {
		return this.descricao;
	}
}
