package teste.basico;

import java.util.Date;

import modelo.basico.Pessoa;
import modelo.dao.PessoaDAO;

public class TesteIncluir {
public static void main(String[] args) {
	PessoaDAO dao = new PessoaDAO();
	dao.abrirTransacao();
	Pessoa p = new Pessoa();
	p.setNome("Maria");
	p.setDataNascimento(new Date());
	dao.incluir(p);
	dao.fecharTransacao().fechar();
	System.out.println(p.toString());
}
}
