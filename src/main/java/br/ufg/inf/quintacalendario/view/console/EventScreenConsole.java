package br.ufg.inf.quintacalendario.view.console;

import br.ufg.inf.quintacalendario.controller.EventController;
import br.ufg.inf.quintacalendario.model.Category;
import br.ufg.inf.quintacalendario.model.Event;
import br.ufg.inf.quintacalendario.model.Institute;
import br.ufg.inf.quintacalendario.model.Regional;
import br.ufg.inf.quintacalendario.view.TelaInicial;
import br.ufg.inf.quintacalendario.view.console.util.EntradaConsole;

import java.io.PrintStream;
import java.util.List;

public class EventScreenConsole extends AbstractTelaCabecalho implements TelaInicial {

    private EntradaConsole entradaConsole;

    public EventScreenConsole(PrintStream out) {
        super(out);
        setEntradaConsole(new EntradaConsole());
    }

    @Override
    public void showOptions() {
        exibaCabecalho();
        desenharOpcoesInicial();
        Integer opcao = getEntradaConsole().pergunteInteiro(desenharOpcoesInicial().toString());
        redirect(opcao);
    }

    @Override
    public int pergunteOpcao() {
        return 0;
    }

    private void redirect(Integer opcao) {
        switch (opcao) {
            case 1:
                cadastrar();
                showOptions();
                break;
            case 2:
                showOptions();
                break;
            case 3:
                showOptions();
                break;
            case 4:
                listar();
                showOptions();
                break;
            case 5:
                listarPorDescricao();
                showOptions();
                break;
            case 6:
                listarPorPeriodo();
                showOptions();
                break;
            case 7:
                System.out.println("Opção em desenvolvimento");
                showOptions();
                break;
            case 8:
                System.out.println("Opção em desenvolvimento");
                showOptions();
                break;
            case 9:
                System.out.println("Opção em desenvolvimento");
                showOptions();
                break;
            case 10:
                new TelaInicialConsole(System.out).showOptions();
                break;
            case 11:
                break;
            default:
                System.out.println("Opção invalida");
                showOptions();
                break;
        }
    }

    public String desenharOpcoesInicial() {
        StringBuilder tela = new StringBuilder();
        tela.append("1 - Cadastrar				  \n")
                .append("2 - Editar					  \n")
                .append("3 - Remover				  \n")
                .append("4 - Pesquisar todos		  \n")
                .append("5 - Pesquisar por descrição  \n")
                .append("6 - Pesquisar por periodo    \n")
                .append("7 - Pesquisar por instituto - ** Em desenvolvimento ** \n")
                .append("8 - Pesquisar por regional  - ** Em desenvolvimento ** \n")
                .append("9 - Pesquisar por categoria - ** Em desenvolvimento ** \n")
                .append("10 - Voltar ao menu principal \n")
                .append("11 - Sair 					  \n");
        return tela.toString();
    }

    private void listarPorDescricao() {
        String descricaoEvento = getEntradaConsole().pergunteString("Digite a descricão do evento", true);
        EventController eventController = new EventController();
        List<Event> events = eventController.listRecordsByDescription(descricaoEvento);
        if (events.isEmpty()) {
            System.out.println("Não existem eventos cadastrados com essa descrição");
        } else {
            events.forEach(x -> System.out.println(x.getId() + " - " + x.getTitulo()));
        }
    }

    public void listarPorPeriodo() {
        String dataInicial = getEntradaConsole().pergunteString("Digite a data inicial, no formato dd/MM/YYYY", true);
        String dataFinal = getEntradaConsole().pergunteString("Digite a data final, no formato dd/MM/YYYY", true);

        EventController eventoController = new EventController();
        List<Event> events = eventoController.listByPeriod(dataInicial, dataFinal);
        if (events.isEmpty()) {
            System.out.println("Não existe eventos cadastrados no periodo informado");
        } else {
            events.forEach(x -> System.out.println(x.getId() + " - " + x.getDescricao()));
        }
    }

