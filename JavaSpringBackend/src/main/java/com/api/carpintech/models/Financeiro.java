package com.api.carpintech.models;

import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;
import jakarta.persistence.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;

@Entity
@Table(name = "financeiro")
public class Financeiro {
    
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", nullable = false, columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "custos_materiais", nullable = false)
    private Double custosMateriais;

    @Column(name = "salarios_funcionarios", nullable = false)
    private Double salariosFuncionarios;

    @Column(name = "pagamentos_clientes", nullable = false)
    private Double pagamentosClientes;

    @Column(name = "despesas_operacionais", nullable = false)
    private Double despesasOperacionais;

    public Financeiro(double custosMateriais, double salariosFuncionarios, double pagamentosClientes, double despesasOperacionais) {
        this.custosMateriais = custosMateriais;
        this.salariosFuncionarios = salariosFuncionarios;
        this.pagamentosClientes = pagamentosClientes;
        this.despesasOperacionais = despesasOperacionais;
    }

    public Financeiro() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Double getCustosMateriais() {
        return custosMateriais;
    }

    public void setCustosMateriais(Double custosMateriais) {
        this.custosMateriais = custosMateriais;
    }

    public Double getSalariosFuncionarios() {
        return salariosFuncionarios;
    }

    public void setSalariosFuncionarios(Double salariosFuncionarios) {
        this.salariosFuncionarios = salariosFuncionarios;
    }

    public Double getPagamentosClientes() {
        return pagamentosClientes;
    }

    public void setPagamentosClientes(Double pagamentosClientes) {
        this.pagamentosClientes = pagamentosClientes;
    }

    public Double getDespesasOperacionais() {
        return despesasOperacionais;
    }

    public void setDespesasOperacionais(Double despesasOperacionais) {
        this.despesasOperacionais = despesasOperacionais;
    }

    
    
}
