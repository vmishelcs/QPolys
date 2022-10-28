package polynomial;

import exceptions.TermEvaluationMissingVariable;
import number.RationalNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

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

        Map<VariableName, Integer> mapVarPower = new HashMap<>();
        mapVarPower.put(VariableName.x, 1);
        mapVarPower.put(VariableName.y, 2);
        mapVarPower.put(VariableName.z, 3);
        Term term3 = new Term(r, mapVarPower);
        assertEquals("(1/2)xy^2z^3", term3.toString());
    }

    @Test
    @DisplayName("Evaluate")
    void testEvaluate() {
        final RationalNumber coeff = new RationalNumber(1, 2);
        final RationalNumber xVal = new RationalNumber(5, 4);
        final RationalNumber yVal = new RationalNumber(3, 1);
        final RationalNumber zVal = new RationalNumber(-1, 3);

        TreeMap<VariableName, RationalNumber> mapVarValue = new TreeMap<>();
        mapVarValue.put(VariableName.x, xVal);
        mapVarValue.put(VariableName.y, yVal);
        mapVarValue.put(VariableName.z, zVal);

        Term term1 = new Term(coeff);
        assertEquals(coeff, term1.evaluateAt(mapVarValue));

        Term term2 = new Term(coeff, VariableName.x, 1);
        assertEquals(coeff.multiply(xVal), term2.evaluateAt(mapVarValue));

        Map<VariableName, Integer> mapVarPower = new HashMap<>();
        mapVarPower.put(VariableName.x, 1);
        mapVarPower.put(VariableName.y, 2);
        mapVarPower.put(VariableName.z, 3);

        Term term3 = new Term(coeff, mapVarPower);
        RationalNumber expected = coeff.multiply(xVal).multiply(yVal.pow(2)).multiply(zVal.pow(3));
        assertEquals(expected, term3.evaluateAt(mapVarValue));

        mapVarPower.put(VariableName.a, 1);
        Term term4 =  new Term(coeff, mapVarPower);
        assertThrows(
                TermEvaluationMissingVariable.class,
                () -> term4.evaluateAt(mapVarValue)
        );
    }

    @Test
    @DisplayName("Compare")
    void testCompare() {
        final RationalNumber coeff = new RationalNumber(1, 1);
        Term term1 = new Term(coeff, VariableName.x, 1);
        assertEquals(0, term1.compareTo(term1));

        Term term2 = new Term(coeff, VariableName.x, 2);
        assertEquals(-1, term1.compareTo(term2));
        assertEquals(1, term2.compareTo(term1));

        Term term3 = new Term(coeff, VariableName.y, 1);
        assertEquals(1, term1.compareTo(term3));
        assertEquals(-1, term3.compareTo(term1));

        Map<VariableName, Integer> mapVarPower4 = new HashMap<>();
        mapVarPower4.put(VariableName.x, 1);
        mapVarPower4.put(VariableName.y, 2);
        Term term4 = new Term(coeff, mapVarPower4);

        Map<VariableName, Integer> mapVarPower5 = new HashMap<>();
        mapVarPower5.put(VariableName.x, 2);
        mapVarPower5.put(VariableName.y, 1);
        Term term5 = new Term(coeff, mapVarPower5);

        assertEquals(-1, term4.compareTo(term5));
        assertEquals(1, term5.compareTo(term4));
    }
}
