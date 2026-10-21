package mx.edu.utez.proyecto1m.controller.usuario;

import jakarta.validation.Valid;
import mx.edu.utez.proyecto1m.controller.usuario.dto.RequestCreateUserDTO;
import mx.edu.utez.proyecto1m.controller.usuario.dto.RequestUpdateUserDTO;
import mx.edu.utez.proyecto1m.model.usuario.Usuario;
import mx.edu.utez.proyecto1m.service.usuario.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsers() {
        return ResponseEntity
                .status(200)
                .body(usuarioService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUserById(@PathVariable("id") long id) {
        return ResponseEntity
                .status(200)
                .body(usuarioService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<Usuario> createUser(@RequestBody @Valid RequestCreateUserDTO payload) {
        return ResponseEntity
                .status(201)
                .body(usuarioService.createUser(payload));
    }
    @PatchMapping("/{id}/status")
    public ResponseEntity<Usuario> toggleUserStatus(@PathVariable("id") long id, @RequestParam("status") boolean status) {
        return ResponseEntity
                .status(200)
                .body(usuarioService.toggleUserStatus(id, status));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUser(@PathVariable("id") long id, @RequestBody @Valid RequestUpdateUserDTO payload) {
        return ResponseEntity
                .status(200)
                .body(usuarioService.updateUser(id, payload));
    }
}