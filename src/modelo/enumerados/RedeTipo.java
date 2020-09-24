package modelo.enumerados;

public enum RedeTipo {
	ESTATAL("Estatal"), NAO_ESTATAL("N�o Estatal"), PRIVADA("Privada");
	
	private String descricao;
	
	private RedeTipo(String descricao) {
		this.descricao = descricao;
	}
	
	public String toString() {
		return this.descricao;
	}
}
