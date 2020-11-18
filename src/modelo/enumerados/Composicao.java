package modelo.enumerados;

public enum Composicao {

	FILHO("Filho(a)"), MAE("M�e"), PAI("Pai"), TIO("Tio(a)"), SOBRINHO("Sobrinho(a)"), 
	CUNHADO("Cunhado(a)"), IRMAO("Irm�o(�)"), GENRO("Genro"), NORA("Nora"), PRIMO("Primo"), 
	AVO("Av�(�)"), ENTEADO("Enteado(a)"), OUTRO("Outro");
	
	private String descricao;
	
	private Composicao(String descricao) {
		this.descricao = descricao;
	}
	@Override
	public String toString() {
		return this.descricao;
	}
	
}
