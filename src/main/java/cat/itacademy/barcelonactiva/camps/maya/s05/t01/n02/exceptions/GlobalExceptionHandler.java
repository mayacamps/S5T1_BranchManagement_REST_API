package cat.itacademy.barcelonactiva.camps.maya.s05.t01.n02.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.util.BindErrorUtils;

@ControllerAdvice
public class GlobalExceptionHandler{
    @ExceptionHandler(BranchNotFoundException.class)
    public ResponseEntity<String> BranchNotFoundException(BranchNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(BranchAlreadyExistsException.class)
    public ResponseEntity<String> BranchAlreadyExistsException(BranchAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(CountryDoesNotExistException.class)
    public ResponseEntity<String> CountryDoesNotExistException(CountryDoesNotExistException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> MethodArgumentNotValidException(MethodArgumentNotValidException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Input error.\n\t" + BindErrorUtils.resolveAndJoin(ex.getFieldErrors()));
    }
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> MethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error.\n\t" + ex.getMessage());
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> allOtherExceptions(Exception ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Uh oh - Something went wrong.\n\t" + ex.getMessage());
    }
}
