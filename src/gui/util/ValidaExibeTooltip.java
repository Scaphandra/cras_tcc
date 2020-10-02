package gui.util;



import java.lang.reflect.Field;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;

public class ValidaExibeTooltip {
//adicionar e remover os estilos da borda
	
	public static void adicionarCorBorda(Node n, Tooltip t) {
		//t é a mensagem
		Tooltip.install(n, t);
		n.setStyle("-fx-border-color: #00CED1;");
	}
	public static void removerCorBorda(Node n, Tooltip t) {
		//t é a mensagem
		Tooltip.uninstall(n, t);
		n.setStyle(null);
	}
	
	public static void mensagem(Node n, Tooltip t) {
		Tooltip.install(n,t);
	}
	
	//exibição do tooltip(duração e comportamento)
	
	public static void tempoT(Tooltip t) {
		try {
			Field fieldBehavior = t.getClass().getDeclaredField("BEHAVIOR");
			fieldBehavior.setAccessible(true);
			Object objBehavior = fieldBehavior.get(t);
			
			Field fieldTimer = objBehavior.getClass().getDeclaredField("activationTimer");
			fieldTimer.setAccessible(true);
			Timeline objTimer = (Timeline) fieldBehavior.get(objBehavior);
			
			objTimer.getKeyFrames().clear();
			objTimer.getKeyFrames().add(new KeyFrame(new Duration(5)));
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
