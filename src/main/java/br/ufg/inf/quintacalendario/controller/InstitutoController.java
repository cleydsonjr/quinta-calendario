package br.ufg.inf.quintacalendario.controller;

import java.util.List;

import org.hibernate.SessionFactory;

import br.ufg.inf.quintacalendario.main.Application;
import br.ufg.inf.quintacalendario.model.Instituto;
import br.ufg.inf.quintacalendario.model.Regional;
import br.ufg.inf.quintacalendario.service.InstitutoService;
import br.ufg.inf.quintacalendario.service.RegionalService;

public class InstitutoController {
	
	private SessionFactory sessionFactory;
	
	public InstitutoController(){
		sessionFactory = Application.getInstance().getSessionFactory();
	}
	
	public void listarInstitutos(){
		InstitutoService institutoService = new InstitutoService(sessionFactory);
		List<Instituto> institutos = institutoService.listar();
		
		institutos.stream().forEach(x -> System.out.println(x.getId() +" - "+ x.getNome()));
	}
}
