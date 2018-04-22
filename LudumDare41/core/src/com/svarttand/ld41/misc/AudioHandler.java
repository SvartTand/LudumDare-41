package com.svarttand.ld41.misc;

import java.util.ArrayList;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.svarttand.ld41.states.LoadingState;

public class AudioHandler {
	
	public static final int SELECT = 0;
	public static final int BLAST_HIT = 1;
	public static final int EXPLOSION = 2;
	public static final int LASERSHOT = 3;
	public static final int NOT_POSSIBLE = 4;
	public static final int NOT_POSSIBLE2 = 5;
	public static final int SMALL_EXP = 6;
	
	
	private ArrayList<Sound> audioList;
	
	public AudioHandler(AssetManager assetManager){
		audioList = new ArrayList<Sound>();
		for (int i = 0; i < LoadingState.AUDIO_AMOUNT; i++) {
			audioList.add(assetManager.get("audio/"+ i + ".wav",Sound.class));
		}
	}
	
	public Sound getsound(int index){
		return audioList.get(index);
	}
	
	public void playSound(int index){
		audioList.get(index).play();
	}
	
	public void dispose(){
		for (int i = 0; i < audioList.size(); i++) {
			audioList.get(i).dispose();
		}
	}

}
