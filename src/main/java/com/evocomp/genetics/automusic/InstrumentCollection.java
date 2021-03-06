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
public abstract class InstrumentCollection {
    public static final int Piano1		= 0;
    public static final int Piano2		= 1;
    public static final int Piano3		= 2;
    public static final int Honkytonk		= 3;
    public static final int EPiano1 		= 4;
    public static final int EPiano2 		= 5;
    public static final int Harpsichord		= 6;
    public static final int Clav 		= 7;
    public static final int Celesta 		= 8;
    public static final int Glockenspiel	= 9;
    public static final int MusicBox 		= 10;
    public static final int Vibraphone		= 11;
    public static final int Marimba		= 12;
    public static final int Xylophone 		= 13;
    public static final int Tubularbell		= 14;
    public static final int Santur 		= 15;
    public static final int Organ1		= 16;
    public static final int Organ2		= 17;
    public static final int Organ3		= 18;
    public static final int ChurchOrg1		= 19;
    public static final int ReedOrgan		= 20;
    public static final int AccordionFr		= 21;
    public static final int Harmonica 		= 22;
    public static final int Bandoneon 		= 23;
    public static final int NylonstrGt		= 24;
    public static final int SteelstrGt		= 25;
    public static final int JazzGt  		= 26;
    public static final int CleanGt 		= 27;
    public static final int MutedGt 		= 28;
    public static final int OverdriveGt		= 29;
    public static final int DistortionGt	= 30;
    public static final int GtHarmonics		= 31;
    public static final int AcousticBs		= 32;
    public static final int FingeredBs		= 33;
    public static final int PickedBs		= 34;
    public static final int FretlessBs		= 35;
    public static final int SlapBass1		= 36;
    public static final int SlapBass2		= 37;
    public static final int SynthBass1		= 38;
    public static final int SynthBass2		= 39;
    public static final int Violin 		= 40;
    public static final int Viola  		= 41;
    public static final int Cello  		= 42;
    public static final int Contrabass		= 43;
    public static final int TremoloStr		= 44;
    public static final int PizzicatoStr	= 45;
    public static final int Harp   		= 46;
    public static final int Timpani		= 47;
    public static final int Strings		= 48;
    public static final int SlowStrings		= 49;
    public static final int SynStrings1		= 50;
    public static final int SynStrings2		= 51;
    public static final int ChoirAahs		= 52;
    public static final int VoiceOohs		= 53;
    public static final int SynVox 		= 54;
    public static final int OrchestraHit	= 55;
    public static final int Trumpet		= 56;
    public static final int Trombone  		= 57;
    public static final int Tuba   		= 58;
    public static final int MutedTrumpet	= 59;
    public static final int FrenchHorns		= 60;
    public static final int Brass1		= 61;
    public static final int SynthBrass1		= 62;
    public static final int SynthBrass2		= 63;
    public static final int SopranoSax		= 64;
    public static final int AltoSax  		= 65;
    public static final int TenorSax 		= 66;
    public static final int BaritoneSax		= 67;
    public static final int Oboe   		= 68;
    public static final int EnglishHorn		= 69;
    public static final int Bassoon		= 70;
    public static final int Clarinet  		= 71;
    public static final int Piccolo		= 72;
    public static final int Flute  		= 73;
    public static final int Recorder  		= 74;
    public static final int PanFlute 		= 75;
    public static final int BottleBlow		= 76;
    public static final int Shakuhachi		= 77;
    public static final int Whistle		= 78;
    public static final int Ocarina		= 79;
    public static final int SquareWave		= 80;
    public static final int SawWave  		= 81;
    public static final int SynCalliope		= 82;
    public static final int ChifferLead		= 83;
    public static final int Charang		= 84;
    public static final int SoloVox  		= 85;
    public static final int FifthSawWave	= 86;
    public static final int BassLead		= 87;
    public static final int Fantasia  		= 88;
    public static final int WarmPad  		= 89;
    public static final int Polysynth 		= 90;
    public static final int SpaceVoice		= 91;
    public static final int BowedGlass		= 92;
    public static final int MetalPad 		= 93;
    public static final int HaloPad  		= 94;
    public static final int SweepPad 		= 95;
    public static final int IceRain  		= 96;
    public static final int Soundtrack		= 97;
    public static final int Crystal		= 98;
    public static final int Atmosphere		= 99;
    public static final int Brightness		= 100;
    public static final int Goblin 		= 101;
    public static final int EchoDrops		= 102;
    public static final int StarTheme		= 103;
    public static final int Sitar  		= 104;
    public static final int Banjo  		= 105;
    public static final int Shamisen  		= 106;
    public static final int Koto   		= 107;
    public static final int Kalimba		= 108;
    public static final int Bagpipe		= 109;
    public static final int Fiddle 		= 110;
    public static final int Shanai 		= 111;
    public static final int TinkleBell		= 112;
    public static final int Agogo  		= 113;
    public static final int SteelDrums		= 114;
    public static final int Woodblock 		= 115;
    public static final int Taiko  		= 116;
    public static final int MeloTom1		= 117;
    public static final int SynthDrum		= 118;
    public static final int ReverseCym		= 119;
    public static final int GtFretNoise		= 120;
    public static final int BreathNoise		= 121;
    public static final int Seashore  		= 122;
    public static final int Bird   		= 123;
    public static final int Telephone1		= 124;
    public static final int Helicopter		= 125;
    public static final int Applause  		= 126;
    public static final int GunShot  		= 127;
}
