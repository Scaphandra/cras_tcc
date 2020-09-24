package modelo.enumerados;

public enum CorRaca {
	PRETA("Preto"), BRANCA("Branco"), PARDA("Pardo"), INDIGENA("Ind�gena"), AMARELA("Amarelo"), NAODECLARADA("N�o declarado");
	
private String descricao;
	
	private CorRaca(String descricao) {
		this.descricao = descricao;
	}
	
	public String toString() {
		return this.descricao;
	}
}
