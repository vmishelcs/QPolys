package polynomial;

import number.RationalNumber;

import java.util.HashMap;
import java.util.TreeMap;

public class Term {
    protected RationalNumber coefficient;
    protected HashMap<VariableName, Monomial> monomialMap = new HashMap<>();

    /**
     * Creates a constant polynomial term.
     * @param coefficient Rational constant.
     */
    public Term(RationalNumber coefficient) {
        this.coefficient = coefficient;
    }

    /**
     * Creates a single-variable term with specified coefficient and variable with specified power.
     * @param coefficient The coefficient of the term.
     * @param variableName The variable that is multiplied by the coefficient.
     * @param power The power of the variable.
     */
    public Term(RationalNumber coefficient, VariableName variableName, int power) {
        this.coefficient = coefficient;
        Monomial m = new Monomial(variableName, power);
        monomialMap.put(variableName, m);
    }

    /**
     * Creates a multivariate polynomial term.
     * @param coefficient The coefficient of this term.
     * @param varPowerMap A variable-power mapping used to set up the monomials that make up this term.
     *                    Example: { (x, 2), (y, 2), (z, 5) } creates x^2y^2z^5.
     */
    public Term(RationalNumber coefficient, TreeMap<VariableName, Integer> varPowerMap) {
        this.coefficient = coefficient;
        for (var entry : varPowerMap.entrySet()) {
            monomialMap.put(entry.getKey(), new Monomial(entry.getKey(), entry.getValue()));
        }
    }

    public RationalNumber evaluateAt(TreeMap<VariableName, RationalNumber> varValueMap) {
        RationalNumber result = this.coefficient;
        for (var entry : varValueMap.entrySet()) {
            Monomial monomial = monomialMap.get(entry.getKey());
            if (monomial != null) {
                result = result.multiply(monomial.evaluateAt(entry.getValue()));
            }
        }
        return result;
    }

    @Override
    public String toString() {
        // Sort variable names.
        TreeMap<VariableName, Monomial> sorted = new TreeMap<>(monomialMap);
        StringBuilder result = new StringBuilder(coefficient.toString());
        for (var entry : sorted.entrySet()) {
            result.append(entry.getValue());
        }
        return result.toString();
    }
}
