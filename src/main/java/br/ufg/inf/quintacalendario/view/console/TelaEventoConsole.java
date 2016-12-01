package br.ufg.inf.quintacalendario.view.console;

import java.io.PrintStream;
import java.util.List;

import br.ufg.inf.quintacalendario.controller.EventosController;
import br.ufg.inf.quintacalendario.model.Evento;
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
			break;
		case 7:
			break;
		default:
			System.out.println("Opção invalida");
			exibaOpcoes();
			break;
		}
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

	private void listar() {
		EventosController controller = new EventosController();
		List<Evento> eventos = controller.listar();
		eventos.stream().forEach(x->System.out.println(x.getId() +" - "+ x.getTitulo()));
	}

	private void cadastrar() {
		String descricaoEvento 	= getEntradaConsole().pergunteString("Digite a descricão do evento", true);
		String tituloEvento 	= getEntradaConsole().pergunteString("Digite o titulo do evento", true);
		String dataInicial		= getEntradaConsole().pergunteString("Digite a data inicial do evento, no formato dd/MM/YYYY", true);
		String dataFinal		= getEntradaConsole().pergunteString("Digite a data final do evento, no formato dd/MM/YYYY", true);
		
		EventosController eventosController = new EventosController();
		eventosController.cadastrar(descricaoEvento, tituloEvento, dataInicial, dataFinal);
		
		System.out.println("Evento Cadastrado com sucesso");
	}	

	public String desenharOpcoesInicial(){
		StringBuilder tela = new StringBuilder();
		tela.append("1 - Cadastrar				  \n")
			.append("2 - Editar					  \n")
			.append("3 - Remover				  \n")
			.append("4 - Pesquisar todos		  \n")
			.append("5 - Pesquisar por descrição  \n")
			.append("6 - Pesquisar por instituto  \n")
			.append("7 - Pesquisar por regional   \n")
			.append("8 - Pesquisar por categoria  \n")
			.append("9 - Voltar ao menu principal \n")
			.append("10 - Sair 					  \n");
		return tela.toString();
	}

	public EntradaConsole getEntradaConsole() {
		return entradaConsole;
	}

	public void setEntradaConsole(EntradaConsole entradaConsole) {
		this.entradaConsole = entradaConsole;
	}
}
