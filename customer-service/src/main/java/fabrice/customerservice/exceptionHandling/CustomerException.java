package fabrice.customerservice.exceptionHandling;

public class CustomerException extends RuntimeException {
    public CustomerException(String message) {
        super(message);
    }
}
