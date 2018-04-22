package com.svarttand.ld41.world;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class TileMap {
	public static final int SIZE = 25;
	public static final int TILE_SIZE = 32;
	
	private Tile[][] map;
	private Tile destination;
	private ArrayList<Tile> spawns;
	
	public TileMap(){
		map = new Tile[SIZE][SIZE];
		generateMap();
		destination = map[12][13];
		spawns = new ArrayList<Tile>();
		spawns.add(map[0][0]);
		spawns.add(map[24][0]);
		spawns.add(map[24][24]);
		spawns.add(map[0][24]);
		destination.setPath("Center");
	}

	private void generateMap() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = new Tile(i * TILE_SIZE, j *TILE_SIZE);
				if (i >= 1) {
					map[i][j].addNeighbour(map[i-1][j]);
					map[i-1][j].addNeighbour(map[i][j]);
				}
				if (j >= 1) {
					map[i][j].addNeighbour(map[i][j-1]);
					map[i][j-1].addNeighbour(map[i][j]);
				}
			}
		}
		
	}
	
	public void render(SpriteBatch batch, TextureAtlas atlas){
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				batch.draw(atlas.findRegion(map[i][j].getPath()), map[i][j].getPosX(), map[i][j].getPosY());
			}
		}
	}

	public Tile getTileConvert(float x, float y) {
		return map[(int) (x/TILE_SIZE)][(int) (SIZE-1 - (y/TILE_SIZE)+1)];
		
	}
	
	public Tile getTile(int x, int y){
		return map[x][y];
	}
	
	public Tile getDestination(){
		return destination;
		
	}
	
	public ArrayList<Tile> getSpawns(){
		return spawns;
	}
	

}
