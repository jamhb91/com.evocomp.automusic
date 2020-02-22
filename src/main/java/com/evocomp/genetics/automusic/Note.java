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
public class Note {
    public static final int DEFAULT_VOLUME = 100;
    public static final int DEFAULT_CHANNEL = 0;
    
    /***
     * Constructs a Note Object with all values
     * @param note MIDI Note to be added (0-127)
     * @param channel MIDI Channel for the note
     * @param volume MIDI Velocity for the note
     * @param startPosition Start position on the MIDI file
     * @param duration Duration of the sound
     */
    public Note(int note, int channel, int volume, int startPosition, int duration){
        this.note = note;
        this.channel = channel;
        this.volume = volume;
        this.startPosition = startPosition;
        this.duration = duration;
    }
    
    /***
     * Constructs a Note Object using DEFAULT_VALUE and DEFAULT_CHANNEL
     * @param note MIDI Note to be added (0-127)
     * @param startPosition Start position on the MIDI file
     * @param duration Duration of the sound
     */
    public Note(int note, int startPosition, int duration){
        this.note = note;
        this.channel = DEFAULT_CHANNEL;
        this.volume = DEFAULT_VOLUME;
        this.startPosition = startPosition;
        this.duration = duration;
    }

    /**
     * @return the note
     */
    public int getNote() {
        return note;
    }

    /**
     * @param note the note to set
     */
    public void setNote(int note) {
        this.note = note;
    }

    /**
     * @return the channel
     */
    public int getChannel() {
        return channel;
    }

    /**
     * @param channel the channel to set
     */
    public void setChannel(int channel) {
        this.channel = channel;
    }

    /**
     * @return the duartion
     */
    public int getDuration() {
        return duration;
    }

    /**
     * @param duartion the duartion to set
     */
    public void setDuration(int duartion) {
        this.duration = duartion;
    }

    /**
     * @return the volume
     */
    public int getVolume() {
        return volume;
    }

    /**
     * @param volume the volume to set
     */
    public void setVolume(int volume) {
        this.volume = volume;
    }

    /**
     * @return the startPosition
     */
    public int getStartPosition() {
        return startPosition;
    }

    /**
     * @param startPosition the startPosition to set
     */
    public void setStartPosition(int startPosition) {
        this.startPosition = startPosition;
    }
    private int note;
    private int channel;
    private int duration;
    private int volume;
    private int startPosition;
}
