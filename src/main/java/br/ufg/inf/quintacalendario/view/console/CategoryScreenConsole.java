package br.ufg.inf.quintacalendario.view.console;

import br.ufg.inf.quintacalendario.controller.CategoryController;
import br.ufg.inf.quintacalendario.model.Category;
import br.ufg.inf.quintacalendario.view.TelaInicial;
import br.ufg.inf.quintacalendario.view.console.util.EntradaConsole;

import java.io.PrintStream;
import java.util.List;

public class CategoryScreenConsole extends AbstractTelaCabecalho implements TelaInicial {

    private EntradaConsole entradaConsole;

    public CategoryScreenConsole(PrintStream output) {
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
                List<Category> categories = pesquisar();
                if (categories.isEmpty()) {
                    System.out.println("Não existem categorias cadastradas");
                } else {
                    printarcategorias(categories);
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
        List<Category> categories = pesquisar();
        if (!categories.isEmpty()) {
            printarcategorias(categories);
            Integer codigo = getEntradaConsole().pergunteInteiro("Digite o codigo da Categoria que deseja remover");
            new CategoryController().remove(codigo);
            System.out.println("Categoria removida com sucesso");
        }
    }

    private void pesquisarPorDescricao() {
        String descricao = getEntradaConsole().pergunteString("Digite a descrição desejada", true);
        List<Category> categories = new CategoryController().listRecordsByDescription(descricao);
        printarcategorias(categories);
    }

    private List<Category> pesquisar() {
        List<Category> categories = new CategoryController().listRecords();
        return categories;
    }

    private void editar() {
        List<Category> categories = pesquisar();
        if (categories.isEmpty()) {
            System.out.println("Não existem categorias cadastradas para se realizar a alteração.");
        } else {
            printarcategorias(categories);
            Integer codigo = getEntradaConsole().pergunteInteiro("Digite o codigo da Categoria que deseja editar");

            Category Category = new CategoryController().listById(codigo);

            if (Category.getName().isEmpty()) {
                System.out.println("Categoria não encontrada");
                editar();
            } else {
                System.out.println(Category.getId() + " - " + Category.getName());

                String nome = getEntradaConsole().pergunteString("Digite o novo nome da Categoria", true);
                new CategoryController().edit(codigo, nome);

                System.out.println("Categoria Alterada Com Sucesso");
            }
        }
    }

    private void cadastrar() {
        boolean result = false;
        while (!result) {
            String nome = getEntradaConsole().pergunteString("Digite o nome da Categoria");
            result = new CategoryController().register(nome);
        }

        System.out.println("Categoria Cadastrada Com Sucesso");
    }

    private void printarcategorias(List<Category> categories) {
        categories.stream().forEach(x -> System.out.println(x.getId() + " - " + x.getName()));
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
