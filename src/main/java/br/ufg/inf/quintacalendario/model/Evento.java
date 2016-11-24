package br.ufg.inf.quintacalendario.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

//TODO : falta ajustar o mapeamento das classe, onetoone e many to one

@Entity
@Table(name = "evento")
public class Evento {
	
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate dataInicial;
    private LocalDate dataFinal;
    private String descricao;
    private String titulo;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoria")
    private Categoria categoria;
    
    @OneToMany(cascade=CascadeType.ALL)
    @JoinTable(name="regionais_evento", joinColumns={@JoinColumn(name="evento", referencedColumnName="id")}, inverseJoinColumns={@JoinColumn(name="regional", referencedColumnName="id")})
    private List<Regional> regionais;
    
//    private Instituto instituto;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getDataInicial() {
		return dataInicial;
	}
	public void setDataInicial(LocalDate dataInicial) {
		this.dataInicial = dataInicial;
	}
	public LocalDate getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(LocalDate dataFinal) {
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
	
//	public Regional getRegional() {
//		return regional;
//	}
//	public void setRegional(Regional regional) {
//		this.regional = regional;
//	}
//	public Instituto getInstituto() {
//		return instituto;
//	}
//	public void setInstituto(Instituto instituto) {
//		this.instituto = instituto;
//	}
}
