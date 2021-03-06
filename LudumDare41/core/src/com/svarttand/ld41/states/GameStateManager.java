package com.svarttand.ld41.states;

import java.util.Stack;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameStateManager {
	
	private Stack<State> states;
    public AssetManager assetManager;
    private int score;
    private int wave;

    public GameStateManager(AssetManager manager){
        assetManager = manager;
        states = new Stack<State>();
    }

    public void push(State state){
        states.push(state);
    }

    public void pop(){
        states.pop();
    }

    public void set(State state){
        states.pop().dispose();
        states.push(state);
    }
    public State peek(){
        return states.peek();
    }

    public void update(float dt){
        states.peek().update(dt);
    }

    public void render(SpriteBatch sb, float delta){
        states.peek().render(sb, delta);
    }

    public void dispose(){
        for (int i = 0; i < states.size(); i++) {
            states.pop().dispose();
        }
        assetManager.dispose();
        System.out.println("Disposed!");
    }
    public void resize(int width, int height){
        states.peek().resize(width,height);
    }

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getWave() {
		return wave;
	}

	public void setWave(int wave) {
		this.wave = wave;
	}
    
    

}
