package com.svarttand.ld41.sprites;

import java.util.ArrayList;
import java.util.LinkedList;

import com.svarttand.ld41.misc.PathFinding;
import com.svarttand.ld41.world.Tile;
import com.svarttand.ld41.world.TileMap;

public class Mob {
	
	private float posX;
	private float posY;
	
	private String path;
	
	private MobType type;
	
	private LinkedList<Tile> route;
	
	public Mob(int x, int y, MobType mobType, Tile start, Tile dest) {
		posX = x;
		posY = y;
		path = "Mob";
		type = mobType;
		getRoute(start, dest);
	}
	
	private void getRoute(Tile start, Tile dest) {
		route = PathFinding.calculateRoute(start, dest);
	}

	public void update(float delta){
		float x2;
		float y2;
		float angle;
		if (!route.isEmpty()) {
			angle = (float) Math.atan2(route.getLast().getPosY() - posY, route.getLast().getPosX() - posX);
			posX += (float) Math.cos(angle) * type.getSpeed() * delta;
			posY += (float) Math.sin(angle) * type.getSpeed() * delta;
			
			if (posX >= route.getLast().getPosX() && posX <= route.getLast().getPosX()+ TileMap.TILE_SIZE && posY >= route.getLast().getPosY() && posY <= route.getLast().getPosY()+ TileMap.TILE_SIZE) {
				route.removeLast();
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
		return path;
	}
	
	

}