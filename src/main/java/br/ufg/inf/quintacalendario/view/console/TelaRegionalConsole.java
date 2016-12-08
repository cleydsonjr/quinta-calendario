package br.ufg.inf.quintacalendario.view.console;

import java.io.PrintStream;
import java.util.List;

import br.ufg.inf.quintacalendario.controller.RegionalController;
import br.ufg.inf.quintacalendario.model.Regional;
import br.ufg.inf.quintacalendario.view.TelaInicial;
import br.ufg.inf.quintacalendario.view.console.util.EntradaConsole;

/**
  * Classe responsável pela apresentação das opções e resultados de busca do
  * calendário que remetem às regionais.
 */
public class TelaRegionalConsole extends AbstractTelaCabecalho implements TelaInicial {
    /**
      * Atributo responsável pela interação do programa com os dispositivos de
      * entrada.
     */
    private EntradaConsole entradaConsole;

    public TelaRegionalConsole(PrintStream output) {
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
        Integer opcao = getEntradaConsole().pergunteInteiro(desenharOpcoesInicial().toString());
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
                List<Regional> regionais = pesquisar();
                if (regionais.isEmpty()) {
                    System.out.println("Não existem regionais cadastradas");
                } else {
                    printarRegionais(regionais);
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
     * Método que realiza a operação de remoção de uma regional da base de
     * dados.
     */
    public void remover() {
        List<Regional> regionais = pesquisar();
        if (!regionais.isEmpty()) {
            printarRegionais(regionais);
            Integer codigo = getEntradaConsole().pergunteInteiro("Digite o codigo da regional que deseja remover");
            new RegionalController().remover(codigo);
            System.out.println("Regional removida com sucesso");
        }
    }

    /**
     * Método que realiza uma pesquisa no banco de dados pela descrição dos
     * eventos e imprime as regionais dos eventos encontrados.
     */
    private void pesquisarPorDescricao() {
        String descricao = getEntradaConsole().pergunteString("Digite a descrição desejada", true);
        List<Regional> regionais = new RegionalController().listar(descricao);
        printarRegionais(regionais);
    }

    /**
      * Método que realiza um cnsulta na base de dados por uma regional pelo
      * nome.
     * @return Uma lista de regionais encontradas pela consulta.
     */
    private List<Regional> pesquisar() {
        List<Regional> regionais = new RegionalController().listar();
        return regionais;
    }

    /**
      * Método que realiza uma pesquisa por Id de regional de evento para
      * editar a regional que possui o Id inserido.
     */
    private void editar() {
        List<Regional> regionais = pesquisar();
        if (regionais.isEmpty()) {
            System.out.println("Não existem regionais cadastradas para se realizar a alteração.");
        } else {
            printarRegionais(regionais);
            Integer codigo = getEntradaConsole().pergunteInteiro("Digite o codigo da regional que deseja editar");

            Regional regional = new RegionalController().listarPorId(codigo);

            if (regional.getNome().isEmpty()) {
                System.out.println("Regional não encontrada");
                editar();
            } else {
                System.out.println(regional.getId() + " - " + regional.getNome());

                String nome = getEntradaConsole().pergunteString("Digite o novo nome da Regional", true);
                new RegionalController().editar(codigo, nome);

                System.out.println("Regional Alterada Com Sucesso");
            }
        }
    }

    /**
      * Método que cadastra uma regional inserida.
     */
    private void cadastrar() {
        boolean result = false;
        while (!result) {
            String nome = getEntradaConsole().pergunteString("Digite o nome da regional");
            result = new RegionalController().cadastrar(nome);
        }

        System.out.println("Regional Cadastrada Com Sucesso");
    }

    /**
      * Método que imprime uma lista de regionais.
     * @param institutos lista de regionais a ser impressa.
     */
    private void printarRegionais(List<Regional> regionais) {
        regionais.stream().forEach(x -> System.out.println(x.getId() + " - " + x.getNome()));
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
