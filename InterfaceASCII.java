public class InterfaceASCII {
    final static char CHAR_VERTICAL = '│';
    final static char CHAR_HORIZONTAL = '─';
    final static char CHAR_CANTO_INFERIOR_ESQUERDO = '└';
    final static char CHAR_CANTO_INFERIOR_DIREITO = '┘';
    final static char CHAR_CANTO_SUPERIOR_ESQUERDO = '┌';
    final static char CHAR_CANTO_SUPERIOR_DIREITO = '┐';
    final static char LINHA_VERTICAL = '│';
    final static char LINHA_HORIZONTAL = '─';
/*
 * Linha vertical: │
 * Linha horizontal : ─
 */

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

    static String mensagemBemVindo = (
        "┌─────────────────────────────────────────────────┐\n" +
        "│   Bem vindo ao sistema de reserva de ônibus!    │\n" +
        "└─────────────────────────────────────────────────┘\n"
    );

    final static int OFFSET_ONIBUS_INFO = 19;
    static String onibusInfo = (
        "│  Ônibus   >    %s\n" +
        "│  Rota     >    %s\n" +
        "│  Horário  >    %s\n" +
        "│  Preço    >    %s\n"
    );

    static String repetirChar(char cacactere, int numeroDeVezes) {
        String strAcumuladora = "";
        for (int i = 0; i < numeroDeVezes; i++) {
            strAcumuladora += cacactere;
        }
        return strAcumuladora;
    }

    // Provavelmente overkill daqui pra baixo

    // Define o tamanho da linha horizontal da interface dependendo da informação
    // maior (rota ou nome)
    // 2 caracteres de tamanho são excluídos para colocar os cantos
    private static int pegarTamanhoLinhaHorizontal(Onibus[] listaDeOnibus) {
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
        return tamanhoMaior - 2;
    }

    private static String[] criarLinhaVerticais(Onibus onibus, final int LARGURA) {
        final int NUMERO_INFO = 4;
        String[] offsets = new String[NUMERO_INFO];
        String[] linhasV = new String[NUMERO_INFO];
        int valorLength = String.format("%s", onibus.valor).length();
        offsets[0] = repetirChar(' ', LARGURA - OFFSET_ONIBUS_INFO - onibus.nome.length() + 1);
        offsets[1] = repetirChar(' ', LARGURA - OFFSET_ONIBUS_INFO - onibus.rota.length() + 1);
        offsets[2] = repetirChar(' ', LARGURA - OFFSET_ONIBUS_INFO - onibus.horario.length() + 1);
        offsets[3] = repetirChar(' ', LARGURA - OFFSET_ONIBUS_INFO - valorLength - 1); // Por causa do R$
        for (int i = 0; i < NUMERO_INFO; i++) {
            linhasV[i] = offsets[i] + "│";
        }
        return linhasV;
    }

    static void imprimirOnibusInfo(Onibus[] listaDeOnibus) {
        System.out.println(InterfaceASCII.mensagemBemVindo);
        int tamanhoLinhaHorizontal = pegarTamanhoLinhaHorizontal(listaDeOnibus);
        String linhaHorizontal = repetirChar('─', tamanhoLinhaHorizontal);
        String[] linhasVerticais;

        for (int i = 0; i < listaDeOnibus.length; i++) {
            linhasVerticais = criarLinhaVerticais(listaDeOnibus[i], tamanhoLinhaHorizontal + 2);
            System.out.println(String.format(
                    CHAR_CANTO_SUPERIOR_ESQUERDO + linhaHorizontal + CHAR_CANTO_SUPERIOR_DIREITO + "\n" +
                    InterfaceASCII.onibusInfo,
                        listaDeOnibus[i].nome + linhasVerticais[0],
                        listaDeOnibus[i].rota + linhasVerticais[1],
                        listaDeOnibus[i].horario + linhasVerticais[2],
                        "R$" + listaDeOnibus[i].valor + linhasVerticais[3] +
                    "\n" + CHAR_CANTO_INFERIOR_ESQUERDO + linhaHorizontal + CHAR_CANTO_INFERIOR_DIREITO + "\n"
            ));
        }
    }
}