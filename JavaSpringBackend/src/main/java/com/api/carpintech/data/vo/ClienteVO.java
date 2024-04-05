package com.api.carpintech.data.vo;

import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class ClienteVO extends RepresentationModel<AgendaVO> implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;
    @Mapping("id")
    private Long id;
    private String nome;
    private String telefone;
    private String endereco;

    public ClienteVO(){}

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
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClienteVO clienteVO)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getId(), clienteVO.getId()) && Objects.equals(getNome(), clienteVO.getNome()) && Objects.equals(getTelefone(), clienteVO.getTelefone()) && Objects.equals(getEndereco(), clienteVO.getEndereco());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), getNome(), getTelefone(), getEndereco());
    }
}
