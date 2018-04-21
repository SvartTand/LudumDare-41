package com.svarttand.ld41.world;

public class Tower {
	
	private Tile tile;
	private float posX;
	private float posY;
	private TowerType type;
	
	
	public Tower(Tile tile, float f, float g, TowerType type) {
		posX = f;
		posY = g;
		this.tile = tile;
		this.type = type;
		
	}
	
	public void update(float delta){
		
	}

	public Tile getTile() {
		return tile;
	}

	public float getPosX() {
		return posX;
	}

	public float getPosY() {
		return posY;
	}

	public String getPath() {
		return type.getPath();
	}
	

}
