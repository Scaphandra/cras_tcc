package modelo.enumerados;

public enum EnderecoTipo {
	RUA("Rua"), AVENIDA("Avenida"), TRAVESSA("Travessa"), JARDIM("Jardim"), ESTRADA("Estrada"),	QUADRA("Quadra"),
	LADEIRA("Ladeira"), RODOVIA("Rodovia"), MORRO("Morro");
	private String descricao;
	
	private EnderecoTipo(String descricao) {
		this.descricao = descricao;
	}
	
	public String toString() {
		return this.descricao;
	}
}
