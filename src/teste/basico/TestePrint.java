package teste.basico;

import java.util.Scanner;

public class TestePrint {
	public static void main(String[] args) {
		int menor = 1000;
		int vetor [] = new int[10];
		Scanner ler = new Scanner(System.in);
		System.out.println("Digite 10 n�meros aleat�rios");
		for(int i=0;i<10;i++) {
			int n = ler.nextInt();
			vetor[i] = n;
			if(n<menor) {
				menor = n;
			}
		}
		for(int i=0;i<10;i++) {
			System.out.println(vetor[i]);
		}
		System.out.println("O menor n�mero � "+menor);
	}
	
}
