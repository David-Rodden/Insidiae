package com.rodden.insidiae;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.MathUtils;
import com.rodden.insidiae.input.GameGestureDetector;
import com.rodden.insidiae.input.GameInputProcessor;

public class Insidiae extends ApplicationAdapter {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Sprite mapSprite;
    private BitmapFont font;

    @Override
    public void create() {
        mapSprite = new Sprite(new Texture("sfbay.png"));
        camera = new OrthographicCamera(1000, 1000 * (Gdx.graphics.getHeight() / Gdx.graphics.getWidth()));
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();
        mapSprite.setPosition(0, 0);
        mapSprite.setSize(1000, 1000);
        batch = new SpriteBatch();
        font = new BitmapFont();
        final InputMultiplexer multiplexer = new InputMultiplexer(new GestureDetector(new GameGestureDetector(camera)), new GameInputProcessor(camera));
        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void render() {
        handleInput();
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        mapSprite.draw(batch);
//        ((InputGameProcessor) Gdx.input.getInputProcessor()).drawMousePosition(batch, font);
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
