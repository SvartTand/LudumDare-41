package com.svarttand.ld41.sprites;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.svarttand.ld41.world.Tile;
import com.svarttand.ld41.world.TileMap;

public class MobHandler {
	public static final int DEST_X = 17;
	public static final int DEST_Y = 17;
	private ArrayList<Mob> mobList;
	
	private Tile destination;
	
	
	public MobHandler(TileMap map, Tile dest) {
		mobList = new ArrayList<Mob>();
		addMob(600, 600, map.getTile(20, 20));
	}
	
	public void addMob(int x, int y, Tile tile){
		mobList.add(new Mob(x, y, MobType.MOB, tile, destination));
	}
	
	public void update(float delta){
		for (int i = 0; i < mobList.size(); i++) {
			mobList.get(i).update(delta);
		}
	}
	
	public void render(SpriteBatch batch, TextureAtlas atlas){
		for (int i = 0; i < mobList.size(); i++) {
			
		}
	}
	

}
