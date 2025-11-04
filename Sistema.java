import java.util.Scanner;
// import java.util.Dictionary;
// import java.util.Hashtable;

public class Sistema {
	static final int NUMERO_ASSENTOS = Onibus.NUMERO_ASSENTOS;
	static final boolean[] LISTA_ASSENTOS_VAZIOS = new boolean[NUMERO_ASSENTOS];

	public static void main(String[] args) {
		Onibus onibus_1 = new Onibus("O1", "Tauá -> Picos", 60.5, "16:30 -> 22:00", LISTA_ASSENTOS_VAZIOS.clone());
		Onibus onibus_2 = new Onibus("O2", "Tauá -> Fortaleza", 80.9, "17:00 -> 00:00", LISTA_ASSENTOS_VAZIOS.clone());
		Onibus onibus_3 = new Onibus("O3", "Tauá -> Juazeiro do Norte", 70.2, "19:00 -> 22:00", LISTA_ASSENTOS_VAZIOS.clone());
		
		// No futuro talvez seja um dicionário com chave sendo o nome do ônibus:
		Onibus[] listaDeOnibus = { onibus_1, onibus_2, onibus_3 };
		int escolhaAssento;
		int escolhaOnibus;
		char input;
		Scanner scanner = new Scanner(System.in);

		while (true) {
			InterfaceASCII.imprimirOnibusInfo(listaDeOnibus);
			System.out.print(String.format("Escolha seu ônibus [1-%s] ou digite 0 para sair: ", listaDeOnibus.length));
			escolhaOnibus = scanner.nextInt() - 1;
			if (escolhaOnibus < 0 || escolhaOnibus > listaDeOnibus.length) {
				System.out.println("Saindo...");
				break;
			}
			System.out.println(listaDeOnibus[escolhaOnibus].construirMapaASCII());
			System.out.print(String.format("Insira seu assento [1-%s] ou digite 0 para sair: ", NUMERO_ASSENTOS));
			escolhaAssento = scanner.nextInt() - 1;
			if (escolhaAssento < 0 || escolhaAssento > NUMERO_ASSENTOS - 1) {
				System.out.println("Saindo...");
				break;
			}

			if (!listaDeOnibus[escolhaOnibus].assentos[escolhaAssento]) { // Se assento não está ocupado
				listaDeOnibus[escolhaOnibus].assentos[escolhaAssento] = true;
				System.out.println("===== Boa viagem! =====");
			} else {
				System.out.print("\nAssento já ocupado. Deseja cancelar reserva ou tentar novamente? [S/N]: ");
				input = scanner.next().toUpperCase().charAt(0);
				if (input == 'S') {
					listaDeOnibus[escolhaOnibus].assentos[escolhaAssento] = false;
					System.out.println("===== Reserva cancelada =====");
				}

			}
			System.out.println("\n\n\n\n");

		}

		scanner.close();
	}
}