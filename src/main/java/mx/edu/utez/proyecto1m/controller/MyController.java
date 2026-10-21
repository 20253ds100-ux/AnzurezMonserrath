package mx.edu.utez.proyecto1m.controller;

import mx.edu.utez.proyecto1m.controller.dto.RequestBodyDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin({"*"})
@RequestMapping("/my-services")
public class MyController {
    @GetMapping
    public String miPrimerServicio(){
        System.out.println("Hello world");
        return "Hello world";
    }
    @GetMapping("/segundo-servicio")
    public String srvicio2(){
        return "Este es mi segundo servicio";
    }
    @PostMapping
    public String servicio3(){
        return "servicio 3";
    }
    @GetMapping("/pathVariable/{id}")
    public String pathVariable(@PathVariable String id){
        System.out.println("El id es: " + id);
        return "El id es: " + id;
    }
    @PostMapping("/request-body")
    public String requestBody(@RequestBody RequestBodyDTO payload){
        System.out.println(payload.getNombre());
        System.out.println(payload.getEdad());
        System.out.println(payload.getCorreo());
        return "Servicio con cuerpo";
    }
}
