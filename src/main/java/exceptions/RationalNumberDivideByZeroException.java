package exceptions;

public class RationalNumberDivideByZeroException extends RuntimeException {
    public RationalNumberDivideByZeroException() {
        super("Cannot create a rational number with zero denominator.");
    }
}
