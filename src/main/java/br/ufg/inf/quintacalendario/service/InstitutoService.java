package br.ufg.inf.quintacalendario.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import br.ufg.inf.quintacalendario.model.Instituto;
import br.ufg.inf.quintacalendario.repository.InstitutoRepository;

public class InstitutoService {
	private SessionFactory sessionFactory;
	
	public InstitutoService(SessionFactory session) {
		super();
		sessionFactory = session;
	}

	public boolean salvar(Instituto instituto){
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			validarInstituto(instituto);
	        
	        new InstitutoRepository(session).salvar(instituto);
	        transaction.commit();
	        session.close();
	        
	        return true;
        
		} catch (Exception e) {
			transaction.rollback();
			session.close();
			return false;
		}
	}
	
	private void validarInstituto(Instituto instituto) {
		if (instituto.getNome().trim().isEmpty()) {
			throw new IllegalArgumentException("O nome do instituto nao pode ser vazio");
		}
		
		if ((instituto.getNome().trim().length()) < 4) {
			throw new IllegalArgumentException("O nome do instituto deve ter no minimo 4 caracteres");
		}
	}

	public List<Instituto> listar(){
		Session session = sessionFactory.openSession();
		return new InstitutoRepository(session).listar();
	}
	
	public void limparTabela(){
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		new InstitutoRepository(session).limparTabela();
		transaction.commit();
		session.close();
	}

	public List<Instituto> listar(String descricao) {
		Session session = sessionFactory.openSession();
		return new InstitutoRepository(session).listarPorDescricao(descricao);
	}

	public Instituto listarPorId(Integer codigo) {
		Session session = sessionFactory.openSession();
		return new InstitutoRepository(session).listarPorId(codigo);
	}

	public void editar(Integer codigo, String nome) {
		Session session = sessionFactory.openSession();
		InstitutoRepository repository = new InstitutoRepository(session);
		Instituto instituto = repository.listarPorId(codigo);
		
		Transaction transaction = session.beginTransaction();
		
		instituto.setNome(nome);
		repository.atualizar(instituto);
		
		transaction.commit();
		session.close();
	}

	public void remover(Integer codigo) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		new InstitutoRepository(session).remover(codigo);
		transaction.commit();
		session.close();
	}
}
