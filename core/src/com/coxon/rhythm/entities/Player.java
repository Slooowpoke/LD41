package com.coxon.rhythm.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.coxon.rhythm.RunGame;

public class Player extends Entity implements RenderableEntity{

    public Player(Vector2 position)
    {
        super(position, RunGame.images.get("Player"));
    }


    @Override
    public void render(SpriteBatch batch) {
        batch.draw(sprite, getCentralX(), getCentralY());
    }
}
