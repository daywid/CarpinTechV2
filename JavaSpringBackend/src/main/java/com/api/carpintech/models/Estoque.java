package com.api.carpintech.models;

import jakarta.persistence.*;

import java.util.UUID;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "estoque")
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "estoque", fetch = FetchType.EAGER)
    private List<Material> materiais;

    @Column(name = "quantidade", nullable = false)
    private int quantidade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Material> getMateriais() {
        return materiais;
    }

    public void setMateriais(List<Material> materiais) {
        this.materiais = materiais;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
}
