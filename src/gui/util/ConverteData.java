package gui.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class ConverteData {

	SimpleDateFormat formatBra =  new SimpleDateFormat("dd/MM/yyyy");
	
	public String mostrar(LocalDate dt) {
		
		if (dt == null) {
			return "";
		}
		return formatBra.format(dt).toString();
	}
}
