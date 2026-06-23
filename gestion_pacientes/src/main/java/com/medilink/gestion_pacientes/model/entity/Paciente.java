package com.medilink.gestion_pacientes.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data

@Table(name = "pacientes")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paciente")
    private Long idPaciente;

    @Column(name = "id_usuario", nullable = false)
    private Long idUsuario;

    @Column(name = "numero_documento", nullable = false, unique = true, length = 20)
    private String numeroDocumento;

    @Column(name = "nombre_completo", nullable = false, length = 100)
    private String nombreCompletoPaciente;

    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @Column(name = "genero",nullable = false)
    private String genero;

    @Column(name = "estado_activo", nullable = false)
    private Boolean estadoActivo;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fechaRegistro;

    @OneToOne(mappedBy = "paciente", fetch = FetchType.LAZY)
    private PerfilPaciente perfilPaciente;

}
//CREAR_PACIENTE()
//ACTUALIZAR_PACIENTE()
//OBTENER_PACIENTE_POR_ID(idPaciente)
//LISTAR_PACIENTES()
//DESACTIVAR_PACIENTE(idPaciente)




