package polynomial;

import number.RationalNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PolynomialTest {
    @Test
    @DisplayName("Initialize")
    void testInitialize() {
        Polynomial p0 = new Polynomial();
        assertEquals(RationalNumber.zero.toString(), p0.toString());
        assertTrue(p0.getVariableSet().isEmpty());

        RationalNumber r1 = new RationalNumber(1, 1);
        Term t1 = new Term(r1);
        Polynomial p1 = new Polynomial(t1);
        assertEquals("1", p1.toString());
        assertTrue(p1.getVariableSet().isEmpty());

        RationalNumber r2 = new RationalNumber(1, 2);
        Term t2 = new Term(r2, VariableName.x, 1);
        Polynomial p2 = new Polynomial(t2);
        assertEquals("(1/2)x", p2.toString());
        assertEquals(1, p2.getVariableSet().size());

        RationalNumber r3 = new RationalNumber(-1, 2);
        Term t3 = new Term(r3, VariableName.x, 2);
        Polynomial p3 = new Polynomial(t3);
        assertEquals("(-1/2)x^2", p3.toString());

        Polynomial p4 = new Polynomial(t3, t2);
        assertEquals("(-1/2)x^2 + (1/2)x", p4.toString());
        assertEquals(1, p4.getVariableSet().size());

        RationalNumber r5 = new RationalNumber(2, 1);
        Term t5 = new Term(r5, VariableName.y, 1);
        Polynomial p5 = new Polynomial(t5, t2);
        assertEquals("(1/2)x + 2y", p5.toString());
        assertEquals(2, p5.getVariableSet().size());
    }
}
