package com.svarttand.ld41.world;

import java.util.ArrayList;

public class Tile {

	private float posX;
	private float posY;
	
	private String path;
	
	private ArrayList<Tile> neighbours;
	

	public Tile(float x, float y){
		posX = x;
		posY = y;
		neighbours = new ArrayList<Tile>();
		path = "Tile";
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
	
	
}
