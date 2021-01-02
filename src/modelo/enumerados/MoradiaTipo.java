package modelo.enumerados;

public enum MoradiaTipo {

	P("Pr�pria"), A("Alugada"), C("Cedida"), S("Aluguel Social"),O("Outros");
	
private String descricao;
	
	private MoradiaTipo(String descricao) {
		this.descricao = descricao;
	}
	
	public String toString() {
		return this.descricao;
	}

	
}
