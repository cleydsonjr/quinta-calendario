package br.ufg.inf.quintacalendario.view.console;

import java.io.PrintStream;
import java.util.List;

import br.ufg.inf.quintacalendario.controller.InstitutoController;
import br.ufg.inf.quintacalendario.model.Instituto;
import br.ufg.inf.quintacalendario.view.TelaInicial;
import br.ufg.inf.quintacalendario.view.console.util.EntradaConsole;

/**
 * Classe responsável pela apresentação das opções e resultados de busca do
 * calendário que remetem aos institutos.
 */
public class TelaInstitutoConsole extends AbstractTelaCabecalho
        implements TelaInicial {
    /**
      * Atributo responsável pela interação do programa com os dispositivos de
      * entrada.
     */
    private EntradaConsole entradaConsole;

    public TelaInstitutoConsole(PrintStream output) {
        super(output);
        setEntradaConsole(new EntradaConsole());
    }

    /**
      * Método que exibe as opções do usuário na tela e executa a operação
      * escolhida.
     */
    @Override
    public void exibaOpcoes() {
        exibaCabecalho();
        desenharOpcoesInicial();
        Integer opcao = getEntradaConsole().pergunteInteiro
            (desenharOpcoesInicial().toString());
        redirect(opcao);
    }

    /**
     * Método que redireciona a execução para a operação escolhida pelo usuário.
     * @param opcao opção inserida pelo usuário.
     */
    private void redirect(Integer opcao) {
        switch (opcao) {
            case 1:
                cadastrar();
                exibaOpcoes();
                break;
            case 2:
                editar();
                exibaOpcoes();
                break;
            case 3:
                remover();
                exibaOpcoes();
                break;
            case 4:
                List<Instituto> institutos = pesquisar();
                if (institutos.isEmpty()) {
                    System.out.println("Não existem institutos cadastradas");
                } else {
                    printarInstitutos(institutos);
                }
                exibaOpcoes();
                break;
            case 5:
                pesquisarPorDescricao();
                exibaOpcoes();
                break;
            case 6:
                new TelaInicialConsole(System.out).exibaOpcoes();
                break;
            case 7:
                break;
            default:
                System.out.println("Opção invalida");
                exibaOpcoes();
                break;
        }
    }

    /**
     * Método que realiza a operação de remoção de um insituto da base de dados.
     */
    public void remover() {
        List<Instituto> institutos = pesquisar();
        if (!institutos.isEmpty()) {
            printarInstitutos(institutos);
            Integer codigo = getEntradaConsole().pergunteInteiro("Digite o "
                    + "codigo da Instituto que deseja remover");
            new InstitutoController().remover(codigo);
            System.out.println("Instituto removida com sucesso");
        }
    }

    /**
     * Método que realiza uma pesquisa no banco de dados pela descrição dos
     * eventos e imprime os institutos dos eventos encontrados.
     */
    private void pesquisarPorDescricao() {
        String descricao = getEntradaConsole().pergunteString("Digite a "
                + "descrição desejada", true);
        List<Instituto> institutos = new InstitutoController().
                listar(descricao);
        printarInstitutos(institutos);
    }

    /**
      * Método que realiza um cnsulta na base de dados por um instituto pelo
      * nome.
     * @return Uma lista de institutos encontrados pela consulta.
     */
    private List<Instituto> pesquisar() {
        List<Instituto> institutos = new InstitutoController().listar();
        return institutos;
    }

    /**
      * Método que realiza uma pesquisa por Id de instituto para editar o
      * instituto que possui o Id inserido.
     */
    private void editar() {
        List<Instituto> institutos = pesquisar();
        if (institutos.isEmpty()) {
            System.out.println("Não existem institutos cadastrados para se "
                    + "realizar a alteração.");
        } else {
            printarInstitutos(institutos);
            Integer codigo = getEntradaConsole().pergunteInteiro("Digite o "
                    + "codigo da Instituto que deseja editar");

            Instituto instituto = new InstitutoController().listarPorId(codigo);

            if (instituto == null) {
                System.out.println("Instituto não encontrado");
                editar();
            } else {
                System.out.println(instituto.getId() + " - " + instituto.
                        getNome());

                String nome = getEntradaConsole().pergunteString("Digite o novo"
                        + " nome do Instituto", true);
                new InstitutoController().editar(codigo, nome);

                System.out.println("Instituto Alterado Com Sucesso");
            }
        }
    }

    /**
      * Método que cadastra um instituto inserido.
     */
    private void cadastrar() {
        boolean result = false;
        while (!result) {
            String nome = getEntradaConsole().pergunteString("Digite o nome do "
                    + "Instituto");
            result = new InstitutoController().cadastrar(nome);
        }

        System.out.println("Instituto Cadastrado Com Sucesso");
    }

    /**
      * Método que imprime uma lista de institutos.
     * @param institutos lista de institutos a ser impressa.
     */
    private void printarInstitutos(List<Instituto> institutos) {
        institutos.stream().forEach(x -> System.out.println(x.getId() + " - "
                + x.getNome()));
    }

    @Override
    public int pergunteOpcao() {
        return 0;
    }

    /**
      * Método que exibe as opções disponíveis no menu inicial.
     * @return String que apresesnta as opções disponíveis.
     */
    public String desenharOpcoesInicial() {
        StringBuilder tela = new StringBuilder();
        tela.append("1 - Cadastrar				  \n")
                .append("2 - Editar					  \n")
                .append("3 - Remover				  \n")
                .append("4 - Pesquisar todos		  \n")
                .append("5 - Pesquisar por descrição  \n")
                .append("6 - Voltar ao menu principal \n")
                .append("7 - Sair 					  \n");
        return tela.toString();
    }

    public EntradaConsole getEntradaConsole() {
        return entradaConsole;
    }

    public void setEntradaConsole(EntradaConsole entradaConsole) {
        this.entradaConsole = entradaConsole;
    }

}
