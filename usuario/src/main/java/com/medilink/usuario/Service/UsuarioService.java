package com.medilink.usuario.Service;

import com.medilink.usuario.dto.request.UsuarioRequest;
import com.medilink.usuario.dto.response.UsuarioResponse;
import com.medilink.usuario.exception.UsuarioNoEncontradoExeption;
import com.medilink.usuario.mapper.UsuarioMapper;
import com.medilink.usuario.modelo.Usuario;
import com.medilink.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class  UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public List<UsuarioResponse> obtenerTodo(){
        return usuarioMapper.toResponseList(usuarioRepository.findAll());
    }

     public UsuarioResponse obtenerPorId(Long idBuscado){
        return usuarioMapper.toResponse(usuarioRepository
                .findById(idBuscado)
                .orElseThrow(() -> new UsuarioNoEncontradoExeption(idBuscado)));
     }

     public UsuarioResponse crear(UsuarioRequest usuarioRequest){
        return usuarioMapper.toResponse(usuarioRepository.save(usuarioMapper.toEntity(usuarioRequest)));
     }

     public void eliminar(Long idBuscado){
        usuarioRepository.deleteById(idBuscado);
     }

     public UsuarioResponse actualizar(Long idBuscado, UsuarioRequest usuarioRequest){
        Usuario usuarioExistente = usuarioRepository
                 .findById(idBuscado)
                 .orElseThrow(() -> new UsuarioNoEncontradoExeption(idBuscado));

        usuarioExistente.setNombreUsuario(usuarioRequest.getNombreUsuario());
        usuarioExistente.setCorreoUsuario(usuarioRequest.getCorreoUsuario());
        usuarioExistente.setContrasennaUsuario(usuarioRequest.getContrasennaUsuario());
        usuarioExistente.setRolUsuario(usuarioRequest.getRolUsuario());
        usuarioExistente.setEstadoUsuario(usuarioRequest.getEstadoUsuario());

        return usuarioMapper.toResponse(usuarioRepository.save(usuarioExistente));

     }

}
