/**
 * Class Matrix
 * 
 * @author Marco
 */
package matrixproject.matrix;

import matrixproject.fraction.Fraction;

public class Matrix {
    
    /*
     * Number of rows. In R^(m*n) it is m value.
     * */
    private int row;
    
    /*
     * Number of columns. In R^(m*n) it is n value.
     * */
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
     * Multiplies an integer value to this matrix.
     * Every component will be simplified.
     * @param value int value
     * @return matrix object, with value multiplied to each matrix component.
     */
    public Matrix mul(int value) {
        return mul(value, true);
    }
    
    /**
     * Multiplies a Fraction object to this matrix.
     * Every component will be simplified.
     * @param value Fraction object
     * @return matrix object, with value multiplied to each matrix component.
     */
    public Matrix mul(Fraction value) {
        return mul(value, true);
    }
    
    /**
     * Multiplies an integer value to this matrix.
     * @param value int value
     * @param simplify if true every component will be simplified after mul operation.
     * @return matrix object, with value multiplied to each matrix component.
     */
    public Matrix mul(int value, boolean simplify) {
        return mul(new Fraction(value, 1), simplify);
    }
    
    /**
     * Multiplies a Fraction object to this matrix.
     * @param value Fraction object
     * @param simplify if true every component will be simplified after mul operation.
     * @return matrix object, with value multiplied to each matrix component.
     */
    public Matrix mul(Fraction value, boolean simplify) {
        Fraction [][] mul_matrix = new Fraction[row][col];
        for(int row_counter = 0; row_counter < row; row_counter++)
            for(int col_counter = 0; col_counter < col; col_counter++) {
                Fraction f = value.mul(this.matrix[row_counter][col_counter]);
                if(simplify)
                    f.simplify();
                mul_matrix[row_counter][col_counter] = f;
            }
                
        return new Matrix(mul_matrix);
    }
    
    /**
     * Multiplies two matrices.
     * The row dimension of the parameter must be equal to column dimension of the first one.
     * @param m Matrix object to multiply.
     * @return Another Matrix, containing mul values.
     */
    public Matrix mul(Matrix m) {

        if(this.row != m.col)
            throw new IllegalArgumentException("Column dimension of matrix parameter must be the same with row dimension of this matrix.");
        
        Fraction[][] new_matrix = new Fraction[this.row][m.col];            
        
        for(int row_counter = 0; row_counter < this.row; row_counter++) {
            for (int col_counter = 0; col_counter < m.col; col_counter++) {
                Fraction sum = new Fraction();
                for(int counter = 0; counter < this.col; counter++) {
                    sum = sum.sum(this.matrix[row_counter][counter].mul(m.matrix[counter][col_counter]));
                }
                new_matrix[row_counter][col_counter] = sum;
            }
        }
        return new Matrix(new_matrix);
    }
    
    /**
     * Calculate the transposed version of this Matrix.
     * @return Another Matrix object, rapresenting the transposed version of the source object.
     */
    public Matrix getTransposed(){
        Fraction[][] new_matrix = new Fraction[this.col][this.row];
        
        for(int row_counter = 0; row_counter < this.row; row_counter++) {
            for(int col_counter = 0; col_counter < this.col; col_counter++) {
                new_matrix[col_counter][row_counter] = new Fraction(matrix[row_counter][col_counter].getNumerator(), matrix[row_counter][col_counter].getDenominator());
            }
        }
        
        return new Matrix(new_matrix);
    }
    
    /**
     * Returns a new Matrix object rapresenting the identity of a given dimenison.
     * @param dimension Number of rows and columns of the identity matrix.
     * @return A new Matrix object rapresenting the identity of the given dimension.
     */
    public static Matrix identity(int dimension) {
        if(dimension < 0)
            throw new IllegalArgumentException("Dimension must be a positive number.");
        Fraction[][] new_matrix = new Fraction[dimension][dimension];
        for(int row_counter = 0; row_counter < dimension; row_counter++) {
            for(int col_counter = 0; col_counter < dimension; col_counter++) {
                if(row_counter == col_counter)
                    new_matrix[row_counter][col_counter] = new Fraction(1,1);
                else
                    new_matrix[row_counter][col_counter] = new Fraction(0,1);
            }
        }
        return new Matrix(new_matrix);
    }
    
    /**
     * Checks if this matrix is square (row dimension equals to column dimension).
     * @return true if matrix is square, false otherwise.
     */
    public boolean square() {
        return this.row == this.col;
    }
    
    /* ---- OPERAZIONI ELEMENTARI ---- */
    
