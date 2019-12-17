/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.adventofcode_day4;

import java.util.ArrayList;

/**
 *
 * @author Jessica
 */
public class Day4 {

    /**
     * @param args the command line arguments
     *
     *
     * Two adjacent digits are the same (like 22 in 122345). Going from left to
     * right, the digits never decrease; they only ever increase or stay the
     * same (like 111123 or 135679). Other than the range rule, the following
     * are true:
     *
     * 111111 meets these criteria (double 11, never decreases). 223450 does not
     * meet these criteria (decreasing pair of digits 50). 123789 does not meet
     * these criteria (no double). How many different passwords within the range
     * given in your puzzle input meet these criteria?
     */
    public static void main(String[] args) {
        //number ranges from question
        final int MIN = 264360;
        final int MAX = 746325;

        //counter to increment every time a possible number combination is found
        int counter = 0;

        //saving possible combinations
        //ArrayList possibleCombos = new ArrayList<>();
        //for loop, incrementing every single number in the range of possible numbers
        for (int i = MIN; i < MAX; i++) {
            //Breaking down each individual number each loop
            int numSixth = i % 10;
            int numFifth = i / 10 % 10;
            int numFourth = i / 100 % 10;
            int numThird = i / 1000 % 10;
            int numSecond = i / 10000 % 10;
            int numFirst = i / 100000 % 10;

            //finding if two side by side numbers match
            if (numSixth == numFifth || numFifth == numFourth || numFourth == numThird || numThird == numSecond || numSecond == numFirst) {

                //finding out if numbers only increase or stay the same
                if (numSixth >= numFifth && numFifth >= numFourth && numFourth >= numThird && numThird >= numSecond && numSecond >= numFirst) {
                    //possibleCombos.add(i);

                    boolean onePair = false;
                    //if xxxx22 
                    if (numSixth == numFifth) {
                        //but not xxx222
                        if (numFifth != numFourth) {
                            onePair = true;
                        }
                    }

                    //if xxx22x
                    if (numFifth == numFourth) {
                        //but not xx222x or xxx222
                        if (numFourth != numThird && numFifth!=numSixth) {
                            onePair = true;
                        }
                    }

                    //if xx22xx
                    if (numFourth == numThird) {
                        //but not x222xx or xx222x
                        if (numThird != numSecond && numFourth!=numFifth) {
                            onePair = true;
                        }
                    }

                    //if x22xxx
                    if (numThird == numSecond) {
                        //but not 222xxx or x222xx
                        if (numSecond != numFirst && numThird!= numFourth) {
                            onePair = true;
                        }
                    }

                    //if 22xxxx
                    if (numSecond == numFirst) {
                        //but not 222xxx
                        if (numSecond != numThird) {
                            onePair = true;
                        }
                    }

                    if (onePair) {
                        counter++;
                    }
                }

            }

        }

        System.out.println("Total Combinations" + counter);

    }

}
