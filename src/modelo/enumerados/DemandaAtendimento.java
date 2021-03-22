package modelo.enumerados;

public enum DemandaAtendimento {
	BE("Benef�cio Eventual"), CAD("Cad�nico"), CONSULTA("Consulta benef�cio"), INFO("Informa��o"), ENCAM("Encaminhamento"),
	ATENDIMENTO_DOMICILIO("Atendimento Domic�lio"),	CURSOS("Cursos"), ACOMPANHAMENTO("Acompanhamento"), 
	DOC("Acesso � Documenta��o Civil"), SCFV("Atendimento do SCFV"), BPC("BPC"), INSS("INSS");
	
private String descricao;
	
	private DemandaAtendimento(String descricao) {
		this.descricao = descricao;
	}
	
	public String toString() {
		return this.descricao;
	}
}
