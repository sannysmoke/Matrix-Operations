/**
 * Class Matrix
 * 
 * @author Marco
 */
package matrixproject.matrix;

import matrixproject.fraction.Fraction;

public class Matrix {
    
    private int row;
    private int col;
    private Fraction[][] matrix;
    
    /**
     * Constructor
     * @param row The number of rows
     * @param col The number of columns
     * @param def If is @param true all the matrix elements are set to zero
     */
    public Matrix(int row, int col, boolean def)
    {
        this.row = row;
        this.col = col;
        this.matrix = new Fraction[row][col];
        
        if(def)
        {
            for(int i = 0; i < this.row; i++)
            {
                for(int j = 0; j < this.col; j++)
                {
                    matrix[i][j] = new Fraction();
                }
            }            
        }
    }

    /**
     * ToString method
     * @return String that represents the matrix
     */    
    @Override
    public String toString()
    {
        String string = "";
        for(int i = 0; i < this.row; i++)
        {
            for(int j = 0; j < this.col; j++)
            {
                string += this.matrix[i][j].getNumerator() + "\t";
            }
            
            string += "\n";
            
            for(int j = 0; j < this.col; j++)
            {
                string += "-\t";
            }
            
            string += "\n";
            
            for(int j = 0; j < this.col; j++)
            {
                string += this.matrix[i][j].getDenominator() + "\t";
            }
            
            string += "\n\n";
        }
        return string;
    }
 
    /**
     * ToString method
     * @return String that represents the matrix in float representation
     */    
    public String toStringFloat()
    {
        String string = "";
        for(int i = 0; i < this.row; i++)
        {
            for(int j = 0; j < this.col; j++)
            {
                string += this.matrix[i][j].getFloatValue() + "\t";
            }
            
            string += "\n";
        }
        return string;
    }    
}
