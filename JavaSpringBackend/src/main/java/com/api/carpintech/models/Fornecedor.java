package com.api.carpintech.models;

import jakarta.persistence.*;

import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "fornecedor")
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "nome", nullable = false, length = 100)
    private String nome;


    @Column(name = "telefone", nullable = false, length = 100)
    private String telefone;

    @Column(name = "endereco", nullable = false, length = 100)
    private String endereco;

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

}
