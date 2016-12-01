package br.ufg.inf.quintacalendario.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity(name = "evento")
public class Evento {
	
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date dataInicial;
    private Date dataFinal;
    private String descricao;
    private String titulo;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoria")
    private Categoria categoria;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="regionais_evento", joinColumns={@JoinColumn(name="evento")}, inverseJoinColumns={@JoinColumn(name="regional")})
    private List<Regional> regionais;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="insitutos_evento", joinColumns={@JoinColumn(name="evento")}, inverseJoinColumns={@JoinColumn(name="instituto")})
    private List<Instituto> institutos ;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDataInicial() {
		return dataInicial;
	}
	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}
	public Date getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public List<Regional> getRegionais() {
		return regionais;
	}
	public void setRegionais(List<Regional> regionais) {
		this.regionais = regionais;
	}
	public List<Instituto> getInstitutos() {
		return institutos;
	}
	public void setInstitutos(List<Instituto> institutos) {
		this.institutos = institutos;
	}
}