    private void listar() {
        EventController controller = new EventController();
        List<Event> events = controller.listRecords();
        events.forEach(x -> System.out.println(x.getId() + " - " + x.getTitulo()));
    }

    private void cadastrar() {
        EventController eventController = new EventController();

        if (dadosCadastroSaoValidos()) {

            String titulo = getEntradaConsole().pergunteString("Digite o titulo do evento", true);
            String descricao = getEntradaConsole().pergunteString("Digite a descricão do evento", true);
            String dataInicial = getEntradaConsole().pergunteString("Digite a data inicial do evento, no formato dd/MM/YYYY", true);
            String dataFinal = getEntradaConsole().pergunteString("Digite a data final do evento, no formato dd/MM/YYYY", true);

            int codigoCategoria = selecionarCodigoCategoria();
            int codigoRegional = selecionarCodigoRegional();
            int codigoInstituto = selecionarCodigoInstituto();

            boolean result = eventController.register(descricao, titulo, dataInicial, dataFinal, codigoCategoria, codigoRegional, codigoInstituto);
            if (result) {
                System.out.println("Evento cadastrado com sucesso");
            }
        }
    }

    private int selecionarCodigoRegional() {
        EventController controller = new EventController();
        List<Regional> regionais = controller.listRegionals();
        boolean result = false;
        Integer codigoRegional = 0;
        do {
            Integer codigo;
            regionais.stream().forEach(x -> System.out.println(x.getId() + " - " + x.getNome()));
            codigo = getEntradaConsole().pergunteInteiro("Digite o codigo da regional do evento");

            result = (regionais.stream().anyMatch(x -> x.getId() == codigo));

            if (!result) {
                System.out.println("Codigo invalido");
            } else {
                codigoRegional = codigo;
            }

        } while (!result);

        return codigoRegional;
    }

    private int selecionarCodigoInstituto() {
        EventController controller = new EventController();
        List<Institute> institutes = controller.listInstitutes();
        boolean result;
        Integer codigoInstituto = 0;

        do {
            Integer codigo;
            institutes.stream().forEach(x -> System.out.println(x.getId() + " - " + x.getNome()));
            codigo = getEntradaConsole().pergunteInteiro("Digite o codigo do instituto do evento");

            result = (institutes.stream().anyMatch(x -> x.getId() == codigo));

            if (!result) {
                System.out.println("Codigo invalido");
            } else {
                codigoInstituto = codigo;
            }

        } while (!result);

        return codigoInstituto;
    }

    private int selecionarCodigoCategoria() {
        EventController controller = new EventController();
        List<Category> categories = controller.listCategories();
        boolean result;
        Integer codigoCategoria = 0;

        do {
            Integer codigo;
            categories.stream().forEach(x -> System.out.println(x.getId() + " - " + x.getName()));
            codigo = getEntradaConsole().pergunteInteiro("Digite o codigo da categoria do evento");

            result = (categories.stream().anyMatch(x -> x.getId() == codigo));

            if (!result) {
                System.out.println("Codigo invalido");
            } else {
                codigoCategoria = codigo;
            }

        } while (!result);

        return codigoCategoria;
    }

    private boolean dadosCadastroSaoValidos() {
        EventController controller = new EventController();
        boolean result = true;

        List<Regional> regionais = controller.listRegionals();
        List<Institute> institutes = controller.listInstitutes();
        List<Category> categories = controller.listCategories();

        if (regionais == null || regionais.isEmpty()) {
            System.out.println("Não existem regionais cadastradas, efetue o seu cadastro antes de prosseguir");
            result = false;
        }

        if (institutes == null || institutes.isEmpty()) {
            System.out.println("Não existem institutos cadastrados, efetue o seu cadastro antes de prosseguir");
            result = false;
        }

        if (categories == null || categories.isEmpty()) {
            System.out.println("Não existem categorias cadastradas, efetue o seu cadastro antes de prosseguir");
            result = false;
        }

        return result;
    }

    public EntradaConsole getEntradaConsole() {
        return entradaConsole;
    }

    public void setEntradaConsole(EntradaConsole entradaConsole) {
        this.entradaConsole = entradaConsole;
    }
}
