package com.svarttand.ld41.sprites;

import java.util.ArrayList;
import java.util.Random;



public class MobWave {
	
	private static final int BASIC_AMOUNT[] = {2,1,2};
	private static final int HELI_AMOUNT[] = {1,1,6};
	private static final int UFO_AMOUNT[] = {4,0,2};
	private static final int SLIME_AMOUNT[] = {2,3,0};
	
	private ArrayList<MobType> list;
	private float exponent;
	Random rand;
	float time;
	float interval;
	MobHandler handler;
	private CardType buff;
	
	public MobWave(float exp, MobHandler handler, boolean first) {
		rand = new Random();
		list = new ArrayList<MobType>();
		exponent = exp;
		time = 0;
		
		this.handler = handler;
		int[] mobs = BASIC_AMOUNT;
		
		int x = rand.nextInt(4);
		if (x == 0) {
			mobs = HELI_AMOUNT;
		}else if (x ==1) {
			mobs = UFO_AMOUNT;
		}else if (x == 2) {
			mobs = SLIME_AMOUNT;
		}
		
		
		for (int j = 0; j < mobs[0]*exponent; j++) {
			list.add(MobType.MOB);
		}
		for (int j = 0; j < mobs[1]*exponent; j++) {
			list.add(MobType.SLIME);
		}
		for (int j = 0; j < mobs[2]*exponent; j++) {
			list.add(MobType.HELI_MOB);
		}
		interval = MobHandler.WAVE_FREQUENCY*0.2f/(list.size()+1);
		if (!first) {
			int i = rand.nextInt(7);
			//handler.earthquake();
			if (i == 0) {
				buff = CardType.MORE_HP;
				System.out.println("HP");
			}else if (i == 1) {
				buff = CardType.SPEEDBUFF;
				System.out.println("Speed");
			}else if (i == 2) {
				buff = CardType.ALLSTAR;
				System.out.println("ALLSTAR");
			}else if(i == 3) {
				buff = CardType.EARTHQUAQE;
				handler.earthquake();
			}else if(i == 4) {
				buff = CardType.EARTHQUAQE;
				handler.earthquake();
			}else{
				buff = CardType.BASIC;
				System.out.println("Basic");
			}
		}else{
			
			buff = CardType.BASIC;
			System.out.println("Basic");
		}
		
		
		
	}
	
	public void update(float delta){
		time += delta;
		if (time >= interval && !list.isEmpty()) {
			System.out.println("Spawn" + interval);
			int i = rand.nextInt(list.size());
			handler.addMob(list.get(i), buff);
			list.remove(i);
			time = 0;
			
		}
		
		
	}

	public CardType getCardType() {
		// TODO Auto-generated method stub
		return buff;
	}

}
