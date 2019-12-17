/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.adventofcode_day5;

import java.util.Scanner;

/**
 *
 * @author Jessica
 * TODO - program crashes at the end, but gives correct output. 
 */
public class CodeFinder {

    //instruction variables
    final int ADD = 1;
    final int MULTIPLY = 2;
    final int INPUT = 3;
    final int OUTPUT = 4;
    final int JUMPIFTRUE = 5;
    final int JUMPIFFALSE = 6;
    final int LESSTHAN = 7;
    final int EQUALS = 8;
    final int END = 99;

    boolean programOver = false;
    boolean comboFound = false;
    int totalCombos = 0;
    int[] codes;
    static Scanner input = new Scanner(System.in);
    int instructionPointer;

    public CodeFinder(int[] codes) {
        this.codes = codes;
    }

    //
    public void codeProcessor() {

        instructionPointer = 0;

        do {
            //running instructions and returning the increment value
            instructionPointer += checkCodeValue(codes[instructionPointer], instructionPointer);

        } while (!programOver);

    }

    public int checkCodeValue(int instructions, int instructionPointer) {

        int increment = 0;
        int opcode = 0;
        int positionMode = 0;
        int param1;
        int param2;
        int param3;
        int mode1stParam = 0;
        int mode2ndParam = 0;
        int mode3rdParam = 0;

        //if instructions are <= 8, they don't require any parsing
        if (instructions <= 8) {
            opcode = instructions;
        }

        //If instructions are>8
        if (instructions > 8) {
            //last two digits are the opcode
            opcode = (instructions / 10 % 10) + (instructions % 10);
            //third to last digit is mode of 1st param
            mode1stParam = instructions / 100 % 10;
            //4th to last digit is mode of 2nd param
            mode2ndParam = instructions / 1000 % 10;
            //if there is no 3rd param, this will return 0
            mode3rdParam = instructions / 10000 % 10;
        }

        //setting all parameters based on the mode
        if (mode1stParam == positionMode) {
            param1 = codes[instructionPointer + 1];
        } else {
            param1 = instructionPointer + 1;
        }

        if (mode2ndParam == positionMode) {
            param2 = codes[instructionPointer + 2];
        } else {
            param2 = instructionPointer + 2;
        }

        if (mode3rdParam == positionMode) {
            param3 = codes[instructionPointer + 3];
        } else {
            param3 = instructionPointer + 3;
        }

        switch (opcode) {
            case ADD:
                //gets the next 2 numbers after instruction adds them, and saves them
                codes[param3] = codes[param1] + codes[param2];
                increment = 4;
                break;
            case MULTIPLY:
                //gets the next 2 numbers after instruction multilpies them, and saves them
                codes[param3] = codes[param1] * codes[param2];
                increment = 4;
                break;
            case INPUT:
                //Opcode 3 takes a single integer as input and saves it to the position given by its only parameter. 
                //For example, the instruction 3,50 would take an input value and store it at address 50.
                System.out.println("Input an int");
                codes[param1] = input.nextInt();
                increment = 2;
                break;
            case OUTPUT:
                //Opcode 4 outputs the value of its only parameter. 
                //For example, the instruction 4,50 would output the value at address 50.
                System.out.println(codes[param1]);
                increment = 2;
                break;

            case JUMPIFTRUE:
                //Opcode 5 is jump-if-true: if the first parameter is non-zero, it sets
                //the instruction pointer to the value from the second parameter. Otherwise, it does nothing

                if (codes[param1] != 0) {
                    increment = (increment - instructionPointer) + codes[param2];

                } else {
                    //incrementing by 3 because thats how much it would take to get to the next instruction
                    increment = 3;
                }

                break;
            case JUMPIFFALSE:
                //Opcode 6 is jump-if-false: if the first parameter is zero, it sets the
                //instruction pointer to the value from the second parameter. Otherwise, it does nothing.
                if (codes[param1] == 0) {
                    increment = (increment - instructionPointer) + codes[param2];
                } else {
                    //incrementing by 3 because thats how much it would take to get to the next instruction
                    increment = 3;
                }
                break;
            case LESSTHAN:
                //Opcode 7 is less than: if the first parameter is less than the second parameter, 
                //it stores 1 in the position given by the third parameter. Otherwise, it stores 0.
                if (codes[param1] < codes[param2]) {
                    codes[param3] = 1;
                } else {
                    codes[param3] = 0;
                }
                increment = 4;

                break;
            case EQUALS:
                //Opcode 8 is equals: if the first parameter is equal to the second parameter,
                //it stores 1 in the position given by the third parameter. Otherwise, it stores 0.
                if (codes[param1] == codes[param2]) {
                    codes[param3] = 1;
                } else {
                    codes[param3] = 0;
                }
                increment = 4;

                break;
            case END:
                System.out.println("Found 99");
                programOver = true;

                break;
            default:
                System.out.println("Error in OPCODE");
                programOver = true;

        }
        return increment;

    }

}
