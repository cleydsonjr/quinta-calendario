package br.ufg.inf.quintacalendario.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import br.ufg.inf.quintacalendario.model.Instituto;
/**
 * Classe que encapsula as regras de pesquisa em objetos da classe Instituto da
 * base de dados.
 */
public class InstitutoRepository extends AbstractRepository<Instituto>{

	public InstitutoRepository(Session session) {
		super(session);
	}

    /**
      * Método que lista todas os institutos que remetem a um evento que
      * apresentam a descrição recebida pelo método.
     * @param descricao descrição a ser comparada com a dos eventos cadastrados.
     * @return Uma lista de institutos que remetem a eventos com a descrição
     * recebida.
     */
	@Override
	public List<Instituto> listarPorDescricao(String descricao) {
		StringBuilder jpql = new StringBuilder();
		jpql.append("Select t from instituto t where t.nome like :descricao");
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("descricao", "%"+descricao+"%");
		
		List<Instituto> institutos = select(jpql.toString(), parametros);
		return institutos;
	}

}
