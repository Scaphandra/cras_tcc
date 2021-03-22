package modelo.enumerados;

public enum AtendimentoTipo {
	T("T�cnico"), C("Cad�nico");
	
	private String descricao;
	
	private AtendimentoTipo(String descricao) {
		this.descricao = descricao;
	}
	
	public String toString() {
		return this.descricao;
	}
}	
