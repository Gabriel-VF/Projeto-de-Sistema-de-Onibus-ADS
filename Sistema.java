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

	public static void main(String[] args) {
		int pedidoAssento;
		char input;
		Scanner scanner = new Scanner(System.in);
		boolean[] assentos = new boolean[NUMERO_ASSENTOS];
		String onibus1ASCII = formatarOnibusASCII(SistemaASCII.onibus, assentos);

		while (contarVagasOcupadas(assentos) < NUMERO_ASSENTOS) {
			System.out.println(onibus1ASCII);
			System.out.print(String.format("Insira seu assento [1-%s]: ", NUMERO_ASSENTOS));
			pedidoAssento = scanner.nextInt() - 1;

			if (pedidoAssento < 0 || pedidoAssento > NUMERO_ASSENTOS - 1) {
				break;
			}
			if (!assentos[pedidoAssento]) {
				assentos[pedidoAssento] = true;
			} else {
				System.out.print("\nAssento já ocupado. Deseja cancelar reserva? [S/N]: ");
				input = scanner.next().toUpperCase().charAt(0);
				if (input == 'S') {
					assentos[pedidoAssento] = false;
				}

			}
			onibus1ASCII = formatarOnibusASCII(SistemaASCII.onibus, assentos);
		}
		System.out.println("\n\nSaindo");
		System.out.println(onibus1ASCII);

		scanner.close();
	}
}

/*
 * Menu interativo
 * Visualizar rotas, horários e preços
 * OK: Reservar + mapa visual
 * OK: Cancelamento de reserva
 */