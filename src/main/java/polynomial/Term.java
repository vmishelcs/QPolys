package polynomial;

import exceptions.TermEvaluationMissingVariable;
import number.RationalNumber;

import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class Term implements Comparable<Term> {
    protected RationalNumber coefficient;
    protected TreeMap<VariableName, Integer> mapVarPower = new TreeMap<>();

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
        this.mapVarPower.put(variableName, power);
    }

    /**
     * Creates a multivariate polynomial term.
     * @param coefficient The coefficient of this term.
     * @param mapVarPower A variable-power mapping used to set up the monomials that make up this term.
     *                    Example: { (x, 2), (y, 2), (z, 5) } creates x^2y^2z^5.
     */
    public Term(RationalNumber coefficient, Map<VariableName, Integer> mapVarPower) {
        this.coefficient = coefficient;
        this.mapVarPower.putAll(mapVarPower);
    }

    /**
     * Evaluates this term at a given point.
     * @param mapVarValue Variable-to-rational map used to plug in values.
     * @return Rational number that is a result of plugging in the specified values.
     */
    public RationalNumber evaluateAt(Map<VariableName, RationalNumber> mapVarValue) {
        RationalNumber result = this.coefficient;
        // For each variable, get its value from mapVarValue.
        for (var entry : this.mapVarPower.entrySet()) {
            RationalNumber varValue = mapVarValue.get(entry.getKey());
            if (varValue == null) {
                throw new TermEvaluationMissingVariable();
            }
            int power = entry.getValue();
            // Multiply result by value^(power).
            result = result.multiply(varValue.pow(power));
        }
        return result;
    }

    @Override
    public String toString() {
        // Sort variable names.
        StringBuilder result = new StringBuilder(coefficient.toString());
        for (var entry : this.mapVarPower.entrySet()) {
            result.append(entry.getKey());
            int power = entry.getValue();
            if (power > 1) {
                result.append("^").append(power);
            }
        }
        return result.toString();
    }

    /**
     * Compares polynomial terms using Graded Lexicographic (grlex) ordering. Grlex adheres to the following
     * definition:
     * Let a and b be vectors of nonnegative integers of length n. We say that a > b if |a| = sum(a_i) > |b| = sum(b_i),
     * or if |a| = |b| and the left-most nonzero entry of a - b is positive.
     * @param otherTerm the term to be compared.
     * @return Positive integer, negative integer, or zero if this term is greater than, less than, or equal to
     *         other term.
     */
    @Override
    public int compareTo(Term otherTerm) {
        // First, get sums of powers.
        int thisPowerSum = 0;
        for (var entry : this.mapVarPower.entrySet()) {
            thisPowerSum += entry.getValue();
        }
        int otherPowerSum = 0;
        for (var entry : otherTerm.mapVarPower.entrySet()) {
            otherPowerSum += entry.getValue();
        }
        // Check if we the sums are different.
        if (thisPowerSum > otherPowerSum) {
            return 1;
        }
        else if (otherPowerSum > thisPowerSum) {
            return -1;
        }

        // If we are here, the sums are the same.
        // Get a sorted set of variables of both terms.
        TreeSet<VariableName> varSet = new TreeSet<>(this.mapVarPower.keySet());
        varSet.addAll(otherTerm.mapVarPower.keySet());

        for (var currentVariable : varSet) {
            Integer thisPower = this.mapVarPower.get(currentVariable);
            Integer otherPower = otherTerm.mapVarPower.get(currentVariable);
            if (thisPower == null) {
                // If we are here, this term does not have currentVariable. This means that this term must be "less"
                // than the other term.
                return -1;
            }
            if (otherPower == null) {
                // If we are here, the other term does not have currentVariable. This means that this term must be
                // "greater" than the other term.
                return 1;
            }

            // Here, both terms have currentVariable, so take the difference of powers of currentVariable.
            int powerDifference = thisPower - otherPower;
            if (powerDifference > 0) {
                // If we are here, this term has higher power of currentVariable, so it must be "greater."
                return 1;
            }
            if (powerDifference < 0) {
                // If we are here, this term has smaller power of currentVariable, so it must be "less."
                return -1;
            }
        }

        // If we are here, the terms must be equal.
        return 0;
    }
}
