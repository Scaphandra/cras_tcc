package modelo.enumerados;

public enum DemandaAtendimento {
	BE("Benefício Eventual"), CAD("CadÚnico"), CONSULTA("Consulta benefício"), INFO("Informação"), ENCAM("Encaminhamento"),
	ATENDIMENTO_DOMICILIO("Atendimento Domicílio"),	CURSOS("Cursos"), ACOMPANHAMENTO("Acompanhamento"), 
	DOC("Acesso à Documentação Civil"), SCFV("Atendimento do SCFV"), BPC("BPC"), INSS("INSS");
	
private String descricao;
	
	private DemandaAtendimento(String descricao) {
		this.descricao = descricao;
	}
	
	public String toString() {
		return this.descricao;
	}
}
