package modelo.enumerados;

public enum Genero {
	F("Mulher Cisg�nero"), M("Homem Cisg�nero"), MT("Mulher Transg�nero"), HT("Homem Transg�nero"), 
	MS("Mulher Transexual"), HS("Homem Transexual"), O("Outro");
	
	private String descricao;
	
	private Genero(String descricao) {
		this.descricao = descricao;
	}
	
	public String toString() {
		return this.descricao;
	}
}
