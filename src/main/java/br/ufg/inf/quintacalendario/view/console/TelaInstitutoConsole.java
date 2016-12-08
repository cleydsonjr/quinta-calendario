package br.ufg.inf.quintacalendario.view.console;

import br.ufg.inf.quintacalendario.controller.InstitutoController;
import br.ufg.inf.quintacalendario.model.Instituto;
import br.ufg.inf.quintacalendario.view.TelaInicial;
import br.ufg.inf.quintacalendario.view.console.util.EntradaConsole;

import java.io.PrintStream;
import java.util.List;

public class TelaInstitutoConsole extends AbstractTelaCabecalho implements TelaInicial {

    private EntradaConsole entradaConsole;

    public TelaInstitutoConsole(PrintStream output) {
        super(output);
        setEntradaConsole(new EntradaConsole());
    }

    @Override
    public void exibaOpcoes() {
        exibaCabecalho();
        desenharOpcoesInicial();
        Integer opcao = getEntradaConsole().pergunteInteiro(desenharOpcoesInicial().toString());
        redirect(opcao);
    }

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
                    printarinstitutos(institutos);
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

    public void remover() {
        List<Instituto> institutos = pesquisar();
        if (!institutos.isEmpty()) {
            printarinstitutos(institutos);
            Integer codigo = getEntradaConsole().pergunteInteiro("Digite o codigo da Instituto que deseja remover");
            new InstitutoController().remover(codigo);
            System.out.println("Instituto removida com sucesso");
        }
    }

    private void pesquisarPorDescricao() {
        String descricao = getEntradaConsole().pergunteString("Digite a descrição desejada", true);
        List<Instituto> institutos = new InstitutoController().listar(descricao);
        printarinstitutos(institutos);
    }

    private List<Instituto> pesquisar() {
        List<Instituto> institutos = new InstitutoController().listar();
        return institutos;
    }

    private void editar() {
        List<Instituto> institutos = pesquisar();
        if (institutos.isEmpty()) {
            System.out.println("Não existem institutos cadastrados para se realizar a alteração.");
        } else {
            printarinstitutos(institutos);
            Integer codigo = getEntradaConsole().pergunteInteiro("Digite o codigo da Instituto que deseja editar");

            Instituto instituto = new InstitutoController().listarPorId(codigo);

            if (instituto == null) {
                System.out.println("Instituto não encontrado");
                editar();
            } else {
                System.out.println(instituto.getId() + " - " + instituto.getNome());

                String nome = getEntradaConsole().pergunteString("Digite o novo nome do Instituto", true);
                new InstitutoController().editar(codigo, nome);

                System.out.println("Instituto Alterado Com Sucesso");
            }
        }
    }

    private void cadastrar() {
        boolean result = false;
        while (!result) {
            String nome = getEntradaConsole().pergunteString("Digite o nome do Instituto");
            result = new InstitutoController().cadastrar(nome);
        }

        System.out.println("Instituto Cadastrado Com Sucesso");
    }

    private void printarinstitutos(List<Instituto> institutos) {
        institutos.stream().forEach(x -> System.out.println(x.getId() + " - " + x.getNome()));
    }

    @Override
    public int pergunteOpcao() {
        return 0;
    }

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
