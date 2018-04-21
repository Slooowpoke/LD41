package com.coxon.rhythm.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.coxon.rhythm.RunGame;

public class Note extends Entity implements RenderableEntity{

    double noteHitSecond;

    public Note(Vector2 position, double noteHitSecond) {
        super(position, RunGame.images.get("UpNote"));
        this.noteHitSecond = noteHitSecond;
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(sprite, getCentralX(), getCentralY());
    }

    public void update(double delta, double songPosition){
        double roundedSongPosition = Math.round(songPosition);

        if(roundedSongPosition == Math.round(noteHitSecond)){
            System.out.println("Note hit");
        }

    }
}
