package modelo.enumerados;

public enum VisitaTipo {
	INSTITUCIONAL("Institucional"), DOMICILIAR("Domiciliar");
	
private String descricao;
	
	private VisitaTipo(String descricao) {
		this.descricao = descricao;
	}
	
	public String toString() {
		return this.descricao;
	}
}
