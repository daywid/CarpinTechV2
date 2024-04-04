package com.api.carpintech.models;

import com.api.carpintech.models.enums.RelatorioStatus;
import jakarta.persistence.*;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression.DateTime;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "relatorio")
public class Relatorio {
    
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", nullable = false, columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "conteudo", nullable = false, length = 100)
    private String conteudo;
    
    @Column(name = "dataCriacao", nullable = false)
    private DateTime dataCriacao;
    
    @ManyToOne
    @JoinColumn(name = "funcionario_id", nullable = false)
    private Funcionario funcionario;
    
    @Column(name = "status", nullable = false)
    private RelatorioStatus status;
    
    @Column(name = "titulo", nullable = false, length = 100)
    private String titulo;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public DateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(DateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public RelatorioStatus getStatus() {
        return status;
    }

    public void setStatus(RelatorioStatus status) {
        this.status = status;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    
}
