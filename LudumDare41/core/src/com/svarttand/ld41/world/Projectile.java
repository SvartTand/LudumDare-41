package com.svarttand.ld41.world;

import com.svarttand.ld41.sprites.Mob;

public class Projectile {
	
	private float posX;
	private float posY;
	private ProjectileType type;
	private float destX;
	private float destY;
	private Mob target;
	private float dmg;

	public Projectile(float x, float y, float dX, float dY, ProjectileType type, Mob target, float dmg) {
		posX = x;
		posY = y;
		this.type = type;
		destX = dX;
		destY = dY;
		this.target = target;
		this.dmg = dmg;
	}

	public void update(float delta, TowerHandler handler) {
		destX = target.getPosX();
		destY = target.getPosY();
		
		float angle = (float) Math.atan2(destY - posY, destX - posX);
		posX += (float) Math.cos(angle) * type.getSpeed() * delta;
		posY += (float) Math.sin(angle) * type.getSpeed() * delta;
		
		if (posX >= destX-4 && posX <= destX+4 && posY >= destY-4 && posY <= destY+4) {
			handler.removeProjectile(this);
			target.takeDmg(dmg, type.getFreeze());
		}
	}

	public float getPosY() {
		// TODO Auto-generated method stub
		return posY;
	}

	public float getPosX() {
		// TODO Auto-generated method stub
		return posX;
	}

	public String getPath() {
		// TODO Auto-generated method stub
		return type.getPath();
	}

}
