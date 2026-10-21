package mx.edu.utez.proyecto1m.controller.usuario;

import jakarta.validation.Valid;
import mx.edu.utez.proyecto1m.controller.usuario.dto.RequestUpdatePersonaDTO;
import mx.edu.utez.proyecto1m.model.persona.Persona;
import mx.edu.utez.proyecto1m.service.persona.PersonaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/persona")
public class PersonaController {

    private final PersonaService personaService;

    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Persona> getById(@PathVariable Long id) {
        return ResponseEntity.ok(personaService.getPersonaById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Persona> updatePersona(@PathVariable Long id, @Valid @RequestBody RequestUpdatePersonaDTO dto) {
        return ResponseEntity.ok(personaService.updatePersona(id, dto));
    }
}