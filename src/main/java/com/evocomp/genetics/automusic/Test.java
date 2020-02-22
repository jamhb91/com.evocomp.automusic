/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evocomp.genetics.automusic;

import static com.evocomp.genetics.automusic.Sound.PlaySequence;
import static com.evocomp.genetics.automusic.Sound.RecordSequence;
import java.awt.Dimension;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author jamhb
 */
public class Test extends JFrame{

    public static XYSeriesCollection solutionCollection = null;
    public static XYSeries solutionSeries = null;
    public static JPanel chartSolutionPanel = null;
    
    
    public static XYSeriesCollection distanceCollection = null;
    public static XYSeries distanceSeries = null;
    public static JPanel chartDistancePanel = null;
    
    public static JPanel mainPanel=null;
    
    public static List<MusicChromosome> output = null;
    public static List<MusicChromosome>  previousOutput = null;
    public static MusicChromosome best = null;;
    public static Composition eComposition;
    public static int generation = 0;
    public static int maximumGenerations = 250;
    public static int interval = 200;
    public static Timer timer = new Timer();
    
    public static double SCALING = (1);
    public static double XVALUE = 2d;
    String file = "playback.mid";
    
    
    public Test() {
        super("XY Line Chart for Solution");
        
        initializeOutput();
 
        solutionCollection = new XYSeriesCollection();
        solutionSeries = new XYSeries("Solution with current coeficients");
        
        chartSolutionPanel = createProgressChartPanel();
        
         
        distanceCollection = new XYSeriesCollection();
        distanceSeries = new XYSeries("Distance from solution with current coeficients");
        
        chartDistancePanel = createDistanceChartPanel();
        
        mainPanel = new JPanel();
        mainPanel.setSize(1280,480);
        
        mainPanel.add(chartSolutionPanel);
        mainPanel.add(chartDistancePanel);
        add(mainPanel);
 
        setSize(1320,520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runAlgorithm();
                if(generation>maximumGenerations){
                    timer.cancel();
                    
                    System.out.println(Arrays.toString(best.getChromosome()));
                    
                    Note[] notes = new Note[32];
                    int positionCounter =0;
                    for (int i = 0; i < 32; i++) {
                        notes[i] = new Note(best.getChromosome()[i] + 60, positionCounter, 4);
                        positionCounter=positionCounter+4;
                    }

                    RecordSequence(
                            InstrumentCollection.ChurchOrg1,                   // Instrument from list
                            notes,    // Array of notes to store
                            file,                                           // File to store
                            4                                               // Resolution
                    );
                    PlaySequence(file);
                    System.out.println("End...");
                }
            }
          }, interval, interval);
    }
     
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Test().setVisible(true);
        });
    }
    
    private static void initializeOutput(){
        eComposition = new Composition(
                32, //Number of coeficients
                50 //Number of children
        );
        
        System.out.println("Initial Random Matrix... ");
        output = eComposition.createMatrix();
        output.forEach((musicChromosome) -> {
            System.out.println(Arrays.toString(musicChromosome.getChromosome()) + " Aptitude: " + musicChromosome.getAptitude());
        });
        
        best = eComposition.getBestFromGeneration(output);
    }
    
    private static void runAlgorithm() {
        System.out.println("Running New Generation...");
        MusicChromosome generationBest = eComposition.getBestFromGeneration(output);
        System.out.println("Best Child >> " + generationBest.getAptitude());
        System.out.println(Arrays.toString(generationBest.getChromosome()));
       
        //System.out.println("Matching matrix... ");
        List<MusicChromosome> matchedOutput;
        
        //No Elitism
        matchedOutput = eComposition.matchMatrix(output);
        
        //System.out.println("Muting generation...");
        List<MusicChromosome> mutedOutput = eComposition.getMutatedChildren(matchedOutput, 10);

        solutionSeries.add(generation, generationBest.getAptitude());
        solutionCollection.removeAllSeries();
        solutionCollection.addSeries(solutionSeries);
        
        //Store Best Composition
        if(best.getAptitude() > generationBest.getAptitude()){
            best = generationBest;
        }
        
        distanceSeries.add(generation, generationBest.getAptitude()- best.getAptitude());
        distanceCollection.removeAllSeries();
        distanceCollection.addSeries(distanceSeries);
        
        previousOutput = output;
        output = mutedOutput;
        
        
        generation++;
    }
    
    private JPanel createProgressChartPanel() {
        String chartTitle = "Composition Aptitude Progress";
        String xAxisLabel = "Generation";
        String yAxisLabel = "Best Solution";

        JFreeChart chart = ChartFactory.createXYLineChart(chartTitle,
                xAxisLabel, yAxisLabel, solutionCollection);
        ChartPanel cp = new ChartPanel(chart);
        
        
        cp.setPreferredSize(new Dimension(640,480));
        return cp;
    }
    
    private JPanel createDistanceChartPanel() {
        String chartTitle = "Distance from Maximum";
        String xAxisLabel = "Generation";
        String yAxisLabel = "Best Distance";

        JFreeChart chart = ChartFactory.createXYLineChart(chartTitle,
                xAxisLabel, yAxisLabel, distanceCollection);
        ChartPanel cp = new ChartPanel(chart);
        
        
        cp.setPreferredSize(new Dimension(640,480));
        return cp;
    }
}
