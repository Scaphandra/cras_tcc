package modelo.enumerados;

public enum PessoaEstado {
	RF("Pessoa de Referência"), P ("Pessoa da Composição Familiar");
	
	private String descricao;
	
	PessoaEstado(String descricao){
		this.descricao = descricao;
	}
	
	public String toString() {
		return descricao;
	}
}
