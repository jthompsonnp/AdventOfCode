/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.adventofcode_day1part2;

/**
 *
 * @author Jessica
 */
public class CodeFinder {

    //instruction variables
    final int ADD = 1;
    final int MULTIPLY = 2;
    final int END = 99;
    boolean programOver = false;
    boolean comboFound = false;
    int totalCombos = 0;
    int[] codes;

    public CodeFinder(int[] codes) {
        this.codes = codes;
    }

    public boolean codeFinder(int noun, int verb) {

        totalCombos++;
        codes[1] = noun;
        codes[2] = verb;

        int instructionPointer = 0;
        do {
            checkCodeValue(codes[instructionPointer], instructionPointer);
            instructionPointer += 4;
        } while (!programOver);

       
        return comboFound;
    }

    public void checkCodeValue(int opcode, int instructionPointer) {
        int parameter1;
        int parameter2;
        int positionToSave;

        switch (opcode) {
            case ADD:
                parameter1 = codes[instructionPointer + 1];
                parameter2 = codes[instructionPointer + 2];
                positionToSave = codes[instructionPointer + 3];
                codes[positionToSave] = codes[parameter1] + codes[parameter2];
                break;
            case MULTIPLY:
                parameter1 = codes[instructionPointer + 1];
                parameter2 = codes[instructionPointer + 2];
                positionToSave = codes[instructionPointer + 3];
                codes[positionToSave] = codes[parameter1] * codes[parameter2];
                break;
            case END:
                if (codes[0] == 19690720) {
                    comboFound = true;
                }
            default:
                programOver = true;

        }

    }

}
