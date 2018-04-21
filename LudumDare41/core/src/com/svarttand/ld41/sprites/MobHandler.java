package com.svarttand.ld41.sprites;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.svarttand.ld41.states.PlayState;
import com.svarttand.ld41.world.Tile;

public class MobHandler {
	public static final int DEST_X = 17;
	public static final int DEST_Y = 17;
	private ArrayList<Mob> mobList;
	
	private PlayState state;
	private float freq;
	private float sum;
	
	public MobHandler(PlayState state) {
		mobList = new ArrayList<Mob>();
		freq = 2f;
		this.state = state;
	}
	
	public void addMob(int x, int y, Tile tile){
		mobList.add(new Mob(x, y, MobType.MOB, tile, state.getMap().getDestination(), this));
	}
	
	public void updatePaths(){
		for (int i = 0; i < mobList.size(); i++) {
			mobList.get(i).updatePath();
			System.out.println(mobList.get(i).isUpdating());
		}
		
	}
	
	public void update(float delta){
		sum += delta;
		if (sum > freq) {
			addMob(800, 800, state.getMap().getTile(24, 24));
			
			sum = 0;
		}
		for (int i = 0; i < mobList.size(); i++) {
			mobList.get(i).update(delta);
		}
	}
	
	public void render(SpriteBatch batch, TextureAtlas atlas){
		for (int i = 0; i < mobList.size(); i++) {
			batch.draw(atlas.findRegion(mobList.get(i).getPath()), mobList.get(i).getPosX(), mobList.get(i).getPosY());
		}
	}

	public ArrayList<Mob> getList() {
		// TODO Auto-generated method stub
		return mobList;
	}

	public void remove(Mob mob) {
		mobList.remove(mob);
		
	}
	

}
