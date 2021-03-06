package com.svarttand.ld41.sprites;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.svarttand.ld41.Application;
import com.svarttand.ld41.misc.AudioHandler;
import com.svarttand.ld41.misc.ParticleType;
import com.svarttand.ld41.states.PlayState;
import com.svarttand.ld41.world.Tile;

public class MobHandler {
	public static final float WAVE_FREQUENCY = 40;
	private ArrayList<Mob> mobList;
	
	private PlayState state;
	
	private float sum;
	private Random random;
	private int waveNumber;
	private MobWave currentWave;
	
	public MobHandler(PlayState state) {
		mobList = new ArrayList<Mob>();
		sum = WAVE_FREQUENCY;
		this.state = state;
		waveNumber = 1;
		currentWave = null;
		waveNumber = 1;
		random = new Random();
		
	}
	
	public void addMob(MobType type, CardType buff){
		int i = random.nextInt(4);
		
		if (i == 0) {
			Tile s3 = state.getMap().getSpawns().get(2);
			mobList.add(new Mob(810, 810, type, s3, state.getMap().getDestination(), this, buff));
		}else if (i == 1) {
			Tile s1 = state.getMap().getSpawns().get(0);
			mobList.add(new Mob(-10, -10, type, s1, state.getMap().getDestination(), this, buff));
		}else if (i == 2) {
			Tile s4 = state.getMap().getSpawns().get(3);
			mobList.add(new Mob(-10, 810, type, s4, state.getMap().getDestination(), this, buff));
		}else {
			Tile s2 = state.getMap().getSpawns().get(1);
			mobList.add(new Mob(810, -10, type, s2, state.getMap().getDestination(), this, buff));
		}
		
		
	}
	
	public MobWave getWave(){
		return currentWave;
	}
	
	public void updatePaths(Tile tile){
		for (int i = 0; i < mobList.size(); i++) {
			mobList.get(i).updatePath(tile);
			System.out.println(mobList.get(i).isUpdating());
			
		}
		
	}
	
	public void update(float delta){
		
		sum -= delta;
		if (currentWave == null && waveNumber == 1) {
			
			if ((int)sum == WAVE_FREQUENCY - 40) {
			}else if ((int)sum == WAVE_FREQUENCY - 35) {
				state.getUI().addNewFloatingText("Good Luck!", Application.V_WIDTH*0.5f, Application.V_HEIGHT*0.5f, 5, false, 1);
			}else if ((int)sum == WAVE_FREQUENCY - 25) {
				state.getUI().addNewFloatingText("Population is needed to man the towers, \nYou get more Population by building Houses", Application.V_WIDTH*0.5f, Application.V_HEIGHT*0.7f, 9, false, 1);
			}else if ((int)sum == WAVE_FREQUENCY - 15) {
				state.getUI().addNewFloatingText("$ is used for Buying and upgrading buildings, \n$ is earned by killing enemies", Application.V_WIDTH*0.5f, Application.V_HEIGHT*0.7f, 7, false, 1);
			}else if ((int)sum == WAVE_FREQUENCY - 6) {
				state.getUI().addNewFloatingText("There Are Two Resources\n$ and Population(Pop)", Application.V_WIDTH*0.5f, Application.V_HEIGHT*0.7f, 7, false, 1);
			}else if ((int)sum == WAVE_FREQUENCY-1) {
				state.getUI().addNewFloatingText("Welcome To The Game! \nYour Goal is to Defend the Castle", Application.V_WIDTH*0.5f, Application.V_HEIGHT*0.7f, 4, false, 1);
			}
				
	
			
			
		}else{
			currentWave.update(delta);
			
		}
		if (sum <= 0) {
			if (waveNumber == 1) {
				currentWave = new MobWave(waveNumber, this, true);
			}else{
				currentWave = new MobWave(waveNumber, this, false);
			}
			
			state.getUI().addNewFloatingText(currentWave.getCardType().getExplanation(), Application.V_WIDTH*0.5f, Application.V_HEIGHT*0.7f, 10, false, 1);
			waveNumber++;
			sum = WAVE_FREQUENCY;
		}
		
		
		for (int i = 0; i < mobList.size(); i++) {
			mobList.get(i).update(delta);
		}
	}
	
	public void render(SpriteBatch batch, TextureAtlas atlas){
		for (int i = 0; i < mobList.size(); i++) {
			batch.draw(atlas.findRegion(mobList.get(i).getPath()), mobList.get(i).getPosX(), mobList.get(i).getPosY());
		}
	}

	public ArrayList<Mob> getList() {
		// TODO Auto-generated method stub
		return mobList;
	}

	public void remove(Mob mob, boolean killed) {
		if (killed) {
			state.getParticleHandler().addParticleEffect(ParticleType.DEATH, mob.getPosX(), mob.getPosY());
			state.getResources().addGold(mob.getType().getPoints());
			state.getResources().addScore(mob.getType().getPoints());
			mobList.remove(mob);
			state.getAudioHandler().playSound(AudioHandler.SMALL_EXP);
			state.getShake().shake(500, 500, 500);
		}else{
			state.getParticleHandler().addParticleEffect(ParticleType.EXPLOSION, mob.getPosX(), mob.getPosY());
			state.getShake().shake(500, 500, 500);
			mobList.remove(mob);
		}
		
		
		
	}
	
	
	public float getTimeToNextWave(){
		return sum;
	}
	
	public int getWaveNumber(){
		return waveNumber;
	}

	public void takeDamage(float dmg) {
		state.getUI().takeHP(dmg);
		state.getAudioHandler().playSound(AudioHandler.EXPLOSION);
		
		
	}

	public void earthquake() {
		state.getTowers().earthQuake();
		
	}
	

}
