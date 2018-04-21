package com.coxon.rhythm.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.coxon.rhythm.RunGame;

public class Enemy extends Entity implements RenderableEntity{

    public Enemy(Vector2 position)
    {
        super(position, RunGame.images.get("Enemy"));
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(sprite, getCentralX(), getCentralY());
    }
}
