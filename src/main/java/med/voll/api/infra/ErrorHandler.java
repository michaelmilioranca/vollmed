package med.voll.api.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity handler404() {
    return ResponseEntity.notFound().build();
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity handler400(MethodArgumentNotValidException exception) {
    var errors = exception.getFieldErrors();
    return ResponseEntity.badRequest().body(errors.stream().map(ValidationError::new).toList());
  }

  private record ValidationError(String field, String message) {
    public ValidationError(FieldError error) {
      this(error.getField(), error.getDefaultMessage());
    }
  }
}
