package br.ufg.inf.quintacalendario.view.console;

import br.ufg.inf.quintacalendario.controller.RegionalController;
import br.ufg.inf.quintacalendario.main.Application;
import br.ufg.inf.quintacalendario.model.Regional;
import br.ufg.inf.quintacalendario.service.RegionalService;

public class TelaRegional {
	
	public static void main(String[] args) {
		Regional regional = new Regional();
		regional.setNome("Goiania");
		RegionalService service = new RegionalService(Application.getInstance().getSessionFactory());
		service.salvar(regional);
		
		
		new TelaRegional().listarRegionais();
	}
	
	public void listarRegionais(){
		new RegionalController().listarRegionais();
	}
}
