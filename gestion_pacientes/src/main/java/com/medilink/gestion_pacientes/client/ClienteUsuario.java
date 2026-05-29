package com.medilink.gestion_pacientes.client;

import com.medilink.gestion_pacientes.dto.response.UsuarioResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "usuario-paciente",
        url = "${usuario.url}")
public interface ClienteUsuario {
@GetMapping("/{id}")
UsuarioResponse obtenerUsuarioPorId(@PathVariable("id")Long idUsuario);
}
