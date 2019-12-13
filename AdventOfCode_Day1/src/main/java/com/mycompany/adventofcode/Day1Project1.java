package com.mycompany.adventofcode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 *
 * @author Jessica
 */
public class Day1Project1 {

    public static ArrayList<Integer> numbers = new ArrayList<Integer>();
    static int totalFuel = 0;

    public static void main(String[] args) {

        try {
            loadNumbers();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }


        for (int i = 0; i < numbers.size(); i++) {
            totalFuel += calculateFuel(numbers.get(i));
        }

        System.out.println("Fuel Total: " + totalFuel);
    }

    public static int calculateFuel(int fuelToCalculate) {
        //Resetting fuelForThisModule to 0 every time we enter calculate fuel
        int fuelForThisModule = 0;
        
        do {
            //divide by 3 || round down || subtract 2
            int currentFuelRequirement = ((int) ((fuelToCalculate) / 3)) - 2;
            
            //Add to toal fuel for this module
            fuelForThisModule += currentFuelRequirement;
            
            //set fuelToCalculate to the currentFuelRequirement
            fuelToCalculate = currentFuelRequirement;
            
            //while currentFuelRequirement is > 0, continue calculations
        } while (((int) ((fuelToCalculate) / 3)-2)>0);
        
        return fuelForThisModule;
    }

    public static void loadNumbers() throws FileNotFoundException {
        Scanner s = new Scanner(new File("C:/Users/Jessica/Documents/NetBeansProjects/adventOfCode.txt"));

        while (s.hasNextLine()) {
            numbers.add(s.nextInt());
        }

    }
}
