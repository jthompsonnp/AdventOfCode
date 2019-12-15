/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.adventofcode_day3;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 *
 * @author Jessica
 */
public class Day3 {

    static final int MAX = 100;
    static final int MIN = 0;
    static int horizontal = 0;
    static int vertical = 0;
    static final int FREE = 0;
    static final int TAKEN_ONE = 1;
    static final int TAKEN_TWO = 2;
    static final int CROSSOVER = 3;

    /**
     * @param args the command line arguments 159
     */
    public static void main(String[] args) {

        ArrayList<String> cordinates1 = new ArrayList<>();
        ArrayList<String> cordinates2 = new ArrayList<>();
        cordinates1.add("R75");
        cordinates1.add("D30");
        cordinates1.add("R83");
        cordinates1.add("U83");
        cordinates1.add("L12");
        cordinates1.add("D49");
        cordinates1.add("R71");
        cordinates1.add("U7");
        cordinates1.add("L72");

        cordinates2.add("U62");
        cordinates2.add("R66");
        cordinates2.add("U55");
        cordinates2.add("R34");
        cordinates2.add("D71");
        cordinates2.add("R55");
        cordinates2.add("D58");
        cordinates2.add("R83");

        int[][] graph = new int[100][100];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                graph[i][j] = 0;
            }
        }

        for (int i = 0; i < cordinates1.size(); i++) {
            String instructions = cordinates1.get(i);
            graph = processInstructions(instructions, graph, TAKEN_ONE);

        }
        horizontal = 0;
        vertical = 0;

        for (int i = 0; i < cordinates2.size(); i++) {
            String instructions = cordinates2.get(i);
            graph = processInstructions(instructions, graph, TAKEN_TWO);

        }

        
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (graph[i][j] == 3) {

                }
            }
        }

    }

    public static int[][] processInstructions(String instructions, int[][] graph, int wireNum) {
        String direction = instructions.substring(0, 1);
        int steps = Integer.parseInt(instructions.substring(1));

        switch (direction) {
            case "R":
                for (int i = horizontal; i < steps; i++) {
                    if (graph[i][vertical] != 0 && graph[i][vertical] != wireNum) {
                        graph[i][vertical] = CROSSOVER;
                    } else {
                        graph[i][vertical] = wireNum;
                    }
                }
                horizontal += steps;
                break;
            case "L":
                for (int i = horizontal; i < steps; i++) {
                    if (graph[i][vertical] != 0 || graph[i][vertical] != wireNum) {
                        graph[i][vertical] = CROSSOVER;
                    } else {
                        graph[i][vertical] = wireNum;
                    }
                }
                horizontal -= steps;
                break;
            case "U":
                for (int i = vertical; i < steps; i++) {
                    if (graph[i][vertical] != 0 || graph[i][vertical] != wireNum) {
                        graph[i][vertical] = CROSSOVER;
                    } else {
                        graph[i][vertical] = wireNum;

                    }
                }
                vertical += steps;
                break;
            case "D":
                for (int i = vertical; i < steps; i++) {
                    if (graph[i][vertical] != 0 || graph[i][vertical] != wireNum) {
                        graph[i][vertical] = CROSSOVER;
                    } else {
                        graph[i][vertical] = wireNum;
                    }
                }
                vertical -= steps;
                break;
            default:

        }
        return graph;
    }

}
