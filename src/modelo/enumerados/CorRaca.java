package modelo.enumerados;

public enum CorRaca {
	NAODECLARADA("Não declarada"), PRETA("Preta"), BRANCA("Branca"), PARDA("Parda"), INDIGENA("Indígena"), AMARELA("Amarela");
	
private String descricao;
	
	private CorRaca(String descricao) {
		this.descricao = descricao;
	}
	
	public String toString() {
		return this.descricao;
	}
}
