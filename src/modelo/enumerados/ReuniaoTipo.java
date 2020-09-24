package modelo.enumerados;

public enum ReuniaoTipo {
	TECNICA("Tecnica"), SCFV("SCFV"), APOIO("Apoio"), GERAL("Geral"), EXTERNA("Externa");
	
	private String descricao;
	
	private ReuniaoTipo(String descricao) {
		this.descricao = descricao;
	}
	
	public String toString() {
		return this.descricao;
	}
}
