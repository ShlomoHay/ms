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

    static int[][] open;
    static int[][] test;
    static int[][] bourd;
    static int mineCount;
    static int rowCount;
    static int columnCount;
    static int level;
    static int lose = 0;

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
            mineCount = 3;
            bourd = new int[rowCount][columnCount];
            test = new int[rowCount][columnCount];
            open = new int[rowCount][columnCount];
        }
        if (level == 1) {
            rowCount = 8;
            columnCount = 10;
            mineCount = 10;
            bourd = new int[rowCount][columnCount];
            test = new int[rowCount][columnCount];
            open = new int[rowCount][columnCount];
        }
        if (level == 2) {
            rowCount = 14;
            columnCount = 18;
            mineCount = 40;
            bourd = new int[rowCount][columnCount];
            test = new int[rowCount][columnCount];
            open = new int[rowCount][columnCount];
        }
        if (level == 3) {
            rowCount = 20;
            columnCount = 24;
            mineCount = 99;
            bourd = new int[rowCount][columnCount];
            test = new int[rowCount][columnCount];
            open = new int[rowCount][columnCount];
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
        printTest();
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

    private static void oneStep(int row, int column) {
        if (bourd[row][column] == 9) {
            lose = 1;
        } else {
            test[row][column] = bourd[row][column];
            ziro(row, column);
            printTest();
        }
    }

    private static void ziro(int r, int c) {
        if (bourd[r][c] == 0) {
            open[r][c] = 1;
            openSide(r, c);
        } else {
            open[r][c] = 1;
        }
    }

    private static void openSide(int row, int column) {
        if (row == 0 && column == 0) {
            if (open[row][column + 1] != 1 || open[row + 1][column] != 1
                    || open[row + 1][column + 1] != 1) {
                test[row][column + 1] = bourd[row][column + 1];
                test[row + 1][column] = bourd[row + 1][column];
                test[row + 1][column + 1] = bourd[row + 1][column + 1];
                open[row][column + 1] = 1;
                open[row + 1][column] = 1;
                open[row + 1][column + 1] = 1;
                ziro(row + 1, column);
                ziro(row, column + 1);
                ziro(row + 1, column + 1);
            }
        }
        //(0 , max)
        if (row == 0 && column == columnCount - 1) {
            if (open[row][column - 1] != 1 || open[row + 1][column] != 1
                    || open[row + 1][column - 1] != 1) {
                test[row][column - 1] = bourd[row][column - 1];
                test[row + 1][column] = bourd[row + 1][column];
                test[row + 1][column - 1] = bourd[row + 1][column - 1];
                open[row][column - 1] = 1;
                open[row + 1][column] = 1;
                open[row + 1][column - 1] = 1;
                ziro(row + 1, column);
                ziro(row, column - 1);
                ziro(row + 1, column - 1);
            }
        }
        //(max , 0)
        if (row == rowCount - 1 && column == 0) {
            if (open[row][column + 1] != 1 || open[row - 1][column] != 1
                    || open[row - 1][column + 1] != 1) {
                test[row][column + 1] = bourd[row][column + 1];
                test[row - 1][column] = bourd[row - 1][column];
                test[row - 1][column + 1] = bourd[row - 1][column + 1];
                open[row][column + 1] = 1;
                open[row - 1][column] = 1;
                open[row - 1][column + 1] = 1;
                ziro(row - 1, column);
                ziro(row, column + 1);
                ziro(row - 1, column + 1);
            }
        }
        //(max , max)
        if (row == rowCount - 1 && column == columnCount - 1) {
            if (open[row][column - 1] != 1 || open[row - 1][column] != 1
                    || open[row - 1][column - 1] != 1) {
                test[row][column - 1] = bourd[row][column - 1];
                test[row - 1][column] = bourd[row - 1][column];
                test[row - 1][column - 1] = bourd[row - 1][column - 1];
                open[row][column - 1] = 1;
                open[row - 1][column] = 1;
                open[row - 1][column - 1] = 1;
                ziro(row - 1, column);
                ziro(row, column - 1);
                ziro(row - 1, column - 1);
            }
        }
        //(0 , 0< <max)
        if (row == 0 && column > 0 && column < columnCount - 1) {
            if (open[row][column + 1] != 1 || open[row + 1][column] != 1
                    || open[row + 1][column + 1] != 1 || open[row][column - 1] != 1
                    || open[row + 1][column - 1] != 1) {
                test[row][column + 1] = bourd[row][column + 1];
                test[row + 1][column] = bourd[row + 1][column];
                test[row + 1][column + 1] = bourd[row + 1][column + 1];
                test[row][column - 1] = bourd[row][column - 1];
                test[row + 1][column - 1] = bourd[row + 1][column - 1];
                open[row][column + 1] = 1;
                open[row + 1][column] = 1;
                open[row + 1][column + 1] = 1;
                open[row][column - 1] = 1;
                open[row + 1][column - 1] = 1;
                ziro(row + 1, column);
                ziro(row, column + 1);
                ziro(row + 1, column + 1);
                ziro(row, column - 1);
                ziro(row + 1, column - 1);
            }
        }
        //(0< <max , 0)
        if (row > 0 && row < rowCount - 1 && column == 0) {
            if (open[row][column + 1] != 1 || open[row + 1][column] != 1
                    || open[row + 1][column + 1] != 1 || open[row - 1][column] != 1
                    || open[row - 1][column + 1] != 1) {
                test[row][column + 1] = bourd[row][column + 1];
                test[row + 1][column] = bourd[row + 1][column];
                test[row + 1][column + 1] = bourd[row + 1][column + 1];
                test[row - 1][column] = bourd[row - 1][column];
                test[row - 1][column + 1] = bourd[row - 1][column + 1];
                open[row][column + 1] = 1;
                open[row + 1][column] = 1;
                open[row + 1][column + 1] = 1;
                open[row - 1][column] = 1;
                open[row - 1][column + 1] = 1;
                ziro(row + 1, column);
                ziro(row, column + 1);
                ziro(row + 1, column + 1);
                ziro(row - 1, column);
                ziro(row - 1, column + 1);
            }
        }
        //(0< <max , max)
        if (row > 0 && row < rowCount - 1 && column == columnCount - 1) {
            test[row + 1][column] = bourd[row + 1][column];
            test[row][column - 1] = bourd[row][column - 1];
            test[row + 1][column - 1] = bourd[row + 1][column - 1];
            test[row - 1][column] = bourd[row - 1][column];
            test[row - 1][column - 1] = bourd[row - 1][column - 1];
            open[row + 1][column] = 1;
            open[row][column - 1] = 1;
            open[row + 1][column - 1] = 1;
            open[row - 1][column] = 1;
            open[row - 1][column - 1] = 1;
            ziro(row + 1, column);
            ziro(row, column - 1);
            ziro(row + 1, column - 1);
            ziro(row - 1, column);
            ziro(row - 1, column - 1);
        }
        //(max , 0< <max)
        if (row == rowCount - 1 && column > 0 && column < columnCount - 1) {
            if (open[row - 1][column] != 1 || open[row][column - 1] != 1
                    || open[row - 1][column - 1] != 1 || open[row][column + 1] != 1
                    || open[row - 1][column + 1] != 1) {
                test[row - 1][column] = bourd[row - 1][column];
                test[row][column - 1] = bourd[row][column - 1];
                test[row - 1][column - 1] = bourd[row - 1][column - 1];
                test[row][column + 1] = bourd[row][column + 1];
                test[row - 1][column + 1] = bourd[row - 1][column + 1];
                open[row - 1][column] = 1;
                open[row][column - 1] = 1;
                open[row - 1][column - 1] = 1;
                open[row][column + 1] = 1;
                open[row - 1][column + 1] = 1;
                ziro(row - 1, column);
                ziro(row, column - 1);
                ziro(row - 1, column - 1);
                ziro(row, column + 1);
                ziro(row - 1, column + 1);
            }
        }
        //(0< <max , 0< <max)
        if (row > 0 && row < rowCount - 1 && column > 0 && column < columnCount - 1) {
            if (open[row - 1][column] != 1 || open[row][column - 1] != 1
                    || open[row - 1][column - 1] != 1 || open[row][column + 1] != 1
                    || open[row - 1][column + 1] != 1 || open[row + 1][column] != 1
                    || open[row + 1][column + 1] != 1 || open[row + 1][column - 1] != 1) {
                test[row - 1][column] = bourd[row - 1][column];
                test[row][column - 1] = bourd[row][column - 1];
                test[row - 1][column - 1] = bourd[row - 1][column - 1];
                test[row][column + 1] = bourd[row][column + 1];
                test[row - 1][column + 1] = bourd[row - 1][column + 1];
                test[row + 1][column] = bourd[row + 1][column];
                test[row + 1][column + 1] = bourd[row + 1][column + 1];
                test[row + 1][column - 1] = bourd[row + 1][column - 1];
                open[row - 1][column] = 1;
                open[row][column - 1] = 1;
                open[row - 1][column - 1] = 1;
                open[row][column + 1] = 1;
                open[row - 1][column + 1] = 1;
                open[row + 1][column] = 1;
                open[row + 1][column + 1] = 1;
                open[row + 1][column - 1] = 1;
                ziro(row - 1, column);
                ziro(row, column - 1);
                ziro(row - 1, column - 1);
                ziro(row, column + 1);
                ziro(row - 1, column + 1);
                ziro(row + 1, column);
                ziro(row + 1, column + 1);
                ziro(row + 1, column - 1);
            }
        }
    }

    private static void printTest() {
        int row;
        int column;
        for (row = 0; row < rowCount; row++) {
            column = 0;
            if (level == 0) {
                System.out.println(test[row][column++] + "    " + test[row][column++] + "    " + test[row][column++] + "    " + test[row][column++] + "    ");
                System.out.println("");
            }
            if (level == 1) {
                System.out.println(test[row][column++] + "    " + test[row][column++] + "    " + test[row][column++] + "    " + test[row][column++] + "    "
                        + test[row][column++] + "    " + test[row][column++] + "    " + test[row][column++] + "    " + test[row][column++] + "    " + test[row][column++] + "    "
                        + test[row][column++] + "    ");
                System.out.println("");
            }
            if (level == 2) {
                System.out.println(test[row][column++] + "    " + test[row][column++] + "    " + test[row][column++] + "    " + test[row][column++] + "    "
                        + test[row][column++] + "    " + test[row][column++] + "    " + test[row][column++] + "    " + test[row][column++] + "    " + test[row][column++] + "    "
                        + test[row][column++] + "    " + test[row][column++] + "    " + test[row][column++] + "    " + test[row][column++] + "    " + test[row][column++] + "    "
                        + test[row][column++] + "    " + test[row][column++] + "    " + test[row][column++] + "    " + test[row][column++] + "    ");
                System.out.println("");
            }
            if (level == 3) {
                System.out.println(test[row][column++] + "    " + test[row][column++] + "    " + test[row][column++] + "    " + test[row][column++] + "    "
                        + test[row][column++] + "    " + test[row][column++] + "    " + test[row][column++] + "    " + test[row][column++] + "    " + test[row][column++] + "    "
                        + test[row][column++] + "    " + test[row][column++] + "    " + test[row][column++] + "    " + test[row][column++] + "    " + test[row][column++] + "    "
                        + test[row][column++] + "    " + test[row][column++] + "    " + test[row][column++] + "    " + test[row][column++] + "    " + test[row][column++] + "    "
                        + test[row][column++] + "    " + test[row][column++] + "    " + test[row][column++] + "    " + test[row][column++] + "    " + test[row][column++] + "    ");
                System.out.println("");
            }
        }
    }

    private static int winGame() {
        int r;
        int c;
        int how_much_left;
        how_much_left = 0;
        for (r = 0; r < rowCount; r++) {
            for (c = 0; c < columnCount; c++) {
                if (open[r][c] == 1) {
                    how_much_left++;
                }
            }
        }
        if (how_much_left == rowCount * columnCount - mineCount) {
            return 1;
        } else {
            return 0;
        }
    }

    private static void printopen() {
        int row;
        int column;
        for (row = 0; row < rowCount; row++) {
            column = 0;
            if (level == 0) {
                System.out.println(open[row][column++] + "    " + open[row][column++] + "    " + open[row][column++] + "    " + open[row][column++] + "    ");
                System.out.println("");
            }
            if (level == 1) {
                System.out.println(open[row][column++] + "    " + open[row][column++] + "    " + open[row][column++] + "    " + open[row][column++] + "    "
                        + open[row][column++] + "    " + open[row][column++] + "    " + open[row][column++] + "    " + open[row][column++] + "    " + open[row][column++] + "    "
                        + open[row][column++] + "    ");
                System.out.println("");
            }
            if (level == 2) {
                System.out.println(open[row][column++] + "    " + open[row][column++] + "    " + open[row][column++] + "    " + open[row][column++] + "    "
                        + open[row][column++] + "    " + open[row][column++] + "    " + open[row][column++] + "    " + open[row][column++] + "    " + open[row][column++] + "    "
                        + open[row][column++] + "    " + open[row][column++] + "    " + open[row][column++] + "    " + open[row][column++] + "    " + open[row][column++] + "    "
                        + open[row][column++] + "    " + open[row][column++] + "    " + open[row][column++] + "    " + open[row][column++] + "    ");
                System.out.println("");
            }
            if (level == 3) {
                System.out.println(open[row][column++] + "    " + open[row][column++] + "    " + open[row][column++] + "    " + open[row][column++] + "    "
                        + open[row][column++] + "    " + open[row][column++] + "    " + open[row][column++] + "    " + open[row][column++] + "    " + open[row][column++] + "    "
                        + open[row][column++] + "    " + open[row][column++] + "    " + open[row][column++] + "    " + open[row][column++] + "    " + open[row][column++] + "    "
                        + open[row][column++] + "    " + open[row][column++] + "    " + open[row][column++] + "    " + open[row][column++] + "    " + open[row][column++] + "    "
                        + open[row][column++] + "    " + open[row][column++] + "    " + open[row][column++] + "    " + open[row][column++] + "    " + open[row][column++] + "    ");
                System.out.println("");
            }
        }
    }

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        int row;
        int column;
        int win;
        Scanner console = new Scanner(System.in);
        System.out.println("Please enter a number: ");
        level = console.nextInt();
        ValidateInitialValues();
        while (lose == 0) {
            System.out.println("enter place: ");
            row = console.nextInt();
            column = console.nextInt();
            oneStep(row, column);
            win = winGame();
            if (win == 1) {
                System.out.println("You win!!");
                printBourd();
                break;
            }
        }
        if (lose == 1) {
            System.out.println("You lose");
            printBourd();
        }
    }

}
