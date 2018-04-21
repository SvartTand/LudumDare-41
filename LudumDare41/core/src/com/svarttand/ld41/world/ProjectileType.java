package com.svarttand.ld41.world;

public enum ProjectileType {
	
	BASIC("bullet", 80);
	
	private String path;
	private int speed;
	
	private ProjectileType(String p, int s) {
		path = p;
		speed = s;
	}

	public String getPath() {
		return path;
	}

	public int getSpeed() {
		return speed;
	}
	

}
