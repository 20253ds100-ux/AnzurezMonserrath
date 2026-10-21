package mx.edu.utez.proyecto1m.repository.persona;

import mx.edu.utez.proyecto1m.model.persona.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository <Persona, Long>{
}
