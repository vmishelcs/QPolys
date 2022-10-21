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
        assertEquals("(1/2)", term1.toString());

        Term term2 = new Term(r, VariableName.x, 1);
        assertEquals("(1/2)x", term2.toString());

        VariableName[] variables = { VariableName.x, VariableName.y, VariableName.z };
        int[] powers = { 1, 2, 3 };
        Term term3 = new Term(r, variables, powers);
        assertEquals("(1/2)xy^2z^3", term3.toString());
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