package modelo.enumerados;

public enum Escolaridade {
	NAOESC("Não escolarizado"), FUNDAMENTAL("Fundamental"), MEDIO("Médio"), SUPERIOR("Superior");
	
private String descricao;
	
	private Escolaridade(String descricao) {
		this.descricao = descricao;
	}
	
	public String toString() {
		return this.descricao;
	}
}
