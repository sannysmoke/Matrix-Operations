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
        
        //Mul test.
        System.out.println("Mul:\n" + new_matrix.mul(2));
        
        //Mul between two matrix test
        Fraction[] mul_a_1 = {new Fraction(2,1), new Fraction(0,1), new Fraction(1,1)};
        Fraction[] mul_a_2 = {new Fraction(3,1), new Fraction(1,1), new Fraction(0,1)};
        Fraction[] mul_a_3 = {new Fraction(0,1), new Fraction(1,1), new Fraction(0,1)};
        Fraction[] mul_a_4 = {new Fraction(0,1), new Fraction(0,1), new Fraction(2,1)};
        Fraction[][] mul_a = {mul_a_1, mul_a_2, mul_a_3, mul_a_4};
        Matrix mul_m_a = new Matrix(mul_a);
        
        Fraction[] mul_b_1 = {new Fraction(1,1), new Fraction(0,1), new Fraction(1,1), new Fraction(0,1)};
        Fraction[] mul_b_2 = {new Fraction(2,1), new Fraction(1,1), new Fraction(0,1), new Fraction(1,1)};
        Fraction[] mul_b_3 = {new Fraction(1,1), new Fraction(0,1), new Fraction(2,1), new Fraction(0,1)};
        Fraction[][] mul_b = {mul_b_1, mul_b_2, mul_b_3};
        Matrix mul_m_b = new Matrix(mul_b);
        
        Matrix mul = mul_m_a.mul(mul_m_b);
        System.out.println("Mul between two matrixes:\n" + mul);
        
        mul.switchRows(0, 1);
        System.out.println("Switch between two lines:\n" + mul);
        
        mul.mulScalar(0, new Fraction(1, 2));
        System.out.println("Multiply first row with 1/2:\n" + mul);
        
        mul.mulScalar(0, 2);
        System.out.println("Multiply first row with 2:\n" + mul);   
        
        mul.addMultRow(1, 0, new Fraction(1, 2));
        System.out.println("Multiply first row with 1/2 and sum this to the second row:\n" + mul); 
        
        //Transpose test
        System.out.println("Transpose:\n" + mul_m_a.getTransposed());
        
        //Identity test
        System.out.println("Identity 3x3:\n" + Matrix.identity(3) + "\n\nIdentity 4x4:\n" + Matrix.identity(4));
        
        
        //Gauss test
        
        System.out.println("\n\n------------GAUSS TEST------------\n\n");
        
        Fraction[] gauss1 = {new Fraction(2,1), new Fraction(1,1), new Fraction(0,1), new Fraction(4,1)};
        Fraction[] gauss2 = {new Fraction(1,1), new Fraction(-1,1), new Fraction(1,1), new Fraction(0,1)};
        Fraction[] gauss3 = {new Fraction(2,1), new Fraction(1,1), new Fraction(-1,1), new Fraction(1,1)};
        Fraction[][] gauss = { gauss1, gauss2, gauss3 };
        
        Matrix gaussM = new Matrix(gauss);
        
        System.out.println("Source matrix:\n" + gaussM);
        
        gaussM.gauss();
        
        System.out.println("\n\n\"Gaussed\" matrix:\n" + gaussM);
        
        
    }
    
}
