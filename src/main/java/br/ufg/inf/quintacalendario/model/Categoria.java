package br.ufg.inf.quintacalendario.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Classe que modela a representação das categorias de evento no banco de dados.
 */
@Entity(name = "categoria")
public class Categoria {
    /**
      * Código de identificação da categoria de evento.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    /**
      * Nome da categoria do evento.
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
