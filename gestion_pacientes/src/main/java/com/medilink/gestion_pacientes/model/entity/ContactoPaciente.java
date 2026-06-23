package com.medilink.gestion_pacientes.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "contacto_paciente" )
public class ContactoPaciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contacto")
    private Long idContacto;

    @OneToOne
    @JoinColumn(name = "id_paciente", nullable = false)
    private Paciente paciente;

    @Column(name = "telefono", nullable = false, length = 20)
    private String telefono;

    @Column(name = "direccion_paciente", nullable = false, length = 200)
    private String direccionPaciente;

    @Column(name = "nombre_contacto_emergencia", nullable = false, length = 100)
    private String nombreContactoEmergencia;

    @Column(name = "telefono_contacto_emergencia", nullable = false, length = 20)
    private String telefonoContactoEmergencia;

}


