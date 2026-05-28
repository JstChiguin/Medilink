package com.medilink.usuario.repository;

import com.medilink.usuario.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByCorreoUsuario(String correoUsuario);
}
