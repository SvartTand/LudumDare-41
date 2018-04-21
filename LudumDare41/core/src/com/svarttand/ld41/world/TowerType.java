package com.svarttand.ld41.world;

public enum TowerType {
	
	BASIC("Tower1", 100, 40, 10,1, 2, 1.3f), 
	BASIC2("Tower2", 50,20,10,0.2f, 2, 1.3f),
	HOUSE("House", 50,20,0,0, -10, 0);
	
	private int range;
	private String path;
	private float dmg;
	private float frequency;
	private float cost;
	private float housing;
	private float upgradeExp;
	
	private TowerType(String path, int range, float dmg, int cost, float freq, float house, float exp) {
		this.range = range;
		this.path = path;
		this.dmg = dmg;
		this.cost = cost;
		frequency = freq;
		housing = house;
		upgradeExp = exp;
	}

	public float getHousing() {
		return housing;
	}

	public float getUpgradeExp() {
		return upgradeExp;
	}

	public int getRange() {
		return range;
	}

	public String getPath() {
		return path;
	}

	public float getDmg() {
		return dmg;
	}

	public float getCost() {
		return cost;
	}
	
	public float getFreq(){
		return frequency;
	}
	

}
