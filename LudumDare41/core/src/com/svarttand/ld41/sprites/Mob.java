package com.svarttand.ld41.sprites;

import java.util.ArrayList;
import java.util.LinkedList;

import com.svarttand.ld41.misc.PathFinding;
import com.svarttand.ld41.world.Tile;

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
