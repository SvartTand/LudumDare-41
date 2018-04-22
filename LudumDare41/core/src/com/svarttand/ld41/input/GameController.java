package com.svarttand.ld41.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.svarttand.ld41.Application;
import com.svarttand.ld41.misc.PathFinding;
import com.svarttand.ld41.states.PlayState;
import com.svarttand.ld41.ui.PlayUI.State;
import com.svarttand.ld41.world.Tile;
import com.svarttand.ld41.world.Tower;
import com.svarttand.ld41.world.TowerType;

public class GameController implements InputProcessor{
	private PlayState playState;
	private LabelStyle style;
	private BitmapFont font;
	
	public GameController(PlayState state) {
		playState = state;
		font = new BitmapFont();
		style = new LabelStyle(font, Color.WHITE);
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (screenY < Gdx.graphics.getHeight() * 0.883f) {
			Tile tile = playState.getMap().getTileConvert(screenX, screenY);
			System.out.println(tile.getPosX()/32 + ", " + tile.getPosY()/32);
			System.out.println(playState.getUI().currentState);
			if (playState.getUI().currentState != State.NONE) {
				buildTower(tile);
				//tile.setPath("Tower");
			}
			
		}		
		
		return false;
	}
	
	

	private void buildTower(Tile tile) {
		if (playState.getUI().currentState == State.UPGRADE) {
			
		}else{
			if (tile.getTower() == null) {
				if (!doesBlock(tile)) {
					//System.out.println("works");
					Tower tower = null;
					if (playState.getUI().currentState == State.TOWER1) {
						if (enoughResources(TowerType.BASIC,tile)) {
							tower = new Tower(tile, tile.getPosX(), tile.getPosY(), TowerType.BASIC,style);
							tile.setTower(tower);
							playState.getTowers().addTower(tower);
							playState.getMobs().updatePaths();
						}
						
						
					}else if(playState.getUI().currentState == State.TOWER2){
						if (enoughResources(TowerType.BASIC2,tile)) {
							tower = new Tower(tile, tile.getPosX(), tile.getPosY(), TowerType.BASIC2,style);
							tile.setTower(tower);
							playState.getTowers().addTower(tower);
							playState.getMobs().updatePaths();
						}
					}else{
						if (enoughResources(TowerType.HOUSE, tile)) {
							tower = new Tower(tile, tile.getPosX(), tile.getPosY(), TowerType.HOUSE,style);
							tile.setTower(tower);
							playState.getTowers().addHouse(tower);
							playState.getMobs().updatePaths();
						}
					}	
				}
			}else{
				playState.getUI().addNewFloatingText("Already Tower There!", tile.getPosX(), tile.getPosY(), 1.5f, true);
			}
		}
		
				
				
		
		
		
		
		
	}

	private boolean enoughResources(TowerType type, Tile tile) {
		if (playState.getResources().getGold() >= type.getCost()) {
			if (playState.getResources().getPopSpace() >= type.getHousing()) {
				playState.getResources().buyWithGold(type.getCost());
				playState.getResources().addPopulation(type.getHousing());
				return true;
			}
			playState.getUI().addNewFloatingText("Not Enough Housing", tile.getPosX(), tile.getPosY(), 1.5f, true);
			return false;
		}
		playState.getUI().addNewFloatingText("Not Enough Gold", tile.getPosX(), tile.getPosY(), 1.5f, true);
		return false;
	}

	private boolean doesBlock(Tile tile) {
		for (int i = 0; i < playState.getMap().getSpawns().size(); i++) {
			//System.out.println(playState.getMap().getSpawns().get(i).getPosX() + ", " + playState.getMap().getDestination().getPosX());
			if (!PathFinding.isRoutePossible(playState.getMap().getSpawns().get(i), playState.getMap().getDestination(),tile)) {
				return true;
			}
			if (!PathFinding.isRoutePossible(playState.getMap().getDestination(), playState.getMap().getSpawns().get(i), tile)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	public void dispose(){
		font.dispose();
	}

}
