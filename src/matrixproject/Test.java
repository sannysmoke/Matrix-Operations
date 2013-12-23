/**
 * @author Marco
 */
package matrixproject;

import matrixproject.fraction.Fraction;

public class Test {
    
    public static void main(String[] args)
    {
        Fraction fraction1 = new Fraction(1, 2);
        Fraction fraction2 = new Fraction(1, 2);
        
        Fraction sum = fraction1.sum(fraction2);
        sum.simplify();
        System.out.println(sum.toString());
        System.out.println();
        System.out.println(fraction1.mul(fraction2).toString());
        System.out.println();
        System.out.println(fraction1.div(fraction2).toString());
    }
    
}
