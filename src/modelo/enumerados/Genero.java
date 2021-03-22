package modelo.enumerados;

public enum Genero {
	F("Mulher Cisgênero"), M("Homem Cisgênero"), MT("Mulher Transgênero"), HT("Homem Transgênero"), 
	MS("Mulher Transexual"), HS("Homem Transexual"), O("Outro");
	
	private String descricao;
	
	private Genero(String descricao) {
		this.descricao = descricao;
	}
	
	public String toString() {
		return this.descricao;
	}
}
