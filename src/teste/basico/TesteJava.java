package teste.basico;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import modelo.basico.PesReferencia;

public class TesteJava {

	private static int diaHoje;
	private static int mesHoje;
	private static int anoHoje;

	public static void main(String[] args) {
		

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		String dataStr = "13/11/1979";
		Date data = new Date();
		try {
			data = formato.parse(dataStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		int dia = cal.get(Calendar.DAY_OF_MONTH);
		int mes = cal.get(Calendar.MONTH)+1;
		int ano = cal.get(Calendar.YEAR);
		
		
		//hoje.setTime(d);
		
		diaHoje = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		//o mês entra como um vetor de 12 posições incluindo o 0, por isso precisamos somar com 1
		mesHoje = Calendar.getInstance().get(Calendar.MONTH)+1;
		anoHoje = Calendar.getInstance().get(Calendar.YEAR);
		
		//cálculo da idade
		
		int idade = anoHoje - ano;
		
		if(mesHoje == mes) {
			
		
			
			if(diaHoje < dia) {
				
			
				
				idade -= 1;
			}
		}
		
		else if(mesHoje < mes) {
			
			System.out.println(idade + "mesHoje>mes");
			
			idade -= 1;
		}
		
		PesReferencia pes1 = new PesReferencia();
		
		if(pes1 instanceof PesReferencia) {
			System.out.println("é da classe");
		}else {
			System.out.println("Não é");
		}
		
	
	}
}
