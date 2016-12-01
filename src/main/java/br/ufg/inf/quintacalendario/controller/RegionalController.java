package br.ufg.inf.quintacalendario.controller;

import java.util.List;

import org.hibernate.SessionFactory;

import br.ufg.inf.quintacalendario.main.Application;
import br.ufg.inf.quintacalendario.model.Regional;
import br.ufg.inf.quintacalendario.service.RegionalService;

public class RegionalController {
	
	private SessionFactory sessionFactory;
	
	public RegionalController(){
		sessionFactory = Application.getInstance().getSessionFactory();
	}
	
	public void listarRegionais(){
		RegionalService regionalService = new RegionalService(sessionFactory);
		List<Regional> regionais = regionalService.listar();
		
		regionais.stream().forEach(x -> System.out.println(x.getId() +" - "+ x.getNome()));
	}
}
