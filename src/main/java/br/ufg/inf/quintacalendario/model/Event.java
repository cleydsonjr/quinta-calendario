package br.ufg.inf.quintacalendario.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "event")
public class Event {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date dataInicial;
    private Date dataFinal;
    private String descricao;
    private String titulo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category")
    private Category category;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "regional_event", joinColumns = {@JoinColumn(name = "event")}, inverseJoinColumns = {@JoinColumn(name = "regional")})
    private List<Regional> regionais;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "institute_event", joinColumns = {@JoinColumn(name = "event")}, inverseJoinColumns = {@JoinColumn(name = "institute")})
    private List<Institute> institutes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setInitialDate(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setFinalDate(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescription(String descricao) {
        this.descricao = descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitle(String titulo) {
        this.titulo = titulo;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Regional> getRegionais() {
        return regionais;
    }

    public void setRegionais(List<Regional> regionais) {
        this.regionais = regionais;
    }

    public List<Institute> getInstitutes() {
        return institutes;
    }

    public void setInstitutes(List<Institute> institutes) {
        this.institutes = institutes;
    }
}
