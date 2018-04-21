package com.coxon.rhythm.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Entity {

    Vector2 position, aim;
    Sprite sprite;

    public Entity(Vector2 position, Texture texture)
    {
        this.position = position;
        this.sprite = new Sprite(texture);
    }

    public void update(double delta)
    {
        // Do tthe things
    }

    public float getCentralX(){
        return position.x - sprite.getWidth()/2;
    }

    public float getCentralY(){
        return position.y - sprite.getHeight()/2;
    }



}
