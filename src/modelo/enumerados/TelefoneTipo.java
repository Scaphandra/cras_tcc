package modelo.enumerados;

public enum TelefoneTipo {
	FIXO("Fixo"), CELULAR("Celular"), RECADO("Recado");
	
	private String descricao;
	
	private TelefoneTipo(String descricao) {
		this.descricao = descricao;
	}
	
	public String toString() {
		return this.descricao;
	}
}	
