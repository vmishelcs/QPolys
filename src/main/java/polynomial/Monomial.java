package polynomial;

import exceptions.MonomialPowerLessThanZeroException;
import number.RationalNumber;

/**
 * Monomial class. Stores the monomial variable and the power to which said variable is raised to. The power must be
 * nonnegative.
 * e.g.: x^2 -> variableName = x, power = 2
 */
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
        return r.pow(power);
    }

    @Override
    public String toString() {
        String result = variableName.toString();
        if (power > 1) {
            result += "^" + power;
        }
        return result;
    }
}
