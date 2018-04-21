package com.svarttand.ld41.world;

import java.util.ArrayList;

public class Tile {

	private float posX;
	private float posY;
	
	private String path;
	
	private ArrayList<Tile> neighbours;
	
	private boolean passable;
	
	private Tile parent;
	

	public Tile(float x, float y){
		posX = x;
		posY = y;
		neighbours = new ArrayList<Tile>();
		path = "Tile";
		passable = true;
	}
	
	public void addNeighbour(Tile neighbour){
		neighbours.add(neighbour);
	}
	
	public ArrayList<Tile> getNeighbours(){
		return neighbours;
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

	public void setPath(String string) {
		path = string;
	}
	
	public boolean isPassable(){
		return passable;
	}

	public Tile getParent() {
		// TODO Auto-generated method stub
		return parent;
	}
	
	public void setParent(Tile p){
		parent = p;
	}
	
	public boolean isSame(Tile tile){
		if (posX == tile.getPosX() && posY == tile.posY) {
			return true;
		}
		return false;
	}

	public void setPassable(boolean b) {
		passable = false;
		
	}
	
}
