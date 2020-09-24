package modelo.enumerados;

public enum BEventualTipo {
	ALIMENTAR("Alimentar"), PASSAGEM("Passagem"), ISENCAODOCS("Isenção de Documentos"), ALUGUELSOCIAL("Aluguel Social"),
	MATERIALCONSTRUCAO("Material de Construção"), ISENCAOLUZAGUA("Isenção Luz e/ou Água"), AUXILIOGAS("Auxílio Gás"), ROUPAS("Roupas/Vestimentas"), 
	MOVEISELETRODOMESTICOS("Móveis e Eletrodomésticos"), OUTROS("Outros");
	
private String descricao;
	
	private BEventualTipo(String descricao) {
		this.descricao = descricao;
	}
	
	public String toString() {
		return this.descricao;
	}
}
