package com.mycompany.adventofcode_day3.part2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Day 3, part 2, absolute disaster.
 *
 * @author Jessica
 */
public class Day3Part2 {

    static final int WIRE_ONE = 1;
    static final int WIRE_TWO = 2;
    static final int CROSSOVER = 3;
    static int xPosition;
    static int yPosition;
    static List<String> cordinates1 = new ArrayList<>();
    static List<String> cordinates2 = new ArrayList<>();
    static HashMap<String, Integer> graph = new HashMap<String, Integer>();
    static HashMap<String, Integer> wireOneSteps = new HashMap<String, Integer>();
    static HashMap<String, Integer> crossoverSteps = new HashMap<String, Integer>();

    static int currentSteps;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        readData();
//        crapTestOne();
//        crapTestTwo();
        currentSteps = 0;
        for (int i = 0; i < cordinates1.size(); i++) {
            String instructions = cordinates1.get(i);
            processInstructions(instructions, WIRE_ONE);
        }
        currentSteps = xPosition = yPosition = 0;

        for (int i = 0; i < cordinates2.size(); i++) {
            String instructions = cordinates2.get(i);
            processInstructions(instructions, WIRE_TWO);
        }

        ArrayList crossoverStepTotals = new ArrayList();
        for (String i : crossoverSteps.keySet()) {
            crossoverStepTotals.add(wireOneSteps.get(i) + crossoverSteps.get(i));
        }

