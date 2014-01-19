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
     * Constructor. Creates a Matrix object given a Fraction array of arrays.
     * @param matrix This is the Fraction matrix. Every row must be same size with each other.
     */
    public Matrix(Fraction[][] matrix) {
        this.row = matrix.length;
        for(int counter = 0; counter < matrix.length; counter++) {
            if (counter == 0) {
                this.col = matrix[counter].length;
            }
            else {
                if(matrix[counter].length != this.col)
                    throw new IllegalArgumentException("Rows size is not the same for every row.");
            }
        }
        this.matrix = matrix;
    }
    
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
     * Returns the sum of this matrix with another one.
     * Matrices rows and columns dimensions must be the same.
     * @param m Other matrix object.
     * @return Matrix object rapresenting sum between the two matrices.
     */
    public Matrix sum(Matrix m) {
        //Validation
        if(this.row != m.row || this.col != m.col)
            throw new IllegalArgumentException("Matrices rows and columns dimensions must be the same.");

        Fraction[][] sum_matrix = new Fraction[row][col];
        for(int row_counter = 0; row_counter < row; row_counter++)
            for(int col_counter = 0; col_counter < col; col_counter++)
                sum_matrix[row_counter][col_counter] = this.matrix[row_counter][col_counter].sum(m.matrix[row_counter][col_counter]);

        return new Matrix(sum_matrix);
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
