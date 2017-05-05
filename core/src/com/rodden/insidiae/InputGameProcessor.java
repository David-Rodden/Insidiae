package com.rodden.insidiae;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * Created by David on 4/22/2017.
 */
public class InputGameProcessor implements InputProcessor {
    private OrthographicCamera camera;
    private int pressedX, pressedY;

    public InputGameProcessor(OrthographicCamera camera) {
        this.camera = camera;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (button != 0) return false;
        pressedX = screenX;
        pressedY = screenY;
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    public static void main(String[] args) {
        int x = 12 + readInt(), y = 0, z = x * x;
        for (int i = 0; i < 20; i++) y += z;
        System.out.println(y);
    }

    public static int readInt() {
        return 10;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        final float zoomMultiplier = camera.zoom / 200f;
        camera.translate((pressedX - screenX) * zoomMultiplier, (screenY - pressedY) * zoomMultiplier);
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        final float zoomAmount = amount * 1.15f;
        camera.zoom += zoomAmount;
        return false;
    }
}
