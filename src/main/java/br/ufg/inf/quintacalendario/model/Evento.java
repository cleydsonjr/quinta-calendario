package br.ufg.inf.quintacalendario.model;

import javax.persistence.*;

/**
 * Classe criada apenas para exemplificar uso da persistência
 */
@Entity
@Table(name = "evento")
public class Evento {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
