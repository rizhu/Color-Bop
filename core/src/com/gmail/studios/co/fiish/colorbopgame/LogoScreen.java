package com.gmail.studios.co.fiish.colorbopgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
public class LogoScreen extends ScreenAdapter {
    public static float sElapsedTime;

    private static final float sWORLD_SIZE = 500.0f;

    private Viewport mViewport;
    private Texture mLogo;
    private SpriteBatch mBatch;

    ScreenSwitcher mSwitcher;

    private float mAspectRatio, mNewWidth, mNewHeight;

    @Override
    public void show() {
        mViewport = new ExtendViewport(sWORLD_SIZE, sWORLD_SIZE);
        mLogo = new Texture(Gdx.files.internal("fiishco.png"));
        mAspectRatio = ((float) mLogo.getHeight()) / ((float) mLogo.getWidth());
        mBatch = new SpriteBatch();
        sElapsedTime = 0;
    }

    @Override
    public void render(float delta) {
        mViewport.apply(true);

        Gdx.gl20.glClearColor(1, 1, 1, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mNewWidth = 3 * mViewport.getScreenWidth() / 5;
        mNewHeight = mAspectRatio * mNewWidth;

        mBatch.begin();
        mBatch.draw(mLogo, mViewport.getScreenWidth() / 2 - (mNewWidth / 2), mViewport.getScreenHeight() / 2 - (mNewHeight / 2), mNewWidth, mNewHeight);
        mBatch.end();

        sElapsedTime += delta;
        mSwitcher.switchCheckLogo();
    }

    @Override
    public void dispose() {
        mBatch.dispose();
    }

    @Override
    public void resize(int width, int height) {
        mViewport.update(width, height, true);
    }

    public void setScreenSwitcher(ScreenSwitcher switcher) {
        mSwitcher = switcher;
    }
}

