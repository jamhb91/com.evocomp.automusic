/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evocomp.genetics.automusic;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.*;

/**
 *
 * @author jamhb91
 */
public abstract class Sound {
    public static int numberOfNotes = 10;
    private static Sequencer playbackSequencer;
    
    public static void GetInstrumentList(){
        try {
            for (Instrument instrument : MidiSystem.getSynthesizer().getDefaultSoundbank().getInstruments()) {
                System.out.println(instrument);
            }
        } catch (MidiUnavailableException ex) {
            Logger.getLogger(Sound.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void PlaySequence(String filePath){
        try{
            playbackSequencer  = MidiSystem.getSequencer(); 
            playbackSequencer.open();
            Sequence sequence = MidiSystem.getSequence(new File(filePath));
            playbackSequencer.setSequence(sequence);
            playbackSequencer.start();
            
            playbackSequencer.addMetaEventListener((MetaMessage event) -> {
                if (event.getType() == ShortMessage.STOP) {
                    // Sequencer is done playing
                    System.out.println("Stop event raised...");
                    playbackSequencer.stop();
                }
            });
            
            while(playbackSequencer.isRunning()){
                //Do nothing while is running...
                System.out.println("Playing: " + playbackSequencer.isRunning() );
                Thread.sleep(1000);
            }
        }catch(IOException | InvalidMidiDataException | MidiUnavailableException | InterruptedException ex){
            Logger.getLogger(Sound.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void RecordSequence(int instrument, Note [] notes, String filePath, int resolution) 
    { 
        try { 
            Sequencer recorderSequencer = MidiSystem.getSequencer(); 
            recorderSequencer.open(); 
            Sequence sequence = new Sequence(Sequence.PPQ, resolution); 
            Track track = sequence.createTrack(); 
            
            recorderSequencer.setTempoInBPM(120); 
            recorderSequencer.setSequence(sequence);
            recorderSequencer.recordEnable(track, instrument);
            recorderSequencer.startRecording();
            
            // Set the instrument type 
            track.add(makeEvent(ShortMessage.CONTROL_CHANGE, 0, 0, 0, 0)); 
            track.add(makeEvent(ShortMessage.PROGRAM_CHANGE, 0, instrument, 0, 0)); 
            track.add(makeEvent(ShortMessage.PROGRAM_CHANGE, 1, instrument, 0, 0)); 
            
            for (Note note : notes) {
                AddNote(track,note);
            }
            
            recorderSequencer.stopRecording();
            MidiSystem.write(sequence, 0, new File(filePath));
            recorderSequencer.stop();
            
        } catch (MidiUnavailableException | InvalidMidiDataException | IOException ex) {
            Logger.getLogger(Sound.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    public static void AddNote(Track track, Note note){
        track.add(makeEvent(ShortMessage.NOTE_ON, note.getChannel(), note.getNote(), 120, note.getStartPosition())); 
        track.add(makeEvent(ShortMessage.NOTE_OFF, note.getChannel(), note.getNote(), 120, note.getStartPosition() + note.getDuration()));
    }
    
    public static MidiEvent makeEvent(int command, int channel, int note, int velocity, int tick) 
    {  
        try { 
            ShortMessage a = new ShortMessage(); 
            a.setMessage(command, channel, note, velocity); 
  
            return new MidiEvent(a, tick); 
        } 
        catch (InvalidMidiDataException ex) { 
            Logger.getLogger(Sound.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    } 
}
