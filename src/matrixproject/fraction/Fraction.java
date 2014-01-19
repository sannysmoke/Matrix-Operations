/**
 * Class Fraction
 * 
 * @author Marco
 */
package matrixproject.fraction;

public class Fraction {
    
    private int numerator;
    private int denominator;
        
    /**
     * Constructor 1
     * @param numerator Fraction's numerator
     * @param denominator Fraction's denominator
     */
    public Fraction(int numerator, int denominator)
    {
        if(denominator == 0)
            throw new IllegalArgumentException("Denominator can't be 0");
        
        this.numerator = numerator;
        this.denominator = denominator;
        
        if(denominator < 0)
        {
            this.numerator *= -1;
            this.denominator *= -1;
        }
    }
    
    /**
     * Constructor 2
     */
    public Fraction()
    {
        this.numerator = 0;
        this.denominator = 1;
    }
    
    /*** GET and SET methods ***/
    
    /**
     * GetNumerator method
     * @return Return fraction's numerator
     */
    public int getNumerator()
    {
        return this.numerator;
    }
    
    /**
     * GetDenominator method
     * @return Return fraction's denominator
     */
    public int getDenominator()
    {
        return this.denominator;
    }
    
    /**
     * SetNumerator methot
     * @param numerator New fraction's numerator
     */
    private void setNumerator(int numerator)
    {
        this.numerator = numerator;
    }
    
    /**
     * SetDenominator method
     * @param denominator New fraction's denominator
     */
    private void setDenominator(int denominator)
    {
        if(denominator == 0)
            throw new IllegalArgumentException("Denominator can't be 0");
        
        this.denominator = denominator;
        
        if(denominator < 0)
        {
            this.numerator *= -1;
            this.denominator *= -1;
        }        
    }    
    
    /**
     * Method to sum this fraction with another fraction
     * @param addend The fraction to sum
     * @return The sum
     */
    public Fraction sum(Fraction addend)
    {
        Fraction result = new Fraction();
        int newNumerator;
        
        int mcm = mcm(this.denominator, addend.denominator);
        result.setDenominator(mcm);
        
        newNumerator = (mcm/this.denominator)*this.numerator + 
                (mcm/addend.denominator)*addend.numerator;
        result.setNumerator(newNumerator);
        
        return result;
    }
    
    /**
     * Method to calculate the mcm
     * @param a First int
     * @param b Second int
     * @return The mcm between two int
     */
    private int mcm(int a, int b)
    {
	int mult_a = a;
	int mult_b = b;
	while (mult_a != mult_b) {
		while (mult_a < mult_b)  mult_a += a;
		while (mult_b < mult_a)  mult_b += b;
	}
	return mult_a;        
    }
    
    /**
     * Multiplication between two fractions
     * @param factor The second factor of the multiplication
     * @return Return the product beetween this and factor
     */
    public Fraction mul(Fraction factor)
    {
        Fraction result = new Fraction();
        result.setNumerator(this.numerator * factor.numerator);
        result.setDenominator(this.denominator * factor.denominator);
        return result;
    }
    
    /**
     * Division between two fractions
     * @param divisor The divisor of the division
     * @return Return the division's quotient
     */
    public Fraction div(Fraction divisor)
    {
        Fraction result = new Fraction();
        result.setNumerator(this.numerator * divisor.denominator);
        result.setDenominator(this.denominator * divisor.numerator);
        return result;
    }
    
    /**
     * Method to simplify a fraction
     */
    public void simplify()
    {
        int gcd = 0;
        while(gcd != 1)
        {
            gcd = gcd(this.numerator, this.denominator);
            this.setNumerator(this.numerator/gcd);
            this.setDenominator(this.denominator/gcd);
        }
    }

    /**
     * Method to get float value of this fraction.
     */
    public float getFloatValue() {
        return (float)this.numerator / (float)this.denominator;
    }

    /**
     * Method that return the gcd between two numbers
     * @param a First int
     * @param b Secondo int
     * @return Return the gcd between @param a and @param b
     */
    private int gcd(int a, int b)
    {
        int gcd;
        gcd = a % b;
        while(gcd != 0)
        {
            a = b;
            b = gcd;
            gcd = a % b;
        }
        return b;
    }
    
    /**
     * Clones this Fraction object.
     * @return A Fraction object, with same numerator and denominator.
     */
    @Override
    public Object clone() {
        return new Fraction(this.numerator, this.denominator);
    }
    
    /**
     * Checks if two Fraction objects are equals.
     * It returns true when the simplified version of both fractions have same numerator and denominator.
     * @param o Other Fraction object to check.
     * @return true if the objects are equals, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Fraction))
            return false;
        
        Fraction f = (Fraction) o;
        Fraction f1 = (Fraction)this.clone();
        Fraction f2 = (Fraction)f.clone();
        f1.simplify();
        f2.simplify();
        return f1.numerator == f2.numerator && f1.denominator == f2.denominator;
    }
    
    /**
     * ToString method
     * @return String that represents the fraction
     */
    @Override
    public String toString()
    {
        return this.numerator + "\n-\n" + this.denominator;
    }
}
