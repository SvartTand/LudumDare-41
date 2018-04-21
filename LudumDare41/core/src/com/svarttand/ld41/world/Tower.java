package com.svarttand.ld41.world;

import java.util.ArrayList;

import com.badlogic.gdx.math.Circle;
import com.svarttand.ld41.sprites.Mob;

public class Tower {
	
	private static final int OFFSET = 16;
	
	private Tile tile;
	private float posX;
	private float posY;
	private TowerType type;
	private Circle bounds;
	private Mob target;
	
	private float time;
	
	public Tower(Tile tile, float f, float g, TowerType type) {
		posX = f;
		posY = g;
		this.tile = tile;
		this.type = type;
		bounds = new Circle(posX + OFFSET, posY + OFFSET, type.getRange());
		target = null;
		time = 0;
	}
	
	public void update(float delta, ArrayList<Mob> mobs, TowerHandler handler){
		time += delta;
		if (target.getBounds().overlaps(bounds)) {
			if (time >= type.getFreq()) {
				handler.addProjectile(new Projectile(posX + OFFSET, posY + OFFSET, target.getPosX(), target.getPosY(), ProjectileType.BASIC));
			}
		}
		if (target == null) {
			for (int i = 0; i < mobs.size(); i++) {
				if (mobs.get(i).getBounds().overlaps(bounds)) {
					target = mobs.get(i);
					break;
				}
			}
		}
		
		
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
