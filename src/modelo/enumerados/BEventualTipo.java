package modelo.enumerados;

public enum BEventualTipo {
	ALIMENTAR("Alimentar"), PASSAGEM("Passagem"), ISENCAODOCS("Isen��o de Documentos"), ALUGUELSOCIAL("Aluguel Social"),
	MATERIALCONSTRUCAO("Material de Constru��o"), ISENCAOLUZAGUA("Isen��o Luz e/ou �gua"), AUXILIOGAS("Aux�lio G�s"), ROUPAS("Roupas/Vestimentas"), 
	MOVEISELETRODOMESTICOS("M�veis e Eletrodom�sticos"), OUTROS("Outros");
	
private String descricao;
	
	private BEventualTipo(String descricao) {
		this.descricao = descricao;
	}
	
	public String toString() {
		return this.descricao;
	}
}
