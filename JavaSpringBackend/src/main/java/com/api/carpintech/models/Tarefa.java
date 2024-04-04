package com.api.carpintech.models;

import jakarta.persistence.*;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression.DateTime;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tarefa")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "descricao", length = 100)
    private String descricao;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "dataInicio")
    private DateTime dataInicio;

    @Column(name = "dataFinalizacao")
    private DateTime dataFinalizacao;

    @ManyToOne
    @JoinColumn(name = "projeto_id", nullable = false)
    private Projeto projeto;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(DateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public DateTime getDataFinalizacao() {
        return dataFinalizacao;
    }

    public void setDataFinalizacao(DateTime dataFinalizacao) {
        this.dataFinalizacao = dataFinalizacao;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    
    
}
