package exceptions;

public class TermEvaluationMissingVariable extends RuntimeException {
    public TermEvaluationMissingVariable() {
        super("Map argument for Term::evaluateAt must provide a value for every term variable.");
    }
}
