package com.svarttand.ld41.world;

public enum TowerType {
	
	BASIC("YellowTower", 150, 40, 10,1, 2, 1.3f,5, ProjectileType.BASIC), 
	BASIC2("FireTower", 50,20,10,0.2f, 2, 1.3f,5,ProjectileType.BASIC),
	ICE("IceTower", 50,10,5,1,2,1.5f,5,ProjectileType.ICE_BULLET),
	HOUSE("House", 0,0,5,0, -10, 0,1,ProjectileType.BASIC);
	
	
	private int range;
	private String path;
	private float dmg;
	private float frequency;
	private float cost;
	private float housing;
	private float upgradeExp;
	private int frames;
	private ProjectileType bullet;
	
	private TowerType(String path, int range, float dmg, int cost, float freq, float house, float exp, int frames, ProjectileType bullet) {
		this.range = range;
		this.path = path;
		this.dmg = dmg;
		this.cost = cost;
		frequency = freq;
		housing = house;
		upgradeExp = exp;
		this.frames = frames;
		this.bullet = bullet;
	}

	public ProjectileType getBullet() {
		return bullet;
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

	public int getFrame() {
		// TODO Auto-generated method stub
		return frames;
	}
	

}
