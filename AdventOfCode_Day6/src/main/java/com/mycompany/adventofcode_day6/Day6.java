/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.adventofcode_day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Jessica
 */
public class Day6 {

    static ArrayList orbitalRelationships = new ArrayList();
    static HashMap<String, String> relationshipsMap = new HashMap<String, String>();
    static HashMap<Integer, String> crossRoads = new HashMap<Integer, String>();
    static HashMap<Integer, String> paths = new HashMap<Integer, String>();
    static String value = "";
    static String key = "COM";
    static boolean allPathsFound = false;
    static boolean pathEnd = false;
    static Integer duplicateKey;
    static int countCrossroads = 0;
    static final int PLANET_CODE_LENGTH = 3;

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        try {
            loadData();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }

//        testData();
        int total = 0;

        //all direct paths
        total += orbitalRelationships.size();

        do {
            getPaths(key);
        } while (!allPathsFound);

        //Getting planets that indirectly orbit COM
        //This is equal to all planets, minus COM and the planets that directly orbit COM.
        int inDirectForCOM = 1;
        for (int i = 0; i < orbitalRelationships.size(); i++) {
            String thisRelationship = orbitalRelationships.get(i).toString();
            if (thisRelationship.contains("COM")) {
                inDirectForCOM++;
            }
        }

        total += (total - inDirectForCOM);

        boolean planetFoundInPath;
        for (HashMap.Entry<Integer, String> thisPath : paths.entrySet()) {
            String pathsValue = thisPath.getValue();
            Integer pathsKey = thisPath.getKey();

            do {

                String lastPlanetInPath = pathsValue.substring(pathsValue.length() - PLANET_CODE_LENGTH);
                total += (pathsValue.length() - (PLANET_CODE_LENGTH * 2)) / PLANET_CODE_LENGTH;
                pathsValue = pathsValue.replace(lastPlanetInPath, "");

                paths.put(pathsKey, pathsValue);

                planetFoundInPath = checkPaths(pathsValue.substring(pathsValue.length() - PLANET_CODE_LENGTH), pathsKey);

            } while (!planetFoundInPath);
        }

        displayTesting();

        String longestPath = "";
        for (HashMap.Entry<Integer, String> thisPath : paths.entrySet()) {
            String pathsValue = thisPath.getValue();

            if (pathsValue.length() > longestPath.length()) {
                longestPath = pathsValue;
            }

        }

        total += longestPath.length();

