package com.svarttand.ld41.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class TileMap {
	public static final int SIZE = 25;
	public static final int TILE_SIZE = 32;
	
	private Tile[][] map;
	private Tile destination;
	
	public TileMap(){
		map = new Tile[SIZE][SIZE];
		generateMap();
		destination = map[14][14];
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

	public Tile getTileConvert(int screenX, int screenY) {
		return map[screenX/TILE_SIZE][SIZE-1 - (screenY/TILE_SIZE)];
		
	}
	
	public Tile getTile(int x, int y){
		return map[x][y];
	}
	
	public Tile getDestination(){
		return destination;
		
	}
	

}
