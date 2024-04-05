package com.api.carpintech.data.vo;

import com.api.carpintech.models.Funcionario;
import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;
import java.util.UUID;

public class AgendaVO extends RepresentationModel<AgendaVO> implements Serializable
{

    @Serial
    private static final long serialVersionUID = 1L;

    @Mapping("id")
    private Long id;
    private Long key;
    private String descricao;
    private Calendar date;
    private String tipo;
    private Funcionario funcionario;

    public AgendaVO(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AgendaVO agendaVO)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getId(), agendaVO.getId()) && Objects.equals(getKey(), agendaVO.getKey()) && Objects.equals(getDescricao(), agendaVO.getDescricao()) && Objects.equals(getDate(), agendaVO.getDate()) && Objects.equals(getTipo(), agendaVO.getTipo()) && Objects.equals(getFuncionario(), agendaVO.getFuncionario());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), getKey(), getDescricao(), getDate(), getTipo(), getFuncionario());
    }
}
