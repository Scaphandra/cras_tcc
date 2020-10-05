package modelo.enumerados;

public enum Sexo {
	
	F("Feminino"), M("Masculino");
	
	private String descricao;
	
	private Sexo(String descricao) {
		this.descricao = descricao;
	}
	
	public String toString() {
		return this.descricao;
	}
}
