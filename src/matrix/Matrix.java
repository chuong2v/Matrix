/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrix;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 *
 * @author chuon
 */
public class Matrix implements Serializable {

    private int col, row;
    private double[][] a;

    public Matrix(int row, int col) {
        this.row = row;
        this.col = col;
        this.a = new double[row][col];
    }

    public Matrix(Matrix matrix) {
        this.row = matrix.getRow();
        this.col = matrix.getCol();
        this.a = new double[this.row][this.col];
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                this.setValue(i, j, matrix.getValue(i, j));
            }
        }
    }

    public int setCol(int col) {
        this.col = col;
        return this.col;
    }

    public int getCol() {
        return this.col;
    }

    public int setRow(int row) {
        this.row = row;
        return this.row;
    }

    public int getRow() {
        return this.row;
    }

    public double setValue(int i, int j, double value) {
        this.a[i][j] = value;
        return this.a[i][j];
    }

    public double getValue(int i, int j) {
        return this.a[i][j];
    }

    public Matrix multify(double num) {
        Matrix matrix = new Matrix(this.row, this.col);
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                matrix.setValue(i, j, num * this.getValue(i, j));
            }
        }
        return matrix;
    }

    public Matrix multify(Matrix m2) throws MatrixException {
        Matrix result = new Matrix(this.row, m2.getCol());
        if (this.col != m2.getRow()) {
            throw new MatrixException("Cannot multify!");
        }
        double value;
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < m2.getCol(); j++) {
                value = 0;
                for (int k = 0; k < this.col; k++) {
                    value += this.a[i][k] * m2.getValue(k, j);
                }
                result.setValue(i, j, value);
            }
        }
        return result;
    }

    public Matrix add(Matrix matrix) throws MatrixException {
        if (this.row != matrix.getRow() || this.col != matrix.getCol()) {
            throw new MatrixException("Cannot add 2 matrix with different dimensions");
        }
        Matrix result = new Matrix(this.row, this.col);
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                result.setValue(i, j, this.getValue(i, j) + matrix.getValue(i, j));
            }
        }
        return result;
    }

    public Matrix subtracts(Matrix matrix) throws MatrixException {
        return this.add(matrix.multify(-1));
    }

    public void print() {
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                System.out.print(this.getValue(i, j) + "\t");
            }
            System.out.println("");
        }
    }

    public Matrix transpose() {
        Matrix matrix = new Matrix(this.col, this.row);
        for (int i = 0; i < matrix.getRow(); i++) {
            for (int j = 0; j < matrix.getCol(); j++) {
                matrix.setValue(i, j, this.getValue(j, i));
            }
        }
        return matrix;
    }

    public static Matrix inputMatrix() {
        Matrix matrix;
        Scanner input;
        input = new Scanner(System.in);

        System.out.println("Enter a matrix: ");
        System.out.print("\trow: ");
        int row = input.nextInt();
        System.out.print("\tcol: ");
        int col = input.nextInt();
        if (row == col) {
            matrix = new SquareMatrix(row);
        } else {
            matrix = new Matrix(row, col);
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print("matrix[" + i + "][" + j + "] = ");
                matrix.setValue(i, j, input.nextDouble());
            }
        }
        return matrix;
    }

    public static Matrix inputMatrix(String filePath) throws IOException {
        File in = new File(filePath);
        Scanner scanner = new Scanner(in);
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        Matrix matrix = new Matrix(row, col);

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix.setValue(i, j, scanner.nextDouble());
            }
        }

        return matrix;
    }

    public void printToFile(String filePath) throws FileNotFoundException, IOException {
        File fout = new File(filePath);
        FileOutputStream fos = new FileOutputStream(fout);
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fos));
        bufferedWriter.write(this.row + " " + this.col);
        bufferedWriter.newLine();
        DecimalFormat formater = new DecimalFormat("###,###.##");
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                bufferedWriter.write(formater.format((this.getValue(i, j) + 0)) + "\t");
            }
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
    }
}
