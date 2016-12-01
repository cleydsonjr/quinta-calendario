package br.ufg.inf.quintacalendario.view.console;

import java.io.PrintStream;
import java.util.List;

import br.ufg.inf.quintacalendario.controller.CategoriaController;
import br.ufg.inf.quintacalendario.model.Categoria;
import br.ufg.inf.quintacalendario.view.TelaInicial;
import br.ufg.inf.quintacalendario.view.console.util.EntradaConsole;

public class TelaCategoriaConsole extends AbstractTelaCabecalho implements TelaInicial{
	
	private EntradaConsole entradaConsole;
	
	public TelaCategoriaConsole(PrintStream output) {
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
			List<Categoria> categorias = pesquisar();
			if (categorias.isEmpty()) {
				System.out.println("Não existem categorias cadastradas");
			}else{
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

	public void remover() {
		List<Categoria> categorias = pesquisar();
		if (!categorias.isEmpty()) {
			printarcategorias(categorias);
			Integer codigo = getEntradaConsole().pergunteInteiro("Digite o codigo da Categoria que deseja remover");
			new CategoriaController().remover(codigo);
			System.out.println("Categoria removida com sucesso");
		}
	}

	private void pesquisarPorDescricao() {
		String descricao = getEntradaConsole().pergunteString("Digite a descrição desejada", true);
		List<Categoria> categorias = new CategoriaController().listar(descricao);
		printarcategorias(categorias);
	}

	private List<Categoria> pesquisar() {
		List<Categoria> categorias = new CategoriaController().listar();
		return categorias;
	}

	private void editar() {
		List<Categoria> categorias = pesquisar();
		if (categorias.isEmpty()) {
			System.out.println("Não existem categorias cadastradas para se realizar a alteração.");
		}else{
			printarcategorias(categorias);
			Integer codigo = getEntradaConsole().pergunteInteiro("Digite o codigo da Categoria que deseja editar");
			
			Categoria Categoria = new CategoriaController().listarPorId(codigo);
			
			if (Categoria.getNome().isEmpty()) {
				System.out.println("Categoria não encontrada");
				editar();
			}else{
				System.out.println(Categoria.getId() +" - "+ Categoria.getNome());
				
				String nome = getEntradaConsole().pergunteString("Digite o novo nome da Categoria", true);
				new CategoriaController().editar(codigo, nome);
				
				System.out.println("Categoria Alterada Com Sucesso");
			}
		}
	}

	private void cadastrar() {
		boolean result = false;
		while (!result) {
			String nome = getEntradaConsole().pergunteString("Digite o nome da Categoria");
			result = new CategoriaController().cadastrar(nome);
		}
		
		System.out.println("Categoria Cadastrada Com Sucesso");
	}
	
	private void printarcategorias(List<Categoria> categorias){
		categorias.stream().forEach(x->System.out.println(x.getId() + " - " + x.getNome()));
	}

	@Override
	public int pergunteOpcao() {
		return 0;
	}
	
	public String desenharOpcoesInicial(){
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
