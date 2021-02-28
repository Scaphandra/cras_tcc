package modelo.basico;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//Classe ainda é bean, será incluída após melhorias no sistema
public class Agenda {

	
	private Long id;
	
	private List<Familia> familias = new ArrayList<>();	
	
	private Date previsao;
	
	private int tamanho;
	
	public Agenda() {
		
	}

	public List<Familia> getFamilias() {
		return familias;
	}

	public void setFamilias(List<Familia> familias) {
		this.familias = familias;
	}

	public Date getPrevisao() {
		return previsao;
	}

	public void setPrevisao(Date previsao) {
		this.previsao = previsao;
	}

	public int getTamanho() {
		return tamanho;
	}

	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}
	
	
}
