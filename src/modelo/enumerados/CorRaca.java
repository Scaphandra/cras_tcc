package modelo.enumerados;

public enum CorRaca {
	PRETA("Preto"), BRANCA("Branco"), PARDA("Pardo"), INDIGENA("Indígena"), AMARELA("Amarelo"), NAODECLARADA("Não declarado");
	
private String descricao;
	
	private CorRaca(String descricao) {
		this.descricao = descricao;
	}
	
	public String toString() {
		return this.descricao;
	}
}
