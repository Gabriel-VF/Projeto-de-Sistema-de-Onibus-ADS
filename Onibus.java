public class Onibus {
	private final String formatoASCII = InterfaceASCII.onibus;

	String nome;
	String rota;
	String horario;
	double valor;
	boolean[] assentos;
	static final int NUMERO_ASSENTOS = 46;

	// Construtor
	Onibus(String nome, String rota, double valor, String horario, boolean[] assentos) {
		this.nome = nome;
		this.rota = rota;
		this.assentos = assentos;
		this.horario = horario;
		this.valor = valor;
	}

	// Troca posição dos valores da terceira e quarta fila
	private String[] trocarStrings(String[] listaStrings) {
		String temp;
		for (int i = 2; i < NUMERO_ASSENTOS - 1; i += 4) {
			temp = listaStrings[i + 1];
			listaStrings[i + 1] = listaStrings[i];
			listaStrings[i] = temp;
		}
		return listaStrings;
	}

	String mapaASCII() {
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
		return String.format(formatoASCII, ((Object[]) valoresAssentos));
	}
}
