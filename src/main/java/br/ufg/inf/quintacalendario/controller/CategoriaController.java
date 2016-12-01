package br.ufg.inf.quintacalendario.controller;

import java.util.List;

import org.hibernate.SessionFactory;

import br.ufg.inf.quintacalendario.main.Application;
import br.ufg.inf.quintacalendario.model.Categoria;
import br.ufg.inf.quintacalendario.service.CategoriaService;
import br.ufg.inf.quintacalendario.view.console.TelaCategoriaConsole;

public class CategoriaController {

	private TelaCategoriaConsole tela;
	private SessionFactory sessionFactory;
	
	public CategoriaController() {
		tela = new TelaCategoriaConsole(System.out);
		sessionFactory = Application.getInstance().getSessionFactory();
	}

	public void exibaOpcoes() {
		getTela().exibaOpcoes();
	}

	public boolean cadastrar(String nome) {
		Categoria Categoria = new Categoria();
		Categoria.setNome(nome);
		
		CategoriaService service = new CategoriaService(getSessionFactory());
		return service.salvar(Categoria);
	}
	
	public List<Categoria> listar(){
		CategoriaService service = new CategoriaService(getSessionFactory());
		return service.listar();
	}
	
	public List<Categoria> listar(String descricao){
		CategoriaService service = new CategoriaService(getSessionFactory());
		return service.listar(descricao);
	}
	
	public Categoria listarPorId(Integer codigo) {
		CategoriaService service = new CategoriaService(getSessionFactory());
		Categoria Categoria = service.listarPorId(codigo);
		return Categoria;
	}
	
	public void editar(Integer codigo, String nome) {
		CategoriaService service = new CategoriaService(getSessionFactory());
		service.editar(codigo, nome);
	}
	
	public void remover(Integer codigo){
		CategoriaService service = new CategoriaService(getSessionFactory());
		Categoria Categoria = service.listarPorId(codigo);
		if (Categoria==null) {
			System.out.println("*******Codigo invalido*******");
			System.out.println("");
			getTela().remover();
		}else{
			service.remover(codigo);
		}
	}
	
	public TelaCategoriaConsole getTela() {
		return tela;
	}

	public void setTela(TelaCategoriaConsole tela) {
		this.tela = tela;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
