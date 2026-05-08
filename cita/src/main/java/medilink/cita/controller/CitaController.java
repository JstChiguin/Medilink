package medilink.cita.controller;

import lombok.AllArgsConstructor;
import medilink.cita.model.Cita;
import medilink.cita.service.CitaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/api/v1/citas")
@AllArgsConstructor
public class CitaController {

    private final CitaService citaService;

    @GetMapping("/{id}")
            public ResponseEntity

    CitaService citaService = new CitaService();

    @GetMapping
    public ResponseEntity<ArrayList<Cita>> listar(){
        return ResponseEntity.status(200).body(citaService.listar());
    }


    //@GetMapping("/obtener-cita-fecha/{fecha}")
    //public ResponseEntity<ArrayList<Cita>> citaFecha(@PathVariable String fecha){
    //    return ResponseEntity.status(200).body(citaService.citaFecha(fecha));
    //}

    @PostMapping
    public ResponseEntity<Cita> crear (@Valid @RequestBody CreaCitaRequest citaRequest){
        Cita cita = new Cita(citaRequest.getId(), citaRequest.getUsuario(), citaRequest.getDescripcion(), citaRequest.getFecha());
        Cita citaNuevo = citaService.crear(cita);
        return  ResponseEntity.status(201).body(citaNuevo);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> eliminar (@PathVariable Long id) {
        boolean isEliminado = citaService.eliminar(id);
        if (isEliminado) {
            return ResponseEntity.status(204).body("Se ha eliminado la cita");
        } else{
            return ResponseEntity.status(404).body("No se ha eliminado la cita");
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Cita> actualizar (@PathVariable Long id, @RequestBody Cita cita){
        Cita citaActualizadoActualizado = citaService.actualizar(id, cita);
        return ResponseEntity.status(200).body(citaActualizado);
    }



}
