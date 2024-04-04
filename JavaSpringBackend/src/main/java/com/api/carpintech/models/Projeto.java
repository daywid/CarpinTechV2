package com.api.carpintech.models;

import com.api.carpintech.models.enums.ProjetoStatus;
import jakarta.persistence.*;

import java.util.UUID;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "projeto")
public class Projeto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;


  @Column(name = "nome", nullable = false, length = 100)
  private String nome;

  @Column(name = "descricao", length = 100)
  private String descricao;

  @Column(name = "valor", nullable = false)
  private Double valor;

  // Test this:
  @OneToMany(mappedBy = "projeto", fetch = FetchType.EAGER)
  private List<Tarefa> tarefas;

  @Column(name = "status", nullable = false)
  private ProjetoStatus status;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public Double getValor() {
    return valor;
  }

  public void setValor(Double valor) {
    this.valor = valor;
  }

  public List<Tarefa> getTarefas() {
    return tarefas;
  }

  public void setTarefas(List<Tarefa> tarefas) {
    this.tarefas = tarefas;
  }

  public ProjetoStatus getStatus() {
    return status;
  }

  public void setStatus(ProjetoStatus status) {
    this.status = status;
  }


}