        //arbitrary number
        int smallest = 100000;
        for (int i = 0; i < crossoverStepTotals.size(); i++) {

            int thisCrossover = Integer.parseInt(crossoverStepTotals.get(i).toString());

            if (thisCrossover <= smallest) {
                smallest = thisCrossover;
            }

        }
        System.out.println("Smallest: " + smallest);

//-----------------------------------------PARTONE-----------------------------------------
//        ArrayList crossOvers = new ArrayList();
//        for (String i : graph.keySet()) {
//            if (graph.get(i) == CROSSOVER) {
//                int index = i.indexOf(":");
//                int x = Integer.parseInt(i.substring(0, index));
//                int y = Integer.parseInt(i.substring(index + 1));
//
//                int manhattanDistance = Math.abs(x) + Math.abs(y);
//                if (manhattanDistance != 0) {
//                    crossOvers.add(manhattanDistance);
//                }
//            }
//        }
//
//        for (int i = 0; i < crossOvers.size(); i++) {
//            System.out.println(crossOvers.get(i));
//        }
//
//        //arbitrary number
//        int closest = 1000;
//        for (int i = 0; i < crossOvers.size(); i++) {
//            int thisCrossover = Integer.parseInt(crossOvers.get(i).toString());
//
//            if (thisCrossover <= closest) {
//                closest = thisCrossover;
//            }
//        }
//
//        System.out.println("Closest: " + closest);
    }

    public static void processInstructions(String instructions, int wireNum) {
        String direction = instructions.substring(0, 1);
        int steps = Integer.parseInt(instructions.substring(1));

        switch (direction) {
            case "R":
                for (int i = 0; i < steps; i++) {
                    xPosition++;
                    currentSteps++;

                    if (!graph.containsKey(xPosition + ":" + yPosition)) {
                        graph.put(xPosition + ":" + yPosition, wireNum);
                        if (wireNum == WIRE_ONE) {
                            wireOneSteps.put(xPosition + ":" + yPosition, currentSteps);
                        }
                    } else {
                        if (graph.get(xPosition + ":" + yPosition) != wireNum) {
                            graph.put(xPosition + ":" + yPosition, CROSSOVER);
                            if (wireNum == WIRE_TWO) {
                                crossoverSteps.put(xPosition + ":" + yPosition, currentSteps);
                            }
                        }
                    }
                }

                break;

            case "L":
                for (int i = 0; i < steps; i++) {
                    xPosition--;
                    currentSteps++;

                    if (!graph.containsKey(xPosition + ":" + yPosition)) {
                        graph.put(xPosition + ":" + yPosition, wireNum);
                        if (wireNum == WIRE_ONE) {
                            wireOneSteps.put(xPosition + ":" + yPosition, currentSteps);
                        }
                    } else {
                        if (graph.get(xPosition + ":" + yPosition) != wireNum) {
                            graph.put(xPosition + ":" + yPosition, CROSSOVER);
                            if (wireNum == WIRE_TWO) {
                                crossoverSteps.put(xPosition + ":" + yPosition, currentSteps);
                            }
                        }
                    }

                }
                break;
            case "U":
                for (int i = 0; i < steps; i++) {
                    yPosition++;
                    currentSteps++;

                    if (!graph.containsKey(xPosition + ":" + yPosition)) {
                        graph.put(xPosition + ":" + yPosition, wireNum);
                        if (wireNum == WIRE_ONE) {
                            wireOneSteps.put(xPosition + ":" + yPosition, currentSteps);
                        }
                    } else {
                        if (graph.get(xPosition + ":" + yPosition) != wireNum) {
                            graph.put(xPosition + ":" + yPosition, CROSSOVER);
                            if (wireNum == WIRE_TWO) {
                                crossoverSteps.put(xPosition + ":" + yPosition, currentSteps);
                            }
                        }

                    }

                }
                break;
            case "D":
                for (int i = 0; i < steps; i++) {
                    yPosition--;
                    currentSteps++;

                    if (!graph.containsKey(xPosition + ":" + yPosition)) {
                        graph.put(xPosition + ":" + yPosition, wireNum);
                        if (wireNum == WIRE_ONE) {
                            wireOneSteps.put(xPosition + ":" + yPosition, currentSteps);
                        }
                    } else {
                        if (graph.get(xPosition + ":" + yPosition) != wireNum) {
                            graph.put(xPosition + ":" + yPosition, CROSSOVER);
                            if (wireNum == WIRE_TWO) {
                                crossoverSteps.put(xPosition + ":" + yPosition, currentSteps);
                            }
                        }
                    }

                }
                break;
            default:
        }

    }

    public static void readData() throws IOException {
        String file = "C:/Users/Jessica/Documents/GitHub/AdventOfCode/AdventOfCodeDay3Wire1.txt";
        String fileTwo = "C:/Users/Jessica/Documents/GitHub/AdventOfCode/AdventOfCodeDay3Wire2.txt";
        String wire1 = "";
        String wire2 = "";

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                wire1 = line;
            }
        } catch (FileNotFoundException e) {
            //Some error logging
        }

        try (BufferedReader br = new BufferedReader(new FileReader(fileTwo))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                wire2 = line;

            }
        } catch (FileNotFoundException e) {
            //Some error logging
        }

        for (int i = 0; i < wire1.length(); i++) {
            int index = wire1.indexOf(",");

            if (index >= 0) {
                String thisCordinates = wire1.substring(0, index);
                cordinates1.add(thisCordinates);
                wire1 = wire1.replaceFirst(thisCordinates + ",", "");
            }
        }

        for (int i = 0; i < wire2.length(); i++) {
            int index = wire2.indexOf(",");

            if (index >= 0) {
                String thisCordinates = wire2.substring(0, index);
                cordinates2.add(thisCordinates);
                wire2 = wire2.replaceFirst(thisCordinates + ",", "");
            }
        }

    }

    public static void crapTestOne() {
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
    }

    public static void crapTestTwo() {
        cordinates1.add("R98");
        cordinates1.add("U47");
        cordinates1.add("R26");
        cordinates1.add("D63");
        cordinates1.add("R33");
        cordinates1.add("U87");
        cordinates1.add("L62");
        cordinates1.add("D20");
        cordinates1.add("R33");
        cordinates1.add("U53");
        cordinates1.add("R51");

        cordinates2.add("U98");
        cordinates2.add("R91");
        cordinates2.add("D20");
        cordinates2.add("R16");
        cordinates2.add("D67");
        cordinates2.add("R40");
        cordinates2.add("U7");
        cordinates2.add("R15");
        cordinates2.add("U6");
        cordinates2.add("R7");
    }

}
