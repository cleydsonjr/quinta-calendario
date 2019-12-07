package br.ufg.inf.quintacalendario.view.console;

import br.ufg.inf.quintacalendario.controller.InstituteController;
import br.ufg.inf.quintacalendario.model.Institute;
import br.ufg.inf.quintacalendario.view.TelaInicial;
import br.ufg.inf.quintacalendario.view.console.util.EntradaConsole;

import java.io.PrintStream;
import java.util.List;

public class InstituteScreenConsole extends AbstractTelaCabecalho implements TelaInicial {

    private EntradaConsole entradaConsole;

    public InstituteScreenConsole(PrintStream output) {
        super(output);
        setEntradaConsole(new EntradaConsole());
    }

    @Override
    public void showOptions() {
        exibaCabecalho();
        desenharOpcoesInicial();
        Integer opcao = getEntradaConsole().pergunteInteiro(desenharOpcoesInicial().toString());
        redirect(opcao);
    }

    private void redirect(Integer opcao) {
        switch (opcao) {
            case 1:
                cadastrar();
                showOptions();
                break;
            case 2:
                editar();
                showOptions();
                break;
            case 3:
                remove();
                showOptions();
                break;
            case 4:
                List<Institute> institutes = pesquisar();
                if (institutes.isEmpty()) {
                    System.out.println("Não existem institutos cadastradas");
                } else {
                    printarinstitutos(institutes);
                }
                showOptions();
                break;
            case 5:
                pesquisarPorDescricao();
                showOptions();
                break;
            case 6:
                new TelaInicialConsole(System.out).showOptions();
                break;
            case 7:
                break;
            default:
                System.out.println("Opção invalida");
                showOptions();
                break;
        }
    }

    public void remove() {
        List<Institute> institutes = pesquisar();
        if (!institutes.isEmpty()) {
            printarinstitutos(institutes);
            Integer codigo = getEntradaConsole().pergunteInteiro("Digite o codigo da Instituto que deseja remover");
            new InstituteController().remove(codigo);
            System.out.println("Instituto removida com sucesso");
        }
    }

    private void pesquisarPorDescricao() {
        String descricao = getEntradaConsole().pergunteString("Digite a descrição desejada", true);
        List<Institute> institutes = new InstituteController().listRecordsByDescripton(descricao);
        printarinstitutos(institutes);
    }

    private List<Institute> pesquisar() {
        List<Institute> institutes = new InstituteController().listRecords();
        return institutes;
    }

    private void editar() {
        List<Institute> institutes = pesquisar();
        if (institutes.isEmpty()) {
            System.out.println("Não existem institutos cadastrados para se realizar a alteração.");
        } else {
            printarinstitutos(institutes);
            Integer codigo = getEntradaConsole().pergunteInteiro("Digite o codigo da Instituto que deseja editar");

            Institute institute = new InstituteController().listById(codigo);

            if (institute == null) {
                System.out.println("Instituto não encontrado");
                editar();
            } else {
                System.out.println(institute.getId() + " - " + institute.getNome());

                String nome = getEntradaConsole().pergunteString("Digite o novo nome do Instituto", true);
                new InstituteController().edit(codigo, nome);

                System.out.println("Instituto Alterado Com Sucesso");
            }
        }
    }

    private void cadastrar() {
        boolean result = false;
        while (!result) {
            String nome = getEntradaConsole().pergunteString("Digite o nome do Instituto");
            result = new InstituteController().register(nome);
        }

        System.out.println("Instituto Cadastrado Com Sucesso");
    }

    private void printarinstitutos(List<Institute> institutes) {
        institutes.stream().forEach(x -> System.out.println(x.getId() + " - " + x.getNome()));
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
