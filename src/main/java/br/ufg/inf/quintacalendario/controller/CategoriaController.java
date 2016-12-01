package br.ufg.inf.quintacalendario.controller;

import java.util.List;

import org.hibernate.SessionFactory;

import br.ufg.inf.quintacalendario.main.Application;
import br.ufg.inf.quintacalendario.model.Categoria;
import br.ufg.inf.quintacalendario.model.Instituto;
import br.ufg.inf.quintacalendario.model.Regional;
import br.ufg.inf.quintacalendario.service.CategoriaService;
import br.ufg.inf.quintacalendario.service.InstitutoService;
import br.ufg.inf.quintacalendario.service.RegionalService;

public class CategoriaController {
	
	private SessionFactory sessionFactory;
	
	public CategoriaController(){
		sessionFactory = Application.getInstance().getSessionFactory();
	}
	
	public void listarCategorias(){
		CategoriaService categoriaService = new CategoriaService(sessionFactory);
		List<Categoria> categorias = categoriaService.listar();
		
		categorias.stream().forEach(x -> System.out.println(x.getId() +" - "+ x.getNome()));
	}
}
