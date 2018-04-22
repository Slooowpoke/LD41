package com.coxon.rhythm;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.coxon.rhythm.entities.Enemy;
import com.coxon.rhythm.entities.Note;
import com.coxon.rhythm.entities.Player;
import com.coxon.rhythm.graphics.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class RunGame extends ApplicationAdapter {
	SpriteBatch batch;
	ShapeRenderer shapeBatch;

	Enemy enemy;
	ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	Player player;
	Note note;
	public static Random random = new Random();

	public static BitmapFont font16, font18,font21, font32;

	InputController inputController;

	public static HashMap<String, Texture> images;

	@Override
	public void create () {
		font16 = new BitmapFont(Gdx.files.internal("fonts/font-16.fnt"));
		font18 = new BitmapFont(Gdx.files.internal("fonts/font-18.fnt"));
		font32 = new BitmapFont(Gdx.files.internal("fonts/font-32.fnt"));
		font21 = new BitmapFont(Gdx.files.internal("fonts/font-21.fnt"));
		batch = new SpriteBatch();

		ImageLoader imageLoader = new ImageLoader();
		images = imageLoader.loadImages();

		player = new Player(new Vector2(200,200));
		inputController = new InputController(player.getPosition());

		Gdx.input.setInputProcessor(inputController);

		enemies.add(new Enemy(new Vector2(130, 300)));
		enemies.add(new Enemy(new Vector2(230, 300)));
		enemies.add(new Enemy(new Vector2(430, 300)));
	}

	@Override
	public void render () {
		update(Gdx.graphics.getDeltaTime());

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(inputController.getCamera().combined);
		batch.begin();
		for(Enemy enemy: enemies){
			enemy.render(batch);
		}
		player.render(batch);
		inputController.getSongManager().render(batch);
		batch.end();
	}

	public void update(double delta){
//		System.out.println(music.getPosition());
		for(Enemy enemy: enemies){
			enemy.update(delta);
		}
		inputController.update(delta, player.getPosition(), enemies);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
