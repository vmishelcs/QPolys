package polynomial;

import number.RationalNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

        TreeMap<VariableName, Integer> varPowerMap = new TreeMap<>();
        varPowerMap.put(VariableName.x, 1);
        varPowerMap.put(VariableName.y, 2);
        varPowerMap.put(VariableName.z, 3);
        Term term3 = new Term(r, varPowerMap);
        assertEquals("(1/2)xy^2z^3", term3.toString());
    }

    @Test
    @DisplayName("Evaluate")
    void testEvaluate() {
        final RationalNumber coeff = new RationalNumber(1, 2);
        final RationalNumber xVal = new RationalNumber(5, 4);
        final RationalNumber yVal = new RationalNumber(3, 1);
        final RationalNumber zVal = new RationalNumber(-1, 3);

        TreeMap<VariableName, RationalNumber> varValueMap = new TreeMap<>();
        varValueMap.put(VariableName.x, xVal);
        varValueMap.put(VariableName.y, yVal);
        varValueMap.put(VariableName.z, zVal);

        Term term1 = new Term(coeff);
        assertEquals(coeff, term1.evaluateAt(varValueMap));

        Term term2 = new Term(coeff, VariableName.x, 1);
        assertEquals(coeff.multiply(xVal), term2.evaluateAt(varValueMap));

        TreeMap<VariableName, Integer> varPowerMap = new TreeMap<>();
        varPowerMap.put(VariableName.x, 1);
        varPowerMap.put(VariableName.y, 2);
        varPowerMap.put(VariableName.z, 3);

        Term term3 = new Term(coeff, varPowerMap);
        RationalNumber expected = coeff.multiply(xVal).multiply(yVal.pow(2)).multiply(zVal.pow(3));
        assertEquals(expected, term3.evaluateAt(varValueMap));
    }
}
