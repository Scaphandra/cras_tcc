package modelo.enumerados;

public enum SituacaoFamilia {
	REF("Referenciada"), ACOMP("Acompanhada"), ATEND("Atendida");
	
	private String descricao;
	
	private SituacaoFamilia(String descricao) {
		this.descricao = descricao;
	}
	
	public String toString() {
		return this.descricao;
	}
}
