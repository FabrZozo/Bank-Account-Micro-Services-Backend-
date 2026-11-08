package fabrice.customerservice2.exceptionHandling;

public class CustomerException extends RuntimeException {
    public CustomerException(String message) {
        super(message);
    }
}
