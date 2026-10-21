package mx.edu.utez.proyecto1m.repository.usuario;

import mx.edu.utez.proyecto1m.model.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Optional<Usuario> findByUsername(String username);
    Optional<Usuario> findById(long id);
}