        System.out.println("Total: " + total);
    }

    public static void getPaths(String key) {
        value = "";
        int counter = 0;
        String lastValues = "";
        do {
            counter++;

            pathEnd = false;
            //if the key has a value associated (if the planet has another planet in orbit)
            if (relationshipsMap.get(key) != null) {

                String theseValues = relationshipsMap.get(key);
                //if the value doesnt have a comma, attempt to save
                if (!theseValues.contains(",")) {
                    value += theseValues;
                } else {

                    //if value has a comma, it is multiple, split and save individually
                    String[] valuesSplit = theseValues.split(",");

                    //NOT REUSABLE AT ALL! I know theres only one crossroad with 3 orbiting planets, so this will work for this situation ONLY
                    if (crossRoads.get(2).contains(theseValues)) {
                        switch (countCrossroads) {
                            case 0:
                                value += valuesSplit[0];
                                break;
                            case 1:
                                value += valuesSplit[1];
                                break;
                            default:
                                value += valuesSplit[2];
                                break;
                        }
                        countCrossroads++;
                    } else if (crossRoads.get(1).contains(theseValues)) {

                        value += valuesSplit[0];

                        String newCrossRoads = crossRoads.get(1).replace(theseValues, "");
                        crossRoads.put(1, newCrossRoads);

                    } else {

                        value += valuesSplit[1];
                        String newCrossRoads = crossRoads.get(1).replace(theseValues, "");
                        crossRoads.put(1, newCrossRoads);
                    }

                    lastValues = theseValues;
                }

                //Setting the key to the last value
                key = value.substring(value.length() - PLANET_CODE_LENGTH);

            } else {
                //if value is null -> end of path
                pathEnd = true;
                if (!lastValues.equals("")) {
                    key = "COM";
                }

                for (HashMap.Entry<Integer, String> entry : paths.entrySet()) {
                    String mapValue = entry.getValue();
                    Integer mapKey = entry.getKey();
                    if (mapValue.equals(value)) {
                        allPathsFound = true;

                    }
                }
            }

        } while (!pathEnd);
        paths.put(counter, value);

    }

    public static boolean checkPaths(String planet, int key) {
        boolean planetFoundInPath = false;

        for (HashMap.Entry<Integer, String> thesePaths : paths.entrySet()) {
            if (thesePaths.getValue().contains(planet) && thesePaths.getKey() != key) {
                planetFoundInPath = true;
                break;
            }
        }

        return planetFoundInPath;
    }

    public static void getCrossroads() {
        for (HashMap.Entry<String, String> thisOrbit : relationshipsMap.entrySet()) {
            String thisOrbitkey = thisOrbit.getKey();
            String thisOrbitvalue = thisOrbit.getValue();

            int count = 0;

            if (thisOrbitvalue.indexOf(",") > 0) {
                for (int i = 0; i < thisOrbitvalue.length(); i++) {
                    //ascii code for comma is 44
                    if (thisOrbitvalue.charAt(i) == 44) {
                        count++;
                    }
                }

                if (crossRoads.get(count) == null) {
                    //holding all values where the tree splits
                    crossRoads.put(count, thisOrbitvalue);
                } else {
                    String tempValues = crossRoads.get(count);
                    crossRoads.put(count, tempValues + "," + thisOrbitvalue);
                }
            }

        }

    }

    public static void loadMap() {
        for (int i = 0; i < orbitalRelationships.size(); i++) {
            //saving each line and finding the ) to diferentiate between the planet that's being orbited and the one doing the orbiting 
            String thisRelationship = orbitalRelationships.get(i).toString();
            int index = thisRelationship.indexOf(")");

            //As long as theres a ), theres a relationship to be recorded
            if (index >= 0) {
                String orbit = thisRelationship.substring(0, index);
                String orbiting = thisRelationship.substring(index + 1);

                if (relationshipsMap.containsKey(orbit)) {
                    //if the key already exists, add the new orbiting planet to the list
                    String tempOrbiting = relationshipsMap.get(orbit);
                    relationshipsMap.put(orbit, orbiting + "," + tempOrbiting);

                } else {
                    //if the key does not exist, add it
                    relationshipsMap.put(orbit, orbiting);
                }
            }

        }

        getCrossroads();

    }

    public static void loadData() throws FileNotFoundException {
        Scanner s = new Scanner(new File("C:/Users/Jessica/Documents/GitHub/AdventOfCode/AdventOfCodeDay6.txt"));

        while (s.hasNextLine()) {
            orbitalRelationships.add(s.next());
        }
        loadMap();

    }

    public static void testData() {

        orbitalRelationships.add("COM)B");
        orbitalRelationships.add("B)C");
        orbitalRelationships.add("C)D");
        orbitalRelationships.add("D)E");
        orbitalRelationships.add("E)F");
        orbitalRelationships.add("B)G");
        orbitalRelationships.add("G)H");
        orbitalRelationships.add("D)I");
        orbitalRelationships.add("E)J");
        orbitalRelationships.add("J)K");
        orbitalRelationships.add("K)L");
        loadMap();

    }

    public static void displayTesting() {
        //display relationships
        for (HashMap.Entry<String, String> thisOrbit : relationshipsMap.entrySet()) {
            System.out.println(thisOrbit.getKey() + " - " + thisOrbit.getValue());
        }

        //display crossroads
        for (HashMap.Entry<Integer, String> thisCross : crossRoads.entrySet()) {
            System.out.println(thisCross.getKey() + " - " + thisCross.getValue());
        }

        //display all paths
        for (HashMap.Entry<Integer, String> thisPath : paths.entrySet()) {
            System.out.println(thisPath.getKey() + " - " + thisPath.getValue());
        }
    }

}
