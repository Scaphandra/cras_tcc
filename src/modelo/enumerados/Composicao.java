package modelo.enumerados;

public enum Composicao {

	FILHO("Filho(a)"), MAE("Mãe"), PAI("Pai"), TIO("Tio(a)"), SOBRINHO("Sobrinho(a)"), 
	CUNHADO("Cunhado(a)"), IRMAO("Irmão(ã)"), GENRO("Genro"), NORA("Nora"), PRIMO("Primo"), 
	AVO("Avô(ó)"), ENTEADO("Enteado(a)"), OUTRO("Outro");
	
	private String descricao;
	
	private Composicao(String descricao) {
		this.descricao = descricao;
	}
	@Override
	public String toString() {
		return this.descricao;
	}
	
}
