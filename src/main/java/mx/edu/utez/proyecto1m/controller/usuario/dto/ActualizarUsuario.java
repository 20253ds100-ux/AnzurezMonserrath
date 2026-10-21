package mx.edu.utez.proyecto1m.controller.usuario.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActualizarUsuario {

    @NotBlank(message = "Username no puede estar vacio")
    @Size(min = 6, max = 50, message = "El username debe tener entre 3 y 50 caracteres")
    private String username;

    private String descripcion;
}