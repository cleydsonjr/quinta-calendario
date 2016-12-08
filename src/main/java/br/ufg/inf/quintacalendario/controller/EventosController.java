package br.ufg.inf.quintacalendario.controller;

import br.ufg.inf.quintacalendario.main.Application;
import br.ufg.inf.quintacalendario.model.Categoria;
import br.ufg.inf.quintacalendario.model.Evento;
import br.ufg.inf.quintacalendario.model.Instituto;
import br.ufg.inf.quintacalendario.model.Regional;
import br.ufg.inf.quintacalendario.service.CategoriaService;
import br.ufg.inf.quintacalendario.service.EventoService;
import br.ufg.inf.quintacalendario.service.InstitutoService;
import br.ufg.inf.quintacalendario.service.RegionalService;
import br.ufg.inf.quintacalendario.view.console.TelaEventoConsole;
import org.hibernate.SessionFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventosController {
	private TelaEventoConsole tela;
	private SessionFactory sessionFactory;
	
	public EventosController() {
		setTela(new TelaEventoConsole(System.out));
		setSessionFactory(Application.getInstance().getSessionFactory());
	}
	
	public boolean cadastrar(String descricao, String titulo, String dataInicial, String dataFinal, Integer codigoCategoria
			               , Integer codigoRegional, Integer codigoInstituto) {

		try {
			Evento evento = new Evento();
			
			evento.setDescricao(descricao);
			evento.setTitulo(titulo);
			
			Date data = converterStringParaDate(dataInicial);
			evento.setDataInicial(data);
			
			data = converterStringParaDate(dataFinal);
			evento.setDataFinal(data);
			
			evento.setCategoria(new CategoriaService(getSessionFactory()).listarPorId(codigoCategoria));
			
			List<Instituto> institutos = new ArrayList<Instituto>();
			institutos.add(new InstitutoService(getSessionFactory()).listarPorId(codigoInstituto));
			
			evento.setInstitutos(institutos);
			
			List<Regional> regionais = new ArrayList<Regional>();
			regionais.add(new RegionalService(getSessionFactory()).listarPorId(codigoRegional));
			
			evento.setRegionais(regionais);
			
			EventoService service = new EventoService(getSessionFactory());
			service.salvar(evento);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
	
	public void exibaOpcoes() {
		getTela().exibaOpcoes();
	}
	
	public Date converterStringParaDate(String pData){
		if (pData == null || pData.equals("")) {
			return null;
		}
		
		Date date = null;
		try {
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			date = (java.util.Date) formatter.parse(pData);
		} catch (ParseException e) {
			// TODO: handle exception
		}
		return date;
	}
	
	public List<Evento> listar(){
		EventoService service = new EventoService(getSessionFactory());
		return service.listar();
	}
	
	public List<Evento> listar(String descricao){
		EventoService service = new EventoService(getSessionFactory());
		return service.listarPorDescricao(descricao);
	}
	
	public Evento listarPorId(Integer codigo){
		EventoService service = new EventoService(getSessionFactory());
		return service.listarPorId(codigo);
	}
	
	public List<Evento> listarPorPeriodo(String dataInicial, String dataFinal){
		EventoService service = new EventoService(getSessionFactory());
		return service.listarEventosPorPeriodo(converterStringParaDate(dataInicial), converterStringParaDate(dataFinal));
	}
	
	public List<Regional> listarRegionais() {
		RegionalService service = new RegionalService(getSessionFactory());
		return service.listar();
	}
	
	public List<Instituto> listarInstitutos() {
		InstitutoService service = new InstitutoService(getSessionFactory());
		return service.listar();
	}
	
	public List<Categoria> listarCategorias() {
		CategoriaService service = new CategoriaService(getSessionFactory());
		return service.listar();
	}
	
	public List<Evento> listarPorInstituto(int codigoInstituto) {
		EventoService service = new EventoService(getSessionFactory());
		List<Evento> eventos = service.listarPorInstituto(codigoInstituto);
		return eventos;
	}

	public List<Evento> listarPorCategoria(int codigoCategoria) {
		EventoService service = new EventoService(getSessionFactory());
		List<Evento> eventos = service.listarPorCategoria(codigoCategoria);
		return eventos;
	}

	public List<Evento> listarPorRegional(int codigoRegional) {
		EventoService service = new EventoService(getSessionFactory());
		List<Evento> eventos = service.listarPorRegional(codigoRegional);
		return eventos;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public TelaEventoConsole getTela() {
		return tela;
	}

	public void setTela(TelaEventoConsole tela) {
		this.tela = tela;
	}
}
