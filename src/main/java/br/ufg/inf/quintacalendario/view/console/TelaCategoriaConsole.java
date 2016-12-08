package br.ufg.inf.quintacalendario.view.console;

import java.io.PrintStream;
import java.util.List;

import br.ufg.inf.quintacalendario.controller.CategoriaController;
import br.ufg.inf.quintacalendario.model.Categoria;
import br.ufg.inf.quintacalendario.view.TelaInicial;
import br.ufg.inf.quintacalendario.view.console.util.EntradaConsole;

/**
  * Classe responsável pela apresentação das opções e resultados de busca do
  * calendário que remetem às categorias.
 */
public class TelaCategoriaConsole extends AbstractTelaCabecalho implements
        TelaInicial {

    /**
      * Atributo responsável pela interação do programa com os dispositivos de
      * entrada.
     */
    private EntradaConsole entradaConsole;

    public TelaCategoriaConsole(PrintStream output) {
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
                List<Categoria> categorias = pesquisar();
                if (categorias.isEmpty()) {
                    System.out.println("Não existem categorias cadastradas");
                } else {
                    printarcategorias(categorias);
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
     * Método que realiza a operação de remoção de uma categoria de evento da
     * base de dados.
     */
    public void remover() {
        List<Categoria> categorias = pesquisar();
        if (!categorias.isEmpty()) {
            printarcategorias(categorias);
            Integer codigo = getEntradaConsole().pergunteInteiro("Digite o codigo da Categoria que deseja remover");
            new CategoriaController().remover(codigo);
            System.out.println("Categoria removida com sucesso");
        }
    }

    /**
     * Método que realiza uma pesquisa no banco de dados pela descrição dos
     * eventos e imprime as categorias de evento dos eventos encontrados.
     */
    private void pesquisarPorDescricao() {
        String descricao = getEntradaConsole().pergunteString("Digite a descrição desejada", true);
        List<Categoria> categorias = new CategoriaController().listar(descricao);
        printarcategorias(categorias);
    }

    /**
      * Método que realiza um cnsulta na base de dados por uma categoria de
      * evento pelo nome.
     * @return Uma lista de categorias de evento encontradas pela consulta.
     */
    private List<Categoria> pesquisar() {
        List<Categoria> categorias = new CategoriaController().listar();
        return categorias;
    }

    /**
      * Método que realiza uma pesquisa por Id de categoria de evento para
      * editar a categoria de evento que possui o Id inserido.
     */
    private void editar() {
        List<Categoria> categorias = pesquisar();
        if (categorias.isEmpty()) {
            System.out.println("Não existem categorias cadastradas para se realizar a alteração.");
        } else {
            printarcategorias(categorias);
            Integer codigo = getEntradaConsole().pergunteInteiro("Digite o codigo da Categoria que deseja editar");

            Categoria Categoria = new CategoriaController().listarPorId(codigo);

            if (Categoria.getNome().isEmpty()) {
                System.out.println("Categoria não encontrada");
                editar();
            } else {
                System.out.println(Categoria.getId() + " - " + Categoria.getNome());

                String nome = getEntradaConsole().pergunteString("Digite o novo nome da Categoria", true);
                new CategoriaController().editar(codigo, nome);

                System.out.println("Categoria Alterada Com Sucesso");
            }
        }
    }

    /**
      * Método que cadastra uma categoria de evento inserida.
     */
    private void cadastrar() {
        boolean result = false;
        while (!result) {
            String nome = getEntradaConsole().pergunteString("Digite o nome da Categoria");
            result = new CategoriaController().cadastrar(nome);
        }

        System.out.println("Categoria Cadastrada Com Sucesso");
    }

    /**
      * Método que imprime uma lista de categorias de evento.
     * @param institutos lista de categorias de evento a ser impressa.
     */
    private void printarcategorias(List<Categoria> categorias) {
        categorias.stream().forEach(x -> System.out.println(x.getId() + " - " + x.getNome()));
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
