import java.util.Scanner;

public class Sistema {
	static final int NUMERO_ASSENTOS = 38;

	static String repetirChar(char caractere, int nVezes) {
		String str = "";
		for (int i = 0; i < nVezes; i++) {
			str += caractere;
		}
		return str;
	}

	static String repetirString(String str, int nVezes) {
		String strAcumuladora = "";
		for (int i = 0; i < nVezes; i++) {
			strAcumuladora += str;
		}
		return strAcumuladora;
	}

	static int contarVagasOcupadas(boolean[] onibus) {
		int numeroAssentosOcupados = 0;
		for (int i = 0; i < onibus.length; i++) {
			if (onibus[i] == true) {
				numeroAssentosOcupados++;
			}
		}
		return numeroAssentosOcupados;
	}

	// Troca posição dos valores da terceira e quarta fila
	static String[] trocarStrings(String[] listaStrings) {
		String temp;
		for (int i = 2; i < NUMERO_ASSENTOS - 1; i += 4) {
			temp = listaStrings[i + 1];
			listaStrings[i + 1] = listaStrings[i];
			listaStrings[i] = temp;
		}
		return listaStrings;
	}

	static String formatarOnibusASCII(String onibusASCII, boolean[] assentos) {
		String[] valoresAssentos = new String[NUMERO_ASSENTOS];
		for (int i = 0; i < NUMERO_ASSENTOS; i++) {
			if (assentos[i] == true) {
				valoresAssentos[i] = "--";
				continue;
			}
			valoresAssentos[i] = String.format("%0,2d", i + 1);
		}
		valoresAssentos = trocarStrings(valoresAssentos);

		// Se não fazer casting pra (Object[]) o compiler manda um aviso. Ignore o
		// (Object)
		onibusASCII = String.format(onibusASCII, ((Object[]) valoresAssentos));
		return onibusASCII;
	}

	static String strAssentosDesocupados(boolean[] onibus) {
		String assentosDesocupados = "";
		for (int i = 0; i < NUMERO_ASSENTOS - 1; i++) {
			if (!onibus[i]) {
				assentosDesocupados += (i + 1) + " ";
			}
		}
		if (!onibus[NUMERO_ASSENTOS - 1]) {
			assentosDesocupados += (NUMERO_ASSENTOS);
		}
		return "Opções: " + assentosDesocupados;
	}

	public static void main(String[] args) {
		int pedidoAssento;
		Scanner scanner = new Scanner(System.in);
		boolean[] onibus = new boolean[NUMERO_ASSENTOS];
		String onibusASCII = SistemaASCII.onibusStr;

		System.out.println(repetirChar('-', 20));
		while (contarVagasOcupadas(onibus) < NUMERO_ASSENTOS) {
			System.out.println("\n" + strAssentosDesocupados(onibus));
			System.out.print("Insira seu assento [1-38]: ");
			pedidoAssento = scanner.nextInt() - 1;
			if (pedidoAssento < 0 || pedidoAssento > NUMERO_ASSENTOS - 1) {
				break;
			}
			if (!onibus[pedidoAssento]) {
				onibus[pedidoAssento] = true;
			} else {
				System.out.println("\nAssento já ocupado.");
			}
		}
		System.out.println(repetirChar('-', 20));
		System.out.println("\nSaindo");

		onibusASCII = formatarOnibusASCII(onibusASCII, onibus);
		System.out.println(onibusASCII);

		scanner.close();
	}
}
