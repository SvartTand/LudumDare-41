package com.svarttand.ld41.world;

import javax.annotation.Generated;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class TileMap {
	private static final int SIZE = 25;
	private static final int TILE_SIZE = 32;
	
	private Tile[][] map;
	
	public TileMap(){
		map = new Tile[SIZE][SIZE];
		
		generateMap();
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
	

}
