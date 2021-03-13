package modelo.enumerados;

public enum Genero {
	F("Feminino"), M("Masculino"), HT("Homem Transg�nero"), MT("Mulher Transg�nero"), 
	HS("Homem Transexual"), MS("Mulher Transexual"), C("Cisg�nero"), O("Outro");
	
	private String descricao;
	
	private Genero(String descricao) {
		this.descricao = descricao;
	}
	
	public String toString() {
		return this.descricao;
	}
}
