package com.medilink.usuario.controller;

import com.medilink.usuario.Service.UsuarioService;
import com.medilink.usuario.dto.request.UsuarioRequest;
import com.medilink.usuario.dto.response.UsuarioResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor


public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>>obtenerTodo(){
        return ResponseEntity.ok(usuarioService.obtenerTodo());
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<UsuarioResponse> obtenerUsuario(@PathVariable Long idUsuario){
        return ResponseEntity.ok().body(usuarioService.obtenerPorId(idUsuario));
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> crearUsuario(@Valid @RequestBody UsuarioRequest usuarioRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.crear(usuarioRequest));
    }
    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Long idUsuario){
        usuarioService.eliminar(idUsuario);
        return ResponseEntity.ok("Usuario eliminado correctamente");
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<UsuarioResponse> actualizarUsuario( @PathVariable Long idUsuario,@Valid @RequestBody UsuarioRequest usuarioRequest){
        return ResponseEntity.ok().body(usuarioService.actualizar(idUsuario, usuarioRequest));
    }
}
