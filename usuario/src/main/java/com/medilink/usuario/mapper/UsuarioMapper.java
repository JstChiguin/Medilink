package com.medilink.usuario.mapper;

import com.medilink.usuario.dto.request.UsuarioRequest;
import com.medilink.usuario.dto.response.UsuarioResponse;
import com.medilink.usuario.modelo.enums.EstadoUsuario;
import com.medilink.usuario.modelo.enums.RolUsuario;
import com.medilink.usuario.modelo.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(
        componentModel = "spring",
        imports = {
                RolUsuario.class,
                EstadoUsuario.class
        }
)
public interface UsuarioMapper {

    @Mapping(
            target = "rolUsuario",
            expression = "java(RolUsuario.fromValor(usuarioRequest.getRolUsuario()))"
    )
    @Mapping(
            target = "estadoUsuario",
            expression = "java(EstadoUsuario.fromValor(usuarioRequest.getEstadoUsuario()))"
    )
    Usuario toEntity(UsuarioRequest usuarioRequest);

    UsuarioResponse toResponse(Usuario usuario);

    List<UsuarioResponse> toResponseList(List<Usuario> usuarioList);
}