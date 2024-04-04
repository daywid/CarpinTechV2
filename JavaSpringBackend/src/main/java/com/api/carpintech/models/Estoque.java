package com.api.carpintech.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.UUID;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "estoque")
public class Estoque {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", nullable = false, columnDefinition = "BINARY(16)")
    private UUID id;
    
    // @ManyToMany(fetch = FetchType.EAGER)
	// @JoinTable(name = "user_permission", joinColumns = {@JoinColumn (name = "id_user")},
	// 	inverseJoinColumns = {@JoinColumn (name = "id_permission")}
	// )
	// private List<Permission> permissions;
	

    // test this:
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "estoque_id", joinColumns = {@JoinColumn(name = "materiais_id")}, 
    inverseJoinColumns = {@JoinColumn(name = "id_estoque")})
    private List<Material> materiais;

    @Column(name = "quantidade", nullable = false)
    private int quantidade;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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
