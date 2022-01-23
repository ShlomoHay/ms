/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package msgame;

import java.util.Scanner;

/**
 *
 * @author 97250
 */
public class MSGame {

    static int[][] bourd;
    static int mineCount;
    static int rowCount;
    static int columnCount;
    static int level;

    private static void ValidateInitialValues() throws Exception {
        if (level < 0 || level > 3) {
            throw new Exception("Number of level is not valid");
        }
        creatBourd();
    }

    private static void creatBourd() {
        if (level == 0) {
            rowCount = 4;
            columnCount = 4;
            mineCount = 5;
            bourd = new int[rowCount][columnCount];
        }
        if (level == 1) {
            rowCount = 8;
            columnCount = 10;
            mineCount = 10;
            bourd = new int[rowCount][columnCount];
        }
        if (level == 2) {
            rowCount = 14;
            columnCount = 18;
            mineCount = 40;
            bourd = new int[rowCount][columnCount];
        }
        if (level == 3) {
            rowCount = 20;
            columnCount = 24;
            mineCount = 99;
            bourd = new int[rowCount][columnCount];
        }
        scatteringMines();
    }

    private static void scatteringMines() {
        int numrounds;
        int randrow;
        int randcoulmn;
        for (numrounds = 0; numrounds < mineCount; numrounds++) {
            randrow = (int) ((Math.random() * (rowCount)));
            randcoulmn = (int) ((Math.random() * (columnCount)));
            if (bourd[randrow][randcoulmn] != 9) {
                bourd[randrow][randcoulmn] = 9;
            } else {
                numrounds = numrounds - 1;
            }
        }
        minesAround();
    }

    private static void minesAround() {
        int row;
        int column;
        int boom = 0;
        for (row = 0; row < rowCount; row++) {
            for (column = 0; column < columnCount; column++) {
                if (bourd[row][column] == 9) {
                    continue;
                }
                //(0 , 0)
                if (row == 0 && column == 0) {
                    if (bourd[row + 1][column] == 9) {
                        boom++;
                    }
                    if (bourd[row][column + 1] == 9) {
                        boom++;
                    }
                    if (bourd[row + 1][column + 1] == 9) {
                        boom++;
                    }
                    bourd[row][column] = boom;
                    boom = 0;
                    continue;
                }
                //(0 , max)
                if (row == 0 && column == columnCount - 1) {
                    if (bourd[row + 1][column] == 9) {
                        boom++;
                    }
                    if (bourd[row][column - 1] == 9) {
                        boom++;
                    }
                    if (bourd[row + 1][column - 1] == 9) {
                        boom++;
                    }
                    bourd[row][column] = boom;
                    boom = 0;
                    continue;
                }
                //(max , 0)
                if (row == rowCount - 1 && column == 0) {
                    if (bourd[row - 1][column] == 9) {
                        boom++;
                    }
                    if (bourd[row][column + 1] == 9) {
                        boom++;
                    }
                    if (bourd[row - 1][column + 1] == 9) {
                        boom++;
                    }
                    bourd[row][column] = boom;
                    boom = 0;
                    continue;
                }
                //(max , max)
                if (row == rowCount - 1 && column == columnCount - 1) {
                    if (bourd[row - 1][column] == 9) {
                        boom++;
                    }
                    if (bourd[row][column - 1] == 9) {
                        boom++;
                    }
                    if (bourd[row - 1][column - 1] == 9) {
                        boom++;
                    }
                    bourd[row][column] = boom;
                    boom = 0;
                    continue;
                }
                //(0 , 0< <max)
                if (row == 0 && column > 0 && column < columnCount - 1) {
                    if (bourd[row + 1][column] == 9) {
                        boom++;
                    }
                    if (bourd[row][column + 1] == 9) {
                        boom++;
                    }
                    if (bourd[row + 1][column + 1] == 9) {
                        boom++;
                    }
                    if (bourd[row][column - 1] == 9) {
                        boom++;
                    }
                    if (bourd[row + 1][column - 1] == 9) {
                        boom++;
                    }
                    bourd[row][column] = boom;
                    boom = 0;
                    continue;
                }
                //(0< <max , 0)
                if (row > 0 && row < rowCount - 1 && column == 0) {
                    if (bourd[row + 1][column] == 9) {
                        boom++;
                    }
                    if (bourd[row][column + 1] == 9) {
                        boom++;
                    }
                    if (bourd[row + 1][column + 1] == 9) {
                        boom++;
                    }
                    if (bourd[row - 1][column] == 9) {
                        boom++;
                    }
                    if (bourd[row - 1][column + 1] == 9) {
                        boom++;
                    }
                    bourd[row][column] = boom;
                    boom = 0;
                    continue;
                }
                //(0< <max , max)
                if (row > 0 && row < rowCount - 1 && column == columnCount - 1) {
                    if (bourd[row + 1][column] == 9) {
                        boom++;
                    }
                    if (bourd[row][column - 1] == 9) {
                        boom++;
                    }
                    if (bourd[row + 1][column - 1] == 9) {
                        boom++;
                    }
                    if (bourd[row - 1][column] == 9) {
                        boom++;
                    }
                    if (bourd[row - 1][column - 1] == 9) {
                        boom++;
                    }
                    bourd[row][column] = boom;
                    boom = 0;
                    continue;
                }
                //(max , 0< <max)
                if (row == rowCount - 1 && column > 0 && column < columnCount - 1) {
                    if (bourd[row - 1][column] == 9) {
                        boom++;
                    }
                    if (bourd[row][column - 1] == 9) {
                        boom++;
                    }
                    if (bourd[row - 1][column - 1] == 9) {
                        boom++;
                    }
                    if (bourd[row][column + 1] == 9) {
                        boom++;
                    }
                    if (bourd[row - 1][column + 1] == 9) {
                        boom++;
                    }
                    bourd[row][column] = boom;
                    boom = 0;
                    continue;
                }
                //(0< <max , 0< <max)
                if (row > 0 && row < rowCount - 1 && column > 0 && column < columnCount - 1) {
                    if (bourd[row - 1][column] == 9) {
                        boom++;
                    }
                    if (bourd[row][column - 1] == 9) {
                        boom++;
                    }
                    if (bourd[row - 1][column - 1] == 9) {
                        boom++;
                    }
                    if (bourd[row][column + 1] == 9) {
                        boom++;
                    }
                    if (bourd[row - 1][column + 1] == 9) {
                        boom++;
                    }
                    if (bourd[row + 1][column] == 9) {
                        boom++;
                    }
                    if (bourd[row + 1][column + 1] == 9) {
                        boom++;
                    }
                    if (bourd[row + 1][column - 1] == 9) {
                        boom++;
                    }
                    bourd[row][column] = boom;
                    boom = 0;
                    continue;
                }

            }
        }
        printBourd();
    }

