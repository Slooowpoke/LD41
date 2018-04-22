package com.coxon.rhythm;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;


import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Created by mali on 19/01/18.
 */
public class Camera extends OrthographicCamera{
    int shakeX = 0, shakeY = 0;
    int shakeTimeout = 0;
    int offsetY = 0;
    Vector2 targetPosition;

    public Camera(float width, float height, Vector2 playerPosition) {
        super(width,height);
        targetPosition = new Vector2(playerPosition.x, playerPosition.y);

        setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        zoom = 0.5f;
    }

    public void update(double delta, Vector2 targetPosition){

        float lerp = 0.2f;

        position.x += ( targetPosition.x- (position.x+ shakeX)) * lerp ;
        position.y += ( targetPosition.y - (position.y + shakeY)) * lerp;

        shakeX += (0 - shakeX) * 0.1f;
        shakeY += (0 - shakeY) * 0.1f;
        if(shakeTimeout >= 0) shakeTimeout-= 1 * delta;
        super.update();
    }

    public OrthographicCamera getCamera(){
        return this;
    }

    public void shake() {
        if(shakeTimeout == 0){
            if(RunGame.random.nextBoolean()){
                shakeX += RunGame.random.nextInt(5);
            }else{
                shakeX -= RunGame.random.nextInt(5);
            }
            if(RunGame.random.nextBoolean()){
                shakeY += RunGame.random.nextInt(5);
            }else{
                shakeY -= RunGame.random.nextInt(5);
            }
            shakeTimeout = 10;
        }
    }
}
