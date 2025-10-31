import java.util.Scanner;

public class Sistema {
	static final int NUMERO_ASSENTOS = 46;

	static String repetirChar(char caractere, int nVezes) {
		String str = "";
		for (int i = 0; i < nVezes; i++) {
			str += caractere;
		}
		return str;
	}

	// Previne NullPointerException
	static boolean[] iniciarOnibusVazio(int numeroAssentos) {
		boolean[] falsos = new boolean[numeroAssentos];
		for (int i = 0; i < numeroAssentos; i++) {
			falsos[i] = false;
		}
		return falsos;
	}

	static boolean onibusTemVaga(boolean[] onibus) {
		int numeroAssentosOcupados = 0;
		for (int i = 0; i < onibus.length; i++) {
			if (onibus[i] == true) {
				numeroAssentosOcupados++;
			}
		}
		return numeroAssentosOcupados != NUMERO_ASSENTOS;
	}

	void imprimirOnibus(boolean[] onibus){
		
	}

	public static void main(String[] args) {
		int pedidoAssento = -1;
		Scanner scanner = new Scanner(System.in);
		boolean[] onibus = iniciarOnibusVazio(NUMERO_ASSENTOS);

		System.out.println(repetirChar('-', 20));
		while (onibusTemVaga(onibus)) {
			System.out.print("Insira seu assento: ");
			pedidoAssento = scanner.nextInt() - 1;
			if (onibus[pedidoAssento] == false) {
				onibus[pedidoAssento] = true;
			} else {
				System.out.println("\nAssento já ocupado!");
			}
		}
		System.out.println(repetirChar('-', 20));

		scanner.close();
	}
}
