package com.svarttand.ld41.world;

public enum TowerType {
	
	BASIC("Tower1", 50, 50, 10), 
	BASIC2("Tower2", 50,50,10);
	
	private int range;
	private String path;
	private float dmg;
	private int cost;
	
	private TowerType(String path, int speed, float hp, int points) {
		this.range = speed;
		this.path = path;
		this.dmg = hp;
		this.cost = points;
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
	

}
