package modelo.enumerados;

public enum Genero {
	F("Feminino"), M("Masculino"), O("Outro");
	
	private String descricao;
	
	private Genero(String descricao) {
		this.descricao = descricao;
	}
	
	public String toString() {
		return this.descricao;
	}
}
