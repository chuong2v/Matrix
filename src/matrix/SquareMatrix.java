/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrix;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import static java.lang.Integer.parseInt;
import java.text.DecimalFormat;
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

    public SquareMatrix(Matrix matrix) {
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

    @Override
    public SquareMatrix multify(double num) {
        return new SquareMatrix(super.multify(num));
    }

    @Override
    public SquareMatrix multify(Matrix matrix) throws MatrixException {
        return new SquareMatrix(super.multify(matrix));
    }

    @Override
    public SquareMatrix transpose() {
        return new SquareMatrix(super.transpose());
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

    public SquareMatrix invert() throws MatrixException {
        if (!this.isInvertible()) {
            throw new MatrixException("This is not an invertible matrix!");
        }
        SquareMatrix matrix = new SquareMatrix(n);
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

    public static SquareMatrix inputMatrix(String filePath) throws IOException {
        File in = new File(filePath);
//        FileInputStream fileInputStream = new FileInputStream(in);
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
//        int n = parseInt(bufferedReader.readLine());
//        SquareMatrix squareMatrix = new SquareMatrix(n);
//
//        for (int i = 0; i < n; i++) {
//            String[] str = bufferedReader.readLine().replaceAll("\\s+", " ").split("[\\t\\s]");
//            for (int j = 0; j < n; j++) {
//                squareMatrix.setValue(i, j, Double.parseDouble(str[j]));
//            }
//        }

        Scanner scanner = new Scanner(in);
        int n = scanner.nextInt();
        SquareMatrix squareMatrix = new SquareMatrix(n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                squareMatrix.setValue(i, j, scanner.nextDouble());
            }
        }

        return squareMatrix;
    }

    @Override
    public void printToFile(String filePath) throws FileNotFoundException, IOException {
        File fout = new File(filePath);
        FileOutputStream fos = new FileOutputStream(fout);
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fos));
        bufferedWriter.write(this.n + "");
        bufferedWriter.newLine();
        DecimalFormat formater = new DecimalFormat("###,###.##");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                bufferedWriter.write(formater.format((this.getValue(i, j) + 0)) + "\t");
            }
            bufferedWriter.newLine();
        }

        bufferedWriter.close();
    }
}
