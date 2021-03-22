package modelo.enumerados;

public enum EncaminhamentoTipo {

	BPC("Acesso ao BPC"), CAD("CAD Único"), MEDIA("Proteção Especial Média Complexidade"), 
	ALTA("Proteção Especial Alta Complexidade"), OSC("Organizações da Sociedade Civil"),
	EDU("Educação"), REDE("Rede Socioassistencial"), SAU("Saúde"), OUTRAS("Outras Políticas"), 
	EVENTUAL("Benefícios Eventuais"), ACESSUAS("ACESSUAS"),	TARIFA("Tarifa Social"), 
	CARTEIRA("Carteira Interistadual do Idoso"), ISENCAO("Isenção de Taxa de Concurso Público"),
	JOVEM("ID Jovem"), DESCINSS("Descontos INSS"), NV("Programa Nova Vida"), PASSE("Passe Social Municipal"),
	SCFV("Serviço de Conviência e Fortalecimento de Vínculos"), CEAM("CEAM");
	
	private String descricao;
	
	private EncaminhamentoTipo(String descricao) {
		this.descricao = descricao;
	}
	
	public String toString() {
		return this.descricao;
	}
	
}
