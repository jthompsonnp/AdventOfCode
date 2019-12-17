package com.mycompany.adventofcode_day3.part1;

///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.mycompany.adventofcode_day3;
//
//import java.io.BufferedReader;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// *
// * @author Jessica
// */
//public class Day3 {
//
//    static final int MAX = 4000;
//    static int horizontalPosition = MAX / 2;
//    static int verticalPosition = MAX / 2;
//    static int horizontalOffset = 0;
//    static int verticalOffset = 0;
//    static final int FREE = 0;
//    static final int TAKEN_ONE = 1;
//    static final int TAKEN_TWO = 2;
//    static final int CROSSOVER = 3;
//    static int[][] graph = new int[MAX][MAX];
//    static List<String> cordinates1 = new ArrayList<>();
//    static List<String> cordinates2 = new ArrayList<>();
//
//    /**
//     * @param args the command line arguments 159
//     */
//    public static void main(String[] args) throws IOException {
//        readData();
//
//        for (int i = 0; i < MAX; i++) {
//            for (int j = 0; j < MAX; j++) {
//                graph[i][j] = 0;
//            }
//        }
//
//        for (int i = 0; i < cordinates1.size(); i++) {
//            String instructions = cordinates1.get(i);
//            processInstructions(instructions, TAKEN_ONE);
//        }
//
//        verticalPosition = horizontalPosition = MAX / 2;
//
//        for (int i = 0; i < cordinates2.size(); i++) {
//            String instructions = cordinates2.get(i);
//            processInstructions(instructions, TAKEN_TWO);
//        }
//        int half = (MAX / 2);
//        ArrayList crossOvers = new ArrayList();
//        for (int i = 0; i < MAX; i++) {
//            for (int j = 0; j < MAX; j++) {
//                if (graph[i][j] == CROSSOVER) {
//                    int ii = i;
//                    int jj = j;
//
//                    if (i >= half) {
//                        ii = (i - half);
//                    } else {
//                        ii = (half - i);
//
//                    }
//                    if (j >= half) {
//                        jj = (j - half);
//                    } else {
//                        jj = (half - j);
//
//                    }
//                    crossOvers.add(ii + jj);
//
//                }
//            }
//        }
//
//        int smallest = 1000;
//        for (int i = 0; i < crossOvers.size(); i++) {
//            int thisCrossover = Integer.parseInt(crossOvers.get(i).toString());
//            if (thisCrossover < smallest) {
//                if (thisCrossover != 0) {
//                    smallest = thisCrossover;
//                }
//            }
//
//        }
//
//        System.out.println(smallest);
//
//    }
//
//    public static void processInstructions(String instructions, int wireNum) {
//        String direction = instructions.substring(0, 1);
//        int steps = Integer.parseInt(instructions.substring(1));
//
//        //if horizontal postion is 0
//        //horizontal offset set to number of steps left
//        //eg 200 steps, horizontalposition 100. horizontal offset = horizontal positon-steps
//        switch (direction) {
//            case "R":
//                for (int i = 0; i < steps; i++) {
//
//                    if (horizontalOffset == 0) {
//
//                        if (horizontalPosition == MAX) {
//                            horizontalOffset++;
//
//                        } else {
//
//                            if (graph[horizontalPosition][verticalPosition] == FREE) {
//                                graph[horizontalPosition][verticalPosition] = wireNum;
//                            } else if (graph[horizontalPosition][verticalPosition] == CROSSOVER || graph[horizontalPosition][verticalPosition] == wireNum) {
//                                graph[horizontalPosition][verticalPosition] = CROSSOVER;
//                            }
//                            horizontalPosition++;
//                        }
//                    } else {
//                        horizontalOffset++;
//                    }
//                }
//                break;
//
//            case "L":
//                for (int i = 0; i < steps; i++) {
//                    if (horizontalOffset == 0) {
//
//                        if (horizontalPosition == 0) {
//                            horizontalOffset--;
//                        } else {
//                            if (graph[horizontalPosition][verticalPosition] == FREE) {
//                                graph[horizontalPosition][verticalPosition] = wireNum;
//                            } else if (graph[horizontalPosition][verticalPosition] == CROSSOVER || graph[horizontalPosition][verticalPosition] == wireNum) {
//                            } else {
//                                graph[horizontalPosition][verticalPosition] = CROSSOVER;
//                            }
//                            horizontalPosition--;
//                        }
//                    } else {
//                        horizontalOffset--;
//                    }
//                }
//                break;
//
//            case "U":
//                for (int i = 0; i < steps; i++) {
//                    if (verticalOffset == 0) {
//
//                        if (verticalPosition == MAX) {
//                            verticalOffset++;
//                        } else {
//                            if (graph[horizontalPosition][verticalPosition] == FREE) {
//
//                                graph[horizontalPosition][verticalPosition] = wireNum;
//
//                            } else if (graph[horizontalPosition][verticalPosition] == CROSSOVER || graph[horizontalPosition][verticalPosition] == wireNum) {
//                            } else {
//                                graph[horizontalPosition][verticalPosition] = CROSSOVER;
//                            }
//                            verticalPosition++;
//
//                        }
//                    } else {
//                        verticalOffset++;
//                    }
//                }
//                break;
//
//            case "D":
//                for (int i = 0; i < steps; i++) {
//                    if (verticalOffset == 0) {
//
//                        if (verticalPosition == 0) {
//                            verticalOffset--;
//                        } else {
//
//                            if (graph[horizontalPosition][verticalPosition] == FREE) {
//
//                                graph[horizontalPosition][verticalPosition] = wireNum;
//
//                            } else if (graph[horizontalPosition][verticalPosition] == CROSSOVER || graph[horizontalPosition][verticalPosition - i] == wireNum) {
//                            } else {
//                                graph[horizontalPosition][verticalPosition] = CROSSOVER;
//                            }
//                            verticalPosition--;
//
//                        }
//                    } else {
//                        verticalOffset--;
//                    }
//
//                }
//                break;
//
//            default:
//
//        }
//    }
//
//    public static void readData() throws IOException {
//        String file = "C:/Users/Jessica/Documents/GitHub/AdventOfCode/AdventOfCodeDay3Wire1.txt";
//        String fileTwo = "C:/Users/Jessica/Documents/GitHub/AdventOfCode/AdventOfCodeDay3Wire2.txt";
//        String wire1 = "";
//        String wire2 = "";
//
//        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
//            String line = "";
//            while ((line = br.readLine()) != null) {
//                wire1 = line;
//            }
//        } catch (FileNotFoundException e) {
//            //Some error logging
//        }
//
//        try (BufferedReader br = new BufferedReader(new FileReader(fileTwo))) {
//            String line = "";
//            while ((line = br.readLine()) != null) {
//                wire2 = line;
//
//            }
//        } catch (FileNotFoundException e) {
//            //Some error logging
//        }
//
//        for (int i = 0; i < wire1.length(); i++) {
//            int index = wire1.indexOf(",");
//
//            if (index >= 0) {
//                String thisCordinates = wire1.substring(0, index);
//                cordinates1.add(thisCordinates);
//                wire1 = wire1.replaceFirst(thisCordinates + ",", "");
//            }
//        }
//
//        for (int i = 0; i < wire2.length(); i++) {
//            int index = wire2.indexOf(",");
//
//            if (index >= 0) {
//                String thisCordinates = wire2.substring(0, index);
//                cordinates2.add(thisCordinates);
//                wire2 = wire2.replaceFirst(thisCordinates + ",", "");
//            }
//        }
//
//    }
//}
