package library.demo.controller.Exceptions;

import org.springframework.core.NestedCheckedException;

public class AlreadyExistException extends NestedCheckedException {
    String message;

    public AlreadyExistException(String message){
        super(message);
    }
}
