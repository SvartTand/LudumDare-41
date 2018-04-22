package com.svarttand.ld41.misc;

import com.svarttand.ld41.states.PlayState;

public class Resources {
	
	public static final int STARTING_GOLD = 50;
	public static final int STARTING_SCORE = 50;
	public static final int STARTING_HOUSING = 10;
	public static final int STARTING_POPULATION = 0;
	
	private float gold;
	private float score;
	private float housing;
	private float population;
	
	private PlayState state;
	
	public Resources(PlayState state) {
		gold = STARTING_GOLD;
		score = STARTING_SCORE;
		housing = STARTING_HOUSING;
		population = STARTING_POPULATION;
		this.state = state;
		
	}
	
	
	

	public void addGold(int gold) {
		this.gold += gold;
		state.getUI().updateResources();
	}


	public void addScore(int score) {
		this.score += score;
		state.getUI().updateResources();
	}


	public void addHousing(int housing) {
		this.housing += housing;
		state.getUI().updateResources();
	}

	public void removePop(float p){
		population -= p;
		state.getUI().updateResources();
	}
	public void removeHousing(float h){
		housing += h;
		state.getUI().updateResources();
		
	}

	public void addPopulation(float f) {
		if (f <= 0) {
			this.housing += Math.abs(f);
		}else{
			this.population += f;
		}
		
		state.getUI().updateResources();
	}


	public float getGold() {
		return gold;
	}

	public float getScore() {
		return score;
	}

	public float getHousing() {
		return housing;
	}

	public float getPopulation() {
		return population;
	}




	public float getPopSpace() {
		return housing - population;
	}




	public void buyWithGold(float cost) {
		gold -= cost;
		state.getUI().updateResources();
		
	}
	
	

}
