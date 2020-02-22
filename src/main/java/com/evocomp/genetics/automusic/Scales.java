/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evocomp.genetics.automusic;

/**
 *
 * @author jamhb91
 */
public class Scales {
    
    public static final int [] SCALE_FROM_4_TO_6_ARRAY = new int[]{96,95,94,93,92,91,90,89,88,87,86,85,84,83,82,81,80,79,78,77,76,75,74,73,72,71,70,69,68,67,66,65,64,63,62,61,60};
    
    public static final int [] PENTATONIC_C_MIDDLE_ARRAY = new int[]{72,75,77,79,82,84,87,89,91,94};
    
    public enum PENTATONIC_C {
        C(60),
        Eb(63),
        F(65),
        G(67),
        Bb(70);
        
        private final int value;

        PENTATONIC_C(int newValue) {
            value = newValue;
        }

        public int getValue() { return value; }
    }

    //Scale to be used
    public enum SCALE_FROM_4_TO_6 {
        B7(107),
        Bb7(106),
        A7(105),
        Ab7(104),
        G7(103),
        Gb7(102),
        F7(101),
        E7(100),
        Eb7(99),
        D7(98),
        Db7(97),
        C7(96),
        B6(95),
        Bb6(94),
        A6(93),
        Ab6(92),
        G6(91),
        Gb6(90),
        F6(89),
        E6(88),
        Eb6(87),
        D6(86),
        Db6(85),
        C6(84),
        B5(83),
        Bb5(82),
        A5(81),
        Ab5(80),
        G5(79),
        Gb5(78),
        F5(77),
        E5(76),
        Eb5(75),
        D5(74),
        Db5(73),
        C5(72),
        B4(71),
        Bb4(70),
        A4(69),
        Ab4(68),
        G4(67),
        Gb4(66),
        F4(65),
        E4(64),
        Eb4(63),
        D4(62),
        Db4(61),
        C4(60);
        
        private final int value;

        SCALE_FROM_4_TO_6(int newValue) {
            value = newValue;
        }

        public int getValue() { return value; }
    }
}
