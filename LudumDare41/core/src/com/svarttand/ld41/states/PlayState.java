package com.svarttand.ld41.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.svarttand.ld41.Application;
import com.svarttand.ld41.world.TileMap;

public class PlayState extends State{
	
	private TextureAtlas textureAtlas;
	private Viewport viewport;
	private TileMap map;

	public PlayState(GameStateManager gsm, TextureAtlas atlas) {
		super(gsm);
		viewport = new StretchViewport(Application.V_WIDTH, Application.V_HEIGHT, cam);
		viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		map = new TileMap();
		textureAtlas = atlas;
		
	}

	@Override
	protected void handleInput(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(float delta) {
		System.out.println("ready");
		
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.setProjectionMatrix(cam.combined);
		Gdx.gl.glClearColor(0, (float) 0.6, 1, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		map.render(batch, textureAtlas);
		batch.end();
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
		
	}

}
