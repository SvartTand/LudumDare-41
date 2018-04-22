package com.svarttand.ld41.sprites;

public enum MobType {
	
	MOB("Mob", 50, 400, 5, 8, 8, 0.5f),
	SLIME("MobS", 35, 700, 5,24,3, 0.4f),
	HELI_MOB("HeliMob", 70, 70,2, 6, 4, 0.5f);
	
	private int speed;
	private String path;
	private float hp;
	private int points;
	private int radius;
	private int frames;
	private float duration;
	
	private MobType(String path, int speed, float hp, int points, int rad, int frames, float durat) {
		this.speed = speed;
		this.path = path;
		this.hp = hp;
		this.points = points;
		radius = rad;
		this.frames = frames;
		duration = durat;
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
	
	public int getRadius() {
		return radius;
	}

	public int getFrames() {
		// TODO Auto-generated method stub
		return frames;
	}
	
	public float getDuration(){
		return duration;
	}
	
	

}
