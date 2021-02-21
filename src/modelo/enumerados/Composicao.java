package modelo.enumerados;

public enum Composicao {

	 N("Não Informado"),FILHO("Filho(a)"), CONJUGE("Companheiro(a) ou Conjuge"), MAE("Mãe"), PAI("Pai"), TIO("Tio(a)"), SOBRINHO("Sobrinho(a)"), 
	CUNHADO("Cunhado(a)"), IRMAO("Irmão(ã)"), GENRO("Genro"), NORA("Nora"), PRIMO("Primo"), 
	AVO("Avô(ó)"), ENTEADO("Enteado(a)"), OUTRO("Outro"), RF("Responsável Familiar"), NETO("Neto(a)");
	
	private String descricao;
	
	private Composicao(String descricao) {
		this.descricao = descricao;
	}
	@Override
	public String toString() {
		return this.descricao;
	}
	
}
