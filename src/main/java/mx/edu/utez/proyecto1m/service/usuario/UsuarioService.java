package mx.edu.utez.proyecto1m.service.usuario;

import jakarta.transaction.Transactional;
import mx.edu.utez.proyecto1m.controller.usuario.dto.RequestCreateUserDTO;
import mx.edu.utez.proyecto1m.controller.usuario.dto.RequestUpdateUserDTO;
import mx.edu.utez.proyecto1m.exception.customExceptions.CustomBadRequestException;
import mx.edu.utez.proyecto1m.model.persona.Persona;
import mx.edu.utez.proyecto1m.model.usuario.Roles;
import mx.edu.utez.proyecto1m.model.usuario.Usuario;
import mx.edu.utez.proyecto1m.repository.persona.PersonaRepository;
import mx.edu.utez.proyecto1m.repository.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PersonaRepository personaRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, PersonaRepository personaRepository){
        this.usuarioRepository = usuarioRepository;
        this.personaRepository = personaRepository;
    }

    public List<Usuario> getAllUsers(){
        return usuarioRepository.findAll();
    }
    public Usuario getUserById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new CustomBadRequestException("El usuario con ID " + id + " no existe"));
    }
    @Transactional
    public Usuario toggleUserStatus(Long id, boolean status) {
        Usuario usuario = getUserById(id);
        usuario.setEnable(status);
        return usuarioRepository.save(usuario);
    }
    @Transactional
    public Usuario updateUser(Long id, RequestUpdateUserDTO payload) {
        Usuario usuario = getUserById(id);
        usuarioRepository.findByUsername(payload.getUsername()).ifPresent(u -> {
            if (!u.getId().equals(id)) {
                throw new CustomBadRequestException("El username ya está en uso");
            }
        });
        usuario.setUsername(payload.getUsername());
        usuario.setDescripcion(payload.getDescripcion());

        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario createUser(RequestCreateUserDTO payload){
        if(usuarioRepository.findByUsername(payload.getUsername()).isPresent()){
            throw new CustomBadRequestException("El user ya está en uso");
        }

        Persona nuevaPersona = new Persona();
        nuevaPersona.setNombre(payload.getNombres());
        nuevaPersona.setPrimerApellido(payload.getPrimerApellido());
        nuevaPersona.setSegundoApellido(payload.getSegundoApellido());
        nuevaPersona.setCorreo(payload.getCorreo());
        nuevaPersona.setCurp(payload.getCurp());
        nuevaPersona.setFechaNacimiento(payload.getFechaNacimiento());

        nuevaPersona = personaRepository.save(nuevaPersona);

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setUsername(payload.getUsername());
        nuevoUsuario.setPassword(payload.getPassword());
        nuevoUsuario.setDescripcion(payload.getDescripcion());
        nuevoUsuario.setEnable(true);
        nuevoUsuario.setPersona(nuevaPersona);
        nuevoUsuario.setRol(
                Roles.valueOf(payload.getRol())
        );
        return usuarioRepository.save(nuevoUsuario);
    }
}