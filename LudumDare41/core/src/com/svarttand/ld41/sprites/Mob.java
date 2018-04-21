package com.svarttand.ld41.sprites;

public class Mob {
	
	private float posX;
	private float posY;
	
	private String path;
	
	private MobType type;
	
	public Mob(int x, int y, MobType mobType) {
		posX = x;
		posY = y;
		path = "Mob";
		type = mobType;
	}

	public float getPosX() {
		return posX;
	}

	public float getPosY() {
		return posY;
	}

	public String getPath() {
		return path;
	}
	
	

}
