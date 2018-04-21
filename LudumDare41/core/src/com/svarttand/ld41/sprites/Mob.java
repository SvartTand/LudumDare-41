package com.svarttand.ld41.sprites;

import java.util.ArrayList;
import java.util.LinkedList;

import com.badlogic.gdx.math.Circle;
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
	
	private Circle bounds;
	
	public Mob(int x, int y, MobType mobType, Tile start, Tile dest) {
		posX = x;
		posY = y;
		path = "Mob";
		type = mobType;
		currentTile = start;
		getRoute(currentTile, dest);
		needToUpdate = false;
		bounds = new Circle(posX, posY, type.getRadius());
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
				LinkedList<Tile> l = PathFinding.calculateRoute(currentTile, route.getFirst());
				route = l;
				needToUpdate = false;
			}
			angle = (float) Math.atan2(route.getLast().getPosY() - posY, route.getLast().getPosX() - posX);
			posX += (float) Math.cos(angle) * type.getSpeed() * delta;
			posY += (float) Math.sin(angle) * type.getSpeed() * delta;
			
			//System.out.println(posX + ", " + posY);
			if (posX >= route.getLast().getPosX()-10 && posX <= route.getLast().getPosX()+ TileMap.TILE_SIZE && posY >= route.getLast().getPosY()-10 && posY <= route.getLast().getPosY()+ TileMap.TILE_SIZE) {
				route.removeLast();
				if (!route.isEmpty()) {
					currentTile = route.getLast();
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
		return path;
	}

	public void updatePath() {
		needToUpdate = true;
		
	}

	public boolean isUpdating() {
		// TODO Auto-generated method stub
		return needToUpdate;
	}
	
	public Circle getBounds(){
		return bounds;
	}
	

}
