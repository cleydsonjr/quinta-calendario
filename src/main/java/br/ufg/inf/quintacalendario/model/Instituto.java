package br.ufg.inf.quintacalendario.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
  * Classe que modela a representação dos institutos no banco de dados.
 */
@Entity(name="instituto")
public class Instituto {
	/**
          * Código de identificação do institutos.
         */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
        /**
          * Nome do instituto.
         */
	private String nome;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
}
