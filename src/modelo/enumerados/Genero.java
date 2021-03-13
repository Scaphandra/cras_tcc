package modelo.enumerados;

public enum Genero {
	F("Feminino"), M("Masculino"), HT("Homem Transgênero"), MT("Mulher Transgênero"), 
	HS("Homem Transexual"), MS("Mulher Transexual"), C("Cisgênero"), O("Outro");
	
	private String descricao;
	
	private Genero(String descricao) {
		this.descricao = descricao;
	}
	
	public String toString() {
		return this.descricao;
	}
}
