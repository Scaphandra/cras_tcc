package modelo.enumerados;

public enum EncaminhamentoTipo {

	BPC("Acesso ao BPC"), CAD("CAD �nico"), MEDIA("Prote��o Especial M�dia Complexidade"), 
	ALTA("Prote��o Especial Alta Complexidade"), OSC("Organiza��es da Sociedade Civil"),
	EDU("Educa��o"), REDE("Rede Socioassistencial"), SAU("Sa�de"), OUTRAS("Outras Pol�ticas"), 
	EVENTUAL("Benef�cios Eventuais"), ACESSUAS("ACESSUAS"),	TARIFA("Tarifa Social"), 
	CARTEIRA("Carteira Interistadual do Idoso"), ISENCAO("Isen��o de Taxa de Concurso P�blico"),
	JOVEM("ID Jovem"), DESCINSS("Descontos INSS"), NV("Programa Nova Vida"), PASSE("Passe Social Municipal"),
	SCFV("Servi�o de Convi�ncia e Fortalecimento de V�nculos"), CEAM("CEAM");
	
	private String descricao;
	
	private EncaminhamentoTipo(String descricao) {
		this.descricao = descricao;
	}
	
	public String toString() {
		return this.descricao;
	}
	
}
