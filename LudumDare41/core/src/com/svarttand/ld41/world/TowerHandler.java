package com.svarttand.ld41.world;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.svarttand.ld41.misc.AudioHandler;
import com.svarttand.ld41.misc.ParticleType;
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
		playState.getAudioHandler().playSound(AudioHandler.LASERSHOT);
		
	}

	public void removeProjectile(Projectile projectile) {
		playState.getParticleHandler().addParticleEffect(ParticleType.HIT, projectile.getPosX(), projectile.getPosY());
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

	public void renderLabels(SpriteBatch batch) {
		for (int i = 0; i < towers.size(); i++) {
			towers.get(i).getLabel().draw(batch, 1);
		}
		for (int i = 0; i < houses.size(); i++) {
			houses.get(i).getLabel().draw(batch, 1);
		}
		
	}

	public void earthQuake() {
		Random random = new Random();
		playState.getShake().shake(1000, 5000, 1000);
		playState.getAudioHandler().playSound(AudioHandler.EARTHQUAKE);
		int j = 0;
		for (int i = 0; i < towers.size(); i++) {
			j = random.nextInt(6);
			if (j == 3) {
				towers.get(i).delete();
				playState.getResources().removePop(towers.get(i).getType().getHousing());
				playState.getParticleHandler().addParticleEffect(ParticleType.BUILD, towers.get(i).getPosX()+ TileMap.TILE_SIZE*0.5f, towers.get(i).getPosY()+ TileMap.TILE_SIZE*0.5f);
				towers.remove(i);
			}
		}
		for (int i = 0; i < houses.size(); i++) {
			j = random.nextInt(6);
			if (j == 3) {
				houses.get(i).delete();
				playState.getResources().removeHousing(houses.get(i).getType().getHousing());
				playState.getParticleHandler().addParticleEffect(ParticleType.BUILD, houses.get(i).getPosX()+ TileMap.TILE_SIZE*0.5f, houses.get(i).getPosY()+ TileMap.TILE_SIZE*0.5f);
				houses.remove(i);
			}
		}
		
	}

}
