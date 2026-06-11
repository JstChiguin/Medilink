package com.medilink.usuario.Service;

import com.medilink.usuario.dto.request.UsuarioRequest;
import com.medilink.usuario.dto.response.UsuarioResponse;
import com.medilink.usuario.exception.CorreoDuplicadoException;
import com.medilink.usuario.exception.UsuarioNoEncontradoExeption;
import com.medilink.usuario.mapper.UsuarioMapper;
import com.medilink.usuario.modelo.EstadoUsuario;
import com.medilink.usuario.modelo.RolUsuario;
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

         if (usuarioRepository.existsByCorreoUsuario(usuarioRequest.getCorreoUsuario())) {
             throw new CorreoDuplicadoException(usuarioRequest.getCorreoUsuario()
             );
         }

         return usuarioMapper.toResponse(usuarioRepository.save(usuarioMapper.toEntity(usuarioRequest)));
     }
     public void eliminar(Long idBuscado){

        if (!usuarioRepository.existsById(idBuscado)) {
            throw new UsuarioNoEncontradoExeption(idBuscado);
        }

        usuarioRepository.deleteById(idBuscado);
     }

     public UsuarioResponse actualizar(Long idBuscado, UsuarioRequest usuarioRequest){
        Usuario usuarioExistente = usuarioRepository
                 .findById(idBuscado)
                 .orElseThrow(() -> new UsuarioNoEncontradoExeption(idBuscado));

        usuarioExistente.setNombreUsuario(usuarioRequest.getNombreUsuario());
        usuarioExistente.setCorreoUsuario(usuarioRequest.getCorreoUsuario());
        usuarioExistente.setContrasennaUsuario(usuarioRequest.getContrasennaUsuario());

         usuarioExistente.setRolUsuario(RolUsuario.fromValor(usuarioRequest.getRolUsuario()));
         usuarioExistente.setEstadoUsuario(EstadoUsuario.fromValor(usuarioRequest.getEstadoUsuario()));

        return usuarioMapper.toResponse(usuarioRepository.save(usuarioExistente));

     }

}
