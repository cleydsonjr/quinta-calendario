package br.ufg.inf.quintacalendario.model;

import java.time.LocalDate;

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
//    private Categoria categoria;
//    private Regional regional;
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
//	public Categoria getCategoria() {
//		return categoria;
//	}
//	public void setCategoria(Categoria categoria) {
//		this.categoria = categoria;
//	}
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
