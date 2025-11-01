import java.util.Scanner;

public class Sistema {
	static final int NUMERO_ASSENTOS = 38; // 38

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

	// Previne NullPointerException
	static boolean[] iniciarOnibusVazio(int numeroAssentos) {
		boolean[] falsos = new boolean[numeroAssentos];
		for (int i = 0; i < numeroAssentos; i++) {
			falsos[i] = false;
		}
		return falsos;
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

	static String[] trocarStrings(String[] listaStrings) {
		String temp;
		for (int i = 2; i < NUMERO_ASSENTOS - 1; i += 4) {
			temp = listaStrings[i + 1];
			listaStrings[i + 1] = listaStrings[i];
			listaStrings[i] = temp;
		}
		return listaStrings;
	}

	static String formatarOnibusStr(String onibusStr, boolean[] assentos) {
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
		onibusStr = String.format(onibusStr, ((Object[]) valoresAssentos));
		return onibusStr;
	}
	public static void main(String[] args) {
		int pedidoAssento = 100;
		Scanner scanner = new Scanner(System.in);
		boolean[] onibus = iniciarOnibusVazio(NUMERO_ASSENTOS);
		String onibusStr = SistemaASCII.onibusStr;

		System.out.println(repetirChar('-', 20));
		while (contarVagasOcupadas(onibus) < NUMERO_ASSENTOS) {
			System.out.print("Insira seu assento [1-38]: ");
			pedidoAssento = scanner.nextInt() - 1;
			if (pedidoAssento < 0) {
				break;
			}
			if (onibus[pedidoAssento] == false) {
				onibus[pedidoAssento] = true;
			} else {
				System.out.println("\nAssento já ocupado!");
			}
		}
		System.out.println(repetirChar('-', 20));
		System.out.println("\nSaindo");
		onibusStr = formatarOnibusStr(onibusStr, onibus);
		System.out.println(String.format(onibusStr, 1));

		scanner.close();
	}
}
