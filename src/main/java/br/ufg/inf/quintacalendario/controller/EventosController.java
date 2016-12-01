package br.ufg.inf.quintacalendario.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import br.ufg.inf.quintacalendario.main.Application;
import br.ufg.inf.quintacalendario.model.Evento;
import br.ufg.inf.quintacalendario.service.EventoService;
import br.ufg.inf.quintacalendario.view.console.TelaEventoConsole;

public class EventosController {
	
	
	private TelaEventoConsole tela;
	private SessionFactory sessionFactory;
	
	public EventosController() {
		setTela(new TelaEventoConsole(System.out));
		setSessionFactory(Application.getInstance().getSessionFactory());
	}
	
	public void exibaOpcoes() {
		getTela().exibaOpcoes();
	}

	public void cadastrar(String descricao, String titulo, String dataInicial, String dataFinal) {
		Evento evento = new Evento();
		
		evento.setDescricao(descricao);
		evento.setTitulo(titulo);
		
		Date data = converterStringParaDate(dataInicial);
		evento.setDataInicial(data);
		
		data = converterStringParaDate(dataFinal);
		evento.setDataFinal(data);
		
		EventoService service = new EventoService(getSessionFactory());
		service.salvar(evento);
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
