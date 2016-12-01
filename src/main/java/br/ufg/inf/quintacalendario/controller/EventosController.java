package br.ufg.inf.quintacalendario.controller;

import java.util.List;

import org.hibernate.SessionFactory;

import br.ufg.inf.quintacalendario.main.Application;
import br.ufg.inf.quintacalendario.model.Evento;
import br.ufg.inf.quintacalendario.service.EventoService;

public class EventosController {
	private SessionFactory sessionFactory;
	
	public EventosController(){
		sessionFactory = Application.getInstance().getSessionFactory();
	}
	
	public void listarEventos(){
		EventoService eventoService = new EventoService(sessionFactory);
		List<Evento> eventos = eventoService.listar();
		
		eventos.stream().forEach(x -> System.out.println(x.getId() +" - "+ x.getDescricao()));
	}
}
