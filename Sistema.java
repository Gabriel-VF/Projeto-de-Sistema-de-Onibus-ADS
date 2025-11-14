import java.util.Scanner;

public class Sistema {
	final static char CHAR_VERTICAL = '│';
	final static char CHAR_HORIZONTAL = '─';
	final static char CHAR_CANTO_INFERIOR_ESQUERDO = '└';
	final static char CHAR_CANTO_INFERIOR_DIREITO = '┘';
	final static char CHAR_CANTO_SUPERIOR_ESQUERDO = '┌';
	final static char CHAR_CANTO_SUPERIOR_DIREITO = '┐';
	static final int NUMERO_ASSENTOS = 46;
	static final boolean[] LISTA_ASSENTOS_VAZIOS = new boolean[NUMERO_ASSENTOS];
	static String OnibusASCII = (
			"┌--──────────────--┐\n" +
			"│  o               |\n" +
			"│[%s][%s]..[%s][%s]│\n" +
			"|                  |\n" +
			"│[%s][%s]..[%s][%s]│\n" +
			"|                  |\n" +
			"│[%s][%s]..[%s][%s]│\n" +
			"|                  |\n" +
			"│[%s][%s]..[%s][%s]│\n" +
			"|                  |\n" +
			"│[%s][%s]..[%s][%s]│\n" +
			"|                  |\n" +
			"│[%s][%s]..[%s][%s]│\n" +
			"|                  |\n" +
			"│[%s][%s]..[%s][%s]│\n" +
			"|                  |\n" +
			"│[%s][%s]..[%s][%s]│\n" +
			"|                  |\n" +
			"│[%s][%s]..[%s][%s]│\n" +
			"|                  |\n" +
			"│[%s][%s]..[%s][%s]│\n" +
			"|                  |\n" +
			"│[%s][%s]..[%s][%s]│\n" +
			"|                  |\n" +
			"│[%s][%s]..[  WC  ]│\n" +
			"└──────────────────┘");

