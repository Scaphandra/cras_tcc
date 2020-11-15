package modelo.enumerados;

public enum FaixaEtaria {

	ZERO("Crianças de 0 a 6"), CRIANCA("Crianças e Adolescentes de 06 a 15"), ADOLESCENTE("Adolescentes de 15 a 17"), 
	JOVEM("Jovens de 18a 29"), ADULTO("Adultos de 30 a 59"), IDOSO("Idoso");
	
	private String descricao;
	
	private FaixaEtaria(String descricao) {
		this.descricao = descricao;
	}
	
	public String toString() {
		return this.descricao;
	}
}
