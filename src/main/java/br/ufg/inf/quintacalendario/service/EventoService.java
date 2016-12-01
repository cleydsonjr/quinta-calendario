package br.ufg.inf.quintacalendario.service;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import br.ufg.inf.quintacalendario.model.Evento;
import br.ufg.inf.quintacalendario.repository.EventoRepository;

public class EventoService {
	
	private SessionFactory sessionFactory;
	
	public EventoService(SessionFactory sessionFactory) {
		super();
		this.setSessionFactory(sessionFactory);
	}

	public boolean salvar(Evento evento){
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			validarEvento(evento);
			
			new EventoRepository(session).salvar(evento);
			transaction.commit();
			session.close();
			
			return true;
		} catch (Exception e) {
			transaction.rollback();
			session.close();
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public void atualizar(Evento evento){
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		EventoRepository eventoRepository = new EventoRepository(session);
		eventoRepository.atualizar(evento);
		transaction.commit();
	}

	public List<Evento> listar(){
		Session session = sessionFactory.openSession();
		return new EventoRepository(session).listar();
	}
	
	public List<Evento> listarPorDescricao(String descricao){
		Session session = sessionFactory.openSession();
		List<Evento> eventos = new EventoRepository(session).listarPorDescricao(descricao);
		return eventos;
	}
	
	public Evento listarPorId(long id){
		Session session = sessionFactory.openSession();
		return new EventoRepository(session).listarPorId(id);
	}
	
	public List<Evento> listarPorCategoria(long idCategoria){
		Session session = sessionFactory.openSession();
		return new EventoRepository(session).listarPorCategoria(idCategoria);
	}
	
	public List<Evento> listarPorInstituto(long idInstituto){
		Session session = sessionFactory.openSession();
		return new EventoRepository(session).listarPorInstituto(idInstituto);
	}
	
	public List<Evento> listarPorRegional(long idRegional){
		Session session = sessionFactory.openSession();
		return new EventoRepository(session).listarPorRegional(idRegional);
	}
	
	public List<Evento> listarEventosPorPeriodo(Date dataInicial, Date dataFinal){
		Session session = sessionFactory.openSession();
		return new EventoRepository(session).listarPorPeriodo(dataInicial, dataFinal);
	}
	
	public List<Evento> listarEventosPorData(Date dataInicial){
		Session session = sessionFactory.openSession();
		return new EventoRepository(session).listarPorData(dataInicial);
		
	}
	
	public void limparTabela(){
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		new EventoRepository(session).limparTabela();
		transaction.commit();
		session.close();
	}
	
	public void removerCategoriasEvento(Evento evento){
		evento.setCategoria(null);
	}
	
	public void removerInstitutoEvento(Evento evento){
		evento.getInstitutos().clear();
	}
	
	public void removerRegionalEvento(Evento evento){
		evento.getRegionais().clear();
	}
	
	private void validarEvento(Evento evento) {
		// TODO Criar validacoes de evento
	}

	public void limparObjeto(Evento evento) {
		Session session = getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		
		removerCategoriasEvento(evento);
		removerInstitutoEvento(evento);
		removerRegionalEvento(evento);
		
		new EventoRepository(session).atualizar(evento);
		transaction.commit();
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
