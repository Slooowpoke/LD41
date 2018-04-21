package com.coxon.rhythm;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.coxon.rhythm.entities.Enemy;
import com.coxon.rhythm.entities.Note;
import com.coxon.rhythm.entities.Player;
import com.coxon.rhythm.graphics.ImageLoader;

import java.util.HashMap;

public class RunGame extends ApplicationAdapter {
	SpriteBatch batch;

	Enemy enemy;
	Player player;
	Note note;

	public static HashMap<String, Texture> images;
	Music music;

	@Override
	public void create () {
		batch = new SpriteBatch();

		ImageLoader imageLoader = new ImageLoader();
		images = imageLoader.loadImages();

		player = new Player(new Vector2(200,200));
		enemy = new Enemy(new Vector2(130, 300));
		note = new Note(new Vector2(100, 200), 5);

		music = Gdx.audio.newMusic(Gdx.files.internal("music/galaxies.mp3"));

		music.play();
	}

	@Override
	public void render () {
		update(Gdx.graphics.getDeltaTime());

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		enemy.render(batch);
		player.render(batch);
		note.render(batch);
		batch.end();
	}

	public void update(double delta){
//		System.out.println(music.getPosition());
		note.update(delta, music.getPosition());
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
