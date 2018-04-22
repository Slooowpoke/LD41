package com.coxon.rhythm;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.coxon.rhythm.entities.Enemy;

import java.util.ArrayList;

/**
 * Created by mali on 01/01/18.
 */
public class InputController implements InputProcessor {

    boolean up, down, left, right;
    float angle;
    Vector2 mouse = new Vector2(0, 0);
    Camera camera;
    SongManager songManager;

    Vector2 targetCameraPosition;

    InputController(Vector2 position) {
        targetCameraPosition = position;
        songManager = new SongManager(position);
        camera = new Camera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), position);
    }

    public void update(double delta, Vector2 playerPosition, ArrayList<Enemy> enemies) {
        float dx = 0, dy = 0;
        float currentSpeed = 8f;
        if (isUpPressed()) {
            dy += currentSpeed;
        } else if (isDownPressed()) {
            dy -= currentSpeed;
        }

        if (isRightPressed()) {
            dx += currentSpeed;
        } else if (isLeftPressed()) {
            dx -= currentSpeed;
        }

        if (dx != 0 || dy != 0) {
            targetCameraPosition.add(dx, dy);
        }
        songManager.update(delta, playerPosition, enemies);
        camera.update(delta, targetCameraPosition);
    }

    public SongManager getSongManager() {
        return songManager;
    }

    public Camera getCamera() {
        return camera;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Keys.W) {
            up = true;
        } else if (keycode == Keys.A) {
            left = true;
        } else if (keycode == Keys.S) {
            down = true;
        } else if (keycode == Keys.D) {
            right = true;
        }

        if (keycode == Keys.UP) {
            songManager.checkForNote("UpNote");
        } else if (keycode == Keys.DOWN) {
            songManager.checkForNote("DownNote");
        } else if (keycode == Keys.LEFT) {
            songManager.checkForNote("LeftNote");
        } else if (keycode == Keys.RIGHT) {
            songManager.checkForNote("RightNote");
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Keys.W) {
            up = false;
        } else if (keycode == Keys.A) {
            left = false;
        } else if (keycode == Keys.S) {
            down = false;
        } else if (keycode == Keys.D) {
            right = false;
        }

        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
//        if(camera != null){
//            Vector3 position = camera.unproject(new Vector3(screenX, screenY, camera.zoom));
//
//            mouse.x = position.x;
//            mouse.y = position.y;
//
//        }

        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    public boolean isUpPressed() {
        return up;
    }

    public boolean isDownPressed() {
        return down;
    }

    public boolean isLeftPressed() {
        return left;
    }

    public boolean isRightPressed() {
        return right;
    }

    public float getAngle() {
        return angle;
    }

    public float getMouseX() {
        return mouse.x;
    }

    public float getMouseY() {
        return mouse.y;
    }
}
