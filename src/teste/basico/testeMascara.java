package teste.basico;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class testeMascara {
	public static void main(String[] args) {
		String valorFormatado = NumberFormat.getCurrencyInstance().format(12005.11);
		System.out.println(valorFormatado+"\n");
		
		String decimal = new DecimalFormat("#,##0.00").format(1200);
		System.out.println(decimal);
	}
}
