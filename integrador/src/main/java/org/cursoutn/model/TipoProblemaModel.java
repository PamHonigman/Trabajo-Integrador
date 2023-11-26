package org.cursoutn.model;

import jakarta.persistence.*;

@Entity
@Table(name="tipo_problema")
public class TipoProblemaModel {
    @Id
    @Column
    @GeneratedValue
    private int id;
    @Column
    private String nombreTipoProblema;
    @ManyToOne
    @JoinColumn(name="incidente_id", nullable = false)
    private IncidenteModel incidente;
}