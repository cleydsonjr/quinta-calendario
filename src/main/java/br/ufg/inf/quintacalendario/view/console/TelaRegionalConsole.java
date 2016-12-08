package br.ufg.inf.quintacalendario.view.console;

import br.ufg.inf.quintacalendario.controller.RegionalController;
import br.ufg.inf.quintacalendario.model.Regional;
import br.ufg.inf.quintacalendario.view.TelaInicial;
import br.ufg.inf.quintacalendario.view.console.util.EntradaConsole;

import java.io.PrintStream;
import java.util.List;

public class TelaRegionalConsole extends AbstractTelaCabecalho implements TelaInicial {

    private EntradaConsole entradaConsole;

    public TelaRegionalConsole(PrintStream output) {
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

    public void remover() {
        List<Regional> regionais = pesquisar();
        if (!regionais.isEmpty()) {
            printarRegionais(regionais);
            Integer codigo = getEntradaConsole().pergunteInteiro("Digite o codigo da regional que deseja remover");
            new RegionalController().remover(codigo);
            System.out.println("Regional removida com sucesso");
        }
    }

    private void pesquisarPorDescricao() {
        String descricao = getEntradaConsole().pergunteString("Digite a descrição desejada", true);
        List<Regional> regionais = new RegionalController().listar(descricao);
        printarRegionais(regionais);
    }

    private List<Regional> pesquisar() {
        List<Regional> regionais = new RegionalController().listar();
        return regionais;
    }

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

    private void cadastrar() {
        boolean result = false;
        while (!result) {
            String nome = getEntradaConsole().pergunteString("Digite o nome da regional");
            result = new RegionalController().cadastrar(nome);
        }

        System.out.println("Regional Cadastrada Com Sucesso");
    }

    private void printarRegionais(List<Regional> regionais) {
        regionais.stream().forEach(x -> System.out.println(x.getId() + " - " + x.getNome()));
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
