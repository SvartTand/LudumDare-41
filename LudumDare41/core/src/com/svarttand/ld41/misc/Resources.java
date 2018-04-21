package com.svarttand.ld41.misc;

public class Resources {
	
	public static final int STARTING_GOLD = 50;
	public static final int STARTING_SCORE = 50;
	public static final int STARTING_HOUSING = 10;
	public static final int STARTING_POPULATION = 0;
	
	private float gold;
	private float score;
	private float housing;
	private float population;
	
	public Resources() {
		gold = STARTING_GOLD;
		score = STARTING_SCORE;
		housing = STARTING_HOUSING;
		population = STARTING_POPULATION;
		
		
	}
	
	
	

	public void addGold(int gold) {
		this.gold += gold;
	}


	public void addScore(int score) {
		this.score += score;
	}


	public void addHousing(int housing) {
		this.housing += housing;
	}


	public void addPopulation(float f) {
		this.population += f;
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
		
	}
	
	

}
