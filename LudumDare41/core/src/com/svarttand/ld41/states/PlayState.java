package com.svarttand.ld41.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.svarttand.ld41.Application;
import com.svarttand.ld41.input.GameController;
import com.svarttand.ld41.misc.Resources;
import com.svarttand.ld41.sprites.MobHandler;
import com.svarttand.ld41.ui.PlayUI;
import com.svarttand.ld41.world.TileMap;
import com.svarttand.ld41.world.TowerHandler;

public class PlayState extends State{
	
	private TextureAtlas textureAtlas;
	private Viewport viewport;
	private TileMap map;
	private GameController controller;
	private InputMultiplexer multiplexer;
	
	private MobHandler mobHandler;
	private TowerHandler towerHandler;

	private Resources resources;
	
	private PlayUI ui;
	public PlayState(GameStateManager gsm, TextureAtlas atlas) {
		super(gsm);
		viewport = new StretchViewport(Application.V_WIDTH, Application.V_HEIGHT, cam);
		viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		map = new TileMap();
		textureAtlas = atlas;
		ui = new PlayUI(textureAtlas, this);
		resources = new Resources();
		
		mobHandler = new MobHandler(this);
		towerHandler = new TowerHandler(this);
		controller = new GameController(this);
		multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(controller);

		multiplexer.addProcessor(ui.getStage());
		Gdx.input.setInputProcessor(multiplexer);
		
	}

	@Override
	protected void handleInput(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(float delta) {
		mobHandler.update(delta);
		towerHandler.update(delta);
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.setProjectionMatrix(cam.combined);
		Gdx.gl.glClearColor(0, (float) 0.6, 1, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		map.render(batch, textureAtlas);
		towerHandler.render(batch, textureAtlas);
		mobHandler.render(batch, textureAtlas);
		batch.draw(textureAtlas.findRegion("Panel"), 0, 0);
		batch.end();
		ui.getStage().draw();
		
	}

	@Override
	public void dispose() {
		ui.getStage().dispose();
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
		
	}

	public TileMap getMap() {
		return map;
	}
	
	public PlayUI getUI(){
		return ui;
	}
	
	public TowerHandler getTowers(){
		return towerHandler;
	}

	public MobHandler getMobs() {
		return mobHandler;
	}
	
	public Resources getResources(){
		return resources;
	}

}
