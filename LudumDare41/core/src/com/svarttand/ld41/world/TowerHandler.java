package com.svarttand.ld41.world;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.svarttand.ld41.states.PlayState;

public class TowerHandler {
	
	private ArrayList<Tower> towers;
	private PlayState playState;
	
	private ArrayList<Projectile> projectiles;
	private ArrayList<Tower> houses;
	
	public TowerHandler(PlayState playState) {
		towers = new ArrayList<Tower>();
		this.playState = playState;
		projectiles = new ArrayList<Projectile>();
		houses = new ArrayList<Tower>();
	}
	
	public void addTower(Tower tower){
		System.out.println("added");
		towers.add(tower);
	}
	
	public void update(float delta){
		for (int i = 0; i < towers.size(); i++) {
			towers.get(i).update(delta, playState.getMobs().getList(), this);
		}
		
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).update(delta, this);
		}
	}
	
	public void render(SpriteBatch batch, TextureAtlas atlas){
		for (int i = 0; i < towers.size(); i++) {
			batch.draw(atlas.findRegion(towers.get(i).getPath()), towers.get(i).getPosX(), towers.get(i).getPosY());
		}
		for (int i = 0; i < houses.size(); i++) {
			batch.draw(atlas.findRegion(houses.get(i).getPath()), houses.get(i).getPosX(), houses.get(i).getPosY());
		}
		for (int i = 0; i < projectiles.size(); i++) {
			batch.draw(atlas.findRegion(projectiles.get(i).getPath()), projectiles.get(i).getPosX(), projectiles.get(i).getPosY());
		}
	}

	public void addProjectile(Projectile projectile) {
		projectiles.add(projectile);
		
	}

	public void removeProjectile(Projectile projectile) {
		projectiles.remove(projectile);
		
	}

	public void addHouse(Tower tower) {
		houses.add(tower);
		
	}
	
	public float getHousing(){
		float sum = 0;
		for (int i = 0; i < houses.size(); i++) {
			sum += houses.get(i).getHousing();
		}
		return sum;
	}

}
