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
public class MusicChromosome {
    private Integer [] chromosome;
    private Double aptitude;
    
    public MusicChromosome(Integer [] chromosome, Double aptitude){
        this.chromosome = chromosome;
        this.aptitude = aptitude;
    }

    /**
     * @return the chromosome
     */
    public Integer[] getChromosome() {
        return chromosome;
    }

    /**
     * @param chromosome the chromosome to set
     */
    public void setChromosome(Integer[] chromosome) {
        this.chromosome = chromosome;
    }

    /**
     * @return the aptitude
     */
    public Double getAptitude() {
        return aptitude;
    }

    /**
     * @param aptitude the aptitude to set
     */
    public void setAptitude(Double aptitude) {
        this.aptitude = aptitude;
    }
}
