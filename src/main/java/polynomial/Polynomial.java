package polynomial;

import number.RationalNumber;

import java.util.Set;
import java.util.TreeSet;

public class Polynomial {
    private TreeSet<VariableName> varSet = new TreeSet<>();
    private TreeSet<Term> terms = new TreeSet<>();

    /**
     * Creates the zero polynomial.
     */
    public Polynomial() {
        this.terms.add(new Term(RationalNumber.zero));
    }

    /**
     * Creates a polynomial with the specified terms.
     * @param terms An array of terms for the polynomial.
     */
    public Polynomial(Term... terms) {
        for (Term term : terms) {
            this.varSet.addAll(term.getVariableSet());
            this.terms.add(term);
        }
    }

    public Set<VariableName> getVariableSet() {
        return this.varSet;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        // Iterate through all terms in DESCENDING order and append to resulting string.
        for (Term term : terms.descendingSet()) {
            result.append(term.toString());
            // Add a '+' unless we are at the first term (AKA last term in descending order).
            if (term != terms.first()) {
                result.append(" + ");
            }
        }

        return result.toString();
    }
}
