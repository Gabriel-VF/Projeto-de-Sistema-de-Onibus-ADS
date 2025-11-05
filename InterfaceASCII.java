public class InterfaceASCII {
    final static char CHAR_VERTICAL = '│';
    final static char CHAR_HORIZONTAL = '─';
    final static char CHAR_CANTO_INFERIOR_ESQUERDO = '└';
    final static char CHAR_CANTO_INFERIOR_DIREITO = '┘';
    final static char CHAR_CANTO_SUPERIOR_ESQUERDO = '┌';
    final static char CHAR_CANTO_SUPERIOR_DIREITO = '┐';

    static String onibus =
           ("┌--──────────────--┐\n" +
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
            "└──────────────────┘"
    );

    private final static int LARGURA_MENSAGEM_BEM_VINDO = 51;
    static String mensagemBemVindo = (
        "┌─────────────────────────────────────────────────┐\n" +
        "│   Bem vindo ao sistema de reserva de ônibus!    │\n" +
        "└─────────────────────────────────────────────────┘\n"
    );

    // Espaço até a informação do ônibus
    private final static int OFFSET_ONIBUS_INFO = 17;
    static String onibusInfo = (
        "│  Ônibus   >    %s\n" +
        "│  Rota     >    %s\n" +
        "│  Horário  >    %s\n" +
        "│  Preço    >    %s\n"
    );

    static String repetirChar(char caractere, int numeroDeVezes) {
        String strAcumuladora = "";
        for (int i = 0; i < numeroDeVezes; i++) {
            strAcumuladora += caractere;
        }
        return strAcumuladora;
    }

    // Provavelmente overkill daqui pra baixo

    private static int definirLargura(Onibus[] listaDeOnibus) {
        int tamanhoLinhaHorizontal;
        int tamanhoMaior = 0;
        for (int i = 0; i < listaDeOnibus.length; i++) {
            tamanhoLinhaHorizontal = listaDeOnibus[i].rota.length() + OFFSET_ONIBUS_INFO;
            if (tamanhoLinhaHorizontal > tamanhoMaior) {
                tamanhoMaior = tamanhoLinhaHorizontal;
            }
        }
        for (int i = 0; i < listaDeOnibus.length; i++) {
            tamanhoLinhaHorizontal = listaDeOnibus[i].nome.length() + OFFSET_ONIBUS_INFO;
            if (tamanhoLinhaHorizontal > tamanhoMaior) {
                tamanhoMaior = tamanhoLinhaHorizontal;
            }
        }
        return tamanhoMaior;
    }

    private static String[] criarLinhaVerticais(Onibus onibus, final int LARGURA) {
        final int NUMERO_VALORES = 4;
        String[] offsets = new String[NUMERO_VALORES];
        String[] linhasV = new String[NUMERO_VALORES];
        int valorLength = String.format("%s", onibus.valor).length();

        // -1 por causa do CHAR_VERTICAL final que vai ser adicionado
        offsets[0] = repetirChar(' ', LARGURA - OFFSET_ONIBUS_INFO - onibus.nome.length() - 1);
        offsets[1] = repetirChar(' ', LARGURA - OFFSET_ONIBUS_INFO - onibus.rota.length() - 1);
        offsets[2] = repetirChar(' ', LARGURA - OFFSET_ONIBUS_INFO - onibus.horario.length() - 1);
        offsets[3] = repetirChar(' ', LARGURA - OFFSET_ONIBUS_INFO - valorLength - 3); // Por causa do "R$" e CHAR_VERTICAL
        for (int i = 0; i < NUMERO_VALORES; i++) {
            linhasV[i] = offsets[i] + CHAR_VERTICAL;
        }
        return linhasV;
    }

    static void imprimirOnibusInfo(Onibus[] listaDeOnibus) {
        System.out.println(InterfaceASCII.mensagemBemVindo);

        // Reajusta largura da interface de acordo com necessidade. Por padrão é a mesma largura da mensagem de bem-vindo
        int tamanhoLinhaHorizontal = definirLargura(listaDeOnibus);
        // -2 por causa dos cantos
        if (tamanhoLinhaHorizontal < LARGURA_MENSAGEM_BEM_VINDO - 2){
            tamanhoLinhaHorizontal = LARGURA_MENSAGEM_BEM_VINDO - 2;
        }
        String linhaHorizontal = repetirChar(CHAR_HORIZONTAL, tamanhoLinhaHorizontal);
        String[] linhasVerticais;

        for (int i = 0; i < listaDeOnibus.length; i++) {
            linhasVerticais = criarLinhaVerticais(listaDeOnibus[i], tamanhoLinhaHorizontal + 2);
            System.out.print(
                CHAR_CANTO_SUPERIOR_ESQUERDO + linhaHorizontal + CHAR_CANTO_SUPERIOR_DIREITO + "\n" + 
                    String.format(InterfaceASCII.onibusInfo,
                    listaDeOnibus[i].nome + linhasVerticais[0],
                    listaDeOnibus[i].rota + linhasVerticais[1],
                    listaDeOnibus[i].horario + linhasVerticais[2],
                    "R$" + listaDeOnibus[i].valor + linhasVerticais[3] + "\n" + 
                CHAR_CANTO_INFERIOR_ESQUERDO + linhaHorizontal + CHAR_CANTO_INFERIOR_DIREITO
            ));
        }
    }
}