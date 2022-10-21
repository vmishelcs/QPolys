package polynomial;

import exceptions.TermConstructorException;
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
     * Creates a term with specified coefficient and variable with specified power.
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
     * Creates a term with specified coefficient and variables with specified powers.
     * @param coefficient The coefficient of the term.
     * @param variableNames The array of variable names (e.g.: x, y, z, etc).
     * @param powers The array of powers corresponding to the variable names; that is, powers[i] corresponds to
     *               the power of variableNames[i].
     */
    public Term(RationalNumber coefficient, VariableName[] variableNames, int[] powers) {
        // Make sure variablesNames and powers arrays are the same length.
        if (variableNames.length != powers.length) {
            throw new TermConstructorException(
                    "VariableName array and powers array must be the same length."
            );
        }

        this.coefficient = coefficient;
        for (int i = 0; i < variableNames.length; ++i) {
            Monomial m = new Monomial(variableNames[i], powers[i]);
            Monomial putResult = monomialMap.put(variableNames[i], m);
            if (putResult != null) {
                throw new TermConstructorException(
                        "Every variable name in the term must be unique."
                );
            }
        }
    }

    public RationalNumber evaluateAt(RationalNumber r) {
        RationalNumber result = this.coefficient;
        for (var key : monomialMap.keySet()) {
            result = result.multiply(monomialMap.get(key).evaluateAt(r));
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
