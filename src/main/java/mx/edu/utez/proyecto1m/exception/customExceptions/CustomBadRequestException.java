package mx.edu.utez.proyecto1m.exception.customExceptions;

public class CustomBadRequestException  extends RuntimeException {
    public CustomBadRequestException(String mensaje){
        super(mensaje)
        ;
    }
}