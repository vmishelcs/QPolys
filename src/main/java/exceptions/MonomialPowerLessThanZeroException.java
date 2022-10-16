package exceptions;

public class MonomialPowerLessThanZeroException extends RuntimeException {
    public MonomialPowerLessThanZeroException() {
        super("Monomial variable power must be greater than or equal to 0.");
    }
}
