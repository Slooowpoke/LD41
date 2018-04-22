package com.coxon.rhythm.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.coxon.rhythm.RunGame;

public class Note extends Entity implements RenderableEntity {

    double noteHitSecond;
    boolean moving = true;
    double lastSongPosition;
    double angle;
    Vector2 displayPosition, originalPosition;
    String type;
    boolean isReadyHit = false, isMissed;
    boolean hitSuccess = false;
    boolean attacking;
    Enemy targetEnemy;

    public Note(Vector2 position, double noteHitSecond, String type) {
        super(position, RunGame.images.get(type));
        this.type = type;
        this.noteHitSecond = noteHitSecond;
        this.displayPosition = new Vector2(position);
        this.originalPosition = new Vector2(position);
    }

    public void setAttack(Enemy enemy){
        if(!attacking){
            attacking = true;
            isMissed = false;
            isReadyHit = false;
            hitSuccess = false;
            targetEnemy = enemy;
        }
    }

    @Override
    public void render(SpriteBatch batch) {
//        if(!moving) return;
        if(hitSuccess){
           sprite.setColor(new Color(255,255,255,255));
        }
        batch.draw(sprite, (int)getCentralInterpolatedX(), (int)getCentralInterpolatedY());
    }

    public void update(double delta, double songPosition, Vector2 playerPosition) {
        if(attacking && targetEnemy != null){
            playerPosition = targetEnemy.getPosition();
        }

        double roundedSongPosition = Math.round(songPosition);
        double distanceBetweenLastSongPosition = songPosition - lastSongPosition;

        float displayPositionX = getInterpolatedPosition().x;
        float displayPositionY = getInterpolatedPosition().y;
        displayPositionX +=  (position.x - getInterpolatedPosition().x) * 0.5f;
        displayPositionY +=  (position.y - getInterpolatedPosition().y) * 0.5f;
        displayPosition = new Vector2(displayPositionX, displayPositionY);

        if (moving) {
            double playerDirectionX = playerPosition.x - position.x, playerDirectionY = playerPosition.y - position.y;
            angle = Math.atan2(playerDirectionY, playerDirectionX);


            float speed = (float) (distanceBetweenLastSongPosition) * 50f;
            double dx = 0.8f * Math.cos(angle);
            double dy = 0.8f * Math.sin(angle);
//            double dy = originalPosition.y + ((originalPosition.y - playerPosition.y) * progress);

            float nx = (float) (position.x + (dx));
            float ny = (float) (position.y + (dy));
            position.x = nx;
            position.y = ny;
            if(position.dst(playerPosition) < 36){

                isReadyHit = true;
            }

            if(position.dst(playerPosition) < 10){
                isMissed = true;
                moving = false;
            }

        }

        if (roundedSongPosition == Math.round(noteHitSecond)) {
//            System.out.println("Note hit");
        }

        lastSongPosition = songPosition;

    }

    public float getCentralInterpolatedX() {
        return displayPosition.x;
    }


    public float getCentralInterpolatedY() {
        return displayPosition.y;
    }


    public Vector2 getInterpolatedPosition() {
        return new Vector2(displayPosition);
    }

    public double getHitSecond() {
        return noteHitSecond;
    }


    public boolean reachedEnd() {
        return true;
    }

    public String getType() {
        return type;
    }

    public boolean isReadyToBeHit() {
        return isReadyHit && !isMissed;
    }

    public void setHitSuccess(boolean b) {
        hitSuccess = b;
    }

    public boolean getHitSuccess() {
        return hitSuccess;
    }

    public boolean getNoteMissed() {
        return isMissed;
    }
}
