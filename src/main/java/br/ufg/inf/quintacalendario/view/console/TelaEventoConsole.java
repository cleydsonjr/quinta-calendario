package br.ufg.inf.quintacalendario.view.console;

import java.io.PrintStream;
import java.util.List;

import br.ufg.inf.quintacalendario.controller.EventosController;
import br.ufg.inf.quintacalendario.model.Categoria;
import br.ufg.inf.quintacalendario.model.Evento;
import br.ufg.inf.quintacalendario.model.Instituto;
import br.ufg.inf.quintacalendario.model.Regional;
import br.ufg.inf.quintacalendario.view.TelaInicial;
import br.ufg.inf.quintacalendario.view.console.util.EntradaConsole;

public class TelaEventoConsole extends AbstractTelaCabecalho implements TelaInicial{

	private EntradaConsole entradaConsole;
	
	public TelaEventoConsole(PrintStream out) {
		super(out);
		setEntradaConsole(new EntradaConsole());
	}

	@Override
	public void exibaOpcoes() {
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
			exibaOpcoes();
			break;
		case 2:
			exibaOpcoes();
			break;
		case 3:
			exibaOpcoes();
			break;
		case 4:
			listar();
			exibaOpcoes();
			break;
		case 5:
			listarPorDescricao();
			exibaOpcoes();
			break;
		case 6:
			listarPorPeriodo();
			exibaOpcoes();
			break;
		case 7:
			listarPorInstituto();
			exibaOpcoes();
			break;
		case 8:
			listarPorRegional();
			exibaOpcoes();
			break;
		case 9:
			listarPorCategoria();
			exibaOpcoes();
			break;
		case 10:
			new TelaInicialConsole(System.out).exibaOpcoes();
			break;
		case 11:
			break;
		default:
			System.out.println("Opção invalida");
			exibaOpcoes();
			break;
		}
	}
	
	public String desenharOpcoesInicial(){
		StringBuilder tela = new StringBuilder();
		tela.append("1 - Cadastrar				  \n")
			.append("2 - Editar					  \n")
			.append("3 - Remover				  \n")
			.append("4 - Pesquisar todos		  \n")
			.append("5 - Pesquisar por descrição  \n")
			.append("6 - Pesquisar por periodo    \n")
			.append("7 - Pesquisar por instituto  \n")
			.append("8 - Pesquisar por regional   \n")
			.append("9 - Pesquisar por categoria  \n")
			.append("10 - Voltar ao menu principal \n")
			.append("11 - Sair 					  \n");
		return tela.toString();
	}
	
	private void listarPorDescricao() {
		String descricaoEvento 	= getEntradaConsole().pergunteString("Digite a descricão do evento", true);
		EventosController eventosController = new EventosController();
		List<Evento> eventos = eventosController.listar(descricaoEvento);
		if (eventos.isEmpty()) {
			System.out.println("Não existem eventos cadastrados com essa descrição");
		}else{
			eventos.stream().forEach(x->System.out.println(x.getId() +" - "+ x.getTitulo()));
		}
	}
	
	public void listarPorPeriodo(){
		String dataInicial = getEntradaConsole().pergunteString("Digite a data inicial, no formato dd/MM/YYYY", true);
		String dataFinal = getEntradaConsole().pergunteString("Digite a data final, no formato dd/MM/YYYY", true);
		
		EventosController eventoController = new EventosController();
		List<Evento> eventos = eventoController.listarPorPeriodo(dataInicial, dataFinal);
		if (eventos.isEmpty()) {
			System.out.println("Não existe eventos cadastrados no periodo informado");
		}else{
			eventos.stream().forEach(x->System.out.println(x.getId() +" - "+x.getDescricao()));	
		}
	}

	private void listar() {
		EventosController controller = new EventosController();
		List<Evento> eventos = controller.listar();
		eventos.stream().forEach(x->System.out.println(x.getId() +" - "+ x.getTitulo()));
	}
	
	public void listarPorInstituto(){
		int codigoInstituto = selecionarCodigoInstituto();
		EventosController controller = new EventosController();
		List<Evento> eventos = controller.listarPorInstituto(codigoInstituto);
		
		if (eventos.isEmpty()) {
			System.out.println("Não existem eventos cadastrados para esse instituto");
		}else{
			eventos.stream().forEach(x->System.out.println(x.getId() +" - "+x.getDescricao()));
		}
	}
	
	public void listarPorCategoria(){
		int codigoCategoria = selecionarCodigoCategoria();
		EventosController controller = new EventosController();
		List<Evento> eventos = controller.listarPorCategoria(codigoCategoria);
		
		if (eventos.isEmpty()) {
			System.out.println("Não existem eventos cadastrados para essa categoria");
		}else{
			eventos.stream().forEach(x->System.out.println(x.getId() +" - "+x.getDescricao()));
		}
	}

	public void listarPorRegional(){
		int codigoRegional = selecionarCodigoRegional();
		EventosController controller = new EventosController();
		List<Evento> eventos = controller.listarPorRegional(codigoRegional);
		
		if (eventos.isEmpty()) {
			System.out.println("Não existem eventos cadastrados para essa regional");
		}else{
			eventos.stream().forEach(x->System.out.println(x.getId() +" - "+x.getDescricao()));
		}
	}
	
	private void cadastrar() {
		EventosController eventosController = new EventosController();
		
		if(dadosCadastroSaoValidos()){
		
			String titulo 		= getEntradaConsole().pergunteString("Digite o titulo do evento", true);
			String descricao 	= getEntradaConsole().pergunteString("Digite a descricão do evento", true);
			String dataInicial	= getEntradaConsole().pergunteString("Digite a data inicial do evento, no formato dd/MM/YYYY", true);
			String dataFinal	= getEntradaConsole().pergunteString("Digite a data final do evento, no formato dd/MM/YYYY", true);
			
			int codigoCategoria = selecionarCodigoCategoria();
			int codigoRegional  = selecionarCodigoRegional();
			int codigoInstituto = selecionarCodigoInstituto();
			
			boolean result = eventosController.cadastrar(descricao, titulo, dataInicial, dataFinal, codigoCategoria, codigoRegional, codigoInstituto);
			if (result) {
				System.out.println("Evento cadastrado com sucesso");
			}
		}
	}
	
	private int selecionarCodigoRegional(){
		EventosController controller = new EventosController();
		List<Regional> regionais = controller.listarRegionais();
		boolean result = false;
		Integer codigoRegional = 0;
		do {
			Integer codigo;
			regionais.stream().forEach(x->System.out.println(x.getId()+" - "+x.getNome()));
			codigo = getEntradaConsole().pergunteInteiro("Digite o codigo da regional do evento");
			
			result = (regionais.stream().anyMatch(x->x.getId() == codigo));
			
			if (!result) {
				System.out.println("Codigo invalido");
			}else{
				codigoRegional = codigo;
			}
			
		} while (!result);
		
		return codigoRegional;
	}
	
	private int selecionarCodigoInstituto(){
		EventosController controller = new EventosController();
		List<Instituto> institutos = controller.listarInstitutos();
		boolean result;
		Integer codigoInstituto = 0;
		
		do {
			Integer codigo;
			institutos.stream().forEach(x->System.out.println(x.getId()+" - "+x.getNome()));
			codigo = getEntradaConsole().pergunteInteiro("Digite o codigo do instituto do evento");
			
			result = (institutos.stream().anyMatch(x->x.getId() == codigo));
			
			if (!result) {
				System.out.println("Codigo invalido");
			}else{
				codigoInstituto = codigo;
			}
			
		} while (!result);
		
		return codigoInstituto;
	}
	
	private int selecionarCodigoCategoria(){
		EventosController controller = new EventosController();
		List<Categoria> categorias = controller.listarCategorias();
		boolean result;
		Integer codigoCategoria = 0;
		
		do {
			Integer codigo;
			categorias.stream().forEach(x->System.out.println(x.getId()+" - "+x.getNome()));
			codigo = getEntradaConsole().pergunteInteiro("Digite o codigo da categoria do evento");
			
			result = (categorias.stream().anyMatch(x->x.getId() == codigo));
			
			if (!result) {
				System.out.println("Codigo invalido");
			}else{
				codigoCategoria = codigo;
			}
			
		} while (!result);
		
		return codigoCategoria;
	}
	
	private boolean dadosCadastroSaoValidos(){
		EventosController controller = new EventosController();
		boolean result = true;
		
		List<Regional> regionais 	= controller.listarRegionais();
		List<Instituto> institutos 	= controller.listarInstitutos();
		List<Categoria> categorias 	= controller.listarCategorias();
		
		if (regionais == null || regionais.isEmpty()) {
			System.out.println("Não existem regionais cadastradas, efetue o seu cadastro antes de prosseguir");
			result = false;
		}
		
		if (institutos == null || institutos.isEmpty()) {
			System.out.println("Não existem institutos cadastrados, efetue o seu cadastro antes de prosseguir");
			result = false;
		}
		
		if (categorias == null || categorias.isEmpty()) {
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
