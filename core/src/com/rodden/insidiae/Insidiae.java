package com.rodden.insidiae;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Insidiae extends ApplicationAdapter {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Sprite mapSprite;

    @Override
    public void create() {
        mapSprite = new Sprite(new Texture("sfbay.png"));
        camera = new OrthographicCamera(1000, 1000 * (Gdx.graphics.getHeight() / Gdx.graphics.getWidth()));
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();
        mapSprite.setPosition(0, 0);
        mapSprite.setSize(1000, 1000);
        batch = new SpriteBatch();
        Gdx.input.setInputProcessor(new InputGameProcessor(camera, mapSprite));
    }

    @Override
    public void render() {
        handleInput();
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        mapSprite.draw(batch);
        batch.end();
    }

    private void handleInput() {
        camera.zoom = MathUtils.clamp(camera.zoom, .1f, 1000 / camera.viewportWidth);
        float effectiveViewportWidth = camera.viewportWidth * camera.zoom;
        float effectiveViewportHeight = camera.viewportHeight * camera.zoom;
        camera.position.x = MathUtils.clamp(camera.position.x, effectiveViewportWidth / 2f, 1000 - effectiveViewportWidth / 2f);
        camera.position.y = MathUtils.clamp(camera.position.y, effectiveViewportHeight / 2f, 1000 - effectiveViewportHeight / 2f);

    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = 50f;
        camera.viewportHeight = 50f * height / width;
        camera.update();
    }

    @Override
    public void dispose() {
        mapSprite.getTexture().dispose();
        batch.dispose();
    }
}