	// Cria o mapa ASCII do ônibus para imprimir
	static String mapaASCII(boolean[] assentos) {
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
		return String.format(OnibusASCII, ((Object[]) valoresAssentos));
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

	final static int LARGURA_MENSAGEM_BEM_VINDO = 51;
	static String mensagemBemVindo = (
			"┌─────────────────────────────────────────────────┐\n" +
			"│   Bem vindo ao sistema de reserva de ônibus!    │\n" +
			"└─────────────────────────────────────────────────┘\n");

	// Espaço até a informação do ônibus
	final static int OFFSET_ONIBUS_INFO = 17;
	static String onibusInfo = (
			"│  Ônibus   >    %s\n" +
			"│  Rota     >    %s\n" +
			"│  Horário  >    %s\n" +
			"│  Preço    >    %s\n");

	static String repetirChar(char caractere, int numeroDeVezes) {
		String strAcumuladora = "";
		for (int i = 0; i < numeroDeVezes; i++) {
			strAcumuladora += caractere;
		}
		return strAcumuladora;
	}

	// Define largura dependendo do tamanho do nome ou tamanho da rota
	static int definirLargura(String[] listaRotas, String[] listaNomes) {
		int tamanhoLinhaHorizontal;
		int tamanhoMaior = 0;
		for (int i = 0; i < listaRotas.length; i++) {
			tamanhoLinhaHorizontal = listaNomes[i].length() + OFFSET_ONIBUS_INFO;
			if (tamanhoLinhaHorizontal > tamanhoMaior) {
				tamanhoMaior = tamanhoLinhaHorizontal;
			}
		}
		for (int i = 0; i < listaRotas.length; i++) {
			tamanhoLinhaHorizontal = listaRotas[i].length() + OFFSET_ONIBUS_INFO;
			if (tamanhoLinhaHorizontal > tamanhoMaior) {
				tamanhoMaior = tamanhoLinhaHorizontal;
			}
		}
		return tamanhoMaior;
	}

	// Adiciona o caractere vertical (|) na direita do menu
	static String[] criarLinhaVerticais(String nome, String rota, String horario, double valor,
			final int LARGURA) {
		final int NUMERO_VALORES = 4;
		String[] offset = new String[NUMERO_VALORES];
		String[] linhasV = new String[NUMERO_VALORES];
		int valorLength = String.format("%s", valor).length();

		// -1 por causa do CHAR_VERTICAL final que vai ser adicionado
		offset[0] = repetirChar(' ', LARGURA - OFFSET_ONIBUS_INFO - nome.length() - 1);
		offset[1] = repetirChar(' ', LARGURA - OFFSET_ONIBUS_INFO - rota.length() - 1);
		offset[2] = repetirChar(' ', LARGURA - OFFSET_ONIBUS_INFO - horario.length() - 1);
		offset[3] = repetirChar(' ', LARGURA - OFFSET_ONIBUS_INFO - valorLength - 3); // Por causa do "R$" e CHAR_VERTICAL
		for (int i = 0; i < NUMERO_VALORES; i++) {
			linhasV[i] = offset[i] + CHAR_VERTICAL;
		}
		return linhasV;
	}

	// Imprime as opções de ônibus e mensagem de bem-vindo
	static void imprimirOnibusInfo(String[] listaNomes, String[] listaRotas, String[] listaHorarios,
			double[] listaValores) {
		System.out.println(mensagemBemVindo);

		// Reajusta largura da interface de acordo com necessidade. Por padrão é a mesma
		// largura da mensagem de bem-vindo
		int tamanhoLinhaHorizontal = definirLargura(listaRotas, listaNomes);
		// -2 por causa dos cantos
		if (tamanhoLinhaHorizontal < LARGURA_MENSAGEM_BEM_VINDO - 2) {
			tamanhoLinhaHorizontal = LARGURA_MENSAGEM_BEM_VINDO - 2;
		}
		String linhaHorizontal = repetirChar(CHAR_HORIZONTAL, tamanhoLinhaHorizontal);
		String[] linhasVerticais;

		for (int i = 0; i < listaNomes.length; i++) {
			linhasVerticais = criarLinhaVerticais(listaNomes[i], listaRotas[i], listaHorarios[i], listaValores[i],
					tamanhoLinhaHorizontal + 2);
			System.out.print(
					CHAR_CANTO_SUPERIOR_ESQUERDO + linhaHorizontal + CHAR_CANTO_SUPERIOR_DIREITO + "\n" +
					String.format(onibusInfo,
						listaNomes[i] + linhasVerticais[0],
						listaRotas[i] + linhasVerticais[1],
						listaHorarios[i] + linhasVerticais[2],
						"R$" + listaValores[i] + linhasVerticais[3] + "\n" +
					CHAR_CANTO_INFERIOR_ESQUERDO + linhaHorizontal + CHAR_CANTO_INFERIOR_DIREITO
					));
		}
	}

	public static void main(String[] args) {
		String[] listaNomes = { "onibus 1", "onibus 2", "onibus 3" };
		boolean[][] mapasDeAssentos = new boolean[listaNomes.length][NUMERO_ASSENTOS]; // mapasDeAssentos[índice do ônibus][assento]
		String[] listaDesenhosASCII = new String[listaNomes.length];
		String[] listaRotas = new String[listaNomes.length];
		String[] listaHorarios = new String[listaNomes.length];
		double[] listaValores = new double[listaNomes.length];

		int escolhaDeAssento;
		int escolhaDeOnibus;
		char input;
		Scanner scanner = new Scanner(System.in);

		// Construíndo os ônibus
		for (int i = 0; i < listaNomes.length; i++) {
			listaDesenhosASCII[i] = mapaASCII(new boolean[NUMERO_ASSENTOS]);
			mapasDeAssentos[i] = new boolean[NUMERO_ASSENTOS];
		}
		listaRotas[0] = "Tauá -> Picos";
		listaRotas[1] = "Tauá -> Fortaleza";
		listaRotas[2] = "Tauá -> Juazeiro do Norte";
		listaHorarios[0] = "16:30 -> 22:00";
		listaHorarios[1] = "17:00 -> 00:00";
		listaHorarios[2] = "19:00 -> 22:00";
		listaValores[0] = 100.5;
		listaValores[1] = 80.5;
		listaValores[2] = 110.2;

		while (true) {
			imprimirOnibusInfo(listaNomes, listaRotas, listaHorarios, listaValores);
			System.out.print(String.format("Escolha seu ônibus [1-%s]: ", listaNomes.length));
			escolhaDeOnibus = scanner.nextInt() - 1;
			if (escolhaDeOnibus < 0 || escolhaDeOnibus > listaNomes.length - 1) {
				break;
			}
			System.out.println(listaDesenhosASCII[escolhaDeOnibus]);
			System.out.print(String.format("Insira seu assento [1-%s]: ", NUMERO_ASSENTOS));
			escolhaDeAssento = scanner.nextInt() - 1;
			if (escolhaDeAssento < 0 || escolhaDeAssento > NUMERO_ASSENTOS - 1) {
				break;
			}
			if (!mapasDeAssentos[escolhaDeOnibus][escolhaDeAssento]) {
				mapasDeAssentos[escolhaDeOnibus][escolhaDeAssento] = true;
			} else {
				System.out.print("\nAssento já ocupado. Deseja cancelar reserva? [S/N]: ");
				input = scanner.next().toUpperCase().charAt(0);
				if (input == 'S') {
					mapasDeAssentos[escolhaDeOnibus][escolhaDeAssento] = false;
				}

			}
			listaDesenhosASCII[escolhaDeOnibus] = mapaASCII(mapasDeAssentos[escolhaDeOnibus]);
		}

		System.out.println("\n\nSaindo");

		scanner.close();
	}
}