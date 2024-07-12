package fiap.salaobeleza.handler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import fiap.salaobeleza.exception.ResourceNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
        // Cria um objeto de erro (você pode querer criar uma classe para isso)
        // que contém a mensagem de erro e outros detalhes que você deseja retornar.
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND.value(), ex.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    // Classe interna para detalhes do erro
    private static class ErrorDetails {
        private int statusCode;
        private String message;
        private long timestamp;

        public ErrorDetails(int statusCode, String message, long timestamp) {
            this.statusCode = statusCode;
            this.message = message;
            this.timestamp = timestamp;
        }

        // Getters
        public int getStatusCode() {
            return statusCode;
        }

        public String getMessage() {
            return message;
        }

        public long getTimestamp() {
            return timestamp;
        }
    }
}