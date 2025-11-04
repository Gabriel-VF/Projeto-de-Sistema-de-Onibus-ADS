import java.util.Scanner;
// import java.util.Dictionary;
// import java.util.Hashtable;

public class Sistema {
	static final int NUMERO_ASSENTOS = Onibus.NUMERO_ASSENTOS;

	public static void main(String[] args) {
		Onibus onibus_1 = new Onibus("O1", "Tauá - Picos", new boolean[NUMERO_ASSENTOS]);
		Onibus onibus_2 = new Onibus("O2", "Tauá x Fortaleza", new boolean[NUMERO_ASSENTOS]);
		Onibus onibus_3 = new Onibus("O3", "Tauá x Juazeiro do Norte", new boolean[NUMERO_ASSENTOS]);
		// No futuro talvez seja um dicionário com chave sendo o nome do ônibus:
		Onibus[] listaDeOnibus = { onibus_1, onibus_2, onibus_3 };
		int escolhaAssento;
		int escolhaOnibus;
		char input;
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.print(String.format("Escolha seu ônibus [1-%s]: ", listaDeOnibus.length));
			escolhaOnibus = scanner.nextInt() - 1;
			System.out.println(listaDeOnibus[escolhaOnibus].construirMapaASCII());
			System.out.print(String.format("Insira seu assento [1-%s]: ", NUMERO_ASSENTOS));
			escolhaAssento = scanner.nextInt() - 1;

			if (escolhaAssento < 0 || escolhaAssento > NUMERO_ASSENTOS - 1) {
				break;
			}
			if (!listaDeOnibus[escolhaOnibus].assentos[escolhaAssento]) { // Se assento não está ocupado
				listaDeOnibus[escolhaOnibus].assentos[escolhaAssento] = true;
			} else {
				System.out.print("\nAssento já ocupado. Deseja cancelar reserva? [S/N]: ");
				input = scanner.next().toUpperCase().charAt(0);
				if (input == 'S') {
					listaDeOnibus[escolhaOnibus].assentos[escolhaAssento] = false;
				}

			}
		}

		System.out.println("\n\nSaindo");

		scanner.close();
	}
}