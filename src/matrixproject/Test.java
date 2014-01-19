/**
 * Modify test
 * @author Marco
 */
package matrixproject;

import matrixproject.fraction.Fraction;
import matrixproject.matrix.Matrix;

public class Test {
    
    public static void main(String[] args)
    {
        //Fractions tests.
        System.out.println("----- Fractions Tests -----");
        Fraction fraction1 = new Fraction(1, 2);
        Fraction fraction2 = new Fraction(1, 2);

        System.out.println("1/2 is: " + fraction1.getFloatValue());
        
        Fraction sum = fraction1.sum(fraction2);
        sum.simplify();
        System.out.println(sum.toString());
        System.out.println();
        System.out.println(fraction1.mul(fraction2).toString());
        System.out.println();
        System.out.println(fraction1.div(fraction2).toString());
        System.out.println();
        Fraction sim = new Fraction(12, 4);
        sim.simplify();
        System.out.println(sim.toString());
        System.out.println();
        Fraction sim2 = new Fraction(1, 4);
        sim2.simplify();
        System.out.println(sim2.toString());
        
        //Matrices tests.
        System.out.println("----- Matrices Tests -----");
        Matrix matrix = new Matrix(4, 4, true);
        System.out.println(matrix.toString());
        System.out.println();
        System.out.println(matrix.toStringFloat());
        
        //New constructor test.
        Fraction[] row1 = { new Fraction(1,1), new Fraction(1,2), new Fraction(1,3) };
        Fraction[] row2 = { new Fraction(2,1), new Fraction(2,2), new Fraction(2,3) };
        Fraction[] row3 = { new Fraction(3,1), new Fraction(3,2), new Fraction(3,3) };
        Fraction[][] complete = { row1, row2, row3 };
        
        Matrix new_matrix = new Matrix(complete);
        System.out.println(new_matrix.toString());
        
        //Sum test.
        Fraction[] sum_a_1 = { new Fraction(2,1), new Fraction(1,1) };
        Fraction[] sum_a_2 = { new Fraction(15,1), new Fraction(9,1) };
        Fraction[][] a_source = { sum_a_1, sum_a_2 };
        Matrix a = new Matrix(a_source);

        Fraction[] sum_b_1 = { new Fraction(8,1), new Fraction(6,1) };
        Fraction[] sum_b_2 = { new Fraction(5,1), new Fraction(3,1) };
        Fraction[][] b_source = { sum_b_1, sum_b_2 };
        Matrix b = new Matrix(b_source);
        
        System.out.println("Sum is:");
        System.out.println(a.sum(b));
        
        //Equals test.
        System.out.println("Equals: " + new_matrix.equals(new_matrix));
        System.out.println("Equals: " + a.equals(b));
    }
    
}