    private static void printBourd() {
        int row;
        int column;
        for (row = 0; row < rowCount; row++) {
            column = 0;
            if (level == 0) {
                System.out.println(bourd[row][column++] + "    " + bourd[row][column++] + "    " + bourd[row][column++] + "    " + bourd[row][column++] + "    ");
                System.out.println("");
            }
            if (level == 1) {
                System.out.println(bourd[row][column++] + "    " + bourd[row][column++] + "    " + bourd[row][column++] + "    " + bourd[row][column++] + "    "
                        + bourd[row][column++] + "    " + bourd[row][column++] + "    " + bourd[row][column++] + "    " + bourd[row][column++] + "    " + bourd[row][column++] + "    "
                        + bourd[row][column++] + "    ");
                System.out.println("");
            }
            if (level == 2) {
                System.out.println(bourd[row][column++] + "    " + bourd[row][column++] + "    " + bourd[row][column++] + "    " + bourd[row][column++] + "    "
                        + bourd[row][column++] + "    " + bourd[row][column++] + "    " + bourd[row][column++] + "    " + bourd[row][column++] + "    " + bourd[row][column++] + "    "
                        + bourd[row][column++] + "    " + bourd[row][column++] + "    " + bourd[row][column++] + "    " + bourd[row][column++] + "    " + bourd[row][column++] + "    "
                        + bourd[row][column++] + "    " + bourd[row][column++] + "    " + bourd[row][column++] + "    " + bourd[row][column++] + "    ");
                System.out.println("");
            }
            if (level == 3) {
                System.out.println(bourd[row][column++] + "    " + bourd[row][column++] + "    " + bourd[row][column++] + "    " + bourd[row][column++] + "    "
                        + bourd[row][column++] + "    " + bourd[row][column++] + "    " + bourd[row][column++] + "    " + bourd[row][column++] + "    " + bourd[row][column++] + "    "
                        + bourd[row][column++] + "    " + bourd[row][column++] + "    " + bourd[row][column++] + "    " + bourd[row][column++] + "    " + bourd[row][column++] + "    "
                        + bourd[row][column++] + "    " + bourd[row][column++] + "    " + bourd[row][column++] + "    " + bourd[row][column++] + "    " + bourd[row][column++] + "    "
                        + bourd[row][column++] + "    " + bourd[row][column++] + "    " + bourd[row][column++] + "    " + bourd[row][column++] + "    " + bourd[row][column++] + "    ");
                System.out.println("");
            }
        }
    }

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        Scanner console = new Scanner(System.in);
        System.out.println("Please enter a number: ");
        level = console.nextInt();
        ValidateInitialValues();
    }

}
