package com.api.carpintech.models;

import com.api.carpintech.models.enums.ProjetoStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.UUID;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "projeto")
public class Projeto {
    
  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(name = "id", nullable = false, columnDefinition = "BINARY(16)")
  private UUID id;  

  @Column(name = "nome", nullable = false, length = 100)
  private String nome;

  @Column(name = "descricao", length = 100)
  private String descricao;

  @Column(name = "valor", nullable = false)
  private Double valor;

  // Test this:
  @OneToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "projeto_id", joinColumns = {@JoinColumn(name = "tarefas_id")}, 
  inverseJoinColumns = {@JoinColumn(name = "id_projeto")})
  @Column(name = "tarefas", nullable = false)
  private List<Tarefa> tarefas;

  @Column(name = "status", nullable = false)
  private ProjetoStatus status;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
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
