package com.svarttand.ld41.world;

public enum TowerType {
	
	BASIC("Tower1", 100, 100, 10,1), 
	BASIC2("Tower2", 50,50,10,0.5f);
	
	private int range;
	private String path;
	private float dmg;
	private float frequency;
	private int cost;
	
	private TowerType(String path, int range, float dmg, int cost, float freq) {
		this.range = range;
		this.path = path;
		this.dmg = dmg;
		this.cost = cost;
		frequency = freq;
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

	public int getCost() {
		return cost;
	}
	
	public float getFreq(){
		return frequency;
	}
	

}
