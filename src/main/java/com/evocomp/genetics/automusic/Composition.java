/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evocomp.genetics.automusic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author jamhb91
 */
public class Composition {
    private int compositionSize = 0;
    private int generationSize = 0;
    private static final int NUMBER_OF_NOTES = 36;
    private static final int MATCH_SIZE = 5;
    
    public Composition(int compositionSize, int generationSize){
        this.compositionSize = compositionSize;
        this.generationSize = generationSize;
    }
    
    public List<MusicChromosome> createMatrix (){
        List<MusicChromosome> matrix = new ArrayList<>();
        for(int i=0;i<generationSize;i++){
            matrix.add(createRandomChild(compositionSize));
        }
        return matrix;
    }
    
    private MusicChromosome createRandomChild(int size){
        Integer [] chromosome = new Integer[size];
        Random r = new Random();
        
        for(int i=0; i<size; i++){
            chromosome[i] = r.nextInt(NUMBER_OF_NOTES);
        }
        
        return new MusicChromosome(chromosome,calculateAptitude(chromosome));
    }
    
    private Double calculateAptitude(Integer[] chromosome) {
        double result = 0;
        Scales.SCALE_FROM_4_TO_6 noteScale;
        int previousOnScale = 1;
        int previousResult = 0;
        
        for (Integer integer : chromosome) {
            noteScale = Scales.SCALE_FROM_4_TO_6.values()[integer];
            for (Scales.PENTATONIC_C dominantNote : Scales.PENTATONIC_C.values()) {
                if(noteScale.name().startsWith(dominantNote.name())){
                    double preliminar;
                    if(Math.abs(integer-previousOnScale)<8 && Math.abs(integer-previousOnScale)>2){
                        preliminar = previousOnScale*20;
                    }
                    else{
                        preliminar = previousOnScale*2;
                    }
                    result = result + preliminar;
                    previousOnScale++;
                }
                else{
                    previousOnScale=1;
                }
                previousResult=integer;
            }
        }
        return result;
    }
    
    public MusicChromosome getBestFromGeneration(List<MusicChromosome> chormosomes){
        int winnerPos = 0;
        double maxAptitude = 0;
        
        for (int i = 0; i < generationSize; i++) {
            if(chormosomes.get(i).getAptitude() > maxAptitude){
                maxAptitude = chormosomes.get(i).getAptitude();
                winnerPos = i;
            }
        }
        return chormosomes.get(winnerPos);
    }
    
    public List<MusicChromosome> matchMatrix(List<MusicChromosome> chormosomes){
        List<MusicChromosome> matchedMatrix = new ArrayList<>();
                
        for(int j=0; j<generationSize;j++){
            matchedMatrix.add(getMatchWinner(chormosomes));
        }
        
        return matchedMatrix;
    }
    
    public MusicChromosome getMatchWinner(List<MusicChromosome> chormosomes){        
        int winnerPos = 0;
        double maxAptitude = 0;
        Collections.shuffle(chormosomes);
        
        for(int i =1; i<MATCH_SIZE; i++){
            if(chormosomes.get(i).getAptitude() > maxAptitude){
                maxAptitude = chormosomes.get(i).getAptitude();
                winnerPos = i;
            }
        }
        
        System.out.println("Best Chromosome is: " + Arrays.toString(chormosomes.get(winnerPos).getChromosome()));
        System.out.println("Aptitude: " + chormosomes.get(winnerPos).getAptitude());
        
        return chormosomes.get(winnerPos);
    }
    
    public List<MusicChromosome> getMutatedChildren(List<MusicChromosome> matchedMatrix, int hardMutationSize){
        Random r =new Random();
        List<MusicChromosome> mutedChildren = matchedMatrix;
        for(int j =0;j<generationSize;j++){
            Integer[] chromosome;
            if(r.nextBoolean()){
                chromosome = makeReverse(matchedMatrix.get(j).getChromosome());
            }
            else{
                chromosome = makeShift(matchedMatrix.get(j).getChromosome());
            }
            mutedChildren.add(new MusicChromosome(chromosome, calculateAptitude(chromosome)));
        }
        return doHardMutations(matchedMatrix,hardMutationSize);
    }
    
    private List<MusicChromosome> doHardMutations(List<MusicChromosome> matchedMatrix, int mutationSize){
        Collections.shuffle(matchedMatrix);
        Random r = new Random();
        for (int i = 0; i < mutationSize; i++) {
            int position = r.nextInt(compositionSize);
            
            //Before Mutation
//            System.out.println("Mutation position:" + position);
//            System.out.println(Arrays.toString(matchedMatrix.get(i).getChromosome()));
//            if(matchedMatrix.get(i).getChromosome()[position]<(NUMBER_OF_NOTES-1) &&
//                    matchedMatrix.get(i).getChromosome()[position]>0){
//                matchedMatrix.get(i).getChromosome()[position] = matchedMatrix.get(i).getChromosome()[position] + getOneOrMinusOne();
//            }
            matchedMatrix.get(i).getChromosome()[position] = r.nextInt(NUMBER_OF_NOTES);
//            System.out.println(Arrays.toString(matchedMatrix.get(i).getChromosome()));
        }
        
        return matchedMatrix;
    }
    
    private int getOneOrMinusOne(){
        Random r = new Random();
        if(r.nextBoolean()){
            return 1;
        }
        else{
            return -1;
        }
    }
    
    public Integer [] makeShift(Integer [] array){
        Random r = new Random();
        
        Integer [] shifted = Arrays.copyOf(array, array.length);
        int iniPos = r.nextInt((compositionSize-2)/2);
        int length = r.nextInt((compositionSize-2)/2-iniPos);
        int secPos = iniPos + length +  r.nextInt(((compositionSize-2)/2)-length);

        for (int i = 0; i < length; i++) {
            shifted[iniPos + i] = array [secPos + i];
            shifted[secPos + i] = array [iniPos + i];
        }

        //System.out.println(">>firstPos: " + iniPos + " SecondPos: " + secPos + " Lenght:" + length);
        //System.out.println(">Original: " + Arrays.toString(array));
        //System.out.println("> Shifted: " + Arrays.toString(shifted));
        
        return shifted;
    }
    
    public Integer [] makeReverse(Integer [] array){
        Random r = new Random();
        
        Integer [] reversed = Arrays.copyOf(array, array.length);
        int startPos = r.nextInt(compositionSize-2);
        int length = r.nextInt(compositionSize-startPos);

        int reverseCount = startPos+length;
        for (int i = startPos; i <= startPos + length; i++) {
            reversed[i] = array[reverseCount];
            reverseCount--;
        }

        //System.out.println(">>StartPos: " + startPos + " Lenght:" + length);
        //System.out.println(">Original: " + Arrays.toString(array));
        //System.out.println(">Reversed: " + Arrays.toString(reversed));
        
        return reversed;
    }
}
