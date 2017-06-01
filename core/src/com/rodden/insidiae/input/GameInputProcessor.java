package com.rodden.insidiae.input;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * Created by David on 5/31/2017.
 */
public class GameInputProcessor implements InputProcessor {
    private OrthographicCamera camera;

    public GameInputProcessor(OrthographicCamera camera) {
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
        return false;
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
        return false;
    }

    /**
     * Zooms into or out of the map based on which direction the user scrolls
     *
     * @param amount - The intensity of the scroll
     * @return
     */
    @Override
    public boolean scrolled(int amount) {
        final float zoomAmount = amount * 1.15f;
        camera.zoom += zoomAmount;
        return false;
    }
}
