package modelo.enumerados;

public enum CorRaca {
	NAODECLARADA("N�o declarada"), PRETA("Preta"), BRANCA("Branca"), PARDA("Parda"), INDIGENA("Ind�gena"), AMARELA("Amarela");
	
private String descricao;
	
	private CorRaca(String descricao) {
		this.descricao = descricao;
	}
	
	public String toString() {
		return this.descricao;
	}
}
