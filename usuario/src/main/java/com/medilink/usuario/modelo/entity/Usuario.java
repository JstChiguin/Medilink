package com.medilink.usuario.modelo.entity;

import com.medilink.usuario.modelo.enums.EstadoUsuario;
import com.medilink.usuario.modelo.enums.RolUsuario;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "usuarios" )
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(name = "nombre_usuario",nullable = false, length = 150)
    private String nombreUsuario;

    @Column(name = "correo_usuario",nullable = false, unique = true, length = 150)
    private String correoUsuario;

    @Column(name = "contrasenna_usuario",nullable = false, length = 50)
    private String contrasennaUsuario;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol_usuario",nullable = false)
    private RolUsuario rolUsuario;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_usuario",nullable = false)
    private EstadoUsuario estadoUsuario;

    @Column( name = "fecha_registro", nullable = false, length = 50)
    private LocalDate fechaRegistroUsuario;

}
