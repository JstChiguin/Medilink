package com.medilink.gestion_pacientes.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "perfil_pacientes" )
public class PerfilPaciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_perfil")
    private Long idPerfil;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_paciente", nullable = false)
    private Paciente paciente;

    @Column(name = "antecedentes_medicos", columnDefinition = "TEXT")
    private String antecedentesMedicos;

    @Column(name = "alergias", columnDefinition = "TEXT")
    private String alergias;

    @Column(name = "medicamentos_actuales", columnDefinition = "TEXT")
    private String medicamentosActuales;

    @Column(name = "informacion_relevante", columnDefinition = "TEXT")
    private String informacionRelevante;

    @Column(name = "ultima_actualizacion", nullable = false)
    private LocalDateTime ultimaActualizacion;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_contacto", nullable = false)
    private ContactoPaciente contactoPaciente;

}
//    CREAR_PERFIL()
//    ACTUALIZAR_PERFIL()
//    OBTENER_PERFIL_POR_PACIENTE(idPaciente)
//