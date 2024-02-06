package cat.itacademy.barcelonactiva.camps.maya.s05.t01.n02.exceptions;

public class CountryDoesNotExistException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public CountryDoesNotExistException(String message){super(message);}
}
