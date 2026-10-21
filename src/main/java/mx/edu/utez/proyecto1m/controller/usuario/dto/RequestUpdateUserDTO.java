package mx.edu.utez.proyecto1m.controller.usuario.dto;

import jakarta.validation.constraints.NotBlank;

public class RequestUpdateUserDTO {

    @NotBlank(message = "El username no puede estar vacío")
    private String username;

    private String descripcion;
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}