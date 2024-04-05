/**
* Class Agenda.
* 
* Class that represents a work schedule of a company.
* 
* It has fields such as: id, description, date, type and employee.
* 
* The type field is a string that can be "work" or "holiday".
* 
* The employee field is an object of type Employee.
* 
* The class is annotated with @Entity and @Table(name = "agenda"),
* to be used by JPA.
* 
* 
*/
package com.api.carpintech.models;

import jakarta.persistence.*;

import java.util.Calendar;
import java.util.Objects;

@Entity
@Table(name = "agenda")
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descricao", nullable = false, length = 100)
    private String descricao;
    /**
     * Test with Calendar from java util, 
     * Joda Time is an option too.
    */
    @Column(name = "date", nullable = false)
    private Calendar date;

    @Column(name = "tipo", nullable = false, length = 20)
    private String tipo;

    @ManyToOne
    @JoinColumn(name = "funcionario_id", nullable = false)
    private Funcionario funcionario;

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

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Agenda agenda)) return false;
        return Objects.equals(getId(), agenda.getId()) && Objects.equals(getDescricao(), agenda.getDescricao()) && Objects.equals(getDate(), agenda.getDate()) && Objects.equals(getTipo(), agenda.getTipo()) && Objects.equals(getFuncionario(), agenda.getFuncionario());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDescricao(), getDate(), getTipo(), getFuncionario());
    }
}
