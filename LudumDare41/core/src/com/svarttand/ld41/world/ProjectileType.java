package com.svarttand.ld41.world;

public enum ProjectileType {
	
	BASIC("projectile", 150, false),
	ICE_BULLET("IceProjectile", 150, true);
	
	private String path;
	private int speed;
	private boolean freeze;
	
	private ProjectileType(String p, int s, boolean freeze) {
		path = p;
		speed = s;
		this.freeze = freeze;
	}

	public String getPath() {
		return path;
	}

	public int getSpeed() {
		return speed;
	}
	
	public boolean getFreeze(){
		return freeze;
	}
	

}
