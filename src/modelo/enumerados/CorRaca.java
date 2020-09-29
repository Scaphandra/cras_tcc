package modelo.enumerados;

public enum CorRaca {
	PRETA("Preta"), BRANCA("Branca"), PARDA("Parda"), INDIGENA("Ind�gena"), AMARELA("Amarela"), NAODECLARADA("N�o declarada");
	
private String descricao;
	
	private CorRaca(String descricao) {
		this.descricao = descricao;
	}
	
	public String toString() {
		return this.descricao;
	}
}
