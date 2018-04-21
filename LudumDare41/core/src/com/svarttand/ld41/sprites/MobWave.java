package com.svarttand.ld41.sprites;

import java.util.ArrayList;
import java.util.Random;



public class MobWave {
	
	private static final int BASIC_AMOUNT[] = {2,1,1};
	
	private ArrayList<MobType> list;
	private float exponent;
	Random rand;
	float time;
	float interval;
	MobHandler handler;
	
	public MobWave(float exp, MobHandler handler) {
		rand = new Random();
		list = new ArrayList<MobType>();
		exponent = exp;
		time = 0;
		
		this.handler = handler;
		
		for (int j = 0; j < BASIC_AMOUNT[0]*exponent; j++) {
			list.add(MobType.MOB);
		}
		for (int j = 0; j < BASIC_AMOUNT[1]*exponent; j++) {
			list.add(MobType.MOB);
		}
		for (int j = 0; j < BASIC_AMOUNT[2]*exponent; j++) {
			list.add(MobType.MOB);
		}
		interval = MobHandler.WAVE_FREQUENCY/5/list.size()+1;
		
		
	}
	
	public void update(float delta){
		time += delta;
		if (time >= interval && !list.isEmpty()) {
			System.out.println("Spawn" + interval);
			int i = rand.nextInt(list.size());
			handler.addMob(list.get(i));
			list.remove(i);
			time = 0;
			
		}
		
		
	}

}
