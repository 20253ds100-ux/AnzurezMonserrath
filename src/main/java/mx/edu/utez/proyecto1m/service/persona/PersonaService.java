package mx.edu.utez.proyecto1m.service.persona;

import jakarta.transaction.Transactional;
import mx.edu.utez.proyecto1m.controller.usuario.dto.RequestUpdatePersonaDTO;
import mx.edu.utez.proyecto1m.exception.customExceptions.CustomBadRequestException;
import mx.edu.utez.proyecto1m.model.persona.Persona;
import mx.edu.utez.proyecto1m.repository.persona.PersonaRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonaService {

    private final PersonaRepository personaRepository;

    public PersonaService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public Persona getPersonaById(Long id) {
        return personaRepository.findById(id)
                .orElseThrow(() -> new CustomBadRequestException("La persona con ID " + id + " no existe"));
    }

    @Transactional
    public Persona updatePersona(Long id, RequestUpdatePersonaDTO payload) {
        Persona persona = getPersonaById(id);

        persona.setNombre(payload.getNombres());
        persona.setPrimerApellido(payload.getPrimerApellido());
        persona.setSegundoApellido(payload.getSegundoApellido());
        persona.setCorreo(payload.getCorreo());
        persona.setCurp(payload.getCurp());
        persona.setFechaNacimiento(payload.getFechaNacimiento());

        return personaRepository.save(persona);
    }
}