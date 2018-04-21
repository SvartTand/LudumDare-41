package com.svarttand.ld41.sprites;

public enum MobType {
	
	MOB("Mob", 50, 50, 10);
	
	private int speed;
	private String path;
	private float hp;
	private int points;
	
	private MobType(String path, int speed, float hp, int points) {
		this.speed = speed;
		this.path = path;
		this.hp = hp;
		this.points = points;
	}

	public int getSpeed() {
		return speed;
	}

	public String getPath() {
		return path;
	}

	public float getHp() {
		return hp;
	}

	public int getPoints() {
		return points;
	}
	
	

}
