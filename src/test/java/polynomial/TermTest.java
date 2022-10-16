package polynomial;

import number.RationalNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TermTest {
    @Test
    @DisplayName("Initialize")
    void testInitialize() {
        final RationalNumber r = new RationalNumber(1, 2);
        Term term1 = new Term(r);

        Term term2 = new Term(r, VariableName.x, 1);
    }

    @Test
    @DisplayName("Evaluate")
    void testEvaluate() {
        final RationalNumber r = new RationalNumber(1, 2);
        Term term1 = new Term(r);
        assertEquals(r, term1.evaluateAt(r));

        Term term2 = new Term(r, VariableName.x, 1);
        assertEquals(r.multiply(r), term2.evaluateAt(r));

        Term term3 = new Term(r, VariableName.x, 2);
        assertEquals(r.multiply(r).multiply(r), term3.evaluateAt(r));
    }
}