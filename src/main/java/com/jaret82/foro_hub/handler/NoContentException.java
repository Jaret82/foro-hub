package com.jaret82.foro_hub.handler;

public class NoContentException extends  RuntimeException{

    public NoContentException(String mensaje){
        super(mensaje);
    }
}
