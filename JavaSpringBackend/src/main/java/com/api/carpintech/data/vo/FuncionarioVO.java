package com.api.carpintech.data.vo;

import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class FuncionarioVO extends RepresentationModel<AgendaVO> implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;

    @Mapping("id")
    private Long id;
    private Long key;
    private String nome;
    private String funcao;
    private Double salario;
    private Double horasTrabalhadas;

    public FuncionarioVO(){}

    public Long getKey() {
        return key;
    }

    public void setKey(Long id) {
        this.key = id;
    }

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

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public Double getHorasTrabalhadas() {
        return horasTrabalhadas;
    }

    public void setHorasTrabalhadas(Double horasTrabalhadas) {
        this.horasTrabalhadas = horasTrabalhadas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FuncionarioVO that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(id, that.id) && Objects.equals(getKey(), that.getKey()) && Objects.equals(getNome(), that.getNome()) && Objects.equals(getFuncao(), that.getFuncao()) && Objects.equals(getSalario(), that.getSalario()) && Objects.equals(getHorasTrabalhadas(), that.getHorasTrabalhadas());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, getKey(), getNome(), getFuncao(), getSalario(), getHorasTrabalhadas());
    }
}

