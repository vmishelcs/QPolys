package number;

import exceptions.RationalNumberDivideByZeroException;

public class RationalNumber extends Number implements Comparable<RationalNumber> {
    private final int numerator;
    private final int denominator;

    public static final RationalNumber zero = new RationalNumber(0, 1);

    public RationalNumber(int numerator, int denominator) {
        if (denominator == 0) {
            throw new RationalNumberDivideByZeroException();
        }

        int gcd = greatestCommonDivisor(Math.abs(numerator),
                Math.abs(denominator));
        boolean positive = (numerator > 0 && denominator > 0) ||
                (numerator < 0 && denominator < 0);
        this.numerator = positive ?
                Math.abs(numerator / gcd) :
                -Math.abs(numerator / gcd);
        this.denominator = Math.abs(denominator / gcd);
    }

    /**
     * Obtains the greatest common divisor of two integers to store a reduced
     * a rational number in its reduced form.
     * @param a Integer.
     * @param b Integer.
     * @return The greatest common divisor of a and b.
     */
    private int greatestCommonDivisor(int a, int b) {
        // We know that gcd(x, 0) = gcd(0, x) = x always.
        if (a == 0) {
            return b;
        }
        else if (b == 0) {
            return a;
        }

        // Suppose a = 2^m * x and b = 2^n * y, where x, y are odd.
        // We have gcd(2^m * x, 2^n * y) = 2^k * gcd(x, y), where
        // k = min(m, n). Thus, we divide `a` and `b` by 2 until
        // we obtain an odd value.
        int m = 0;
        int n = 0;
        while (a % 2 == 0) {
            ++m;
            a /= 2;
        }
        while (b % 2 == 0) {
            ++n;
            b /= 2;
        }
        // Set k = min(m, n).
        int k = Math.min(m, n);

        // At this point, both a and b are odd.
        while (a != b) {
            if (a > b) {
                a -= b;
            }
            else {
                b -= a;
            }
        }

        // Multiply result by 2^k.
        return a * (1 << k);
    }

    /**
     * Checks if this rational number is greater than or equal to 0.
     * @return True if this rational number is greater than or equal to zero,
     *         false otherwise.
     */
    public boolean isNonNegative() {
        return numerator >= 0;
    }

    // Numerical operations.
    public RationalNumber add(RationalNumber r) {
        int numerator = this.numerator * r.denominator + r.numerator * this.denominator;
        int denominator = this.denominator * r.denominator;
        return new RationalNumber(numerator, denominator);
    }
    public RationalNumber subtract(RationalNumber r) {
        int numerator = this.numerator * r.denominator - r.numerator * this.denominator;
        int denominator = this.denominator * r.denominator;
        return new RationalNumber(numerator, denominator);
    }
    public RationalNumber multiply(RationalNumber r) {
        return new RationalNumber(this.numerator * r.numerator,
                this.denominator * r.denominator);
    }
    public RationalNumber divide(RationalNumber r) {
        return new RationalNumber(this.numerator * r.denominator,
                this.denominator * r.numerator);
    }

    // Accessor methods.
    public int getNumerator() {
        return this.numerator;
    }
    public int getDenominator() {
        return this.denominator;
    }

    // Inherited Number methods.
    @Override
    public int intValue() {
        double x = this.doubleValue();
        return (int) x;
    }
    @Override
    public long longValue() {
        double x = this.doubleValue();
        return (long) x;
    }
    @Override
    public float floatValue() {
        return ((float) numerator) / ((float) denominator);
    }
    @Override
    public double doubleValue() {
        return ((double) numerator) / ((double) denominator);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (o.getClass() != this.getClass()) {
            return false;
        }

        RationalNumber r = (RationalNumber) o;
        return (r.numerator == this.numerator) &&
                (r.denominator == this.denominator);
    }

    @Override
    public int compareTo(RationalNumber r) {
        // Subtract `r` from `this`.
        RationalNumber subtractResult = this.subtract(r);
        if (subtractResult.numerator < 0) {
            return -1;
        }
        else if (subtractResult.equals(RationalNumber.zero)) {
            return 0;
        }
        else {
            return 1;
        }
    }

    @Override
    public String toString() {
        return "(" + this.numerator + "/" + this.denominator + ")";
    }
}
