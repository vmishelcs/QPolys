package polynomial;

import exceptions.MonomialPowerLessThanZeroException;
import number.RationalNumber;

public class Monomial {
    private VariableName variableName;
    private int power;

    public Monomial(VariableName variableName, int power) {
        if (power < 0) {
            throw new MonomialPowerLessThanZeroException();
        }
        this.variableName = variableName;
        this.power = power;
    }

    public RationalNumber evaluateAt(RationalNumber r) {
        if (power == 0) {
            return new RationalNumber(1, 1);
        }
        RationalNumber result = r;
        for (int i = 1; i < power; ++i) {
            result = result.multiply(r);
        }
        return result;
    }
}
