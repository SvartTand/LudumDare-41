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
	private boolean needToUpdate;
	private Tile currentTile;
	
	public Mob(int x, int y, MobType mobType, Tile start, Tile dest) {
		posX = x;
		posY = y;
		path = "Mob";
		type = mobType;
		currentTile = start;
		getRoute(currentTile, dest);
		needToUpdate = false;
		
	}
	
	private void getRoute(Tile start, Tile dest) {
		route = PathFinding.calculateRoute(start, dest);
	}

	public void update(float delta){
		
		float x2;
		float y2;
		float angle;
		if (!route.isEmpty()) {
			if (needToUpdate) {
				LinkedList<Tile> l = PathFinding.calculateRoute(currentTile, route.getLast());
				route = l;
				needToUpdate = false;
			}
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

	public void updatePath() {
		needToUpdate = true;
		
	}

	public boolean isUpdating() {
		// TODO Auto-generated method stub
		return needToUpdate;
	}
	
	

}
