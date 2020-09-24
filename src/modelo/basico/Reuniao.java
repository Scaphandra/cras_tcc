package modelo.basico;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import modelo.enumerados.ReuniaoTipo;

@Entity
@Table(name="reunioes")
public class Reuniao {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_reuniao;
	
	@Enumerated(EnumType.STRING)
	private ReuniaoTipo tipo_reuniao;
	
	private String motivo_reuniao;
	
	@ManyToMany(mappedBy="reunioes_funcionario")
	private List<Funcionario> funcionarios_reuniao = new ArrayList<>();
	
	@Temporal(TemporalType.DATE)
	private Date data_reuniao;
	
	//TODO esse campo precisa estar disponível na interface gráfica com espaço como campo de texto longo
	private String ata;
	
	public Reuniao() {
		
	}

	public Long getId_reuniao() {
		return id_reuniao;
	}

	public void setId_reuniao(Long id) {
		this.id_reuniao = id;
	}
	
	@Enumerated(EnumType.STRING)
	public ReuniaoTipo getTipo_reuniao() {
		return tipo_reuniao;
	}

	public void setTipo_reuniao(ReuniaoTipo tipo) {
		this.tipo_reuniao = tipo;
	}

	public String getMotivo_reuniao() {
		return motivo_reuniao;
	}

	public void setMotivo_reuniao(String motivo) {
		this.motivo_reuniao = motivo;
	}

	public List<Funcionario> getFuncionarios_reuniao() {
		return funcionarios_reuniao;
	}

	public void setFuncionarios_reuniao(Funcionario funcionario) {
		this.funcionarios_reuniao.add(funcionario);
	}

	public Date getData_reuniao() {
		return data_reuniao;
	}

	public void setData_reuniao(Date data) {
		this.data_reuniao = data;
	}

	public String getAta() {
		return ata;
	}

	public void setAta(String ata) {
		this.ata = ata;
	}

	public Reuniao(ReuniaoTipo tipo, String motivo, List<Funcionario> funcionarios, Date data, String ata) {
		super();
		this.tipo_reuniao = tipo;
		this.motivo_reuniao = motivo;
		this.funcionarios_reuniao = funcionarios;
		this.data_reuniao = data;
		this.ata = ata;
	}

	@Override
	public String toString() {
		return "Reuniao [id_reuniao=" + id_reuniao + ", tipo_reuniao=" + tipo_reuniao.toString() + "]";
	}
	
	
}
