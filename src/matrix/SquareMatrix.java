/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrix;

import java.util.Scanner;

/**
 *
 * @author chuon
 */
public class SquareMatrix extends Matrix {

    private int n;

    public SquareMatrix(int n) {
        super(n, n);
        this.n = n;
    }
    
    public SquareMatrix(Matrix matrix){
        super(matrix);
        this.n = matrix.getCol();
    }

    public int setN(int n) {
        this.n = n;
        this.setCol(n);
        return this.setRow(n);
    }

    public int getN() {
        return this.n;
    }

    public double determinant() {
        if (n == 1) {
            return this.getValue(0, 0);
        }
        double result = 0;
        for (int i = 0; i < n; i++) {
            if (this.getValue(0, i) != 0) {
                result += Math.pow(-1, i) * this.getValue(0, i) * this.cut(0, i).determinant();
            }
        }
        return result;
    }

    public SquareMatrix cut(int x, int y) {
        int m = getN() - 1;
        SquareMatrix result = new SquareMatrix(m);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if (i >= x && j < y) {
                    result.setValue(i, j, this.getValue(i + 1, j));
                } else if (i < x && j >= y) {
                    result.setValue(i, j, this.getValue(i, j + 1));
                } else if (i >= x && j >= y) {
                    result.setValue(i, j, this.getValue(i + 1, j + 1));
                } else if (i < x && j < y) {
                    result.setValue(i, j, this.getValue(i, j));
                }
            }
        }
        return result;
    }

    public boolean isInvertible() {
        return this.determinant() != 0;
    }

    public Matrix invert() throws MatrixException {
        if (!this.isInvertible()) {
            throw new MatrixException("This is not an invertible matrix!");
        }
        Matrix matrix = new SquareMatrix(n);
        matrix = this.adjugate();
        matrix = matrix.transpose();
        return matrix.multify(1 / this.determinant());
    }

    public SquareMatrix adjugate() {
        SquareMatrix matrix = new SquareMatrix(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix.setValue(i, j, Math.pow(-1, i + j) * this.cut(i, j).determinant());
            }
        }
        return matrix;
    }
    
    public static SquareMatrix inputMatrix() {
        SquareMatrix matrix;
        Scanner input;
        input = new Scanner(System.in);

        System.out.println("Enter a square matrix: ");
        System.out.print("\tn =  ");
        int n = input.nextInt();
        matrix = new SquareMatrix(n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("matrix[" + i + "][" + j + "] = ");
                matrix.setValue(i, j, input.nextDouble());
            }
        }
        return matrix;
    }
}
