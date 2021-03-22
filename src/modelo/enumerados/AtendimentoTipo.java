package modelo.enumerados;

public enum AtendimentoTipo {
	T("Técnico"), C("CadÚnico");
	
	private String descricao;
	
	private AtendimentoTipo(String descricao) {
		this.descricao = descricao;
	}
	
	public String toString() {
		return this.descricao;
	}
}	
