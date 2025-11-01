import java.util.Scanner;
// import java.util.Dictionary;
// import java.util.Hashtable;

public class Sistema {
	static final int NUMERO_ASSENTOS = 46;

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
		// (Object[])
		onibusASCII = String.format(onibusASCII, ((Object[]) valoresAssentos));
		return onibusASCII;
	}

	static void imprimirAssentos(boolean[] assentos) {
		System.out.println();
		for (int i = 0; i < NUMERO_ASSENTOS; i++) {
			if (assentos[i]) {
				System.out.print((i + 1) + " ");
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
		String[] listaDeOnibus = { "onibus 1", "onibus 2", "onibus 3" };
		boolean[][] mapaDosOnibus = new boolean[listaDeOnibus.length][NUMERO_ASSENTOS];
		String[] listaDesenhosASCII = new String[listaDeOnibus.length];
		// String[] listaRotas = new String[listaDeOnibus.length];
		int escolhaDeAssento;
		int escolhaDeOnibus;
		char input;
		Scanner scanner = new Scanner(System.in);
		for (int i = 0; i < listaDeOnibus.length; i++) {
			listaDesenhosASCII[i] = formatarOnibusASCII(SistemaASCII.onibus, new boolean[NUMERO_ASSENTOS]);
			mapaDosOnibus[i] = new boolean[NUMERO_ASSENTOS];
			;
		}

		while (true) {
			System.out.print("Escolha seu ônibus: ");
			escolhaDeOnibus = scanner.nextInt() - 1;
			System.out.println(listaDesenhosASCII[escolhaDeOnibus]);
			System.out.print(String.format("Insira seu assento [1-%s]: ", NUMERO_ASSENTOS));
			escolhaDeAssento = scanner.nextInt() - 1;

			if (escolhaDeAssento < 0 || escolhaDeAssento > NUMERO_ASSENTOS - 1) {
				break;
			}
			if (!mapaDosOnibus[escolhaDeOnibus][escolhaDeAssento]) {
				System.out.println("Dando valor de true para " + escolhaDeOnibus);
				mapaDosOnibus[escolhaDeOnibus][escolhaDeAssento] = true;
			} else {
				System.out.print("\nAssento já ocupado. Deseja cancelar reserva? [S/N]: ");
				input = scanner.next().toUpperCase().charAt(0);
				if (input == 'S') {
					mapaDosOnibus[escolhaDeOnibus][escolhaDeAssento] = false;
				}

			}
			listaDesenhosASCII[escolhaDeOnibus] = formatarOnibusASCII(SistemaASCII.onibus,
					mapaDosOnibus[escolhaDeOnibus]);
		}

		System.out.println("\n\nSaindo");

		scanner.close();
	}
}

/*
 * Menu interativo
 * Visualizar rotas, horários e preços
 * OK: Reservar + mapa visual
 * OK: Cancelamento de reserva
 */