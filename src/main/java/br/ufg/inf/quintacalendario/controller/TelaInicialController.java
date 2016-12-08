package br.ufg.inf.quintacalendario.controller;

public class TelaInicialController {

	public void redirect(int opcao) {
		switch (opcao) {
		case 1:
			new EventosController().exibaOpcoes();
			break;
		case 2:
			new RegionalController().exibaOpcoes();
			break;
		case 3:
			new CategoriaController().exibaOpcoes();
			break;
		case 4:
			new InstitutoController().exibaOpcoes();
			break;
		case 5:
			
			break;
		case 6:
			
			break;

		default:
			break;
		}
	}

}