    /**
     * Function that switch two rows
     * @param i Index of first row to switch
     * @param j Index of second row to switch
     */
    public void switchRows(int i, int j)
    {
        Fraction[] tmp = this.matrix[i];
        this.matrix[i] = this.matrix[j];
        this.matrix[j] = tmp;
    }
    
    /**
     * Function that multiply a row with Fraction
     * @param i Index of row to be multiplied
     * @param f Fraction to multiply row with
     */
    public void mulScalar(int i, Fraction f)
    {
        for(int j = 0; j < this.col; j++)
        {
            this.matrix[i][j] = this.matrix[i][j].mul(f);
        }
    }
    
    /**
     * Function that multiply a row with int
     * @param i Index of row to be multiplied
     * @param value Integer to multiply row with
     */
    public void mulScalar(int i, int value)
    {
        this.mulScalar(i, new Fraction(value, 1));
    }
    
    /**
     * Fuction that sum the source row, multiplied with value, with destination row
     * @param dest Destination row
     * @param source Source row
     * @param value Value to be multiplied with source
     */
    public void addMultRow(int dest, int source, Fraction value)
    {
        this.addMultRow(dest, source, value, true);
    }
    
    /**
     * Fuction that sum the source row, multiplied with value, with destination row
     * @param dest Destination row
     * @param source Source row
     * @param value Value to be multiplied with source
     * @param simplify If true simplify the destination row
     */
    public void addMultRow(int dest, int source, Fraction value, boolean simplify)
    {
        for(int j = 0; j < this.col; j++)
        {
            this.matrix[dest][j] = this.matrix[dest][j].sum(this.matrix[source][j].mul(value));
            if(simplify)
                this.matrix[dest][j].simplify();
        }
    }
    
    /**
     * Gauss method
     * This function reduce a matrix with the Gauss algorithm
     */
    
    public void gauss()
    {
        for(int index = 0; index < this.row-1; index++)
        {
            //search the column not null with index of row lowest
            int indexRow = searchRowIndexLow(index);
            
            //if the row identified isn't the first, switch that with the first
            if(indexRow != 0)
            {
                switchRows(indexRow, index);
            }
            
            //Make null all elements under the pivot
            int indexCol = searchColIndexNotNull(index);
            
            for(int i = index+1; i < this.row; i++)
            {                
                if(!this.matrix[i][indexCol].equals(new Fraction(0, 1)))
                {
                    Fraction delta = (this.matrix[i][indexCol].div(this.matrix[index][indexCol])).mul(new Fraction(-1, 1));
                    this.addMultRow(i, index, delta);
                }
            }
        }
    }
    
    public int searchRowIndexLow(int first)
    {        
        for(int j = 0; j < this.col; j++)
        {
            for(int i = first; i < this.row; i++)
            {
                if(!this.matrix[i][j].equals(new Fraction(0, 1)))
                {
                    return i;
                }
            }
        }
        
        return 0;
    }
    
    /**
     * Function that returns the first column not null of the specified row
     * @param i Row to check
     * @return Return the first column not null of the specified row
     */
    public int searchColIndexNotNull(int i)
    {        
        for(int j = 0; j < this.col; j++)
        {
            if(!this.matrix[i][j].equals(new Fraction(0, 1)))
            {
                return j;
            }       
        }
        
        return 0;
    }
    
    /**
     * Function that check if the matrix is null
     * @return Return true if the matrix is null, false otherwise
     */
    public boolean isNull()
    {
        for(int i = 0; i < this.col; i++)
        {
            for(int j = 0; j < this.row; j++)
            {
                if(!this.matrix[i][j].equals(new Fraction()))
                {
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Checks if two Matrix objects are equals.
     * It uses Fraction object equals method, that return true when the simplified version of the fraction is the same,
     * so it's not a strict confrontation between numerator and denominator.
     * @param o Other Matrix object to check.
     * @return true if the objects are equals, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Matrix))
            return false;
        
        Matrix m = (Matrix)o;
        boolean value = true;
        if(this.row != m.row || this.col != m.col)
            value = false;
        for(int row_counter = 0; row_counter < m.row && value; row_counter++)
            for(int col_counter = 0; col_counter < m.col && value; col_counter++)
                if(!this.matrix[row_counter][col_counter].equals(m.matrix[row_counter][col_counter]))
                    value = false;
        return value;
    }
    
    /**
     * Clones this Matrix object.
     * @return Another Matrix object, exact copy of this one.
     */
    @Override
    public Matrix clone() {
        return new Matrix(this.matrix);
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
