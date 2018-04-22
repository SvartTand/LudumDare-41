package com.svarttand.ld41.misc;

public enum ParticleType {
	
	SPLASH("splash.p", 10, false),
	DEATH("Death.p", 10,false),
	HIT("Hit.p",10,false),
	EXPLOSION("Explosion.p", 10,false);
	
	private String path;
	private int poolSIze;
	private boolean behind;


	private ParticleType(String path, int poolSIze, boolean behind) {
		this.path = path;
		this.poolSIze = poolSIze;
		this.behind = behind;
	}
	
	public String getPath() {
		return path;
	}


	public int getPoolSIze() {
		return poolSIze;
	}

	public boolean getBehind() {
		return behind;
	}

}
