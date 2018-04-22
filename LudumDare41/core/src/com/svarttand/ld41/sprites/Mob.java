package com.svarttand.ld41.sprites;

import java.util.LinkedList;
import com.badlogic.gdx.math.Circle;
import com.svarttand.ld41.misc.Animation;
import com.svarttand.ld41.misc.PathFinding;
import com.svarttand.ld41.world.Tile;
import com.svarttand.ld41.world.TileMap;

public class Mob {
	
	private static final int FREEZE_DURATION = 1;
	private float posX;
	private float posY;
	
	private MobType type;
	
	private LinkedList<Tile> route;
	private boolean needToUpdate;
	private Tile currentTile;
	
	private Circle bounds;
	private float speed;
	
	private float hp;
	private float dmg;
	private MobHandler handler;
	
	private boolean dead;
	private Tile newTile;
	
	private Animation animation;
	private float freeze;
	
	public Mob(int x, int y, MobType mobType, Tile start, Tile dest, MobHandler handler, CardType buff) {
		posX = x;
		posY = y;
		type = mobType;
		currentTile = start;
		getRoute(currentTile, dest);
		needToUpdate = false;
		bounds = new Circle(posX, posY, type.getRadius());
		hp = type.getHp()*buff.getHp();
		this.handler = handler;
		animation = new Animation(type.getPath(), type.getFrames(), type.getDuration());
		speed = type.getSpeed()*buff.getSpeed();
		dmg = type.getPoints()*buff.getDmg();
		dead = false;
		freeze = FREEZE_DURATION+1;
		
	}
	
	private void getRoute(Tile start, Tile dest) {
		route = PathFinding.calculateRoute(start, dest);
	}

	public void update(float delta){
		animation.update(delta);
		freeze += delta;
		float speedTemp = speed;
		if (freeze <= FREEZE_DURATION) {
			speedTemp*=freeze;
		}
		
		float angle;
		if (!route.isEmpty()) {
			if (needToUpdate && route.contains(newTile)) {
				LinkedList<Tile> l = PathFinding.calculateRoute(currentTile, route.getFirst());
				route = l;
				needToUpdate = false;
			}
			angle = (float) Math.atan2(route.getLast().getPosY() - posY, route.getLast().getPosX() - posX);
			posX += (float) Math.cos(angle) * speedTemp * delta;
			posY += (float) Math.sin(angle) * speedTemp * delta;
			bounds.x = posX;
			bounds.y = posY;
			//System.out.println(posX + ", " + posY);
			if (posX >= route.getLast().getPosX()-10 && posX <= route.getLast().getPosX()+ TileMap.TILE_SIZE && posY >= route.getLast().getPosY()-10 && posY <= route.getLast().getPosY()+ TileMap.TILE_SIZE) {
				route.removeLast();
				if (!route.isEmpty()) {
					currentTile = route.getLast();
				}else{
					handler.takeDamage(dmg);
					handler.remove(this, false);
				}
			}
		}
		
	}

	public float getPosX() {
		return posX;
	}

	public float getPosY() {
		return posY;
	}

	public String getPath() {
		return animation.getFrame();
	}

	public void updatePath(Tile tile) {
		newTile = tile;
		needToUpdate = true;
		
	}

	public boolean isUpdating() {
		return needToUpdate;
	}
	
	public Circle getBounds(){
		return bounds;
	}

	public void takeDmg(float dmg, Boolean freeze) {
		if (freeze) {
			this.freeze = 0;
		}
		hp -= dmg;
		if (hp <= 0 && !dead) {
			handler.remove(this, true);
			dead = true;
		}
		
	}

	public MobType getType() {
		
		return type;
	}
	

}
